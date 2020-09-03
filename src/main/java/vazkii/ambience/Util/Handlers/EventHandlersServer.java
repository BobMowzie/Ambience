package vazkii.ambience.Util.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeConfig.Server;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vazkii.ambience.Ambience;
import vazkii.ambience.PlayerThread;
import vazkii.ambience.SongPicker;
import vazkii.ambience.Util.SplashFactory2;
import vazkii.ambience.Util.WorldData;
import vazkii.ambience.Util.particles.DripLavaParticleFactory;
import vazkii.ambience.Util.particles.DripWaterParticleFactory;
import vazkii.ambience.World.Biomes.Area;
import vazkii.ambience.network.AmbiencePackageHandler;
import vazkii.ambience.network.MyMessage;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerMain;

@Mod.EventBusSubscriber(modid = Ambience.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandlersServer {

	public int attackFadeTime = 300;
	public static int attackingTimer;

	public EventHandlersServer() {
		attackingTimer = attackFadeTime;
	}

	String mobName = null;

	// Quando alguma coisa ataca o player
	@SubscribeEvent
	@OnlyIn(value = Dist.CLIENT)
	public void onEntitySetAttackTargetEvent(LivingSetAttackTargetEvent event) {

		if (event.getTarget() instanceof ServerPlayerEntity) {
			Ambience.attacked = true;
			attackingTimer = attackFadeTime;

			EventHandlers.playInstant();
		}

	}

	// FUNCIONA Quando player ataca alguma coisa
	@SubscribeEvent
	@OnlyIn(value = Dist.CLIENT)
	public void onPlayerAttackEvent(AttackEntityEvent event) {
		mobName = event.getTarget().getName().getString().toLowerCase();

		if (event.getTarget() instanceof MobEntity) {
			// if (event.getTarget().isCreatureType(EnumCreatureType.MONSTER, false)) {
			Ambience.attacked = true;

			attackingTimer = attackFadeTime;
			EventHandlers.playInstant();
		}
	}

	// On something dies
	@SubscribeEvent
	@OnlyIn(value = Dist.CLIENT)
	public void onEntityDeath(LivingDeathEvent event) {
		DamageSource source = event.getSource();

		// When Player kills something
		if (source.getTrueSource() instanceof PlayerEntity & event.getEntity() == Minecraft.getInstance().player) {
			Ambience.attacked = false;
		}

		// When Player dies
		if (event.getEntity() instanceof PlayerEntity & event.getEntity() == Minecraft.getInstance().player) {
			Ambience.attacked = false;
		}

	}

	//Injection of events to the particles
	@SubscribeEvent
	@OnlyIn(value = Dist.CLIENT)
	public void onWorldLoad(WorldEvent.Load ev) {

		Minecraft mc = Minecraft.getInstance();
		if (mc.particles != null) {
			try {
				// get existing splash particle factory

				// do Map<ResourceLocation, IParticleFactory<?>> facts = Minecraft.getInstance().particles.factories;
				Map<ResourceLocation, IParticleFactory<?>> facts = ObfuscationReflectionHelper.getPrivateValue(ParticleManager.class, mc.particles, "field_178932_g");
				IParticleFactory pf = facts.get(ParticleTypes.SPLASH.getRegistryName());

				// check that it's the vanilla one
				if (pf instanceof SplashParticle.Factory) {
					// inject custom splash particle factory
					mc.particles.registerFactory(ParticleTypes.SPLASH, SplashFactory2::new);
					IParticleFactory npf = facts.get(ParticleTypes.SPLASH.getRegistryName());

					// check that it worked
					if (npf instanceof SplashFactory2) {
						// wrap the original factory to copy the sprite data
						((SplashFactory2) npf).wrap((SplashParticle.Factory) pf);
					}
				}
				
				//For Dripping Water on water inside caves
				pf = facts.get(ParticleTypes.DRIPPING_WATER.getRegistryName());
				
				// check that it's the vanilla one
				if (pf instanceof DripParticle.DrippingWaterFactory) {
					// inject custom splash particle factory
					mc.particles.registerFactory(ParticleTypes.DRIPPING_WATER, DripWaterParticleFactory::new);
					IParticleFactory npf = facts.get(ParticleTypes.DRIPPING_WATER.getRegistryName());

					// check that it worked
					if (npf instanceof DripWaterParticleFactory) {
						// wrap the original factory to copy the sprite data
						((DripWaterParticleFactory) npf).wrap((DripParticle.DrippingWaterFactory) pf);
					}
				}
				
				//For Dripping Lava on ground		
				pf = facts.get(ParticleTypes.LANDING_LAVA.getRegistryName());
				
				// check that it's the vanilla one
				if (pf instanceof DripParticle.LandingLavaFactory) {
					// inject custom splash particle factory
					mc.particles.registerFactory(ParticleTypes.LANDING_LAVA, DripLavaParticleFactory::new);
					IParticleFactory npf = facts.get(ParticleTypes.LANDING_LAVA.getRegistryName());
					
					// check that it worked
					if (npf instanceof DripLavaParticleFactory) {
						// wrap the original factory to copy the sprite data
						((DripLavaParticleFactory) npf).wrap((DripParticle.LandingLavaFactory) pf);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	@OnlyIn(value = Dist.CLIENT)
	public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
		if (!Minecraft.getInstance().gameSettings.showDebugInfo)
			return;

		event.getRight().add(null);
		if ((Ambience.dimension >= -1 & Ambience.dimension <= 1)
				| PlayerThread.currentSong != "null" & EventHandlers.nextSong != "null") {

			if (PlayerThread.currentSong != null) {
				String name = "Now Playing: " + SongPicker.getSongName(PlayerThread.currentSong);
				event.getRight().add(name);
			}
			if (EventHandlers.nextSong != null) {
				String name = "Next Song: " + SongPicker.getSongName(EventHandlers.nextSong);
				event.getRight().add(name);
			}
		}
	}

	// Server Side
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		Ambience.selectedArea = null;

		WorldData data = new WorldData();

		ServerWorld world = (ServerWorld) event.getPlayer().world;
		data.GetArasforWorld(world);

		if (data.listAreas != null)
			Ambience.setWorldData(data.GetArasforWorld(world));

		if (data.listAreas.size() > 0) {
			CompoundNBT nbt = WorldData.SerializeThis(Ambience.getWorldData().listAreas);

			AmbiencePackageHandler.sendToClient(new MyMessage(nbt), (ServerPlayerEntity) event.getPlayer());

		}
	}

	/*
	 * int waitTime = 0;
	 * 
	 * @SubscribeEvent public void onServerTick(ServerTickEvent event) { // this //
	 * most certainly WILL fire, even in single player, see for yourself: // Sync
	 * data betwen all the players when player create a new area
	 * 
	 * 
	 * 
	 * }
	 */
}

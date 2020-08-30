package vazkii.ambience.Util.particles;

import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import vazkii.ambience.Util.SplashFactory2;

public class DripWaterParticleFactory extends DripParticle.DrippingWaterFactory {
	public DripWaterParticleFactory(IAnimatedSprite p_i50679_1_) {
		super(p_i50679_1_);
		// TODO Auto-generated constructor stub
	}


	public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed,
			double ySpeed, double zSpeed) {
		// the splash particle is used in several ways, so there's some checks to tell
		// if this is a splash from a drip.

		try {
			// the splash when moving in water has speed, while drips and fishing splashes
			// don't
			if (xSpeed == 0 && ySpeed == 0 && zSpeed == 0) {

				//Check if is hitting a water block 
				if (!worldIn.getBlockState(new BlockPos(x, y + 2, z)).getBlockState().getFluidState().isEmpty()) {

					if (SplashFactory2.dripsCount <= 10)
						SplashFactory2.dripsCount++;
					// play the sound
					float vol = MathHelper.clamp(1, 0f, 1f);
					worldIn.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.AMBIENT, vol,1f, false);
				}
			}

			// make the particle
			return super.makeParticle(typeIn, worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
		}catch (Exception e) {
			return null;
		}
	}

	public void wrap(DripParticle.DrippingWaterFactory real) {
		// do: this.spriteSet = real.spriteSet;
		IAnimatedSprite spr = ObfuscationReflectionHelper.getPrivateValue(DripParticle.DrippingWaterFactory.class, real,"spriteSet");
		ObfuscationReflectionHelper.setPrivateValue(DripParticle.DrippingWaterFactory.class, this, spr,"spriteSet");

	}
}
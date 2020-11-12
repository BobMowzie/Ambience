package vazkii.ambience.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.ambience.Ambience;
import vazkii.ambience.blocks.Alarm;
import vazkii.ambience.blocks.BlockItemBase;
import vazkii.ambience.blocks.Speaker;
import vazkii.ambience.blocks.SongSwitcher;
import vazkii.ambience.items.Soundnizer;
import vazkii.ambience.items.Horn;
import vazkii.ambience.items.Ocarina;

public class RegistryHandler {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ambience.MODID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ambience.MODID);

	
	
	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		

		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	//Items
	public static final RegistryObject<Soundnizer> Soundnizer =  ITEMS.register("soundnizer", () -> new Soundnizer(0));
	public static final RegistryObject<Horn> Horn =  ITEMS.register("horn", () -> new Horn(20));
	public static final RegistryObject<Ocarina> Ocarina =  ITEMS.register("ocarina", () -> new Ocarina(20));
	
	
	//Blocks
	public static final RegistryObject<Block> Speaker =  BLOCKS.register("speaker", () -> new Speaker(""));
	public static final RegistryObject<Block> SongSwitcher =  BLOCKS.register("songswitcher", () -> new SongSwitcher(""));
	public static final RegistryObject<Block> SongSwitcher_lit =  BLOCKS.register("songswitcher_lit", () -> new SongSwitcher(""));
	//public static final RegistryObject<Block> Alarm =  BLOCKS.register("alarm", Alarm::new);

	public static final RegistryObject<Block> block_Alarm_WHITE =  BLOCKS.register("alarm_white", () -> new Alarm("white",false));
	public static final RegistryObject<Block> block_Alarm_RED =  BLOCKS.register("alarm_red", () -> new Alarm("red",false));
	public static final RegistryObject<Block> block_Alarm_ORANGE =  BLOCKS.register("alarm_orange",() -> new Alarm("orange",false));
	public static final RegistryObject<Block> block_Alarm_YELLOW =  BLOCKS.register("alarm_yellow", () -> new Alarm("yellow",false));
	public static final RegistryObject<Block> block_Alarm_LIME =  BLOCKS.register("alarm_lime", () -> new Alarm("lime",false));
	public static final RegistryObject<Block> block_Alarm_GREEN =  BLOCKS.register("alarm_green",() -> new Alarm("green",false));
	public static final RegistryObject<Block> block_Alarm_LIGHTBLUE =  BLOCKS.register("alarm_lightblue",() -> new Alarm("lightblue",false));
	public static final RegistryObject<Block> block_Alarm_CYAN =  BLOCKS.register("alarm_cyan", () -> new Alarm("cyan",false));
	public static final RegistryObject<Block> block_Alarm_BLUE =  BLOCKS.register("alarm_blue",() -> new Alarm("blue",false));
	public static final RegistryObject<Block> block_Alarm_PURPLE =  BLOCKS.register("alarm_purple",() -> new Alarm("purple",false));
	public static final RegistryObject<Block> block_Alarm_MAGENTA =  BLOCKS.register("alarm_magenta", () -> new Alarm("magenta",false));
	public static final RegistryObject<Block> block_Alarm_PINK =  BLOCKS.register("alarm_pink", () -> new Alarm("pink",false));
	public static final RegistryObject<Block> block_Alarm_BROWN =  BLOCKS.register("alarm_brown",() -> new Alarm("brown",false));

	public static final RegistryObject<Block> block_Alarm_WHITE_lit =  BLOCKS.register("alarm_lit_white", () -> new Alarm("lit_white",true));
	public static final RegistryObject<Block> block_Alarm_RED_lit =  BLOCKS.register("alarm_lit_red", () -> new Alarm("lit_red",true));
	public static final RegistryObject<Block> block_Alarm_ORANGE_lit =  BLOCKS.register("alarm_lit_orange", () -> new Alarm("lit_orange",true));
	public static final RegistryObject<Block> block_Alarm_YELLOW_lit =  BLOCKS.register("alarm_lit_yellow", () -> new Alarm("lit_yellow",true));
	public static final RegistryObject<Block> block_Alarm_LIME_lit =  BLOCKS.register("alarm_lit_lime", () -> new Alarm("lit_lime",true));
	public static final RegistryObject<Block> block_Alarm_GREEN_lit =  BLOCKS.register("alarm_lit_green", () -> new Alarm("lit_green",true));
	public static final RegistryObject<Block> block_Alarm_LIGHTBLUE_lit =  BLOCKS.register("alarm_lit_lightblue", () -> new Alarm("lit_lightblue",true));
	public static final RegistryObject<Block> block_Alarm_CYAN_lit =  BLOCKS.register("alarm_lit_cyan", () -> new Alarm("lit_cyan",true));
	public static final RegistryObject<Block> block_Alarm_BLUE_lit =  BLOCKS.register("alarm_lit_blue", () -> new Alarm("lit_blue",true));
	public static final RegistryObject<Block> block_Alarm_PURPLE_lit =  BLOCKS.register("alarm_lit_purple", () -> new Alarm("lit_purple",true));
	public static final RegistryObject<Block> block_Alarm_MAGENTA_lit =  BLOCKS.register("alarm_lit_magenta", () -> new Alarm("lit_magenta",true));
	public static final RegistryObject<Block> block_Alarm_PINK_lit =  BLOCKS.register("alarm_lit_pink", () -> new Alarm("lit_pink",true));
	public static final RegistryObject<Block> block_Alarm_BROWN_lit =  BLOCKS.register("alarm_lit_brown", () -> new Alarm("lit_brown",true));
		

	
	
	//Block Items
	public static final RegistryObject<Item> Speaker_Item = ITEMS.register("speaker", () -> new BlockItemBase(Speaker.get()));
	public static final RegistryObject<Item> SongSwitcher_Item = ITEMS.register("songswitcher", () -> new BlockItemBase(SongSwitcher.get()));
	public static final RegistryObject<Item> SongSwitcher_Item_lit = ITEMS.register("songswitcher_lit", () -> new BlockItemBase(SongSwitcher_lit.get()));

	public static final RegistryObject<Item> Alarm_WHITE_Item = ITEMS.register("alarm_white", () -> new BlockItemBase(block_Alarm_WHITE.get()));
	public static final RegistryObject<Item> Alarm_RED_Item = ITEMS.register("alarm_red", () -> new BlockItemBase(block_Alarm_RED.get()));
	public static final RegistryObject<Item> Alarm_ORANGE_Item = ITEMS.register("alarm_orange", () -> new BlockItemBase(block_Alarm_ORANGE.get()));
	public static final RegistryObject<Item> Alarm_YELLOW_Item = ITEMS.register("alarm_yellow", () -> new BlockItemBase(block_Alarm_YELLOW.get()));
	public static final RegistryObject<Item> Alarm_LIME_Item = ITEMS.register("alarm_lime", () -> new BlockItemBase(block_Alarm_LIME.get()));
	public static final RegistryObject<Item> Alarm_GREEN_Item = ITEMS.register("alarm_green", () -> new BlockItemBase(block_Alarm_GREEN.get()));
	public static final RegistryObject<Item> Alarm_LIGHTBLUE_Item = ITEMS.register("alarm_lightblue", () -> new BlockItemBase(block_Alarm_LIGHTBLUE.get()));
	public static final RegistryObject<Item> Alarm_CYAN_Item = ITEMS.register("alarm_cyan", () -> new BlockItemBase(block_Alarm_CYAN.get()));
	public static final RegistryObject<Item> Alarm_BLUE_Item = ITEMS.register("alarm_blue", () -> new BlockItemBase(block_Alarm_BLUE.get()));
	public static final RegistryObject<Item> Alarm_PURPLE_Item = ITEMS.register("alarm_purple", () -> new BlockItemBase(block_Alarm_PURPLE.get()));
	public static final RegistryObject<Item> Alarm_MAGENTA_Item = ITEMS.register("alarm_magenta", () -> new BlockItemBase(block_Alarm_MAGENTA.get()));
	public static final RegistryObject<Item> Alarm_PINK_Item = ITEMS.register("alarm_pink", () -> new BlockItemBase(block_Alarm_PINK.get()));
	public static final RegistryObject<Item> Alarm_BROWN_Item = ITEMS.register("alarm_brown", () -> new BlockItemBase(block_Alarm_BROWN.get()));

	public static final RegistryObject<Item> Alarm_WHITE_Item_lit = ITEMS.register("alarm_lit_white", () -> new BlockItemBase(block_Alarm_WHITE_lit.get()));
	public static final RegistryObject<Item> Alarm_RED_Item_lit = ITEMS.register("alarm_lit_red", () -> new BlockItemBase(block_Alarm_RED_lit.get()));
	public static final RegistryObject<Item> Alarm_ORANGE_Item_lit = ITEMS.register("alarm_lit_orange", () -> new BlockItemBase(block_Alarm_ORANGE_lit.get()));
	public static final RegistryObject<Item> Alarm_YELLOW_Item_lit = ITEMS.register("alarm_lit_yellow", () -> new BlockItemBase(block_Alarm_YELLOW_lit.get()));
	public static final RegistryObject<Item> Alarm_LIME_Item_lit = ITEMS.register("alarm_lit_lime", () -> new BlockItemBase(block_Alarm_LIME_lit.get()));
	public static final RegistryObject<Item> Alarm_GREEN_Item_lit = ITEMS.register("alarm_lit_green", () -> new BlockItemBase(block_Alarm_GREEN_lit.get()));
	public static final RegistryObject<Item> Alarm_LIGHTBLUE_Item_lit = ITEMS.register("alarm_lit_lightblue", () -> new BlockItemBase(block_Alarm_LIGHTBLUE_lit.get()));
	public static final RegistryObject<Item> Alarm_CYAN_Item_lit = ITEMS.register("alarm_lit_cyan", () -> new BlockItemBase(block_Alarm_CYAN_lit.get()));
	public static final RegistryObject<Item> Alarm_BLUE_Item_lit = ITEMS.register("alarm_lit_blue", () -> new BlockItemBase(block_Alarm_BLUE_lit.get()));
	public static final RegistryObject<Item> Alarm_PURPLE_Item_lit = ITEMS.register("alarm_lit_purple", () -> new BlockItemBase(block_Alarm_PURPLE_lit.get()));
	public static final RegistryObject<Item> Alarm_MAGENTA_Item_lit = ITEMS.register("alarm_lit_magenta", () -> new BlockItemBase(block_Alarm_MAGENTA_lit.get()));
	public static final RegistryObject<Item> Alarm_PINK_Item_lit = ITEMS.register("alarm_lit_pink", () -> new BlockItemBase(block_Alarm_PINK_lit.get()));
	public static final RegistryObject<Item> Alarm_BROWN_Item_lit = ITEMS.register("alarm_lit_brown", () -> new BlockItemBase(block_Alarm_BROWN_lit.get()));
}

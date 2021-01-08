package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.block.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class BlockLoader {
	 public static Block oreHuaji = new OreHuaji();
	 public static Block huajiStarBlock = new HuajiStarBlock();
	 public static Block huajiStarBlockSky = new HuajiStarBlockSky();
	 public static Block huajiBlender = new HuajiBlender();
	 public static Block huajiBomb = new HuajiBomb();
	 public static Block huajiPolyFurnace = new HuajiPolyfurnace();
	 public static Block blockNewLandMan = new BlockNewLandMan();

	    public BlockLoader(FMLPreInitializationEvent event)
	    {
	        register(oreHuaji, "oreHuaji","ore_huaji");
	        register(huajiStarBlock, "huajiStarBlock","huaji_star_block");
	        register(huajiStarBlockSky, "huajiStarBlockSky","huaji_star_block_sky");
	        register(huajiBlender, "huajiBlender","huaji_blender");
	        register(huajiBomb, "huajiBomb","huaji_bomb");
	        register(huajiPolyFurnace, "huajiPolyFurnace","huaji_poly_furnace");
	        registerBlock(blockNewLandMan,"new_land_man");
	    }
	    public void init(FMLInitializationEvent event)
	    {

            OreDictionary.registerOre("oreHuaji", oreHuaji);

	    }

	    @SideOnly(Side.CLIENT)
	    public static void registerRenders()
	    {
	        registerRender(oreHuaji);
	        registerRender(huajiBomb);
	        registerRender(huajiStarBlock);
	        registerRender(huajiStarBlockSky);
	        registerRender(huajiBlender,0);
	        registerRender(huajiPolyFurnace,0);
	    }

	    private static void register(Block block, String name1,String name2)
	    {
	    	block.setTranslationKey(HuajiAge.MODID+"."+name1);
	    	block.setRegistryName(name2);
	    	ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	        ForgeRegistries.BLOCKS.register(block);
	   
	    }
	    
	    private static void registerBlock(Block block,String name)
	    {
	    	block.setRegistryName(name);
	        ForgeRegistries.BLOCKS.register(block);
	   
	    }

	    @SideOnly(Side.CLIENT)
	    private static void registerRender(Block block)
	    {
	        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
	        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	    }
	    @SideOnly(Side.CLIENT)
	    private static void registerRender(Block block, int meta)
	    {
	        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
	        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
	    }

}

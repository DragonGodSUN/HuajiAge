


package com.lh_lshen.mcbbs.huajiage.common;

import com.lh_lshen.mcbbs.huajiage.block.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
    	ResourceLocation optionalGroup = new ResourceLocation("");
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_sword"), optionalGroup, new ItemStack(ItemLoader.huajiSword), new Object[]{
 	            " D ",
 	            " D ",
 	            " E ",
 	            'D', ItemLoader.huajiIngot,
 	            'E', Items.DIAMOND  // note carefully - 'E' not "E" !
 	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_star_sword"), optionalGroup, new ItemStack(ItemLoader.starSword), new Object[]{
 	            " D ",
 	            "DED",
 	            " F ",
 	            'D', ItemLoader.netronStarFragment,
 	            'E', ItemLoader.huajiSword,  // note carefully - 'E' not "E" !
 	            'F', Items.DIAMOND
 	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_star_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiStar), new Object[]{
    	            " D ",
    	            "DED",
    	            " D ",
    	            'D', ItemLoader.huaji,
    	            'E', Items.NETHER_STAR  // note carefully - 'E' not "E" !
    	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_star_sky_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiStarSky), new Object[]{
 	            "DDD",
 	            "DED",
 	            "DDD",
 	            'D', BlockLoader.huajiStarBlock,
 	            'E', ItemLoader.huajiStar  // note carefully - 'E' not "E" !
 	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:egg_rice"), optionalGroup, new ItemStack(ItemLoader.eggRice), new Object[]{
  	            "ABC",
  	            "FEF",
  	            "GDG",
  	            'A', Items.COOKED_CHICKEN,
  	            'B', Items.COOKED_BEEF,
  	            'C', Items.COOKED_PORKCHOP,
  	            'D', Items.BOWL,
  	            'E', ItemLoader.huaji,
  	            'F', Items.EGG,
  	            'G', Items.CARROT
  	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_blender"), optionalGroup, new ItemStack(BlockLoader.huajiBlender), new Object[]{
 	            "DAD",
 	            "ABA",
 	            "DAD",
 	            'D', ItemLoader.huaji,
 	            'B', Blocks.BEACON,
 	            'A', Blocks.OBSIDIAN
 	            
 	            // note carefully - 'E' not "E" !
 	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_helmet_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiHelmet), new Object[]{
  	            "DDD",
  	            "D D",
  	            "   ",
  	            'D', ItemLoader.huajiIngot,
  	              // note carefully - 'E' not "E" !
  	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_bomb_recipe"), optionalGroup, new ItemStack(BlockLoader.huajiBomb), new Object[]{
   	            "DDD",
   	            "DSD",
   	            "DDD",
   	            'D', Items.GUNPOWDER,
   	            'S',ItemLoader.huaji
   	              // note carefully - 'E' not "E" !
   	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_chest_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiChestplate), new Object[]{
   	            "D D",
   	            "DAD",
   	            "DDD",
   	            'D', ItemLoader.huajiIngot,
   	            'A',Items.DIAMOND
   	            // note carefully - 'E' not "E" !
   	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_leggings_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiLeggings), new Object[]{
    	            "DAD",
    	            "D D",
    	            "D D",
    	            'D', ItemLoader.huajiIngot,
    	            'A',Items.DIAMOND
    	            // note carefully - 'E' not "E" !
    	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_boots_recipe"), optionalGroup, new ItemStack(ItemLoader.huajiBoots), new Object[]{
    	            "A A",
    	            "D D",
    	            "D D",
    	            'D', ItemLoader.huajiIngot,
    	            'A',Items.DIAMOND
    	            // note carefully - 'E' not "E" !
    	    });
    	 GameRegistry.addShapedRecipe(new ResourceLocation("huajiage:huaji_raw_gluten"), optionalGroup, new ItemStack(ItemLoader.rawGluten), new Object[]{
    	            " D ",
    	            "DAD",
    	            " D ",
    	            'D', Items.WHEAT,
    	            'A',ItemLoader.flashFlour
    	            // note carefully - 'E' not "E" !
    	    });
//    	  GameRegistry.addShapelessRecipe(new ResourceLocation("huajiage:huaji_star_sky_block_recipe"), optionalGroup,
//                  new ItemStack(BlockLoader.huajiStarBlockSky,1),
//                  new Ingredient[] {Ingredient.fromStacks(new ItemStack(ItemLoader.huajiStarSky, 9)), });
      	   GameRegistry.addShapelessRecipe(new ResourceLocation("huajiage:huaji_star_block_sky_back_recipe"), optionalGroup,
                   new ItemStack(ItemLoader.huajiStarSky, 9),
                    new Ingredient[] {Ingredient.fromStacks( new ItemStack(BlockLoader.huajiStarBlockSky,1))});
//    	   GameRegistry.addShapelessRecipe(new ResourceLocation("huajiage:huaji_star_block_recipe"), optionalGroup,
//                   new ItemStack(BlockLoader.huajiStarBlock,1),
//                   new Ingredient[] {Ingredient.fromStacks(new ItemStack(ItemLoader.huajiStar, 9)), });
    	   GameRegistry.addShapelessRecipe(new ResourceLocation("huajiage:huaji_star_block_back_recipe"), optionalGroup,
                  new ItemStack(ItemLoader.huajiStar, 9),
                   new Ingredient[] {Ingredient.fromStacks( new ItemStack(BlockLoader.huajiStarBlock,1))});
    	   GameRegistry.addShapelessRecipe(new ResourceLocation("huajiage:egg_rice_u"), optionalGroup,
                   new ItemStack(ItemLoader.eggRiceU,1),
                   new Ingredient[] {Ingredient.fromStacks(new ItemStack(ItemLoader.huajiStar, 1)),Ingredient.fromStacks(new ItemStack(ItemLoader.eggRice, 1)), Ingredient.fromStacks(new ItemStack(Blocks.DIAMOND_BLOCK, 1))});

    }

    private static void registerSmelting()
    {
    	  GameRegistry.addSmelting(new ItemStack(ItemLoader.huajiFragmnet,1), new ItemStack(ItemLoader.huaji,1),10f);
    	  GameRegistry.addSmelting(new ItemStack(ItemLoader.rawGluten,1), new ItemStack(ItemLoader.bakingGluten,1),50f);

    }

    private static void registerFuel()
    {

    }
}

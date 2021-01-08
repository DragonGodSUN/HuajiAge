package com.lh_lshen.mcbbs.huajiage.recipelist;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HuajiPolyRecipeList
{
    private static final HuajiPolyRecipeList SMELTING_BASE = new HuajiPolyRecipeList();
    /** The list of smelting results. */
    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    /** A list which contains how many experience points each recipe output will give. */
    private final Map<ItemStack, Integer> pointList = Maps.<ItemStack, Integer>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static HuajiPolyRecipeList instance()
    {
        return SMELTING_BASE;
    }

    private HuajiPolyRecipeList()
    {
    	addPolySmelting(ItemLoader.huajiStar, new ItemStack(ItemLoader.huajiStarUniverse), 1);
    	addPolySmelting(ItemLoader.huajiStarSky, new ItemStack(ItemLoader.huajiStarUniverse), 36);
    	addPolySmelting(ItemLoader.huajiStarPoly, new ItemStack(ItemLoader.huajiStarUniverse), 0);
    	addPolySmeltingRecipeForBlock(BlockLoader.huajiStarBlock, new ItemStack(ItemLoader.huajiStarUniverse), 9);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addPolySmeltingRecipeForBlock(Block input, ItemStack stack,int point)
    {
        this.addPolySmelting(Item.getItemFromBlock(input), stack, point);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addPolySmelting(Item input, ItemStack stack, int point)
    {
        this.addPolyRecipe(new ItemStack(input, 1, 32767), stack, point);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
//    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
//    {
//        if (getSmeltingResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
//        this.smeltingList.put(input, stack);
//        this.experienceList.put(stack, Float.valueOf(experience));
//    }
    public void addPolyRecipe(ItemStack input, ItemStack stack, int point)
    {
        if (getSmeltingResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.smeltingList.put(input, stack);
        this.pointList.put(input, point);
        
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
   
    public Integer getPoint(ItemStack stack)
    {
        for (Entry<ItemStack, Integer> entry : this.pointList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return 0;
    }
    public Integer getPool(ItemStack stack)
    {
    	int pool=NBTHelper.getTagCompoundSafe(stack).getInteger("poly");
        for (Entry<ItemStack, Integer> entry : this.pointList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                if(pool>0) {
                	return pool;
                }
            }
        }

        return 0;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }
    public Map<ItemStack, Integer> getPointList()
    {
        return this.pointList;
    }

//    public float getSmeltingExperience(ItemStack stack)
//    {
//        float ret = stack.getItem().getSmeltingExperience(stack);
//        if (ret != -1) return ret;
//
//        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
//        {
//            if (this.compareItemStacks(stack, entry.getKey()))
//            {
//                return ((Float)entry.getValue()).floatValue();
//            }
//        }
//
//        return 0.0F;
//    }
}
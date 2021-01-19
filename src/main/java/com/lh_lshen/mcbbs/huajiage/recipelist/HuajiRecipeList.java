package com.lh_lshen.mcbbs.huajiage.recipelist;

import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

public class HuajiRecipeList
{
    private static final HuajiRecipeList SMELTING_BASE = new HuajiRecipeList();
    /** The list of smelting results. */
    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    /** A list which contains how many experience points each recipe output will give. */
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static HuajiRecipeList instance()
    {
        return SMELTING_BASE;
    }

    private HuajiRecipeList()
    {
//    	this.addSmelting(Items.IRON_INGOT, new ItemStack(ItemLoader.huajiIngot), 1F);
    	this.addSmelting(ItemLoader.huajiStar, new ItemStack(ItemLoader.netronStarFragment), 50F);
//    	this.addSmelting(Items.WHEAT, new ItemStack(ItemLoader.flashFlour), 1F);
    	this.addSmeltingRecipeForBlock(Blocks.CHORUS_FLOWER, new ItemStack(ItemLoader.hopeElement), 5F);
    	this.addSmeltingRecipeForBlock(Blocks.GOLD_BLOCK, new ItemStack(ItemLoader.huajiIngot), 2F);
    	this.addSmeltingRecipeForBlock(Blocks.REDSTONE_BLOCK, new ItemStack(ItemLoader.redstoneDruse), 2F);
    	this.addSmeltingRecipeForBlock(Blocks.HAY_BLOCK, new ItemStack(ItemLoader.flashFlour), 1F);
    	this.addSmeltingRecipeForBlock(Blocks.SEA_LANTERN, new ItemStack(ItemLoader.waveCrystal), 10F);
    	
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addSmelting(Item input, ItemStack stack, float experience)
    {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getSmeltingResult(input) != ItemStack.EMPTY) { net.minecraftforge.fml.common.FMLLog.log.info("Ignored smelting recipe with conflicting input: {} = {}", input, stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
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

    public float getSmeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
}
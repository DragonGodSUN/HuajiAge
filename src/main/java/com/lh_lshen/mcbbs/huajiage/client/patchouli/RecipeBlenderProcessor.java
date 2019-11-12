package com.lh_lshen.mcbbs.huajiage.client.patchouli;

import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiRecipeList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.common.util.ItemStackUtil;

public class RecipeBlenderProcessor implements IComponentProcessor {

	ItemStack inItem;
	ItemStack outItem;
	
	@Override
	public void setup(IVariableProvider<String> variables) {
		String material = variables.get("recipe");
		inItem = new ItemStack(Item.getByNameOrId(material));
		outItem = HuajiRecipeList.instance().getSmeltingResult(inItem);
	}

	@Override
	public String process(String key) {

		if(key.equals("item_in")) {

			ItemStack stack = outItem.isEmpty() ? ItemStack.EMPTY : inItem;
			
			return ItemStackUtil.serializeStack(stack);
		}
		else if(key.equals("item_out")) {

			ItemStack stack = outItem.isEmpty() ? ItemStack.EMPTY : outItem;
			
			return ItemStackUtil.serializeStack(stack);
		}
		
		else if(key.equals("icount"))
			return Integer.toString(outItem.getCount());
		
		else if(key.equals("iname"))
			return "$(bold)"+outItem.getDisplayName();
		
		return null;
	}

}

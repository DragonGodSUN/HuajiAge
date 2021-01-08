package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;

public class ItemOrgaHair extends ItemOrgaArmorBase {

	public ItemOrgaHair() {
		super(EntityEquipmentSlot.HEAD);
	
		 MinecraftForge.EVENT_BUS.register(this);
		
	}
//	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
//	    NBTTagCompound tagCompound = stack.getTagCompound();
//	    if (tagCompound == null) {
//	        tagCompound = new NBTTagCompound();
//	        stack.setTagCompound(tagCompound);
//	    }
//	    return tagCompound;
//	}
	private boolean isOpen(ItemStack stack) {
	    return NBTHelper.getTagCompoundSafe(stack).hasKey("open");
	} 
	@Nonnull
	@Override
	public final String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        Entity player = entity;
        World world = entity.world;
			return HuajiAge.MODID+":textures/models/armor/orga.png";
	}
	
	 public static boolean isSetEquippedByPlayer(EntityPlayer player) {
	        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemOrgaHair &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase;
	    }
 @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	 
	
	 
	 }
    
		
	}


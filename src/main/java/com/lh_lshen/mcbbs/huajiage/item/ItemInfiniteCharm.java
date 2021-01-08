package com.lh_lshen.mcbbs.huajiage.item;

import javax.annotation.Nullable;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInfiniteCharm extends Item {
	private boolean orga;
	public ItemInfiniteCharm()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  this.addPropertyOverride(new ResourceLocation("orga"), new IItemPropertyGetter()
	        {
			  @SideOnly(Side.CLIENT)
			  @Override
			  public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			  {
				  if (entityIn == null)
				  {
					  return 0.0F;
				  }
				  if(isOrga(stack))
				  {
					  return 1.0f;
					  
				  }
				  else{
					  return 0f;
				  }
			  }
	        });
	}
	public boolean isOrga(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack).getBoolean("orga");
	}
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
	if(NBTHelper.getTagCompoundSafe(stack).getBoolean("orga")) {
		return 	I18n.translateToLocal("item.huajiage.orgaFlagUnbroken.name");
		}else {
			return super.getItemStackDisplayName(stack);
		}
	}
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(entityIn instanceof EntityPlayer) {
			 ItemStack item[]= {((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.HEAD),
					            ((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.CHEST),
					            ((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.LEGS),
					            ((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.FEET)
			 };
			 if(item!=null) {
				 for(ItemStack i:item) {
				   if(i.getMaxDamage()-i.getItemDamage()<i.getMaxDamage()) 
				   {
					   i.setItemDamage(0);
				   }
			}}
			 if(ConfigHuaji.Huaji.orgaSuit&&(((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemOrgaArmorBase)&&(((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase)&&
					 (((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase)&&(((EntityPlayer) entityIn).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase)) {
				if(!NBTHelper.getTagCompoundSafe(stack).getBoolean("orga")) { 
				 NBTHelper.getTagCompoundSafe(stack).setBoolean("orga",true);
				 NBTHelper.setEntityBoolean(entityIn, "huajiage.orga.suit", true);
				 }
			 }else {
				 if(NBTHelper.getTagCompoundSafe(stack).getBoolean("orga")) {
				 NBTHelper.getTagCompoundSafe(stack).setBoolean("orga",false);
				 NBTHelper.setEntityBoolean(entityIn,  "huajiage.orga.suit",false);
				 }
			 }
//			 if(NBTHelper.getTagCompoundSafe(stack).getBoolean("orga")&&!NBTHelper.getEntityBoolean(entityIn, "huajiage.orga.suit")) {
//				 NBTHelper.setEntityBoolean(entityIn, "huajiage.orga.suit", true);
//			 }else {
//				 NBTHelper.setEntityBoolean(entityIn,  "huajiage.orga.suit",false);
//			 } 
		 }
	}
	
}

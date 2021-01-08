package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import javax.annotation.Nullable;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLordKey extends Item {
	public ItemLordKey()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  this.maxStackSize=1;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		tooltip.add( I18n.format("item.lord_key:tooltips.1"));
		
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		ItemStack stackb= playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		// TODO Auto-generated method stub
		if(stackb.getItem() instanceof ItemBlancedHelmet&&stackb.getTagCompound().hasKey("active")&&!stackb.getTagCompound().hasKey("lord")) {
			NBTHelper.getTagCompoundSafe(stackb).setBoolean("lord",true);
			stack.shrink(1);
			playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,1f, 1f);
			playerIn.playSound(SoundEvents.BLOCK_GLASS_BREAK,5f, 1f);
			for(int i=0;i<3;i++) {
			worldIn.spawnEntity(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY,  playerIn.posZ, true));
			}
			if(worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentTranslation("messege.huaji.blancedHelmet.lord.break",playerIn.getName()));}
			
		}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
 

}

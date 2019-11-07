package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMultiKnife extends Item {
	
	public ItemMultiKnife()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  this.setMaxStackSize(1);
		  this.setMaxDamage(900);
	}
//	@Override
//	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
//
//	if(player.ticksExisted%5==0) {
//		if(!player.getEntityWorld().isRemote) {
//		EntityMultiKnife knife=new EntityMultiKnife(player.getEntityWorld(), player);
//		Vec3d v1=player.getLookVec();
//		knife.shoot(player, player.rotationPitch, player.rotationYaw, 1F,1F,0f);
//		float fn=MathHelper.sqrt(v1.x*v1.x+v1.y*v1.y+v1.z*v1.z);
//		knife.posX=player.posX +1.5*v1.x/fn ;
//		knife.posY=player.posY+player.getEyeHeight()+1.5*v1.y/fn ;
//		knife.posZ=player.posZ +1.5*v1.z/fn ;
//		knife.setRotation(MathHelper.wrapDegrees(-player.rotationYaw));
//		knife.setPitch(player.rotationPitch);
//		knife.setLife(360f);
//		player.getEntityWorld().spawnEntity(knife);
//		}
//		player.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1f,1f);
//		}
//		
//	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		 ItemStack itemstack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote) {
		EntityMultiKnife knife=new EntityMultiKnife(worldIn, playerIn);
		Vec3d v1=playerIn.getLookVec();
		knife.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1F,1F,0f);
		float fn=MathHelper.sqrt(v1.x*v1.x+v1.y*v1.y+v1.z*v1.z);
		knife.posX+=v1.x/fn;
		knife.posY+=ConfigHuaji.Huaji.knifeHeight +0.1f +v1.y/fn;
		knife.posZ+=v1.z/fn;
		knife.setRotation(MathHelper.wrapDegrees(-playerIn.rotationYaw));
		knife.setPitch(playerIn.rotationPitch);
		knife.setLife(360f);
		if(!playerIn.isCreative()) {
		itemstack.damageItem(1, playerIn);}
		worldIn.spawnEntity(knife);
		}
		playerIn.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1f,1f);
		playerIn.swingArm(handIn);
//		 if (!worldIn.isRemote) {
//	            playerIn.setActiveHand(handIn);
//	        }
//		
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		
		 ItemStack mat = new ItemStack(ItemLoader.huajiIngot);
	        if (!mat.isEmpty()) return true;
				return super.getIsRepairable(toRepair,repair);
			}
	
	
// @Override
//public EnumAction getItemUseAction(ItemStack stack) {
//	// TODO Auto-generated method stub
//	return EnumAction.BLOCK;
//}
// @Override
//public int getMaxItemUseDuration(ItemStack stack) {
//
//	return 18000;
//}

}

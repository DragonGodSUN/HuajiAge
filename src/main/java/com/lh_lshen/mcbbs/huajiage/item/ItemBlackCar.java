package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ItemBlackCar extends Item {
	public ItemBlackCar()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  this.maxStackSize=1;
		  MinecraftForge.EVENT_BUS.register(this);
	}
 
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		 ItemStack itemstack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote) {
			EntityRoadRoller road=new EntityRoadRoller(worldIn, playerIn);
			Vec3d v1=playerIn.getLookVec();
			road.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1F,1F,0f);
			float fn=MathHelper.sqrt(v1.x*v1.x+v1.y*v1.y+v1.z*v1.z);
			road.posX+=v1.x/fn;
			road.posY+=0.1f +v1.y/fn;
			road.posZ+=v1.z/fn;
			road.setRotation(MathHelper.wrapDegrees(-playerIn.rotationYaw));
			road.setPitch(playerIn.rotationPitch);
			road.setType(EntityRoadRoller.enumTYPE.CAR.getName());
			road.setDamage(50f);
			road.setLife(512f);
			if(!playerIn.isCreative()) {
			itemstack.shrink(1);}
			worldIn.spawnEntity(road);
		}
		playerIn.playSound(SoundLoader.ORGA_RIDER, 2f,1f);
		playerIn.swingArm(handIn);		
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}

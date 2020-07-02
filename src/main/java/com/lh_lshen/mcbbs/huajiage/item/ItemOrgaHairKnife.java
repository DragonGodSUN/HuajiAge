package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityOrgaHairKnife;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickEmpty;

public class ItemOrgaHairKnife extends Item {
	public ItemOrgaHairKnife()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  setMaxStackSize(16);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			Vec3d vec = playerIn.getLookVec();
			EntityOrgaHairKnife hair = new EntityOrgaHairKnife(worldIn, playerIn);
			hair.setLife(600);
			hair.setPosition(playerIn.posX, playerIn.posY+playerIn.eyeHeight, playerIn.posZ);
			hair.motionX = vec.x;
			hair.motionY = vec.y;
			hair.motionZ = vec.z;
			hair.setRotation(MathHelper.wrapDegrees(-playerIn.rotationYaw));
			hair.setPitch(playerIn.rotationPitch);
			float r =(float) Math.random()*360;
			hair.setRotationRandom(r);
			hair.setDamage(10f);
			worldIn.spawnEntity(hair);
			playerIn.inventory.getCurrentItem().shrink(1);
		}
			playerIn.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1f, 1f);
		
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("item.orga_knife:tooltips.1"));
	}
}

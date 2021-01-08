package com.lh_lshen.mcbbs.huajiage.item;


import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.List;

public class ItemVehicleKey extends Item {
	private static final String DEFAULT_MODEL_ID ="huajiage:bike_standard_default";
	private static final String BIKE_STAND_ID ="huajiage:bike_wheel";

	public ItemVehicleKey()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabVehicle);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("item.huajiage.vehicle_key.tooltip.1"));

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IExposedData data = StandUtil.getStandData(playerIn);
		if (data!=null) {
			String type = data.getStand();
			int stage = data.getStage();
			String model = data.getModel();
			data.setStand(BIKE_STAND_ID);
			data.setStage(1);
			data.setModel(DEFAULT_MODEL_ID);
			data.setTrigger(false);
			playerIn.getHeldItem(handIn).shrink(1);
			if(StandLoader.getStand(data.getStand())!=null){
				StandUtil.setChargeMax(playerIn,StandLoader.getStand(data.getStand()).getMaxMP());
			}
		if(!type.equals(StandLoader.EMPTY)) {
			if(!worldIn.isRemote) {
			playerIn.dropItem(ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc),type,stage,model),true);
			}
		}
			playerIn.playSound(SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_ON, 1f, 1f);
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

}

package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ItemExpensiveCamera extends Item {
	protected Random rand = new Random();
	public ItemExpensiveCamera()
	{
		  super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		tooltip.add( I18n.format("item.huajiage.expensive_camera.tooltips.1"));
		if (Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_LSHIFT) || org.lwjgl.input.Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
		tooltip.add( I18n.format("item.huajiage.expensive_camera.tooltips.2"));
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item_main_hand = StandPowerHelper.getPlayerHoldItem(playerIn,true);
		ItemStack item_off_hand = StandPowerHelper.getPlayerHoldItem(playerIn,false);
		IExposedData data = StandUtil.getStandData(playerIn);
		if (data != null && data.getStand().equals("huajiage:hermit_purple")) {
			if(StandPowerHelper.getItemRegistryName(item_main_hand).equals("huajiage:expensive_camera")) {
				StandChargeHandler chargeHandler = StandUtil.getChargeHandler(playerIn);
				if (chargeHandler !=null ){
					StandBase standBase = StandLoader.getStand(data.getStand());
					if (standBase != null ){
						boolean isEnough = chargeHandler.canBeCost(standBase.getCost());
						if (isEnough) {
							if (!worldIn.isRemote) {
								MessagePerfromSkill msg = new MessagePerfromSkill(standBase.getCost());
								StandNetWorkHandler.sendToServer(msg);
								StandPowerHelper.sendMessage(playerIn, "stand.huajiage.skill.huajiage.hermit_purple.telepathy");
								StandPowerHelper.playSound(playerIn, "huajiage:stand_hermit_purple_camera_broken", 1, 1);
								StandPowerHelper.telepathizeItem(playerIn, item_off_hand);
							}
							if (data.getStage()<1){
								worldIn.playEvent(2001, playerIn.getPosition().add(0, playerIn.getPositionEyes(playerIn.ticksExisted).y-playerIn.getPosition().getY(), 0), Blocks.WOOL.getStateId(Blocks.WOOL.getStateFromMeta(15)));
								if (!worldIn.isRemote) {
									item_main_hand.shrink(1);
								}
							}
						}
					}
				}
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}
}

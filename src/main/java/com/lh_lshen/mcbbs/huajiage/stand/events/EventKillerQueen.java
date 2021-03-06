package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemKillerQueenTrigger;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventKillerQueen {

	@SubscribeEvent
	public static void onTargetEntity(AttackEntityEvent evt) {
		EntityPlayer player = evt.getEntityPlayer();
		Entity hit =evt.getTarget();
		StandBase stand = StandUtil.getType(player);
		if(stand!=null&&stand.equals(StandLoader.KILLER_QUEEN)&&player.isPotionActive(PotionLoader.potionStand)) {
			if(!player.inventory.hasItemStack(new ItemStack(ItemLoader.killerQueenTrigger))) {
			ItemStack stack = new ItemStack(ItemLoader.killerQueenTrigger);
			ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.ENTITY.getName(), hit.getUniqueID().toString(), 0, 0, 0);
			player.inventory.addItemStackToInventory(stack);
			}else {
				InventoryPlayer inventory = player.inventory;
				List<ItemStack> mainInventory = inventory.mainInventory;
				for(ItemStack stack : mainInventory) {
					if(stack.getItem() == ItemLoader.killerQueenTrigger) {
						ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.ENTITY.getName(), hit.getUniqueID().toString(), 0, 0, 0);
						break;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onTargetBlock(PlayerEvent.BreakSpeed evt) {
		EntityPlayer player = evt.getEntityPlayer();
		BlockPos pos =evt.getPos();
		if(player!=null) {
			StandBase stand = StandUtil.getType(player);
			if(stand!=null&&stand.equals(StandLoader.KILLER_QUEEN)&&player.isPotionActive(PotionLoader.potionStand)) {
				if(!player.inventory.hasItemStack(new ItemStack(ItemLoader.killerQueenTrigger))) {
					ItemStack stack = new ItemStack(ItemLoader.killerQueenTrigger);
					ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.BLOCK.getName(),"empty",pos.getX(), pos.getY(), pos.getZ());
					player.inventory.addItemStackToInventory(stack);
					}else {
						InventoryPlayer inventory = player.inventory;
						List<ItemStack> mainInventory = inventory.mainInventory;
						for(ItemStack stack : mainInventory) {
							if(stack.getItem() == ItemLoader.killerQueenTrigger) {
								ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.BLOCK.getName(),"empty",pos.getX(), pos.getY(), pos.getZ());
								break;
							}
						}
					}
			}
		}
	}
	
	@SubscribeEvent
	public static void onTargetBlock(BlockEvent.BreakEvent evt) {
		EntityPlayer player = evt.getPlayer();
		BlockPos pos =evt.getPos();
		if(player!=null && player.isCreative()) {
			StandBase stand = StandUtil.getType(player);
			if(stand!=null&&stand.equals(StandLoader.KILLER_QUEEN)&&player.isPotionActive(PotionLoader.potionStand)) {
				if(!player.inventory.hasItemStack(new ItemStack(ItemLoader.killerQueenTrigger))) {
					ItemStack stack = new ItemStack(ItemLoader.killerQueenTrigger);
					ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.BLOCK.getName(),"empty",pos.getX(), pos.getY(), pos.getZ());
					player.inventory.addItemStackToInventory(stack);
					}else {
						InventoryPlayer inventory = player.inventory;
						List<ItemStack> mainInventory = inventory.mainInventory;
						for(ItemStack stack : mainInventory) {
							if(stack.getItem() == ItemLoader.killerQueenTrigger) {
								ItemKillerQueenTrigger.setData(stack, ItemKillerQueenTrigger.TYPE.BLOCK.getName(),"empty",pos.getX(), pos.getY(), pos.getZ());
								break;
							}
						}
					}
			}
		}
	}
}

package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.LootTablesLoader;
import com.lh_lshen.mcbbs.huajiage.init.events.EventStand;
import com.lh_lshen.mcbbs.huajiage.init.events.EventTimeStop;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemLootTableTest extends Item {
	@Override
	public boolean hasEffect(ItemStack stack) {
		
		return true;
	}
	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
//	    NBTTagCompound tagCompound = stack.getTagCompound();
//	    if (tagCompound == null) {
//	        tagCompound = new NBTTagCompound();
//	        stack.setTagCompound(tagCompound);
//	    }
	    return NBTHelper.getTagCompoundSafe(stack);
	}
	private void modeSwitch(ItemStack stack,int i) {
	    getTagCompoundSafe(stack).setInteger("mode",i);
	}
	private int getmode(ItemStack stack) {
	    return getTagCompoundSafe(stack).getInteger("mode");
	}
	private String getmodeName(ItemStack stack) {
		switch(getTagCompoundSafe(stack).getInteger("mode")) {
		case 0:
			return "Loot Table Test";
		case 1:
			return "The Hope Flower";
		case 2:
			return "Time Stop ---Dio";
		case 3:
			return "Time Stop ---Passerby";
		case 4:
			return "Stand Power!!!";
		}
	    return null;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand hand) {
		ItemStack stack=playerIn.getHeldItemMainhand();
		if(stack.getItem()==ItemLoader.loottest) {
		StandHandler stand = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null);
		if(playerIn.isSneaking()) {
			int mode=getmode(stack);
			if(mode<4) {
			modeSwitch(stack, mode+1);
			}else {
			modeSwitch(stack,0);	
			}
			if(worldIn.isRemote) {
			playerIn.sendMessage(new TextComponentString(getmode(stack)+":"+getmodeName(stack)));}
		
		}
		if(!playerIn.isSneaking()) {
		switch (getmode(stack)){
		case 0:{
			if (!worldIn.isRemote) {
			final LootTable lootTable = worldIn.getLootTableManager().getLootTableFromLocation(LootTablesLoader.LOOT_TABLE_HUAJI);
			final LootContext lootContext = new LootContext.Builder((WorldServer) worldIn).withPlayer(playerIn).build();

			final List<ItemStack> itemStacks = lootTable.generateLootForPools(itemRand, lootContext);
			for (final ItemStack itemStack : itemStacks) {
				ItemHandlerHelper.giveItemToPlayer(playerIn, itemStack);
			}

			playerIn.inventoryContainer.detectAndSendChanges();
			
			if (!(itemStacks.size() > 0)) {

				playerIn.sendMessage(new TextComponentTranslation("message.huajiage:player_received_loot.no_loot"));
		      }
       
		   }
			break;
		}
		case 1:{playerIn.playSound(SoundLoader.ORGA_FLOWER, 2f, 1f); break;}
		case 2:{
		if(!playerIn.world.isRemote) {
//		NBTHelper.setEntityInteger(playerIn, HuajiConstant.STAND_TYPE, 21);
		stand.setStand(EnumStandtype.THE_WORLD.getname());
		playerIn.getEntityData().setInteger("huajiage.the_world",9*20);
    	playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,9*20,0));
    	playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,9*20,4));
    	playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED,9*20,6));
    	playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,9*20,4));
        playerIn.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
        playerIn.heal(15f);}
        double rand0=Math.random()*100;
        if(rand0<25) { 	
        	playerIn.playSound(SoundLoader.THE_WORLD, 5f,1f);
        }else if(rand0<50){
        	playerIn.playSound(SoundLoader.THE_WORLD_1, 5f,1f);
        }else if(rand0<75){
        	playerIn.playSound(SoundLoader.THE_WORLD_2, 5f,1f);
        }else {
        	playerIn.playSound(SoundLoader.THE_WORLD_3, 5f,1f);
        }
        break;
		      }
		case 3:{
		if(!playerIn.world.isRemote) {
		stand.setStand("");
		playerIn.getEntityData().setInteger("huajiage.time_stop",9*20);
		playerIn.getEntityData().setDouble("huajiage.time_stop.x", playerIn.posX);
		playerIn.getEntityData().setDouble("huajiage.time_stop.y", playerIn.posY);
		playerIn.getEntityData().setDouble("huajiage.time_stop.z", playerIn.posZ);
        playerIn.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
        break;
		   }
		}
		case 4:{
			if(!playerIn.world.isRemote) {
//			EventStand.standPower(playerIn);
//			NBTHelper.setEntityInteger(playerIn, HuajiConstant.STAND_TYPE, 21);
			stand.setStand(EnumStandtype.THE_WORLD.getname());
			playerIn.addPotionEffect(new PotionEffect(PotionLoader.potionStand,EnumStandtype.THE_WORLD.getDuration()));
	        break;
			}
		}
		    }
		  }
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));

	}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		
		return super.hitEntity(stack, target, attacker);
	}
}
	
	
	
	
	
	
	


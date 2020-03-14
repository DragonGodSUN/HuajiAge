package com.lh_lshen.mcbbs.huajiage.item;

import java.time.format.TextStyle;
import java.util.List;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemOrgaRequiem extends Item {
	public ItemOrgaRequiem()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
		  this.setMaxStackSize(1);
	}

	private static NBTTagCompound getTagCompoundSafe(ItemStack stack) {
	    NBTTagCompound tagCompound = stack.getTagCompound();
	    if (tagCompound == null) {
	        tagCompound = new NBTTagCompound();
	        stack.setTagCompound(tagCompound);
	    }
	    return tagCompound;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(entityIn instanceof EntityPlayer) {
		EntityPlayer player =((EntityPlayer)entityIn);
		this.update(stack, player);
		if(!player.world.isRemote) {
			if(!NBTHelper.getEntityString(player, HuajiConstant.Tags.REQUIEM_OWNER).equals(getTagCompoundSafe(stack).getString("owner"))&&player.ticksExisted%40==0) 
			{NBTHelper.setEntityString(player, HuajiConstant.Tags.REQUIEM_OWNER,getTagCompoundSafe(stack).getString("owner"));}
		     }    
		}
	}
	public void update(ItemStack stack, EntityPlayer player) {
		if(stack.isEmpty() || !(stack.getItem() instanceof ItemOrgaRequiem))
			return;
		boolean owner = true;
		if(!getTagCompoundSafe(stack).hasKey("owner")) {
			getTagCompoundSafe(stack).setString("owner", player.getUniqueID().toString());
			getTagCompoundSafe(stack).setString("owner_name", player.getName());
			if(player.world.isRemote) {
			Minecraft.getMinecraft().getSoundHandler().stopSounds();
			HuajiSoundPlayer.playMusic(SoundLoader.ORGA_REQUIEM_2);}
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED,100,2));
		} else if (!getTagCompoundSafe(stack).getString("owner").equals(player.getUniqueID().toString())) {
			owner = false;
		}

	if(!owner){
		if(player.ticksExisted%60==0&&!player.isPotionActive(PotionLoader.potionFlowerHope)
				&&!player.isPotionActive(PotionLoader.potionRequiem)){	
			player.playSound(SoundLoader.ORGA_REQUIEM_HIT, 1f, 1f);
		if(!player.world.isRemote) {
			player.addPotionEffect(new PotionEffect(PotionLoader.potionRequiemTarget,60));
	                    }
		       		}
		}
	else {
		if(player.isPotionActive(PotionLoader.potionFlowerHope)) 
			{
				player.removePotionEffect(PotionLoader.potionFlowerHope);
			}		
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.orga_requiem:tooltips.1")+TextFormatting.GRAY+getTagCompoundSafe(stack).getString("owner_name"));
	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {	
		tooltip.add(I18n.format("item.orga_requiem:tooltips.content.1"));
		tooltip.add(I18n.format("item.orga_requiem:tooltips.content.2"));
		tooltip.add(I18n.format("item.orga_requiem:tooltips.content.3"));
	}else {
		tooltip.add(I18n.format("item.orga_requiem:tooltips.2"));
	}
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}

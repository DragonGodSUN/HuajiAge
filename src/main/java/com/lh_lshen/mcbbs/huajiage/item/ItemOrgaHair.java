package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.immutable.Stack;

public class ItemOrgaHair extends ItemOrgaArmorBase {

	public ItemOrgaHair() {
		super(EntityEquipmentSlot.HEAD);
	
		 MinecraftForge.EVENT_BUS.register(this);
		
	}
//	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
//	    NBTTagCompound tagCompound = stack.getTagCompound();
//	    if (tagCompound == null) {
//	        tagCompound = new NBTTagCompound();
//	        stack.setTagCompound(tagCompound);
//	    }
//	    return tagCompound;
//	}
	private boolean isOpen(ItemStack stack) {
	    return NBTHelper.getTagCompoundSafe(stack).hasKey("open");
	} 
	@Nonnull
	@Override
	public final String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {

			return HuajiAge.MODID+":textures/models/armor/orga.png";
	}
	
	 public static boolean isSetEquippedByPlayer(EntityPlayer player) {
	        return player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemOrgaHair &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase &&
	            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase;
	    }
 @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	 
	
	 
	 }
    
		
	}


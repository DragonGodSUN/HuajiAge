package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelBlanceHelmet;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlancedHelmet extends ItemModelArmorBase {
	public static final ArmorMaterial blanceMaterial = EnumHelper.addArmorMaterial("BLANCE",HuajiAge.MODID+":"+ "blance_helmet", 360,
			new int[] {15,15,15,15}, 33, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3F);
	
	public ItemBlancedHelmet() {
		super(EntityEquipmentSlot.HEAD,blanceMaterial);
		
	}
	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
//	    NBTTagCompound tagCompound = stack.getTagCompound();
//	    if (tagCompound == null) {
//	        tagCompound = new NBTTagCompound();
//	        stack.setTagCompound(tagCompound);
//	    }
	    return NBTHelper.getTagCompoundSafe(stack);
	}
	private boolean isOpen(ItemStack stack) {
	    return getTagCompoundSafe(stack).getBoolean("open");
	}
	
	private boolean isActive(ItemStack stack) {
	    return getTagCompoundSafe(stack).getBoolean("active");
	}
	
	private boolean isLord(ItemStack stack) {
	    return getTagCompoundSafe(stack).getBoolean("lord");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected ModelBiped model(EntityEquipmentSlot slot) {
		return new ModelBlanceHelmet(slot);
	}
	@Nonnull
	@Override
	public final String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        Entity player = entity;
        World world = entity.world;
		return HuajiAge.MODID+":textures/models/armor/blance_helmet.png";
	}
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
//	public void keyInput(InputEvent.KeyInputEvent evt) {
//
//		if(KeyLoader.swich1.isPressed()) {
//		EntityPlayer player = Minecraft.getMinecraft().player;
//		 if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof BlancedHelmet) {
//            HuajiAgeNetWorkHandler.sendToServer(new MessageKeyMode());
//		 }
//		}
//	  }
	
		 
 @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    if(isOpen(itemStack)) {	
    	if(!world.isRemote) {
    		if (!player.isPotionActive(MobEffects.NIGHT_VISION)||!(player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration()>250)) {
    			player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,300,0,false,false));}
    		if (!player.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
    			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,50,1,false,false));}
    		if(!player.isPotionActive(MobEffects.STRENGTH))  {
    			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,50,2,false,false));}
    		if(!player.isPotionActive(MobEffects.WATER_BREATHING))  {
    			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING,50,0,false,false));}
    		if(!player.isPotionActive(PotionLoader.potionfive))  {
    			player.addPotionEffect(new PotionEffect(PotionLoader.potionfive,50,0,false,true));}
    		}
		}
    if(isLord(itemStack)) {
    	player.capabilities.allowFlying=true;
    }
    if(!player.capabilities.isCreativeMode&&!isLord(itemStack)) {
    	player.capabilities.allowFlying=false;
    }
		
 }
 @Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {

	 if(isActive(stack)) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();
		if (slot == EntityEquipmentSlot.HEAD) {
			    multimap.putAll(super.getAttributeModifiers(slot, stack));
				multimap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(UUID.fromString("ebb91868-6aed-11e9-a923-1681be663d3e"), "max health motdifier",5,1));
				if(isOpen(stack)) {
					multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(UUID.fromString("05fd4064-6aee-11e9-a923-1681be663d3e"), "attack damage motdifier",5,1));
					multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(UUID.fromString("1cb6e486-6aee-11e9-a923-1681be663d3e"), "attack speed motdifier",5,1));
					multimap.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("1cb6e710-6aee-11e9-a923-1681be663d3e"), "movement speed motdifier",5,1));
				}    
				if(isLord(stack)) {
					multimap.put(SharedMonsterAttributes.ARMOR_TOUGHNESS.getName(), new AttributeModifier(UUID.fromString("1cb6e710-6aee-11e9-a923-1681be663d3e"), "movement speed motdifier",15,0));
					multimap.put(SharedMonsterAttributes.ARMOR.getName(), new AttributeModifier(UUID.fromString("1cb6e710-6aee-11e9-a923-1681be663d3e"), "movement speed motdifier",5,0));
				}
		}
		return multimap;}
		else {
			return super.getAttributeModifiers(slot, stack);
		}
	}
 @Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		if(getTagCompoundSafe(stack).hasKey("active")) {
		tooltip.add(TextFormatting.AQUA+I18n.format("item.blance_helmet:switch")+TextFormatting.GOLD+I18n.format("item.blance_helmet:switch_on"));
		}else {
			tooltip.add(TextFormatting.AQUA+I18n.format("item.blance_helmet:switch")+TextFormatting.GOLD+I18n.format("item.blance_helmet:switch_off"));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{	
		tooltip.add(getTagCompoundSafe(stack).hasKey("active")?(""+TextFormatting.BOLD+I18n.format("item.blance_helmet:changeMode",KeyLoader.modeSwitch.getKeyModifier()+"+"+Keyboard.getKeyName(KeyLoader.modeSwitch.getKeyCode()))):(I18n.format("item.blance_helmet:unicode_tooltips.2.desc")));}
		else {
		tooltip.add( I18n.format("item.blance_helmet:unicode_tooltips.1.desc"));}
		tooltip.add("" + TextFormatting.YELLOW +I18n.format("item.blance_helmet:unicode_tooltips.3.desc"));
	}
 public void ModeChange(ItemStack stack,EntityPlayer player) {
	 
		if(!isOpen(stack)) {
			if(!stack.getTagCompound().getBoolean("open")&&stack.getTagCompound().getBoolean("active")) {
				 getTagCompoundSafe(stack).setBoolean("open", true);
				 player.sendMessage(new TextComponentTranslation("messege.huaji.blancedHelmet.open",player.getName()));
			   }
			else {
			   player.sendMessage(new TextComponentTranslation("messege.huaji.blancedHelmet.failed"));
		   }
		}
		else {
			getTagCompoundSafe(stack).setBoolean("open", false);
		}
     }
 
}

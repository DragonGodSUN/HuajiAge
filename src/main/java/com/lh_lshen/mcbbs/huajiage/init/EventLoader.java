package com.lh_lshen.mcbbs.huajiage.init;


import java.awt.TextComponent;
import java.time.format.TextStyle;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.core.config.builder.api.Component;
import org.apache.logging.log4j.message.Message;

import com.google.common.eventbus.DeadEvent;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.init.events.EventKeyInput;
import com.lh_lshen.mcbbs.huajiage.init.events.EventOrga;
import com.lh_lshen.mcbbs.huajiage.init.events.EventRequiem;
import com.lh_lshen.mcbbs.huajiage.init.events.EventTimeStop;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaArmorBase;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaHair;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageLeftClick;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageOrgaRequiemClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageOrgaShot;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.TargetOrgaShotEffectMessageToClient;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.swing.event.MouseClicked;

public class EventLoader {
	 public static final EventBus EVENT_BUS = new EventBus();
	  public EventLoader()
	    {
	        MinecraftForge.EVENT_BUS.register(this);
	        MinecraftForge.EVENT_BUS.register(EventKeyInput.class);
	        MinecraftForge.EVENT_BUS.register(EventRequiem.class);
	        MinecraftForge.EVENT_BUS.register(EventOrga.class);
	        MinecraftForge.EVENT_BUS.register(EventTimeStop.class);
	        
	        EventLoader.EVENT_BUS.register(this);
	    }

   
	  @Cancelable
	    public static class SecondHitEvent extends net.minecraftforge.event.entity.item.ItemEvent
	    {
      public SecondHitEvent(EntityItem item)
	        {
	            super(item);

	        }
	    }
	  @SubscribeEvent
	    public void SecondHit(SecondHitEvent event)
	    {
	        if (!event.getEntityItem().world.isRemote)
	        { 
	        	int j;
	        	int i=event.getEntityItem().getItem().getMaxDamage()-(event.getEntityItem().getItem().getItemDamage())+2;
	        	for(j=0;j<i;j++) {
	        		World world=event.getEntityItem().world;
	            BlockPos pos = event.getEntityItem().getPosition();
	        	EntityPlayer player=event.getEntityItem().getEntityWorld().getPlayerEntityByName(NBTHelper.getTagCompoundSafe(event.getEntityItem().getItem()).getString("owner"));
	        	EntityLivingBase target=(EntityLivingBase)world.findNearestEntityWithinAABB(EntityLivingBase.class,event.getEntityItem().getEntityBoundingBox().grow(100),event.getEntityItem());
	        	 if(player!=null){
	            Entity s = new  EntitySecondFoil(world,player,target,player.getHorizontalFacing().getAxis()); 
	            s.setPosition(pos.getX(), pos.getY(), pos.getZ());
	            world.spawnEntity(s);}
	            }
	        	
	            event.getEntityItem().setDead();
	        	
	        }
	    }
	 
	@SubscribeEvent
	     public void onHuajiPotion(LivingUpdateEvent event)
	     {
		  EntityLivingBase entity = event.getEntityLiving();
		  PotionEffect effect = entity.getActivePotionEffect(PotionLoader.potionHuajiProtection); 
	            
          if (effect != null ){
	             { EntityLiving c=(EntityLiving) event.getEntityLiving();
	                    event.getEntityLiving().getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD,c.posX, c.posY,c.posZ, 0.5, 0.5, 0.5, 1);
	                
	             }
	           }
	     
           }

	}

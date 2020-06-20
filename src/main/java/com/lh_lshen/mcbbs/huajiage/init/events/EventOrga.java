package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaArmorBase;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandOrgaRequiem;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventOrga {
	@SubscribeEvent
    public static void onOrgaLivingUpdate(LivingUpdateEvent event) {
	EntityLivingBase entity=event.getEntityLiving(); 
	StandBase stand = StandUtil.getType(entity);
	if(stand!=null && stand == StandLoader.ORGA_REQUIEM) 
		{
		if(entity.isPotionActive(PotionLoader.potionFlowerHope) && entity.isPotionActive(PotionLoader.potionStand))
			{
				entity.removePotionEffect(PotionLoader.potionFlowerHope);
				entity.removePotionEffect(MobEffects.SLOWNESS);
			}
		
		}
	}
	@SubscribeEvent
	public static void onOrgaPlayerDeath(LivingDeathEvent event) {
	EntityLivingBase entity=event.getEntityLiving(); 
	StandBase stand = StandUtil.getType(entity);
	if( stand!=null && stand == StandLoader.ORGA_REQUIEM && entity.isPotionActive(PotionLoader.potionStand))
		{
				event.setCanceled(true);
				entity.setHealth(1f);
				HuajiSoundPlayer.playToNearbyClient(entity, SoundLoader.ORGA_REQUIEM_PROTECT, 1f);
		}
	}
	@SubscribeEvent
	public static void onOrgaPlayerHurt(LivingHurtEvent evt) {
	EntityLivingBase entity=evt.getEntityLiving(); 
	StandBase stand = StandUtil.getType(entity);
	Entity attacker=evt.getSource().getTrueSource();
	if( stand!=null && stand == StandLoader.ORGA_REQUIEM && entity.isPotionActive(PotionLoader.potionStand))
		{
		if(attacker!=null&&attacker!=entity)
				{
					((EntityLivingBase)attacker).attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.REQUIEM_BACK,entity),evt.getAmount()*2);
					if(entity.getHealth()<2) 
						{		
							Vec3d targetPosition = entity.getPositionVector();  	 
							HuajiAgeNetWorkHandler.sendToNearby(entity.world, entity, new MessageParticleGenerator(targetPosition, EnumParticleTypes.FIREWORKS_SPARK, 200, 2, 1));
							if(!((EntityLivingBase)attacker).isPotionActive(PotionLoader.potionOrgaTarget)) 
								{
									((EntityLivingBase)attacker).addPotionEffect(new PotionEffect(PotionLoader.potionOrgaTarget,100));  
								}
						  }
					}
			}}
}

package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaArmorBase;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRequiem {
	@SubscribeEvent
	public static void onRequiemHit(AttackEntityEvent evt) {
	EntityPlayer player = evt.getEntityPlayer();
	Entity hit =evt.getTarget();
		if (player.getHeldItemMainhand().isEmpty()&&player.isPotionActive(PotionLoader.potionRequiem)&&hit instanceof EntityLivingBase) {
		player.playSound(SoundLoader.ORGA_REQUIEM_HIT, 1f, 1f);
		((EntityLivingBase) hit).attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE,player),5f);
		((EntityLivingBase) hit).getEntityData().setInteger(HuajiConstant.Tags.REQUIEM, 60);
		}
	}
  @SubscribeEvent
	public static void requiemTarget(LivingUpdateEvent evt){
	EntityLivingBase target=evt.getEntityLiving();
	EntityPlayer player=target.world.getClosestPlayerToEntity(target, 100);
		if(target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM)<=0) {
		return;
		}
		if(target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM)>0) {
		target.getEntityData().setInteger(HuajiConstant.Tags.REQUIEM, target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM) - 1);
			if(!(target instanceof EntityPlayer)) {
				if(target.ticksExisted %5==0) {
				target.attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE,player),12f);
				}
			}else {
				if(target.ticksExisted %10==0) {
				target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE),12f);
				}
			}
		}
	}
  @SubscribeEvent
	public static void onRequiem(LivingUpdateEvent event)
	{
	EntityLivingBase entity=event.getEntityLiving(); 
	if(entity instanceof EntityPlayer) {
		if(entity.getItemStackFromSlot(EntityEquipmentSlot .HEAD).getItem() instanceof ItemOrgaArmorBase&&
		entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
		entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase &&
		entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase) {
			if(entity.isPotionActive(PotionLoader.potionRequiem)) {
				if(((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))) {
				}
				else {
					entity.removePotionEffect(PotionLoader.potionRequiem);
					if(entity.world.isRemote) {
						Minecraft.getMinecraft().getSoundHandler().stopSounds();
						}
					}
				}
			}
		}
	}
  @SubscribeEvent
	public static void RequiemHit(LivingUpdateEvent evt) {
	EntityLivingBase target =evt.getEntityLiving(); 
	EntityPlayer player=target.world.getClosestPlayerToEntity(target, 100);
	if(target.isPotionActive(PotionLoader.potionRequiemTarget)) {
		if(!(target instanceof EntityPlayer)&&!target.isPotionActive(PotionLoader.potionFlowerHope)&&!target.isPotionActive(PotionLoader.potionRequiem)) {
			if(target.ticksExisted %5==0) {
				target.attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE,player),12f);
				}
			}
			else {
			if(target.ticksExisted %10==0) {
				target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE),12f);
				}	
			}			
		}	
	} 
}

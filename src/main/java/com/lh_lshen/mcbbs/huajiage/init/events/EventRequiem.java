package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageRequiem;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaArmorBase;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
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
		if (player.isPotionActive(PotionLoader.potionRequiem)&&hit instanceof EntityLivingBase) {
		player.playSound(SoundLoader.ORGA_REQUIEM_HIT, 1f, 1f);
		((EntityLivingBase) hit).attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE,player),5f);
		((EntityLivingBase) hit).getEntityData().setInteger(HuajiConstant.Tags.REQUIEM, 60);
		((EntityLivingBase) hit).getEntityData().setString(HuajiConstant.Tags.PLAYER_NAME, player.getName());
		}
	}
  @SubscribeEvent
	public static void requiemTarget(LivingUpdateEvent evt){
	EntityLivingBase target=evt.getEntityLiving();
	String name = NBTHelper.getEntityString(target, HuajiConstant.Tags.PLAYER_NAME);
	EntityPlayer player=target.world.getPlayerEntityByName(name);
	if(player!=null) {
		if(target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM)<=0) {
			return;
			}
			if(target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM)>0) {
			target.getEntityData().setInteger(HuajiConstant.Tags.REQUIEM, target.getEntityData().getInteger(HuajiConstant.Tags.REQUIEM) - 1);
				if(!(target instanceof EntityPlayer)) {
					if(target.ticksExisted %5==0) {
					target.attackEntityFrom(new DamageRequiem(player),player.getMaxHealth()/3);
					}
				}else {
					if(target.ticksExisted %10==0) {
					if(target instanceof EntityDragon) {
						EntityDragon d = ((EntityDragon) target);
						d.attackEntityFromPart(d.dragonPartHead, new DamageRequiem(player),12f+player.getMaxHealth());
						}else {
						target.attackEntityFrom(new DamageRequiem(player),12f+player.getMaxHealth());
						}
					}
				}
			}
		}
	}
  @SubscribeEvent
	public static void onRequiem(LivingUpdateEvent event)
	{
	EntityLivingBase entity=event.getEntityLiving(); 
	StandBase stand = StandUtil.getType(entity);
	if(entity instanceof EntityPlayer) {
		if(entity.getItemStackFromSlot(EntityEquipmentSlot .HEAD).getItem() instanceof ItemOrgaArmorBase&&
		entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
		entity.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase &&
		entity.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase ||
		stand!=null&&stand.getName().equals(StandLoader.ORGA_REQUIEM.getName())) {
			if(entity.isPotionActive(PotionLoader.potionRequiem)) {
				if(((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem)) ||
						entity.isPotionActive(PotionLoader.potionStand)) {
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
	String name = NBTHelper.getEntityString(target, HuajiConstant.Tags.PLAYER_NAME);
	EntityPlayer player=target.world.getPlayerEntityByName(name);
	if(target.isPotionActive(PotionLoader.potionRequiemTarget)) {
		if(!(target instanceof EntityPlayer)&&!target.isPotionActive(PotionLoader.potionFlowerHope)&&!target.isPotionActive(PotionLoader.potionRequiem)) {
			if(target.ticksExisted %5==0) {
				if(player!=null) {
				target.attackEntityFrom(new DamageRequiem(player),12f+player.getMaxHealth());
				}else {
				target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE),32f);
				}
			}else {
				if(target.ticksExisted %10==0) {
					if(player!=null) {
						target.attackEntityFrom(new DamageRequiem(player),12f+player.getMaxHealth());
						}else {
						target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.REQUIEM_DAMAGE),32f);
						}
					}	
				}			
			}
		}
	} 
}

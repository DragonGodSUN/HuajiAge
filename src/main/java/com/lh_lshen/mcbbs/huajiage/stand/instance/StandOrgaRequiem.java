package com.lh_lshen.mcbbs.huajiage.stand.instance;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;

import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateOrgaRequiemDefault;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;

public class StandOrgaRequiem extends StandBase {
	
	public StandOrgaRequiem() {
		super();
	}
	public StandOrgaRequiem(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int charge,
			String texPath,String localName, boolean displayHand) {
			super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
			initState(new StateOrgaRequiemDefault(name,CapabilityExposedData.States.DEFAULT.getName(),isHandDisplay(),true));
//			this.addState(CapabilityExposedData.States.PROTECT.getName());
	}
	@Override
	public StandRes getBindingRes() {
		return StandResLoader.ORGA_REQUIEM_RES;
	}
	@Override
	public void doStandPower(EntityLivingBase user) {
		super.doStandPower(user);
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		IExposedData data = user.getCapability(CapabilityLoader.EXPOSED_DATA, null);
		double d = MathHelper.nextDouble(new Random(), 0, 1);
		if(data!=null) {
		data.setTrigger(true);
		user.dropItem(ItemLoader.orgaHairKnife, 16);
		if(d<0.3) {
		user.dropItem(ItemLoader.blackCar, 1);
		}
		user.addPotionEffect(new PotionEffect(PotionLoader.potionRequiem,600));
		user.addPotionEffect(new PotionEffect(PotionLoader.potionStand,600));
		user.addPotionEffect(new PotionEffect(MobEffects.SPEED,600,2));
		if(user.isPotionActive(MobEffects.SLOWNESS)) {
			user.removePotionEffect(MobEffects.SLOWNESS);
			}
		}
	}

	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {

//			world.playSound(user.getPositionVector().x,user.getPositionVector().y,user.getPositionVector().z, SoundLoader.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH, SoundCategory.PLAYERS, 5f, 1f,true);
	
	}

}

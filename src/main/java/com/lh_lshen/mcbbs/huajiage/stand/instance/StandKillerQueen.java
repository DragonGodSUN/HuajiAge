package com.lh_lshen.mcbbs.huajiage.stand.instance;

import java.util.List;
import java.util.Random;

import com.jcraft.jorbis.Block;
import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.api.IStandRes;
import com.lh_lshen.mcbbs.huajiage.capability.*;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySheerHeartAttack;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandCapabilityServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class StandKillerQueen extends StandBase {
	
	public StandKillerQueen() {
		super();
	}
	public StandKillerQueen(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int charge,
			String texPath,String localName, boolean displayHand) {
			super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
			addState(CapabilityExposedData.States.PUNCH.getName());
	}
	@Override
	public StandRes getBindingRes() {
		return StandResLoader.KILLER_QUEEN_RES;
	}
	@Override
	public void doStandPower(EntityLivingBase user) {
		StandBase type = StandUtil.getType(user);
		int stage = StandUtil.getStandStage(user);
		boolean isPunch = CapabilityExposedData.States.PUNCH.getName().equals(StandUtil.getStandState(user));
		if(type == null) {
			return;
		}else if(isPunch){
			StandPowerHelper.rangePunchAttack(user,type,stage,45,2+stage);
		}else{
			StandChargeHandler chargeHandler = user.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
			if( null !=chargeHandler )
			{
				chargeHandler.charge(getCharge()/3);
			}
		}
		
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
		EntitySheerHeartAttack attack = new EntitySheerHeartAttack(user.world);
		Vec3d vec = user.getLookVec();
//		int number = user.world.getEntitiesWithinAABB(EntitySheerHeartAttack.class, user.)
		attack.setTamedBy((EntityPlayer) user);
		attack.setDamage(15f);
		attack.motionX=vec.x*0.5;
		attack.motionY=vec.y*0.5;
		attack.motionZ=vec.z*0.5;
		attack.setPosition(user.posX, user.posY+0.5f, user.posZ);
		user.world.spawnEntity(attack);
		if(user instanceof EntityPlayer) {
			ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user,StandLoader.KILLER_QUEEN.getName()));
			}
		}
	}

	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {
			world.playSound(user.getPositionVector().x,user.getPositionVector().y,user.getPositionVector().z, SoundLoader.STAND_KILLER_QUEEN_TRIGGER, SoundCategory.PLAYERS, 2f, 1f,true);
	}

}

package com.lh_lshen.mcbbs.huajiage.stand.entity;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityStandBase extends Entity implements IEntityAdditionalSpawnData {
	private Minecraft mc;
	
	private static final String TAG_TYPE = "type";
	private static final String TAG_USER = "user";
	private static final String TAG_USER_NAME = "userName";

	private static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityStandBase.class,
			DataSerializers.STRING);
	private static final DataParameter<String> USER = EntityDataManager.createKey(EntityStandBase.class,
			DataSerializers.STRING);
	private static final DataParameter<String> USERNAME = EntityDataManager.createKey(EntityStandBase.class,
			DataSerializers.STRING);
	
	public EntityStandBase(World worldIn) {
		super(worldIn);
		}
	
	public EntityStandBase(World worldIn,EntityLivingBase user,EnumStandtype stand) {
		super(worldIn);
		setUser(user.getUniqueID().toString());
		setType(stand.getName());
		if(user instanceof EntityPlayer) {
		setUserName(user.getName());
		}else {
		setUserName("steve");
		}
	}

	@Override
	protected void entityInit() {
		dataManager.register(TYPE, EnumStandtype.THE_WORLD.getName());
		dataManager.register(USER, "nouser");
		dataManager.register(USERNAME, "steve");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound cmp) {
		setType(cmp.getString(TAG_TYPE));
		setUser(cmp.getString(TAG_USER));
		setUserName(cmp.getString(TAG_USER_NAME));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound cmp) {
		cmp.setString(TAG_TYPE, getType());
		cmp.setString(TAG_USER, getUserId());
		cmp.setString(TAG_USER_NAME, getUserName());
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase user = getUser();
		if(user !=null) {
		this.posX = user.posX;
		this.posY = user.posY;
		this.posZ = user.posZ;
		}else {
			System.out.println("Oh My God!!!");
		}
		if(user==null||!user.isPotionActive(PotionLoader.potionStand)) {
			this.setDead();
		}
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer) {
		EnumStandtype standType = getStandType();
		ByteBufUtils.writeUTF8String(buffer, standType!=null?standType.getName():EnumStandtype.EMPTY);
	}
	
	@Override
	public void readSpawnData(ByteBuf additionalData) {
		EnumStandtype stand = EnumStandtype.getType(ByteBufUtils.readUTF8String(additionalData));
		StandClientUtil.standUpSound(mc.getMinecraft(),this, stand.getName());
	}
	
//	===============================Settings======================================
	
	private EnumStandtype getStandType() {
		EnumStandtype stand = EnumStandtype.getType(dataManager.get(TYPE));
		return stand;
	}
	
	private String getType() {
		if(getStandType() != null) {
			return getStandType().getName();
		}
		return EnumStandtype.EMPTY;
	}
	
	private EntityLivingBase getUser() {
		EntityLivingBase user = this.world.getPlayerEntityByName(getUserName());
		if(user==null&&!getUserId().isEmpty()&&!getUserId().equals("nouser")) {
			user = this.world.getPlayerEntityByUUID(UUID.fromString(getUserId()));
		}
		return user;
	}
	
	private String getUserId() {
		return dataManager.get(USER);
	}
	
	private String getUserName() {
		return dataManager.get(USERNAME);
	}
	
	public void setUserName(String name) {
		this.dataManager.set(USERNAME, name);;
	}
	
	public void setUser(String uuid) {
		EntityLivingBase user = world.getPlayerEntityByUUID(UUID.fromString(uuid));
		if(user!=null) {
			dataManager.set(USER, user.getUniqueID().toString());
		}else {
			dataManager.set(USER, "nouser");
		}
	}
	
	public void setType(String type) {
		dataManager.set(TYPE, type);
	}


}

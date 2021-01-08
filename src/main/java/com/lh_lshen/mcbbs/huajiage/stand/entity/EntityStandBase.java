package com.lh_lshen.mcbbs.huajiage.stand.entity;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.*;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.UUID;

/**
 * 部分代码源自原版与酒石酸女仆模组
 * 地址：com.github.tartaricacid.touhoulittlemaid.entity.item.EntityMarisaBroom
 */
public class EntityStandBase extends EntityHorse implements IEntityAdditionalSpawnData {
	private Minecraft mc;
	private String noUser = "8fdd0799-16c2-49d9-bdea-e75a07b9ec04";
//	private boolean keyForward = false;
//	private boolean keyBack = false;
//	private boolean keyLeft = false;
//	private boolean keyRight = false;
//	private boolean keySpace = false;
	
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
		this.setSize(0.1f,0.1f);
		}

	public EntityStandBase(World worldIn,EntityLivingBase user,StandBase stand) {
		super(worldIn);
		setUser(user.getUniqueID().toString());
		setType(stand.getName());
		if(user instanceof EntityPlayer) {
		setUserName(user.getName());
		}else {
		setUserName("steve");
		}
	}

//	public EntityStandBase(World worldIn,EntityLivingBase user,StandBase stand,float bx,float by,float bz) {
//		this(worldIn,user,stand);
//		setEntityGrowScale(bx,by,bz);
////		System.out.println(this.getCollisionBorderSize());
//	}


//	@Override
//	public AxisAlignedBB getEntityBoundingBox() {
//		if(!getUserId().isEmpty() && getStandType()!=null){
//			StandStateBase state = StandStates.getStandState(getStandType().getName(), StandUtil.getStandState(getUser()));
//			if(state.hasExtraData(EnumStandTag.StateTags.RIDE.getName())){
//				float d0 = 1.3f;
//				return new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double)this.height, this.posZ + d0);
//			}
//		}
//		return super.getEntityBoundingBox();
//	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(TYPE, StandLoader.THE_WORLD.getName());
		dataManager.register(USER, noUser);
		dataManager.register(USERNAME, "steve");
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setType(cmp.getString(TAG_TYPE));
		setUser(cmp.getString(TAG_USER));
		setUserName(cmp.getString(TAG_USER_NAME));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setString(TAG_TYPE, getType());
		cmp.setString(TAG_USER, getUserId());
		cmp.setString(TAG_USER_NAME, getUserName());
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
		this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(0.9);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		return true;
	}

	@Override
	public boolean hasNoGravity() {
		String type = getType();
		if(type!=null){
			StandBase standBase = StandLoader.getStand(type);
			if(standBase!=null){
				StandCustomInfo info = StandResourceLoader.CUSTOM_STAND_SERVER.getOrDefault(type,null);
				return info==null||!info.hasGravity();
			}
		}
		return super.hasNoGravity();
	}


	@Override
	public Iterable<ItemStack> getArmorInventoryList() {
		return Collections.emptyList();
	}

	@Override
	public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return false;
	}

	@Override
	public boolean canMateWith(@Nonnull EntityAnimal otherAnimal) {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase user = getUser();
		
		if( user==null || user.isDead ) {
			this.setDead();
		}
		if(user !=null) {
			IExposedData data = user.getCapability(CapabilityLoader.EXPOSED_DATA, null);
			if(data == null || !data.isTriggered()) {
				this.setDead();
			}
			if(!this.isBeingRidden()){
			this.posX = user.posX;
			this.posY = user.posY;
			this.posZ = user.posZ;
			}
		}

		
	}

	@Override
	protected void initEntityAI() {

	}

	@Override
	protected void playHurtSound(DamageSource source) {

	}

	@Override
	public void playLivingSound() {

	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {

	}

	@Override
	protected void playEquipSound(ItemStack stack) {

	}

	@Override
	protected void playGallopSound(SoundType p_190680_1_) {

	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	protected SoundEvent getAngrySound() {
		return null;
	}

	@Override
	public boolean isHorseSaddled() {
		return true;
	}

	//	@Override
//	public void travel(float strafe,float vertical, float forward) {
//		Entity entity = getControllingPassenger();
//		if (entity instanceof EntityPlayer && this.isBeingRidden()) {
//			EntityPlayer player = (EntityPlayer) entity;
//			this.rotationYaw = player.rotationYaw;
//			this.prevRotationYaw = this.rotationYaw;
//			this.rotationPitch = player.rotationPitch * 0.5F;
//			this.setRotation(this.rotationYaw, this.rotationPitch);
//			this.renderYawOffset = this.rotationYaw;
//			this.rotationYawHead = this.renderYawOffset;
//
//			if (world.isRemote) {
//				keyForward = keyForward();
//				keyBack = keyBack();
//				keyLeft = keyLeft();
//				keyRight = keyRight();
//				keySpace = keySpace();
//			}
//			strafe = keyLeft ? 0.5f : (keyRight ? -0.5f : 0);
//			vertical = keyForward ? -(player.rotationPitch - 10) / 45 : 0;
//			forward = keyForward ? 3 : (keyBack ? -0.5f : 0);
//			this.moveRelative(strafe, vertical, forward, 0.02f);
//			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
//		}
//	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	public boolean shouldRiderFaceForward(EntityPlayer player) {
		return true;
	}

	@Override
	public EnumHandSide getPrimaryHand() {
		return EnumHandSide.LEFT;
	}

	@Nullable
	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	@Override
	public double getMountedYOffset() {
		return 0.45d;
	}

	@Override
	public boolean attackable() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public void knockBack(@Nonnull Entity entityIn, float strength, double xRatio, double zRatio) {
	}

//	/**
//	 * 来自MC原版代码
//	 * @param strafe
//	 * @param up
//	 * @param forward
//	 * @param friction
//	 */
//	@Override
//	public void moveRelative(float strafe, float up, float forward, float friction)
//	{
//		float f = strafe * strafe + up * up + forward * forward;
//		if (f >= 1.0E-4F)
//		{
//			f = MathHelper.sqrt(f);
//			if (f < 1.0F) f = 1.0F;
//			f = friction / f;
//			strafe = strafe * f;
//			up = up * f;
//			forward = forward * f;
//			if(this.isInWater() || this.isInLava())
//			{
//				strafe = strafe * 0.5f;
//				forward = forward * 0.5f;
//			}
//			float f1 = MathHelper.sin(this.rotationYaw * 0.017453292F);
//			float f2 = MathHelper.cos(this.rotationYaw * 0.017453292F);
//			Vec3d vec3d = getUser().getLookVec();
//			if(Math.abs(motionX)<=1.5f){
//			this.motionX += (double)(strafe * f2 - forward * f1);
//				if(keySpace) {
//					this.motionX += vec3d.x;
//				}
//			}
//			if(Math.abs(motionZ)<=1.5f){
//			this.motionZ += (double)(forward * f2 + strafe * f1);
//				if(keySpace){
//					this.motionZ += vec3d.z;
//				}
//			}
//			if(Math.abs(motionY)<=1.5f) {
//				this.motionY += up;
//				if (keySpace) {
//					this.motionY += vec3d.y;
//				}
//			}
//		}
//	}

	@Override
	public void writeSpawnData(ByteBuf buffer){
		StandBase standType = getStandType();
		EntityLivingBase user = getUser();
		ByteBufUtils.writeUTF8String(buffer, standType!=null?standType.getName():StandLoader.EMPTY);
		ByteBufUtils.writeUTF8String(buffer, user!=null?user.getName():"steve");
	}
	
	@Override
	public void readSpawnData(ByteBuf additionalData) {
		StandBase stand = StandLoader.getStand(ByteBufUtils.readUTF8String(additionalData));
		EntityLivingBase user = this.world.getPlayerEntityByName(ByteBufUtils.readUTF8String(additionalData));
		if(user!=null){
		StandClientUtil.standUpSound(mc.getMinecraft(),this, user);
		float d0 = 1.3f;
		String state = StandUtil.getStandState(user);
		StandStateBase standStateBase = StandStates.getStandState(stand.getName(),state);
		if(standStateBase!=null&&standStateBase.hasExtraData(EnumStandTag.StateTags.RIDE.getName())){
//			setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double)this.height, this.posZ + d0));
			setSize(1.3f,0.2f);
			}
		}
	}

	
//	===============================Settings======================================
	
	private StandBase getStandType() {
		StandBase stand = StandLoader.getStand(dataManager.get(TYPE));
		return stand;
	}
	
	private String getType() {
		if(getStandType() != null) {
			return getStandType().getName();
		}
		return StandLoader.EMPTY;
	}
	
	public EntityLivingBase getUser() {
		EntityLivingBase user = this.world.getPlayerEntityByName(getUserName());
		if(user==null&&!getUserId().isEmpty()&&!getUserId().equals("8fdd0799-16c2-49d9-bdea-e75a07b9ec04")) {
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
			dataManager.set(USER, noUser);
		}
	}
	
	public void setType(String type) {
		dataManager.set(TYPE, type);
	}

	public void setEntitySize(float w, float h){
		this.setSize(w,h);
	}

//	@SideOnly(Side.CLIENT)
//	private static boolean keyForward() {
//		return Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown();
//	}
//
//	@SideOnly(Side.CLIENT)
//	private static boolean keyBack() {
//		return Minecraft.getMinecraft().gameSettings.keyBindBack.isKeyDown();
//	}
//
//	@SideOnly(Side.CLIENT)
//	private static boolean keyLeft() {
//		return Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown();
//	}
//
//	@SideOnly(Side.CLIENT)
//	private static boolean keyRight() {
//		return Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown();
//	}
//
//	@SideOnly(Side.CLIENT)
//	private static boolean keySpace() {
//		return Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
//	}

}

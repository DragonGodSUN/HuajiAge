package com.lh_lshen.mcbbs.huajiage.item;

import com.github.tartaricacid.touhoulittlemaid.block.BlockGarageKit;
import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.github.tartaricacid.touhoulittlemaid.init.MaidItems;
import com.github.tartaricacid.touhoulittlemaid.util.ItemDropUtil;
import com.github.tartaricacid.touhoulittlemaid.util.MaidRayTraceHelper;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemYinYangBall extends Item {

	public ItemYinYangBall()
	{
		super();
		this.setMaxStackSize(1);
		if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
		this.setCreativeTab(MaidItems.MAIN_TABS);
		}
		this.addPropertyOverride(new ResourceLocation("data"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null) {
					return 0F;
				} else {
					return isBallFilled(stack)?1f:0f;
				}
			}
		});
	}

	@Override
	@Optional.Method(modid = "touhou_little_maid")
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.world.isRemote ) {
			if(stack.getItem() == this && target.isEntityAlive() && target instanceof EntityMaid && ( !((EntityMaid) target).isTamed() || ((EntityMaid) target).isOwner(playerIn) )){
				onItemRightClick(playerIn.world, playerIn, hand);
				return true;
			}
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	@Override
	@Optional.Method(modid = "touhou_little_maid")
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	ItemStack stack = playerIn.getHeldItem(handIn);
	if(stack.getItem() == this){
	 	java.util.Optional<EntityMaid> result = MaidRayTraceHelper.rayTraceMaid(playerIn, 8);
		 if (result.isPresent()) {
			 EntityMaid maid = result.get();
			 if (!worldIn.isRemote && maid.isEntityAlive() && maid.isOwner(playerIn)) {
				 NBTTagCompound compound = new NBTTagCompound();
				 maid.writeEntityToNBT(compound);
				 if(maid.getOwner()!=null){
				 setMaidData(stack,maid.getModelId(),compound,maid.getOwner());
				 }else{
				 setMaidData(stack,maid.getModelId(),compound,maid.getOwnerId().toString());
				 }
				 maid.setDead();
				 playerIn.sendMessage(new TextComponentTranslation("message.huajiage.maid_ball.load",new TextComponentTranslation(maid.getName()).getUnformattedComponentText()));
			 }
			 maid.spawnExplosionParticle();
			 playerIn.playSound(SoundEvents.ITEM_BOTTLE_FILL,1f,1f);
		 }
	}
	return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}

	@Override
	@Optional.Method(modid = "touhou_little_maid")
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		IExposedData data = StandUtil.getStandData(player);
		List<String> load_list = Arrays.asList("message.huajiage.maid_ball.stand_load_1",
											   "message.huajiage.maid_ball.stand_load_2",
											   "message.huajiage.maid_ball.stand_load_3",
											   "message.huajiage.maid_ball.stand_load_4",
											   "message.huajiage.maid_ball.stand_load_5",
											   "message.huajiage.maid_ball.stand_load_6",
											   "message.huajiage.maid_ball.stand_load_7"
				);


		if(data ==null){
			return super.onItemUse(player,worldIn,pos,hand,facing,hitX,hitY,hitZ);
		}

		if (player.isSneaking() && stack.getItem() == this) {
			if (isModelLoad(stack) && isDataLoad(stack)) {
				if (isOwner(stack, player)) {
					if (StandUtil.getType(player) != null) {
						player.dropItem(ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc), data.getStand(), data.getStage(), data.getModel()), true);
					}
					data.setStand(StandLoader.MAID.getName());
					data.setStage(1);
					data.setModel(getMaidModel(stack) + "_default");
					data.setTrigger(false);
					dropMaidItems(stack, player, pos.getX(), pos.getY(), pos.getZ());
					reset(stack);
					stack.shrink(1);
					player.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.5f, 1f);
					player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1f, 1f);

					if (worldIn.isRemote) {
						int d = (int) MathHelper.nextFloat(new Random(),0,load_list.size());
						player.sendMessage(new TextComponentTranslation(load_list.get(d)));
					}
				} else {
					player.sendMessage(new TextComponentTranslation("message.huajiage.maid_ball.is_no_owner"));
				}
			} else {
				if (worldIn.isRemote) {
					player.sendMessage(new TextComponentTranslation("message.huajiage.maid_ball.fail"));
				}
			}
		}

		if (!player.isSneaking() && stack.getItem() == this) {
			if(isModelLoad(stack) && isDataLoad(stack)) {
				if (isOwner(stack,player)) {
					EntityMaid maid = new EntityMaid(player.world);
					if(!player.world.isRemote){
						maid.readEntityFromNBT(getMaidTag(stack));
						maid.setPosition(pos.getX(),pos.getY()+1.5f,pos.getZ());
						player.world.spawnEntity(maid);
					}
					maid.spawnExplosionParticle();
					reset(stack);
					player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1f, 1f);
				}else{
					player.sendMessage(new TextComponentTranslation("message.huajiage.maid_ball.is_no_owner"));
				}
			}else{
				if(player.world.isRemote) {
				player.sendMessage(new TextComponentTranslation("message.huajiage.maid_ball.fail"));
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("item.yin_yang_ball:tooltips.1"));
		if(org.lwjgl.input.Keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_LSHIFT) || org.lwjgl.input.Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			StringBuilder builder = new StringBuilder();
			CustomResourceLoader.STAND_MODEL.getInfo(getMaidModel(stack)+"_default").ifPresent((info)->{
				builder.append(info.getName());
				builder.deleteCharAt(Math.max(builder.indexOf("{"), 0));
				builder.deleteCharAt(Math.min(builder.indexOf("}"), builder.length()-1));
			});
			tooltip.add(I18n.format("item.yin_yang_ball:tooltips.2",
					isModelLoad(stack)?new TextComponentTranslation(builder.toString()).getUnformattedComponentText():new TextComponentTranslation("item.yin_yang_ball:empty").getUnformattedComponentText()));
			tooltip.add(I18n.format("item.yin_yang_ball:tooltips.3",
					hasOwner(stack)?new TextComponentTranslation(getMaidOwnerName(stack)).getUnformattedComponentText():new TextComponentTranslation("item.yin_yang_ball:empty").getUnformattedComponentText()));
		}

	}

	@Optional.Method(modid = "touhou_little_maid")
    public void dropMaidItems(ItemStack stack, EntityLivingBase entity ,float x, float y, float z){
		EntityMaid maid = new EntityMaid(entity.world);
		if(!entity.world.isRemote){
			maid.readEntityFromNBT(getMaidTag(stack));
			maid.setPosition(x,y+1.5f,z);
			entity.world.spawnEntity(maid);
			dropMaidItems(maid);
			maid.setDead();
		}
		maid.spawnExplosionParticle();
	 }


	@Optional.Method(modid = "touhou_little_maid")
	public void dropMaidItems(EntityMaid maid){
		IItemHandler itemHandler = maid.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemDropUtil.dropItemHandlerItems(itemHandler, maid.world, maid.getPositionVector());

		NBTTagCompound entityTag = new NBTTagCompound();
		maid.writeEntityToNBT(entityTag);
		entityTag.removeTag("ArmorItems");
		entityTag.removeTag("HandItems");
		entityTag.removeTag(EntityMaid.NBT.MAID_INVENTORY.getName());
		entityTag.removeTag(EntityMaid.NBT.BAUBLE_INVENTORY.getName());
		ItemStack stackKit = BlockGarageKit.getItemStackWithData("touhou_little_maid:entity.passive.maid",
				maid.getModelId(), entityTag);
		EntityItem entityItem = maid.entityDropItem(stackKit, 0);
		entityItem.setEntityInvulnerable(true);
	}

	public boolean isBallFilled(ItemStack stack){
		return isModelLoad(stack) && isDataLoad(stack);
	}

	public NBTTagCompound getMaidTag(ItemStack stack){
		return NBTHelper.getTagCompoundSafe(stack).getCompoundTag(NBT.MAID_DATA.getName());
	}

	public void setMaidData(ItemStack stack, String modelId, NBTTagCompound entityData, String ownerID){
		NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
		if (modelId != null) {
			data.setString(NBT.MAID_MODEL.getName(), modelId);
		}
		if(ownerID != null){
			data.setString(NBT.MAID_OWNER.getName(),ownerID);
			data.setString(NBT.MAID_OWNER_NAME.getName(),"empty");
		}
		data.setTag(NBT.MAID_DATA.getName(), entityData);
		stack.setTagCompound(data);
	}

	public void setMaidData(ItemStack stack, String modelId, NBTTagCompound entityData, EntityLivingBase owner){
		NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
		if (modelId != null) {
			data.setString(NBT.MAID_MODEL.getName(), modelId);
		}
		if(owner != null){
			data.setString(NBT.MAID_OWNER.getName(),owner.getUniqueID().toString());
			data.setString(NBT.MAID_OWNER_NAME.getName(),owner.getName());
		}
		data.setTag(NBT.MAID_DATA.getName(), entityData);
		stack.setTagCompound(data);
	}


	public String getMaidOwner(ItemStack stack){
		NBTTagCompound compound = NBTHelper.getTagCompoundSafe(stack);
		String uuid = compound.getString(NBT.MAID_OWNER.getName());
		if(!uuid.isEmpty()){
			return uuid;
		}
		setMaidOwner(stack,"empty");
		return "empty";
	}

	public String getMaidOwnerName(ItemStack stack){
		NBTTagCompound compound = NBTHelper.getTagCompoundSafe(stack);
		String name = compound.getString(NBT.MAID_OWNER_NAME.getName());
		if(!name.isEmpty()){
			return name;
		}
		setMaidOwnerName(stack,"empty");
		return "empty";
	}

	public void setMaidOwnerName(ItemStack stack, String name){
		NBTHelper.getTagCompoundSafe(stack).setString(NBT.MAID_OWNER_NAME.getName(),name);
	}

	public void setMaidOwner(ItemStack stack, String uuid){
		NBTHelper.getTagCompoundSafe(stack).setString(NBT.MAID_OWNER.getName(),uuid);
	}

	public void setMaidModel(ItemStack stack, String model){
		NBTHelper.getTagCompoundSafe(stack).setString(NBT.MAID_MODEL.getName(),model);
	}

	public String getMaidModel(ItemStack stack){
		NBTTagCompound compound = NBTHelper.getTagCompoundSafe(stack);
		if(compound.hasKey(NBT.MAID_MODEL.getName()) && !compound.getString(NBT.MAID_MODEL.getName()).isEmpty()){
			return compound.getString(NBT.MAID_MODEL.getName());
		}
		setMaidModel(stack, "empty");
		return "empty";
	}

	public boolean isModelLoad(ItemStack stack){
		return !getMaidModel(stack).equals("empty");
	}

	public boolean isDataLoad(ItemStack stack){
		return !getMaidTag(stack).isEmpty();
	}

	public boolean hasOwner(ItemStack stack){
		return !getMaidOwner(stack).equals("empty");
	}

	public boolean isOwner(ItemStack stack, EntityLivingBase entity){
		String uuid = getMaidOwner(stack);
		String uuidU = entity.getUniqueID().toString();
		return uuid.equals(uuidU);
	}

	public void reset(ItemStack stack){
		setMaidData(stack,"empty",new NBTTagCompound(),"empty");
	}

	 public enum NBT{
		 MAID_MODEL("model"),
		 MAID_DATA("data"),
		 MAID_OWNER_NAME("owner_name"),
		 MAID_OWNER("owner")
		 ;
		 NBT(String name) {
			 this.name = name;
		 }
		 private String name;

		 public String getName() {
			 return name;
		 }
	 }
	
}

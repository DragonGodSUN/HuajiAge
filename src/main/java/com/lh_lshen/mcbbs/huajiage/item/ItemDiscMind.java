package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class ItemDiscMind extends Item {
	public ItemDiscMind()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.huajiage.disc_mind.tooltips.1"
				,TextFormatting.GRAY +getOwnerName(stack)));
		tooltip.add(I18n.format("item.huajiage.disc_mind.tooltips.2"
				,TextFormatting.GRAY +getOwnerUUID(stack)));
		tooltip.add(I18n.format("item.huajiage.disc_mind.tooltips.3"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (stack.getItem() instanceof ItemDiscMind){
				String name = target.getName();
				String uuid = target.getUniqueID().toString();
				if (name.equals(getOwnerName(stack)) && uuid.equals(getOwnerUUID(stack))){
					boolean isDeprived = NBTHelper.getEntityBoolean(target,"disc_deprive");
					if (isDeprived){
						NBTHelper.setEntityBoolean(target,"disc_deprive",false);
						if (!playerIn.world.isRemote){
							stack.shrink(1);
						}
						if (playerIn.world.isRemote){
							playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
							playerIn.world.playEvent(2005,target.getPosition().add(0,1,0),0);
						}
					}else {
						if (playerIn.world.isRemote){
							playerIn.sendMessage(new TextComponentTranslation("message.huajiage.disc_mind.mind_recover"));
						}
					}
			}
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (stack.getItem() instanceof ItemDiscMind){
			String name = playerIn.getName();
			String uuid = playerIn.getUniqueID().toString();
			if (name.equals(getOwnerName(stack)) && uuid.equals(getOwnerUUID(stack))){
				boolean isDeprived = NBTHelper.getEntityBoolean(playerIn,"disc_deprive");
				if (isDeprived){
					NBTHelper.setEntityBoolean(playerIn,"disc_deprive",false);
					if (!playerIn.world.isRemote){
						stack.shrink(1);
					}
					if (playerIn.world.isRemote){
						playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
						playerIn.world.playEvent(2005,playerIn.getPosition().add(0,1,0),0);
					}
				}else {
					if (playerIn.world.isRemote){
						playerIn.sendMessage(new TextComponentTranslation("message.huajiage.disc_mind.mind_recover"));
					}
				}
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() instanceof ItemDiscMind
			&& tileEntity!=null && tileEntity.getClass().getName().contains("TileEntityGarageKit")
			&& CommonProxy.ModsLoader.isTouhouMaidLoaded()){
			if ( tileEntity.getTileData().hasKey("MaidData") && ForgeRegistries.ENTITIES.containsKey(new ResourceLocation("touhou_little_maid", "entity.passive.maid"))){
				NBTTagCompound data = tileEntity.getTileData().getCompoundTag("MaidData");
				EntityLivingBase maid = (EntityLivingBase) Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("touhou_little_maid", "entity.passive.maid"))).newInstance(worldIn);
				maid.setPosition(pos.getX(),pos.getY()+1f,pos.getZ());
				maid.readEntityFromNBT(data);
				maid.setHealth(5);
				NBTHelper.setEntityBoolean(maid,"disc_deprive",false);
				if (maid.getName().equals(getOwnerName(stack))) {
					if (!worldIn.isRemote){
						worldIn.spawnEntity(maid);
					}
					stack.shrink(1);
					worldIn.setBlockToAir(pos);
					worldIn.playSound(pos.getX(), pos.getY(),pos.getZ(),SoundEvents.UI_BUTTON_CLICK, SoundCategory.NEUTRAL,1f,1f,false);
				}else {
					if (worldIn.isRemote) {
						player.sendMessage(new TextComponentTranslation("message.huajiage.disc_mind.insert.fail.type"));
					}
				}
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (!getOwnerName(stack).isEmpty()){
			return super.getItemStackDisplayName(stack)+" "+getOwnerName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}

	public static String getOwnerName(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMind) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.NAME.getTag());
		}
		return "";
	}

	public static void setOwnerName(ItemStack stack, String name){
		if (stack.getItem() instanceof ItemDiscMind) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.NAME.getTag(), name);
		}
	}

	public static String getOwnerUUID(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMind) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.UUID.getTag());
		}
		return "";
	}

	public static void setOwnerUUID(ItemStack stack, String uuid){
		if (stack.getItem() instanceof ItemDiscMind) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.UUID.getTag(), uuid);
		}
	}

	public static void setOwner(ItemStack stack, String name, String uuid){
		setOwnerName(stack,name);
		setOwnerUUID(stack,uuid);
	}

	public static ItemStack getDiscMind(EntityLivingBase livingBase){
		ItemStack discMind = new ItemStack(ItemLoader.discMind);
		ItemDiscMind.setOwner(discMind,livingBase.getName(),livingBase.getUniqueID().toString());
		return discMind;
	}

	public enum TAGS{
		NAME("owner_name"),
		UUID("owner_uuid")
		;
		private String tag;

		TAGS(String key) {
			tag = key;
		}
		public String getTag() {
			return tag;
		}

	}
}

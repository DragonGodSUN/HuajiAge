package com.lh_lshen.mcbbs.huajiage.item;


import com.lh_lshen.mcbbs.huajiage.capability.*;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustom;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemDiscStand extends Item {
	private static final String DEFAULT_STAND_ID ="empty";
	private static final String DEFAULT_STAND_STATE = "default";

	public ItemDiscStand()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}
	public static String getStandId(ItemStack stack) {
        if (stack.getItem() == ItemLoader.disc && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.STAND_ID.getName())) {
            return stack.getTagCompound().getString(NBT.STAND_ID.getName());
        }
        return DEFAULT_STAND_ID;
    }
	public static int getStandStage(ItemStack stack) {
        if (stack.getItem() == ItemLoader.disc && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.STAND_STAGE.getName())) {
            return stack.getTagCompound().getInteger(NBT.STAND_STAGE.getName());
        }
        return 0;
    }
	public static String getStandModel(ItemStack stack) {
		if (stack.getItem() == ItemLoader.disc && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.STAND_MODEL.getName())) {
			return stack.getTagCompound().getString(NBT.STAND_MODEL.getName());
		}
		return DEFAULT_STAND_ID;
	}
	private static ItemStack setStandId(ItemStack stack, String stand_id) {
        if (stack.getItem() == ItemLoader.disc) {
            if (stack.hasTagCompound()) {
                stack.getTagCompound().setString(NBT.STAND_ID.getName(), stand_id);
            } else {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString(NBT.STAND_ID.getName(), stand_id);
                stack.setTagCompound(tag);
            }
        }
        return stack;
    }
	private static ItemStack setStandStage(ItemStack stack, int stage) {
        if (stack.getItem() == ItemLoader.disc) {
            if (stack.hasTagCompound()) {
                stack.getTagCompound().setInteger(NBT.STAND_STAGE.getName(), stage);
            } else {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setInteger(NBT.STAND_STAGE.getName(), stage);
                stack.setTagCompound(tag);
            }
        }
        return stack;
    }

	private static ItemStack setModel(ItemStack stack, String model) {
		if (stack.getItem() == ItemLoader.disc) {
			if (stack.hasTagCompound()) {
				stack.getTagCompound().setString(NBT.STAND_MODEL.getName(), model);
			} else {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString(NBT.STAND_MODEL.getName(), model);
				stack.setTagCompound(tag);
			}
		}
		return stack;
	}

	public static ItemStack getItemData(ItemStack stack ,String stand_id ,int stage) {
		setStandId(stack, stand_id);
		setStandStage(stack, stage);
		setModel(stack, DEFAULT_STAND_ID);
		return stack;
	}

	public static ItemStack getItemData(ItemStack stack, String stand_id ,int stage, String model) {
		setStandId(stack, stand_id);
		setStandStage(stack, stage);
		setModel(stack, model);
		return stack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
            for (StandBase stand : StandUtil.getArrowStands()) {
				if (!(stand instanceof StandCustom)) {
					String type = stand.getName();
					items.add(getItemData(new ItemStack(this),type,0));
					items.add(getItemData(new ItemStack(this),type,1));
				}
			}
			for (StandBase stand : StandUtil.getCustomStands()) {
				String type = stand.getName();
				items.add(getItemData(new ItemStack(this),type,0));
				items.add(getItemData(new ItemStack(this),type,1));
			}
            items.add(getItemData(new ItemStack(this),StandLoader.ORGA_REQUIEM.getName(),3));
            if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
			items.add(getItemData(new ItemStack(this),StandLoader.MAID.getName(),1,"touhou_little_maid:hakurei_reimu_default"));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		String stand = getStandId(stack);
		int stage = getStandStage(stack);
		String model = getStandModel(stack);
		tooltip.add(I18n.format("item.huajiage.disc.tooltip.1")+I18n.format(StandUtil.getLocalName(stand)));
		tooltip.add(I18n.format("item.huajiage.disc.tooltip.2")+stage);
		if (!model.equals(DEFAULT_STAND_ID)) {
			tooltip.add(I18n.format("item.huajiage.disc.tooltip.3")+model);
		}
		if(StandLoader.getStand(stand) instanceof StandCustom){
			StandCustomInfo info = ((StandCustom)StandLoader.getStand(stand)).getInfo();
			String author = info.getAuthor();
			tooltip.add(I18n.format("item.huajiage.disc.tooltip.author")+author);
			}

	}
	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String stand = getStandId(stack);
		return super.getItemStackDisplayName(stack)+I18n.format(StandUtil.getLocalName(stand));
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
//		StandHandler standHandler = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null);
//		StandStageHandler stageHandler = playerIn.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
		IExposedData data = StandUtil.getStandData(playerIn);
		if (data!=null) {
			String type = data.getStand();
			int stage = data.getStage();
			String model = data.getModel();
			String standTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getString(NBT.STAND_ID.getName());
			int stageTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getInteger(NBT.STAND_STAGE.getName());
			String modelTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getString(NBT.STAND_MODEL.getName());
			if(!standTag.equals(DEFAULT_STAND_ID)) {
					data.setStand(standTag);
					data.setStage(stageTag);
					data.setState(DEFAULT_STAND_STATE);
					data.setModel(modelTag);
					data.setTrigger(false);
					playerIn.getHeldItem(handIn).shrink(1);
					if(StandLoader.getStand(data.getStand())!=null){
						StandUtil.setChargeMax(playerIn,StandLoader.getStand(data.getStand()).getMaxMP());
					}
				if(!type.equals(DEFAULT_STAND_ID)) {
					if(!worldIn.isRemote) {
					playerIn.dropItem(getItemData(new ItemStack(ItemLoader.disc),type,stage,model),true);
					}
				}
					playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	
	 enum NBT {
	        STAND_ID("StandId"),
	        STAND_MODEL("StandModel"),
	        STAND_STAGE("StandStage");

	        private String name;

	        NBT(String name) {
	            this.name = name;
	        }

	        public String getName() {
	            return name;
	        }
	    }
}

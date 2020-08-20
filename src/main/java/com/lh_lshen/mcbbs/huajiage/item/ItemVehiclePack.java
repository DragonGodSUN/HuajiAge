package com.lh_lshen.mcbbs.huajiage.item;


import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
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

public class ItemVehiclePack extends Item {
	private static final String DEFAULT_STAND_ID ="empty";
	private static final String BIKE_STAND_ID ="huajiage:bike_wheel";

	public ItemVehiclePack()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabVehicle);
	}

	public static String getStandModel(ItemStack stack) {
		if (stack.getItem() == ItemLoader.vehiclePack && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT.VEHICLE_MODEL.getName())) {
			return stack.getTagCompound().getString(NBT.VEHICLE_MODEL.getName());
		}
		return DEFAULT_STAND_ID;
	}

	private static ItemStack setModel(ItemStack stack, String model) {
		if (stack.getItem() == ItemLoader.vehiclePack) {
			if (stack.hasTagCompound()) {
				stack.getTagCompound().setString(NBT.VEHICLE_MODEL.getName(), model);
			} else {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString(NBT.VEHICLE_MODEL.getName(), model);
				stack.setTagCompound(tag);
			}
		}
		return stack;
	}

	public static ItemStack getItemData(ItemStack stack, String model) {
		setModel(stack, model);
		return stack;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			List<ModelStandJson> models = StandUtil.getTagModels(EnumStandTag.ModelTags.BIKE.getName());
			for(ModelStandJson json : models){
				items.add(getItemData(new ItemStack(ItemLoader.vehiclePack),json.info.getModelId().toString()));
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		String model = getStandModel(stack);
		CustomResourceLoader.STAND_MODEL.getInfo(model).ifPresent((info)->{
			StringBuilder builder = new StringBuilder();
			builder.append(info.getName());
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.length()-1);
			tooltip.add(I18n.format("item.huajiage.vehicle_pack.tooltip.1")+I18n.format(builder.toString()));
		});

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IExposedData data = StandUtil.getStandData(playerIn);
		if (data!=null) {
			String type = data.getStand();
			String model = data.getModel();
			String modelTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getString(NBT.VEHICLE_MODEL.getName());
			if(type.equals(BIKE_STAND_ID)) {
				data.setModel(modelTag);
				playerIn.getHeldItem(handIn).shrink(1);
				if(!worldIn.isRemote) {
				playerIn.dropItem(getItemData(new ItemStack(ItemLoader.vehiclePack),model),true);
				}
				playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1f, 1f);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	
	 enum NBT {
	        VEHICLE_MODEL("VehicleModel")
	        ;

	        private String name;

	        NBT(String name) {
	            this.name = name;
	        }

	        public String getName() {
	            return name;
	        }
	    }
}

package com.lh_lshen.mcbbs.huajiage.item;


import java.util.List;
import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.data.StandUserWorldSavedData;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDiscStand extends Item {
	private static final String DEFAULT_STAND_ID =EnumStandtype.EMPTY;

	public ItemDiscStand()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
		  this.addPropertyOverride(new ResourceLocation("stand"), new IItemPropertyGetter()
	        {
				@Override
				public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
					switch(getStandId(stack)) {
					case DEFAULT_STAND_ID:return 0;
					case "the_world":return 1;
					case "star_platinum":return 2;
					case "hierophant_green":return 3;
					}
					return 0;
				}
	        });
		  
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
	public static ItemStack getItemData(ItemStack stack ,String stand_id ,int stage) {
		setStandId(stack, stand_id);
		setStandStage(stack, stage);
		return stack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
            for (EnumStandtype stand : EnumStandtype.values()) {
            	String type = stand.getName();
            	items.add(getItemData(new ItemStack(this),type,0));
            	items.add(getItemData(new ItemStack(this),type,1));
            	}
        	}	
		}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		String stand = getStandId(stack);
		int stage = getStandStage(stack);
		tooltip.add(I18n.format("item.huajiage.disc.tooltip.1")+I18n.format(EnumStandtype.getLocalName(stand)));
		tooltip.add(I18n.format("item.huajiage.disc.tooltip.2")+stage);

	}
	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String stand = getStandId(stack);
		return super.getItemStackDisplayName(stack)+I18n.format(EnumStandtype.getLocalName(stand));
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		StandHandler standHandler = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null);
		StandStageHandler stageHandler = playerIn.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
		String type = standHandler.getStand();
		int stage = stageHandler.getStage();
		String standTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getString(NBT.STAND_ID.getName());
		int stageTag = NBTHelper.getTagCompoundSafe(playerIn.getHeldItem(handIn)).getInteger(NBT.STAND_STAGE.getName());
		if(!standTag.equals(DEFAULT_STAND_ID)) {
				standHandler.setStand(standTag);
				stageHandler.setStage(stageTag);
				playerIn.getHeldItem(handIn).shrink(1);
//				 UUID uuid = playerIn.getUniqueID();
//				 StandUserWorldSavedData.getGlobal(worldIn).add(standTag, uuid);
			if(!type.equals(DEFAULT_STAND_ID)) {
				if(!worldIn.isRemote) {
				playerIn.dropItem(getItemData(new ItemStack(ItemLoader.disc),type,stage),true);
//				StandUserWorldSavedData data =  StandUserWorldSavedData.getGlobal(worldIn);
//				playerIn.sendMessage(new TextComponentString("stand-->"+data.getPlayerStand(playerIn)));
				}
			}
				playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	 enum NBT {
	        STAND_ID("StandId"),
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

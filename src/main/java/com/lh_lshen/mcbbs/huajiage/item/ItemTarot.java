package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTarot extends Item {
	private static final String DEFAULT_STAND_ID = EnumStandtype.THE_WORLD.getName();
//	private static List<String> stands = null;
//	static {
//		for(EnumStandtype stand : EnumStandtype.values()) {
//			stands.add(stand.getName());
//		}
//	}
	public ItemTarot()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("Stand: " + NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName())));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		items.add(getItemData("","",0));
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		String stand = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
		boolean flag = NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName()).equals("");
		
		if(playerIn.isSneaking()) {
			if(flag) {
				setStand(playerIn, stack, stand, 0);
				playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).setStand("");
				if(worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentString(stand+" is Loaded"));
				}
			}
		}else {
			String standName = NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName());
			playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).setStand(standName);
			playerIn.addPotionEffect(new PotionEffect(PotionLoader.potionStand,200));
			}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	public static ItemStack getItemData(String owner_id, String stand_id, int weak) {
        ItemStack stack = new ItemStack(ItemLoader.tarot);
		NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
        data.setString(NBT.OWNER.getName(), owner_id);
        
        if (stand_id != null) {
        	data.setString(NBT.STAND_NAME.getName(), stand_id);
        	data.setInteger(NBT.STAND_WEAK.getName(), weak);
        }
        return stack;
    }
	public void setStand(EntityLivingBase entity,ItemStack stack , String stand_id, int weak) {
			NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
	        if (stand_id != "") {
	        	data.setString(NBT.STAND_NAME.getName(), stand_id);
	        	data.setInteger(NBT.STAND_WEAK.getName(), weak);
	        }
	}
	 public enum NBT {
	        OWNER("Owner"),
	        INDEX("Index"),
		 	STAND_NAME("Name"),
	        STAND_WEAK("Weak");

	        private String name;

	        NBT(String name) {
	            this.name = name;
	        }

	        public String getName() {
	            return name;
	        }
	    }

}

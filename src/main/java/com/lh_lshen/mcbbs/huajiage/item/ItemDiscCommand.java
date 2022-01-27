package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.entity.EntityDiscCommand;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDiscCommand extends Item {
	public ItemDiscCommand()
	{
		 super();
		 this.setMaxStackSize(1);
		 this.setCreativeTab(CreativeTabLoader.tabJo);
		 this.addPropertyOverride(new ResourceLocation("command_type"), new IItemPropertyGetter()
		 {
			@SideOnly(Side.CLIENT)
			@Override
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				if (entityIn == null)
				{
					return 0.0F;
				}
				else{
					String type = getCommandType(stack);
					switch (type){
						case "null":
							return 0f;
						case "explosion":
							return 1f;
						case "move_up":
							return 2f;
						case "self_attack":
							return 3f;
					}
					return 0.0F;
				}
			}
		 });
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
//			ItemStack explosion = new ItemStack(ItemLoader.discCommand);
//			ItemStack move = new ItemStack(ItemLoader.discCommand);
//			ItemStack self_attack = new ItemStack(ItemLoader.discCommand);
//			setCommandType(explosion,"explosion_command");
//			setCommandType(move,"move");
//			setCommandType(self_attack,"self_attack");
//			items.add(explosion);
//			items.add(move);
//			items.add(self_attack);
			for (TYPES_COMMAND types_command : TYPES_COMMAND.values()) {
				ItemStack stack = new ItemStack(ItemLoader.discCommand);
				setCommandType(stack, types_command.getCommand());
				items.add(stack);
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		String type = getCommandType(stack);
		if (!stack.isEmpty()&&stack.getItem() instanceof ItemDiscCommand) {
			if (!worldIn.isRemote){
				EntityDiscCommand disc = new EntityDiscCommand(worldIn);
				disc.setCommand(getCommandType(stack));
				disc.setMaster(playerIn.getUniqueID().toString());
				disc.posX=playerIn.posX;
				disc.posY=playerIn.posY+playerIn.height-0.1f;
				disc.posZ=playerIn.posZ;
				disc.shoot(playerIn,playerIn.rotationPitch,playerIn.rotationYaw,0f,1f,0f);
				worldIn.spawnEntity(disc);
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.huajiage.disc_command:tooltips.1")
				+TextFormatting.GRAY +getOwnerName(stack));
		tooltip.add(I18n.format("item.huajiage.disc_command:tooltips.2")
				+ TextFormatting.GRAY +getOwnerUUID(stack));
		tooltip.add(I18n.format("item.huajiage.disc_command:tooltips.3")
				+ TextFormatting.GRAY +getCommandType(stack));
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}


	public static String getOwnerName(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscCommand) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.NAME.getTag());
		}
		return "";
	}

	public static void setOwnerName(ItemStack stack, String name){
		if (stack.getItem() instanceof ItemDiscCommand) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.NAME.getTag(), name);
		}
	}

	public static String getOwnerUUID(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscCommand) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.UUID.getTag());
		}
		return "";
	}

	public static void setOwnerUUID(ItemStack stack, String uuid){
		if (stack.getItem() instanceof ItemDiscCommand) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.UUID.getTag(), uuid);
		}
	}

	public static void setOwner(ItemStack stack, String name, String uuid){
		setOwnerName(stack,name);
		setOwnerUUID(stack,uuid);
	}

	public static String getCommandType(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscCommand) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.TYPE.getTag());
		}
		return "";
	}

	public static void setCommandType(ItemStack stack, String type){
		if (stack.getItem() instanceof ItemDiscCommand) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.TYPE.getTag(), type);
		}
	}

	public enum TAGS{
		NAME("owner_name"),
		UUID("owner_uuid"),
		TYPE("command_type")
		;
		private String tag;

		TAGS(String key) {
			tag = key;
		}
		public String getTag() {
			return tag;
		}

	}

	public enum TYPES_COMMAND{
		EXPLOSION("explosion"),
		MOVE("move_up"),
		SELF_ATTACK("self_attack")
		;
		private String command;

		TYPES_COMMAND(String key) {
			command = key;
		}

		public String getCommand() {
			return command;
		}

	}
}

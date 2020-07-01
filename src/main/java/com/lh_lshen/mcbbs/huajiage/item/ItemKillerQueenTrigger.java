package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import javax.annotation.Nullable;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKillerQueenTrigger extends Item {
	public ItemKillerQueenTrigger()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}
	
	public static NBTTagCompound getTagCompoundSafe(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack);
	}
	
	public static void setData(ItemStack stack, String type,@Nullable String uuid, float x, float y, float z) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString(NBT.TYPE.getName(), type);
		TYPE t =TYPE.getType(type);
		switch (t) {
		case BLOCK:
			nbt.setDouble(NBT.POS_X.getName(), x);
			nbt.setDouble(NBT.POS_Y.getName(), y);
			nbt.setDouble(NBT.POS_Z.getName(), z);
			break;
		case ENTITY:
			nbt.setString(NBT.UUID.getName(), uuid);
			break;
		default:
			break;
		}
		getTagCompoundSafe(stack).setTag(NBT.NBTs.getName(), nbt);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		NBTTagCompound nbt = getTagCompoundSafe(stack).getCompoundTag(NBT.NBTs.getName());
		String type = nbt.getString(NBT.TYPE.getName());
		TYPE t =TYPE.getType(type);
		tooltip.add( I18n.format("item.killer_queen_trigger:tooltips.type",type));
		if(t!=null) {
			switch (t) {
			case BLOCK:
			tooltip.add( I18n.format("item.killer_queen_trigger:tooltips.x",nbt.getDouble(NBT.POS_X.getName())));
			tooltip.add( I18n.format("item.killer_queen_trigger:tooltips.y",nbt.getDouble(NBT.POS_Y.getName())));
			tooltip.add( I18n.format("item.killer_queen_trigger:tooltips.z",nbt.getDouble(NBT.POS_Z.getName())));
				break;
			case ENTITY:
			String uuid =nbt.getString(NBT.UUID.getName());
	//		Entity entity = world.getEntitiesWithinAABB(Entity.class, bb);
			tooltip.add( I18n.format("item.killer_queen_trigger:tooltips.entity",uuid));
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if(stack.getItem() == ItemLoader.killerQueenTrigger) {
		NBTTagCompound nbt = getTagCompoundSafe(stack).getCompoundTag(NBT.NBTs.getName());
		String type = nbt.getString(NBT.TYPE.getName());
		TYPE t =TYPE.getType(type);
		if(t!=null) {
			switch (t) {
			case BLOCK:
				BlockPos pos =new BlockPos(nbt.getDouble(NBT.POS_X.getName()), nbt.getDouble(NBT.POS_Y.getName()), nbt.getDouble(NBT.POS_Z.getName()));
				worldIn.createExplosion(playerIn, pos.getX(), pos.getY(), pos.getZ(), 3f, false);
				stack.shrink(1); 
				break;
			case ENTITY:
			String uuid =nbt.getString(NBT.UUID.getName());
			List<Entity> entities =worldIn.getEntitiesWithinAABB(Entity.class, playerIn.getEntityBoundingBox().grow(StandLoader.KILLER_QUEEN.getDistance()));
				if(entities!=null) {
					for(Entity entity : entities) {
						if(entity.getUniqueID().toString().equals(uuid)) {
							entity.getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, 2f, false);
							entity.attackEntityFrom(DamageSource.causeExplosionDamage(playerIn), 20f);
							stack.shrink(1);     
						}
					}
				}
				break;
			default:
				break;
				}
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}
 
	public static enum NBT {
		NBTs("nbts"),TYPE("type"),UUID("uuid"),POS_X("pos_x"),POS_Y("pos_y"),POS_Z("pos_z");
		NBT(String name){
			this.name =name;
		}
		private String name;
		public String getName() {
			return name;
		}
	}
	public static enum TYPE {
		ENTITY("entity"),BLOCK("block");
		TYPE(String name){
			this.name =name;
		}
		private String name;
		public String getName() {
			return name;
		}
		public static TYPE getType(String name) {
			for(TYPE type : values()) {
				if(type.getName().equals(name)) {
					return type;
				}
			}
			return null;
		}
	}

}
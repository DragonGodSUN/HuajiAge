package com.lh_lshen.mcbbs.huajiage.item;

import java.awt.image.BufferStrategy;
import java.util.List;

import org.apache.logging.log4j.core.config.builder.api.Component;

import com.google.common.base.Predicate;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.init.EventLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.fixes.EntityId;
import net.minecraft.util.datafix.fixes.MinecartEntityTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSecondFoil extends Item {   
private final int BURST_TIME=200;
public ItemSecondFoil()
    {
        super();
        MinecraftForge.EVENT_BUS.register(this);
        this.setCreativeTab(CreativeTabLoader.tabhuaji);
        setMaxStackSize(1);
        setMaxDamage(4);

       
    }
private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
    NBTTagCompound tagCompound = stack.getTagCompound();
    if (tagCompound == null) {
        tagCompound = new NBTTagCompound();
        stack.setTagCompound(tagCompound);
    }
    return tagCompound;
}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		float health=target.getHealth();
		int i;
		DamageSource second=new DamageSource("huaji.second");
		for(i=0;i<120;i++) {
		target.getCombatTracker().trackDamage(second,100*i,99*i);
		target.getCombatTracker().trackDamage(second, health,health);
		target.setHealth(0);
		
	
		}
		stack.damageItem(1,attacker);
		target.onDeath(new EntityDamageSource("huaji.second",attacker));
		 target.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.0F);
		Item v=ItemLoader.expendedView;
        attacker.world.spawnEntity(new EntityItem(attacker.world, target.posX, target.posY, target.posZ, new ItemStack(v)));
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		// TODO Auto-generated method stub
		if(!entity.world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer target=(EntityPlayer)entity;
			
			float health= target.getHealth();
			target.getCombatTracker().trackDamage(new DamageSource("huaji.second"), health,health);
			target.setHealth(0);
			target.onDeath(new DamageSource("huaji.second"));

			return true;
			
				}
	
		
		return false;
	}
	@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		if(!getTagCompoundSafe(stack).hasKey("owner")||entityIn.getName()!=getTagCompoundSafe(stack).getString("owner")) {
			getTagCompoundSafe(stack).setString("owner", entityIn.getName());;
		}
		if(!getTagCompoundSafe(stack).hasKey("burst_time")) {
			getTagCompoundSafe(stack).setInteger("burst_time",BURST_TIME);
		}
		  super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		}
	@Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        

        if (!worldIn.isRemote)
        {
            EntitySecondFoil entitys = new EntitySecondFoil(worldIn, playerIn,worldIn.findNearestEntityWithinAABB(EntityLiving.class,playerIn.getEntityBoundingBox().grow(20,20,20),playerIn),playerIn.getHorizontalFacing().getAxis());
            worldIn.spawnEntity(entitys);
            itemstack.damageItem(1,playerIn);
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

	@Override
		public boolean onEntityItemUpdate(EntityItem item) {
		EntityPlayer player=item.world.getPlayerEntityByName(getTagCompoundSafe(item.getItem()).getString("owner"));
		 EventLoader.SecondHitEvent event;        	
	        event = new EventLoader.SecondHitEvent(item);
		if(item.onGround) {
			if(getTagCompoundSafe(item.getItem()).hasKey("burst_time")&&item.getEntityData().getInteger("burst_time")==0) {
				item.getEntityData().setInteger("burst_time",getTagCompoundSafe(item.getItem()).getInteger("burst_time"));
			}
			if(item.getEntityData().getInteger("burst_time")>1) {
				item.getEntityData().setInteger("burst_time",item.getEntityData().getInteger("burst_time")-1);
				
			}
			if(player!=null) {
			  if(item.getEntityData().getInteger("burst_time")==1) {
	             EventLoader.EVENT_BUS.post(event);
	        }
                                            }
		            }
		
			return false;
		}
	@SubscribeEvent
	public void playerDropItem(ItemTossEvent  evt) {
		EntityPlayer player=evt.getPlayer();
		EntityItem item=(EntityItem) evt.getEntity();
	if(item.getItem().getItem()==ItemLoader.secondFoil) {
		if(!player.world.isRemote) {player.sendMessage( new TextComponentTranslation("huajiage.secondhit"));}
		item.setPickupDelay(200);
		}
	}

	
	      }




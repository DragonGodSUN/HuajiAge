package com.lh_lshen.mcbbs.huajiage.item;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityDragonFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHuajiLatiaoSword extends ItemSword {
 public static final Item.ToolMaterial HOT = EnumHelper.addToolMaterial("HOT", 3,5400, 25.0F,14.0F, 30);
 private float attackDamage;
	public ItemHuajiLatiaoSword()
	{
		
		 super(HOT);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  MinecraftForge.EVENT_BUS.register(this);
		  this.addPropertyOverride(new ResourceLocation("burst"), new IItemPropertyGetter()
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
	                	return getTagCompoundSafe(stack).getInteger("hot");
	                }
	            }
	        });
		  
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(!isOpen(stack)) {
        stack.damageItem(1, attacker);
        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER,600,2));
		}
		else {
                target.setFire(8);

                target.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f,1f);
                target.playSound(SoundEvents.ENTITY_GENERIC_BURN, 1f, 0.1f);
		}
        return true;
    }
	
	@SubscribeEvent
	public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
		if (!evt.getItemStack().isEmpty()
				&& evt.getItemStack().getItem() == this) {
            EntityPlayer player = evt.getEntityPlayer();
            if(isOpen(evt.getItemStack())) {
            player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
            }
		}
	}
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {

		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", isOpen(stack) ? -2.4F : -2.4F + 1.4F, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (isOpen(stack) ? 6F : 0) + 12F, 0));
			    
		}
		return multimap;
	}
	@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
if(entityIn instanceof EntityPlayer) {
	if(((EntityPlayer) entityIn).getHeldItemMainhand()==stack) {
			if(isOpen(stack)) {
				if(!worldIn.isRemote) {
				if(!(
						((EntityLivingBase) entityIn).isPotionActive(MobEffects.SPEED)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.FIRE_RESISTANCE)
						&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.STRENGTH)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.SATURATION)
						)) {
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED,60,1));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,60,0));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH,60,1));
//				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION,60,0));
				          }
						}
					}else {
						if(stack.getItemDamage()>0&&((EntityPlayer) entityIn).getFoodStats().getFoodLevel()>10) {
							if(((EntityPlayer) entityIn).ticksExisted%50==0) {
								setDamage(stack, getDamage(stack)-100);
								((EntityPlayer) entityIn).getFoodStats().setFoodLevel(((EntityPlayer) entityIn).getFoodStats().getFoodLevel()-1);
								entityIn.playSound(SoundEvents.ENTITY_PARROT_EAT, 0.5f, 1f);
							}
						}
					}
				}
			}
		}
	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
//	    NBTTagCompound tagCompound = stack.getTagCompound();
//	    if (tagCompound == null) {
//	        tagCompound = new NBTTagCompound();
//	        stack.setTagCompound(tagCompound);
//	    }
	    return NBTHelper.getTagCompoundSafe(stack);
	}
	
	private boolean isOpen(ItemStack stack) {
	   if(  getTagCompoundSafe(stack).getInteger("hot")==0) {
	     return false;	   }
	   else {
		   return true;
	   }
	}
	@Override
		public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		Item item=ForgeRegistries.ITEMS.getValue(new ResourceLocation("latiaocraft:latiao"));
			if(item!=null) {
				if(repair.getItem()==item) {
					return true;
				}
			}
			return super.getIsRepairable(toRepair, repair);
		}
	@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
	if(playerIn.isSneaking()) {
		
		 if (!worldIn.isRemote) {
             int hotpoint =isOpen(stack)?0:1;	 
                    getTagCompoundSafe(stack).setInteger("hot",hotpoint);  

		    }
		 
		 playerIn.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
		 }else {
		     if(isOpen(stack)){
			 Vec3d v=playerIn.getLookVec();
			 if (!worldIn.isRemote) {
//				playerIn.heal(1f);
				if(playerIn.getFoodStats().getFoodLevel()<20) {
				playerIn.getFoodStats().setFoodLevel(playerIn.getFoodStats().getFoodLevel()+2);}
				 stack.damageItem(20, playerIn);
			 }	    
			 playerIn.motionY=0.6;
			 playerIn.fallDistance=0;
			 playerIn.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1f, 1f);
			 playerIn.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
//			 worldIn.playEvent(2007, playerIn.getPosition().add(0, 1.2, 0), stack.getItem().getMetadata(stack));
			 Random random = new Random();
			 final int NUMBER_OF_PARTICLES =10;
			 final double HORIZONTAL_SPREAD = 1;  
			 final double DEGREE= 15;  
			 Vec3d targetCoordinates = playerIn.getPositionVector();
			    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
			      double spawnXpos = targetCoordinates.x ;
			      double spawnYpos = targetCoordinates.y ;
			      double spawnZpos = targetCoordinates.z ;
			      for(int d=0;d<360;d+=DEGREE) {
			      worldIn.spawnParticle(EnumParticleTypes.FLAME, spawnXpos+0.5*MathHelper.sin(d) , spawnYpos+0.1, spawnZpos+0.5* MathHelper.cos(d),0.5*MathHelper.sin(d) , 0 ,0.5* MathHelper.cos(d));}
			     
			    }
			   
		    }
		 }
		    return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		
		

			
}
	

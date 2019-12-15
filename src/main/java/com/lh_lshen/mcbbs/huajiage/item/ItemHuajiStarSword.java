package com.lh_lshen.mcbbs.huajiage.item;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.render.layers.LayerHeldTopItem;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHuajiStarSword extends ItemSword {
 protected float attackSpeed = -2.4F;
	protected int damage = 13;
	protected int damageCharged = 10;
 public static final Item.ToolMaterial STAR = EnumHelper.addToolMaterial("STARHUAJI", 3,5400, 20.0F,20.0F, 30);
 public static final Item.ToolMaterial STARU = EnumHelper.addToolMaterial("STARHUAJIU", 3,5400, 20.0F,30.0F, 30);
 private float attackDamage;
	public ItemHuajiStarSword()
	{
		 super(STAR);
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
	                if(isOpen(stack))
	                 {
	                    return 1.0f;
	                
	                }
	                else{
	                	return 0f;
	                }
	            }
	        });
		  
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if(!isOpen(stack)) {
        stack.damageItem(1, attacker);
        
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,2));
        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,60,2));
		}
		else {

                target.attackEntityFrom(DamageSource.OUT_OF_WORLD,10f);
                target.setFire(5);
             
                
                target.playSound(SoundLoader.WAVE1, 1f,1f);
                target.playSound(SoundLoader.ENERGY_HIT, 1f, 0.1f);
		}
        return true;
    }
	@Override
		public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        ItemStack mat = new ItemStack(ItemLoader.netronStarFragment);
        if (!mat.isEmpty()) return true;
			return super.getIsRepairable(toRepair,repair);
		}
	
	@SubscribeEvent
	public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
		if (!evt.getItemStack().isEmpty()
				&& evt.getItemStack().getItem() == this) {
            EntityPlayer player = evt.getEntityPlayer();
            if(isOpen(evt.getItemStack())) {
            player.playSound(SoundLoader.WAVE1, 1f, 1f);
            }
		}
	}
	@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
	
		if(isOpen(stack)) {
			if(entityIn instanceof EntityPlayer) {
				
		if(((EntityPlayer) entityIn).getHeldItemMainhand()==stack) {
			if(!worldIn.isRemote) {
				if(!(((EntityLivingBase) entityIn).isPotionActive(MobEffects.SPEED)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.HUNGER)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.STRENGTH))) {
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED,60,3));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.HUNGER,60,6));
				((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH,60,3));
				}
			}
		}
			}
			
		
		}
		
		
			
		}
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {

		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", isOpen(stack) ? attackSpeed : attackSpeed+ 1F , 0));
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (isOpen(stack) ? damageCharged : 1) + damage, 0));
			    
		}
		return multimap;
	}

	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
	    NBTTagCompound tagCompound = stack.getTagCompound();
	    if (tagCompound == null) {
	        tagCompound = new NBTTagCompound();
	        stack.setTagCompound(tagCompound);
	    }
	    return tagCompound;
	}
	private boolean isOpen(ItemStack stack) {
	    return getTagCompoundSafe(stack).hasKey("open");
	}
	@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
	if(playerIn.isSneaking()) {
		 if (!worldIn.isRemote) {
			 
		        if (isOpen(stack)) {
		            getTagCompoundSafe(stack).removeTag("open");
		        } else {
		            getTagCompoundSafe(stack).setBoolean("open", true);
		           		          
		        }
		    } 
		 playerIn.playSound(SoundLoader.CHARGE, 1f, 1f);
	}
		    return new ActionResult(EnumActionResult.SUCCESS, stack);
		}
		
		

			
}
	

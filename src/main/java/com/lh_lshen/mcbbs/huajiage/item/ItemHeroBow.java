package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityHeroArrow;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.MessageLeftClick;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import mezz.jei.network.PacketHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHeroBow extends ItemBow{
 public ItemHeroBow() 
	{this.maxStackSize = 1;
    this.setMaxDamage(384);
    MinecraftForge.EVENT_BUS.register(this);
    this.setCreativeTab(CreativeTabLoader.tabhuaji);
    this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
    {
        @SideOnly(Side.CLIENT)
        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
        {
            if (entityIn == null)
            {
                return 0.0F;
            }
            else
            {
                return entityIn.getActiveItemStack().getItem() != ItemLoader.heroBow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
            }
        }
    });
    this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
    {
        @SideOnly(Side.CLIENT)
        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
        {
            return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
        }
    });
    this.addPropertyOverride(new ResourceLocation("burst"), new IItemPropertyGetter()
    {
        @SideOnly(Side.CLIENT)
        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
        {
            return entityIn != null &&isOpen(stack)&& entityIn.isHandActive() && (entityIn.getActiveItemStack().getItem() != ItemLoader.heroBow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F)>=1 ? 1.0F : 0.0F;
        }
    });
}
@Override
	public EnumRarity getRarity(ItemStack stack) {
		
		return EnumRarity.EPIC;
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
	    return getTagCompoundSafe(stack).hasKey("open");
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{	
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.1.1.desc"));
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.1.2.desc"));
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.1.3.desc"));
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.1.4.desc"));
		}
		else {
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.2.desc"));}
		tooltip.add(I18n.translateToLocal("item.hero_bow:unicode_tooltips.3.desc"));
	}
/**
 * Called when the player stops using an Item (stops holding the right mouse button).
 */
 @Override
public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
{
    if (entityLiving instanceof EntityPlayer)
    {
        EntityPlayer entityplayer = (EntityPlayer)entityLiving;
        int i = this.getMaxItemUseDuration(stack) - timeLeft;
        if (i < 0) return;
        float f = getArrowVelocity(i);

            if ((double)f >= 0.1D)
            {
                if (!worldIn.isRemote)
                {
                    if
                    (isOpen(stack)&&
                    (entityplayer.getActiveItemStack().getItem() != ItemLoader.heroBow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount()) / 20.0F)>=1) { 
                    if(!entityplayer.isPotionActive(PotionLoader.potionFlowerHope)) { 
                    EntityHeroArrow entityarrow = new EntityHeroArrow(worldIn,entityLiving);
                    entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 1F, f * 6.0F, 1.0F);
          
                    worldIn.spawnEntity(entityarrow);
                    entityplayer.attackEntityFrom(new DamageSource(HuajiConstant.STELLA).setDamageIsAbsolute(),entityplayer.getMaxHealth()*5);
                          }
                    else {
                    	entityplayer.sendMessage(new TextComponentTranslation("message.huaji.orga.hero.shot"));
                    }
                    }
                    else {
                    	 ItemArrow itemarrow = (ItemArrow)(stack.getItem() instanceof ItemArrow ? stack.getItem() : Items.ARROW);
                         EntityArrow entityarrow = itemarrow.createArrow(worldIn, stack, entityplayer);
                         entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 6.0F, 1.0F);

                         if (f == 1.0F)
                         {
                             entityarrow.setIsCritical(true);
                         }

                         int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                         if (j > 0)
                         {
                             entityarrow.setDamage(entityarrow.getDamage() + (double)j * 2D + 0.5D);
                         }

                         int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                         if (k > 0)
                         {
                             entityarrow.setKnockbackStrength(k);
                         }

                         if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0||(entityplayer.getActiveItemStack().getItem() != ItemLoader.heroBow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount()) / 20.0F)>=1)
                         {
                             entityarrow.setFire(100);
                         }
                        entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        if(entityplayer.isPotionActive(MobEffects.STRENGTH)) {
                        	int l=entityplayer.getActivePotionEffect(MobEffects.STRENGTH).getAmplifier();
                        	entityarrow.setDamage(entityarrow.getDamage() + (double)l * 2D);
                        }

                         worldIn.spawnEntity(entityarrow);
						};
                       
                    }
                }
                if(isOpen(stack)&&
                (entityplayer.getActiveItemStack().getItem() != ItemLoader.heroBow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityplayer.getItemInUseCount()) / 20.0F)>=1 
                		) 
                {   if(!entityplayer.isPotionActive(PotionLoader.potionFlowerHope)) { 
                	worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundLoader.STELLA, SoundCategory.PLAYERS, 5.0F, 1.0F);
                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.PLAYERS, 3.0F, 1.0F);
                              }
                  }
                else {
                 worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
    }

 @SideOnly(Side.CLIENT)
 public boolean hasEffect(ItemStack stack)
 {
     return super.hasEffect(stack) || isOpen(stack);
 }
@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(isOpen(stack)) {
			return new String(super.getItemStackDisplayName(stack)+I18n.translateToLocal("huajiage.burstout"));
		}
		else {
			return super.getItemStackDisplayName(stack);
		}
		
	}

@Override
public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
if(entityIn instanceof EntityPlayer) {
if(((EntityPlayer) entityIn).getHeldItemMainhand()==stack) {
	if(!worldIn.isRemote) {
		if(!(((EntityLivingBase) entityIn).isPotionActive(MobEffects.SPEED)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.RESISTANCE)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.STRENGTH)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.REGENERATION))) {
		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED,260,2));
		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,260,2));
		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH,260,3));
		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION,260,2));
		}
		if(!(((EntityLivingBase) entityIn).isPotionActive(MobEffects.NIGHT_VISION))||!(((EntityLivingBase) entityIn).getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration()>240)){
		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,300,0));}
	
	}
	 if(((EntityLivingBase) entityIn).isPotionActive(MobEffects.POISON)) {
     	((EntityLivingBase) entityIn).removeActivePotionEffect(MobEffects.POISON);

     }
     if(((EntityLivingBase) entityIn).isPotionActive(MobEffects.WITHER)) {
     	((EntityLivingBase) entityIn).removeActivePotionEffect(MobEffects.WITHER);

     }
}
	}
	


	
}
@SubscribeEvent
public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
	if (!evt.getItemStack().isEmpty()
			&& evt.getItemStack().getItem() == this) {
		ItemStack itemstack=evt.getItemStack();
		EntityPlayer player=evt.getEntityPlayer();
        if(player.isSneaking()) {
	       HuajiAgeNetWorkHandler.sendToServer(new MessageLeftClick());
	       if(!isOpen(itemstack)) {
	         player.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 3.0F, 1.0F);	
	         player.sendMessage( new TextComponentTranslation("huajiage.stellawarning"));    
                                  }
}
		}
	}
public void ModeChange(ItemStack itemstack,EntityPlayer player) {
	if (!player.getHeldItemMainhand().isEmpty()
			&& player.getHeldItemMainhand().getItem() == this) {
        		try{
        		if(isOpen(itemstack)) {
        			getTagCompoundSafe(itemstack).removeTag("open");
    	        } else {
    	            getTagCompoundSafe(itemstack).setBoolean("open", true);
    	              
    	        }}catch(Exception e){
    	        	System.out.println(1);
    	        }

	}
	
}
/**
 * Called when the equipped item is right clicked.
 */
@Override
public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
{
    ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

}

}

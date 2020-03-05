package com.lh_lshen.mcbbs.huajiage.item;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
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
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExglutenbur extends ItemSword {
	 public static final Item.ToolMaterial Gluten = EnumHelper.addToolMaterial("gluten", 3,5400, 25.0F,17.0F, 30);
	 private float attackDamage;
	 int vi =0;
	 protected float attackSpeed = -2.4F;
	 protected int damage = 7;
	 protected int damageAromatic = 15;
		
		public ItemExglutenbur()
		{
			
			 super(Gluten);
			  this.setCreativeTab(CreativeTabLoader.tabhuaji);
			  MinecraftForge.EVENT_BUS.register(this);
			  this.addPropertyOverride(new ResourceLocation("flavor"), new IItemPropertyGetter()
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
		                	return getTagCompoundSafe(stack).getInteger("flavor");
		                }
		            }
		        });
			  
		}
		
		@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	    {
			switch(flavor(stack)) {
			case 1:{
	        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER,600,3));
	      if(attacker instanceof EntityPlayer) { 
	    	  ( (EntityPlayer)attacker).getFoodStats().addStats(1, 10f);
	    	  }
	        target.motionY=1;
	        stack.damageItem(8, attacker);
	        break;
			}
			case 2:{	   
		        target.setFire(5);
		        stack.damageItem(1, attacker);
		        target.playSound(SoundEvents.ENTITY_FIREWORK_LARGE_BLAST, 1f, 1f);
 
		        target.world.playEvent(2004, target.getPosition(), 0xFF4500);
		    break;
		    }
			case 3:{
		        stack.damageItem(10, attacker);
		        
		        target.playSound(SoundEvents.BLOCK_STONE_BREAK, 1f, 1f);
		        target.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1f, 1f);
		        target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,60,2));
		        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,9));
		        target.world.playEvent(2001, target.getPosition(), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
		  double r=Math.random();
		  if(r<0.3d) {
			  stack.damageItem(50, attacker);
			  target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.KDJL), 50f);
			  target.playSound(SoundLoader.EXGLUTENBUR_HIT, 1f, 1f);
		  }
		        
		     break;
		        }
			}
	        return true;
	    }
		
		@SubscribeEvent
		public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
			if (!evt.getItemStack().isEmpty()
					&& evt.getItemStack().getItem() instanceof ItemExglutenbur) {
	            EntityPlayer player = evt.getEntityPlayer();
	            if(flavor(evt.getItemStack())==2) {
	            player.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1f, 1f);
	            }
			}
		}
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void onMouseDwheelInput(MouseEvent evt) {
			 Minecraft mc = Minecraft.getMinecraft();
	         EntityPlayerSP player = mc.player;
	         boolean flag=evt.getDwheel() < 0;
	         if (player.isSneaking()&&mc.inGameHasFocus && evt.getDwheel() != 0 && player.getHeldItemMainhand().getItem() == ItemLoader.exglutenbur) {
	             HuajiAgeNetWorkHandler.sendToServer(new MessageExglutenburMode(flag));
				 switch(flavor(player.getHeldItemMainhand())) {
				 case 0: {
					 if(flag) {
					 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_1, SoundCategory.PLAYERS, 1f);
//			    	 player.playSound(SoundLoader.EXGLUTENBUR_1, 1f, 1f);
					 }
					 else {
					 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_3, SoundCategory.PLAYERS, 1f);
//					 player.playSound(SoundLoader.EXGLUTENBUR_3, 1f, 1f);	 
					 }
			    	 break;
			              }
				 case 1:{
					 if(flag) {
						 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_2, SoundCategory.PLAYERS, 1f);
//				    	 player.playSound(SoundLoader.EXGLUTENBUR_2, 1f, 1f);
						 }
			    	 break;
			             } 
				 case 2:{
					 if(flag) {
						 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_3, SoundCategory.PLAYERS, 1f);
//				    	 player.playSound(SoundLoader.EXGLUTENBUR_3, 1f, 1f);
				    	 }
						 else {
						 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_1, SoundCategory.PLAYERS, 1f);
//						 player.playSound(SoundLoader.EXGLUTENBUR_1, 1f, 1f);	 
						 }
			    	 break;
			             }
				 case 3:{
					 if(!flag) {
						 HuajiSoundPlayer.playMovingSoundClient(player, SoundLoader.EXGLUTENBUR_2, SoundCategory.PLAYERS, 1f);
//						 player.playSound(SoundLoader.EXGLUTENBUR_2, 1f, 1f);	 
						 }
			    	 break;
			             }
			       }
	             evt.setCanceled(true);
	         }
		}
   @Override
   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

	if(entityIn instanceof EntityLivingBase) {
		switch (flavor(stack)){
		
		case 2:{	
			if(((EntityLivingBase) entityIn).getHeldItemMainhand()==stack) {
				if(!worldIn.isRemote) {
					if(!(((EntityLivingBase) entityIn).isPotionActive(MobEffects.FIRE_RESISTANCE)
							&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.STRENGTH)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.REGENERATION))) {
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,60,0));
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH,60,2));
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION,60,2));
				          }
					}
			}
			break;		
		}
		case 3:{	
			if(((EntityLivingBase) entityIn).getHeldItemMainhand()==stack) {
				if(!worldIn.isRemote) {
					if(!(((EntityLivingBase) entityIn).isPotionActive(MobEffects.WITHER)&&((EntityLivingBase) entityIn).isPotionActive(MobEffects.SPEED))) {
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER,60,1));
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED,60,4));
				          }
					}
				double e=Math.random();
				if(e<0.05d) {
				((EntityLivingBase) entityIn).world.playEvent(2004,((EntityLivingBase) entityIn).getPosition(), 0xFF4500);
			}
				}
			break;
					}
				}
			}
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
		public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {

			Multimap<String, AttributeModifier> multimap = HashMultimap.create();

			if (slot == EntityEquipmentSlot.MAINHAND) {
					multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", flavor(stack)==1 ? attackSpeed + 0.7F : attackSpeed, 0));
				switch(flavor(stack))
				{	
				case 1:{
					multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier",damageAromatic+ damage, 0));
					break;
				       }
				case 2:{
					multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier",1f+damage, 0));
					break;
				       }
				case 3:{
					multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier",10f+damage, 0));
					break;
				       }
				
				 }
		
			}
			return multimap;
		}
		public static int flavor(ItemStack stack) {  
			   return  NBTHelper.getTagCompoundSafe(stack).getInteger("flavor");
		}
		public static void setFlavor(ItemStack stack,int flavor) {  
			   NBTHelper.getTagCompoundSafe(stack).setInteger("flavor",flavor);
		}
		@Override
			public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
			ItemStack stack = playerIn.getHeldItem(handIn);
		if(playerIn.isSneaking()) {
			
			 if (!worldIn.isRemote) {
				 switch(getTagCompoundSafe(stack).getInteger("flavor")){
				 case 0:{
					 getTagCompoundSafe(stack).setInteger("flavor",1);  
					 break;
				     } 
				 case 1:{
					 getTagCompoundSafe(stack).setInteger("flavor",2);  
					 break;
				     } 
				 case 2:{
					 getTagCompoundSafe(stack).setInteger("flavor",3); 
					 break;
				     } 
				 case 3:{
					 getTagCompoundSafe(stack).setInteger("flavor",0);  
					 break;
				     } 
		     }
			    }
			 
			 switch(flavor(stack)) {
			 case 0: {
		    	 playerIn.playSound(SoundLoader.EXGLUTENBUR_1, 1f, 1f);
		    	 break;
		              }
			 case 1:{
		    	 playerIn.playSound(SoundLoader.EXGLUTENBUR_2, 1f, 1f);
		    	 break;
		             } 
			 case 2:{
		    	 playerIn.playSound(SoundLoader.EXGLUTENBUR_3, 1f, 1f);
		    	 break;
		             }
			       }
			   }
			    return new ActionResult(EnumActionResult.SUCCESS, stack);
			}

//	public void ModeChange(ItemStack stack,EntityPlayer playerIn) {
//		if(playerIn.isSneaking()) {
//			 if (!playerIn.world.isRemote) {
//				 switch(getTagCompoundSafe(stack).getInteger("flavor")){
//				 case -1:{
//					 getTagCompoundSafe(stack).setInteger("flavor",3);  
//					 break;
//				     } 
//				 case 4:{
//					 getTagCompoundSafe(stack).setInteger("flavor",0);  
//					 break;
//				     } 
//				 }
//		   }
//		}
//	}

}

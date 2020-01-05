package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemOrgaArmorBase;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventOrga {
	@SubscribeEvent
    public static void onOrgaLivingUpdate(LivingUpdateEvent event) {
	EntityLivingBase entity=event.getEntityLiving(); 
	if(entity instanceof EntityPlayer) 
	{
		if(entity.isPotionActive(PotionLoader.potionFlowerHope)&&entity.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration()<=112*20-100)	
			{
			Item mlik =entity.getHeldItemMainhand().getItem();
			ItemStack mlikS =entity.getHeldItemMainhand();
				if(mlik instanceof ItemBucketMilk)
					{
						if(!entity.world.isRemote) 
						{   
							((EntityPlayer)entity).inventory.deleteStack(mlikS);
							((EntityPlayer)entity).dropItem(mlik, 1); 
						}
					}
			}
		if(entity.isPotionActive(PotionLoader.potionFlowerHope)&&
		((EntityPlayer)entity).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))&&
		NBTHelper.getEntityString(((EntityPlayer)entity), HuajiConstant.REQUIEM_OWNER).equals(((EntityPlayer)entity).getUniqueID().toString()))
			{
				entity.removePotionEffect(PotionLoader.potionFlowerHope);
				entity.removePotionEffect(MobEffects.SLOWNESS);
			}
	 
		if(entity.isPotionActive(PotionLoader.potionFlowerHope)&&entity.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration()==112*20-10)
			{
				entity.playSound(SoundLoader.ORGA_FLOWER,5f,1f);
				entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,112*20,5));
			}
	 
		 if(entity.isPotionActive(PotionLoader.potionFlowerHope)&&entity.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration()==52*20)
		 	{
				 entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,52*20,6));
				 entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,52*20));
		 	}
	 
		 if(entity.isPotionActive(PotionLoader.potionRequiem)&&entity.getActivePotionEffect(PotionLoader.potionRequiem).getDuration()==30*20-1)	
		 {
		    if(entity.world.isRemote ) 
			    {
				    Minecraft.getMinecraft().getSoundHandler().stopSounds();
				    HuajiSoundPlayer .playMusic(SoundLoader.ORGA_REQUIEM_GOLD);
			    }
		 }
		if(entity.isPotionActive(PotionLoader.potionFlowerHope)&&entity.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration()<10) 
			{
				entity.attackEntityFrom(new DamageSource(HuajiConstant.HOPE_FLOWER), 9999f);
				if(((EntityPlayer) entity).isCreative()) 
					{
						((EntityPlayer) entity).dropItem(true);
						entity.setHealth(0f);
					}		
			}
	}
	try { 
		if(entity.isPotionActive(PotionLoader.potionOrgaTarget)&&entity.getActivePotionEffect(PotionLoader.potionOrgaTarget).getDuration()==30)
			{ 
				EntityPlayer player= entity.world.getClosestPlayerToEntity(entity, 100);
					if(player!=null&&entity!=player) 
					{
						if(!player.inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))) 
						{
							player.sendMessage(new TextComponentTranslation("message.huaji.orga.shot")); 
						}
						entity.attackEntityFrom(new EntityDamageSource(HuajiConstant.ORGA_SHOT,player), 30f);		
					}
					else 
						{
							entity.attackEntityFrom(new DamageSource(HuajiConstant.ORGA_SHOT), 30f);	                             	 
						}
			}  
		}catch(Exception e){e.printStackTrace();}}
	@SubscribeEvent
	public static void onOrgaPlayerDeath(LivingDeathEvent event) {
	EntityLivingBase player=event.getEntityLiving(); 
	if(player instanceof EntityPlayer)
		{
			if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem()   instanceof ItemOrgaArmorBase &&
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem()    instanceof ItemOrgaArmorBase &&
				player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem()     instanceof ItemOrgaArmorBase   )
				{
					if(!player.isPotionActive(PotionLoader.potionFlowerHope)&&!player.isPotionActive(PotionLoader.potionRequiem)) 
					{
						if(((EntityPlayer)player).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))&&
							NBTHelper.getEntityString(player, HuajiConstant.REQUIEM_OWNER).equals(player.getUniqueID().toString()))
							{
								player.addPotionEffect(new PotionEffect(PotionLoader.potionRequiem,30*20));
							}
							else 
							{
								player.addPotionEffect(new PotionEffect(PotionLoader.potionFlowerHope,112*20+100));
							}
					}
				  if(player.isPotionActive(PotionLoader.potionFlowerHope)&&player.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration()>=10||!player.isPotionActive(PotionLoader.potionFlowerHope))
					{
						event.setCanceled(true);
						player.setHealth(1f);
					}
				}
			}}
	@SubscribeEvent
	public static void onOrgaPlayerHurt(LivingHurtEvent evt) {
	EntityLivingBase player=evt.getEntityLiving(); 
	Entity attacker=evt.getSource().getTrueSource();
	if(player instanceof EntityPlayer)
		{
		if(	player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem()  instanceof ItemOrgaArmorBase &&
			player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
			player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem()    instanceof ItemOrgaArmorBase &&
			player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem()     instanceof ItemOrgaArmorBase &&
			attacker instanceof EntityLivingBase&&attacker!=null&&attacker!=player)
				{
				if(((EntityPlayer)player).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))&&
					NBTHelper.getEntityString(((EntityPlayer)player), HuajiConstant.REQUIEM_OWNER).equals(((EntityPlayer)player).getUniqueID().toString()))
					{
						((EntityLivingBase)attacker).attackEntityFrom(new EntityDamageSource(HuajiConstant.REQUIEM_BACK,player),evt.getAmount()*2);
					}    		
					if(player.getHealth()<2) 
						{		
							Vec3d targetPosition = player.getPositionVector();  	 
							if(!((EntityPlayer)player).inventory.hasItemStack(new ItemStack(ItemLoader.orgaRequiem))) 
				            	{  
									HuajiSoundPlayer.playToNearbyClient(player, SoundLoader.ORGA_SHOT, 1f);
									HuajiAgeNetWorkHandler.sendToNearby(player.world, player, new MessageParticleGenerator(targetPosition, EnumParticleTypes.SPELL_INSTANT, 150, 2, 1));
				            	}else 
				            	{
									HuajiAgeNetWorkHandler.sendToNearby(player.world, player, new MessageParticleGenerator(targetPosition, EnumParticleTypes.FIREWORKS_SPARK, 200, 2, 1));
				            	}

							if(!((EntityLivingBase)attacker).isPotionActive(PotionLoader.potionOrgaTarget)) 
								{
									((EntityLivingBase)attacker).addPotionEffect(new PotionEffect(PotionLoader.potionOrgaTarget,100));  
								}
						  }
					}
			}}
}

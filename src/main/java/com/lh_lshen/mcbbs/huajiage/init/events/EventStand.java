package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventStand {
	 private static final ModelTheWorld THE_WORLD_MODEL = new ModelTheWorld();
	 private static final ResourceLocation THE_WORLD_TEX = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_the_world.png");
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderStandFirst(RenderHandEvent event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;
		boolean f1 = Minecraft.getMinecraft().gameSettings.hideGUI;

		if (stack.getItem() != ItemLoader.roadRoller &&player.isPotionActive(PotionLoader.potionStand) && perspective == 0 && !f1)
		{
			if (Loader.isModLoaded("realrender") || Loader.isModLoaded("rfpr"))
				return;

			GlStateManager.pushMatrix();

			GlStateManager.enableBlend();

			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.6F);
			EventStand.setLightmapDisabled(false);

			Minecraft.getMinecraft().getTextureManager().bindTexture(THE_WORLD_TEX);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			
			THE_WORLD_MODEL.setRotationAngles(0, 0, player.ticksExisted, 0, 0, 1, player ,0.5f);
			THE_WORLD_MODEL.handl1.render(1f);
			THE_WORLD_MODEL.handl2.render(1f);
			THE_WORLD_MODEL.handl3.render(1f);
			THE_WORLD_MODEL.handl4.render(1f);
			THE_WORLD_MODEL.handl5.render(1f);
			THE_WORLD_MODEL.handr1.render(1f);
			THE_WORLD_MODEL.handr2.render(1f);
			THE_WORLD_MODEL.handr3.render(1f);
			THE_WORLD_MODEL.handr4.render(1f);
			THE_WORLD_MODEL.handr5.render(1f);
			
//			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.2F);
			
			EventStand.setLightmapDisabled(true);

			if (perspective == 0)
			{
				event.setCanceled(true);
			}

			GlStateManager.disableBlend();
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();

		}
	}
	 @SubscribeEvent
	  public static void onTheWorldStand(LivingUpdateEvent evt)
	  {
		  EntityLivingBase eater =evt.getEntityLiving(); 
		  List<Entity> entityCllection = eater.getEntityWorld().getEntitiesWithinAABB(Entity.class, eater.getEntityBoundingBox().grow(2, 2, 2));
		  if(!eater.isPotionActive(PotionLoader.potionStand)) {
			  return;
		  }
		  if(eater.isPotionActive(PotionLoader.potionStand)) {
			  for(Entity i:entityCllection) {
				  if(i instanceof IProjectile || i instanceof EntityFireball) {
					  Vec3d eater_pos=eater.getPositionEyes(0);
					  BlockPos target_pos=i.getPosition();
					  Vec3d back = new Vec3d(target_pos.getX()-eater_pos.x, target_pos.getY()-eater_pos.y, target_pos.getZ()-eater_pos.z).normalize();
					  if(NBTHelper.getEntityInteger(eater, HuajiConstant.THE_WORLD)<=0) {
					  	  i.motionX=back.x;
						  i.motionY=back.y;
						  i.motionZ=back.z;
						  }
				  		}
				  if(i instanceof EntityLivingBase) {
					  EntityLivingBase target=(EntityLivingBase)i;
						  if(target!=eater) {
							  BlockPos eater_pos=eater.getPosition();
							  BlockPos target_pos=target.getPosition();
							  Vec3d back = new Vec3d(target_pos.getX()-eater_pos.getX(), target_pos.getY()-eater_pos.getY(), target_pos.getZ()-eater_pos.getZ()).normalize();
							  if(eater instanceof EntityPlayerMP) {
								  EntityPlayerMP player =(EntityPlayerMP) eater;
								  if(target.isSpectatedByPlayer(player)) {
									  if(NBTHelper.getEntityInteger(target, HuajiConstant.DIO_HIT)<60) {
										  NBTHelper.setEntityInteger(target, HuajiConstant.DIO_HIT, 60);
										  HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE);
									  }
									  if(target.ticksExisted%2==0) {
										  target.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(0).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
									  }
								  }
							  }else {
								  if(NBTHelper.getEntityInteger(target, HuajiConstant.DIO_HIT)<60) {
									  NBTHelper.setEntityInteger(target, HuajiConstant.DIO_HIT, 60);
									  HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE);
								  }
								  if(target.ticksExisted%2==0) {
									  target.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(0).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
								  }
							  }
							  target.motionX=back.x;
							  target.motionY=back.y;
							  target.motionZ=back.z;
						  }
				  }
			  }
		  }
 	  }
	  @SubscribeEvent
	  public static void onBreaking(PlayerEvent.BreakSpeed evt)
	  {
		  if(ConfigHuaji.Huaji.allowTheWorldDestory) {
			  float op =evt.getOriginalSpeed();
			  if(evt.getEntityPlayer().isPotionActive(PotionLoader.potionStand)) {
				  evt.setNewSpeed(op*30);
			  }else {
				  evt.setNewSpeed(op);
			  }
			  if(NBTHelper.getEntityInteger(evt.getEntityPlayer(), HuajiConstant.THE_WORLD)>0) {
				  EventStand.standPower(evt.getEntityPlayer());
			  }
		  }
	  }
//	  @SubscribeEvent
//	  public static void onBreak(BlockEvent.HarvestDropsEvent evt)
//	  {
//		  if(ConfigHuaji.Huaji.allowTheWorldDestory) {
//			  if(NBTHelper.getEntityInteger(evt.getHarvester(), HuajiConstant.THE_WORLD)>0) {
//				  evt.isSilkTouching();
//			  }
//		  }
//	  }
	  public static void standPower(EntityPlayer player) {
			  if(!player.isPotionActive(PotionLoader.potionStand)) {
				  player.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  HuajiSoundPlayer.playToNearbyClient(player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
				  ServerUtil.sendPacketToNearbyPlayers(player, new MessageParticleGenerator(player.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK));
			  }
			  if(player.isPotionActive(PotionLoader.potionStand)&&player.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  player.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	@SideOnly(Side.CLIENT)
	private static void setLightmapDisabled(boolean disabled)
	{
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);

		if (disabled)
		{
			GlStateManager.disableTexture2D();
		}
		else
		{
			GlStateManager.enableTexture2D();
		}

		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

}

package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.network.MessageLeftClick;
import com.lh_lshen.mcbbs.huajiage.network.MessageLeftClickRoadRoller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRoadRoller extends Item {
	public ItemRoadRoller()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  this.maxStackSize=1;
		  MinecraftForge.EVENT_BUS.register(this);
	}
 
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		 ItemStack itemstack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote) {
		EntityRoadRoller road=new EntityRoadRoller(worldIn, playerIn);
		Vec3d v1=playerIn.getLookVec();
		road.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1F,1F,0f);
		float fn=MathHelper.sqrt(v1.x*v1.x+v1.y*v1.y+v1.z*v1.z);
		road.posX+=v1.x/fn;
		road.posY+=ConfigHuaji.Huaji.knifeHeight +0.1f +v1.y/fn;
		road.posZ+=v1.z/fn;
		road.setRotation(MathHelper.wrapDegrees(-playerIn.rotationYaw));
		road.setPitch(playerIn.rotationPitch);
		road.setLife(512f);
		if(!playerIn.isCreative()) {
		itemstack.shrink(1);}
		worldIn.spawnEntity(road);
		}
		playerIn.playSound(SoundLoader.ROAD_ROLLER, 2f,1f);
		playerIn.swingArm(handIn);
//		 if (!worldIn.isRemote) {
//	            playerIn.setActiveHand(handIn);
//	        }
//		
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	@SubscribeEvent
	public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
            EntityPlayer player = evt.getEntityPlayer();
            List<EntityRoadRoller> road=player.getEntityWorld().getEntitiesWithinAABB(EntityRoadRoller.class,player.getEntityBoundingBox().grow(10));
            boolean flag=false;
            if(road!=null) {
            	for(EntityRoadRoller r:road) {
            		if(r.motionX==0&&r.motionY==0&&r.motionZ==0) {
            			flag=true;
            		}else {
            			flag=false;
            		}
            	}
            if(flag) {
            HuajiAgeNetWorkHandler.sendToServer(new MessageLeftClickRoadRoller());
//            Random random = new Random();
//    	    final int NUMBER_OF_PARTICLES = 50;
//    	    final double HORIZONTAL_SPREAD = 2; 
//    	    Vec3d targetCoordinates = player.getPositionVector();
//    	    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
//    	      double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
//    	      double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
//    	      double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
//    	      player.getEntityWorld().spawnParticle(EnumParticleTypes.LAVA, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
//    	    }
//    	    player.playSound(SoundLoader.DIO_HIT,1f, 1f);
    	    
        	          
         }


                }
                
            }
		

}

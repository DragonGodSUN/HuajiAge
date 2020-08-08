package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageLeftClickRoadRoller;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ItemRoadRoller extends Item {
	public ItemRoadRoller()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
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
			road.posY+=0.1f +v1.y/fn;
			road.posZ+=v1.z/fn;
			road.setRotation(MathHelper.wrapDegrees(-playerIn.rotationYaw));
			road.setPitch(playerIn.rotationPitch);
			road.setDamage(10f);
			road.setLife(512f);
			if(!playerIn.isCreative()) {
			itemstack.shrink(1);}
			worldIn.spawnEntity(road);
		}
		playerIn.playSound(SoundLoader.ROAD_ROLLER, 2f,1f);
		playerIn.swingArm(handIn);		
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	@SubscribeEvent
	public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
            EntityPlayer player = evt.getEntityPlayer();
            List<EntityRoadRoller> road=player.getEntityWorld().getEntitiesWithinAABB(EntityRoadRoller.class,player.getEntityBoundingBox().grow(20));
            boolean flag=false;
            if(road!=null)
            {
            	for(EntityRoadRoller r:road) {
            		if(r.motionX==0&&r.motionY==0&&r.motionZ==0) {
            			flag=true;
            		}else {
            			flag=false;
            		}
            	}
            if(flag) 
	            {
	            StandNetWorkHandler.sendToServer(new MessageLeftClickRoadRoller());
	            }
            } }

}

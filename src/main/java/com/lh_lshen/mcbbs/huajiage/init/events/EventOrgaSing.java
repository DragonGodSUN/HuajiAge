package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = HuajiAge.MODID , value = Side.CLIENT)
public class EventOrgaSing {
	public static int index;
	@SubscribeEvent
    public static void onOrgaLivingUpdate(LivingUpdateEvent event) {
	EntityLivingBase entity=event.getEntityLiving(); 
	World world = event.getEntityLiving().world;
	boolean flag1 = entity.isPotionActive(PotionLoader.potionFlowerHope);
	if(entity instanceof EntityPlayer) 
	{
		if(world.isRemote) {
			if(flag1 && !ConfigHuaji.Huaji.useOrgaFlower) {
				int duration = entity.getActivePotionEffect(PotionLoader.potionFlowerHope).getDuration();
				if(duration==80*20-10) {
					entity.playSound(SoundEvents.UI_TOAST_OUT, 1f, 1f);
					entity.sendMessage(new TextComponentTranslation("message.huajiage.orga.sing.1"));
					index=2;
					}
				if(duration<80*20-5) {
					if(index<=18&&index!=0) {
						if(duration%80==0) {
						entity.playSound(SoundEvents.UI_TOAST_OUT, 1f, 1f);
						entity.sendMessage(new TextComponentTranslation("message.huajiage.orga.sing."+index));
						index++;
							}
						}else {
							if(index>0) {
								index=0;
							}
						}
					}
				if(duration== 80*10-20) {
					for(int i =1; i< 6; i++) {
						entity.sendMessage(new TextComponentTranslation("message.huajiage.orga.sing.p"+i));
						}
					}
				if(duration== 80*10-40) {
					for(int i =6; i<12; i++) {
						entity.sendMessage(new TextComponentTranslation("message.huajiage.orga.sing.p"+i));
						}
					}
				if(duration== 80*10-60) {
					for(int i =12; i<18; i++) {
						entity.sendMessage(new TextComponentTranslation("message.huajiage.orga.sing.p"+i));
						}
					}
				}
			}
		}
	}
}

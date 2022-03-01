package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventItemResistance {
//	@SubscribeEvent
//	public static void onItemUpdate(ItemEvent event) {
//		EntityItem entity=event.getEntityItem();
//		if(entity != null) {
//			ItemStack itemStack = entity.getItem();
//			if (itemStack.getItem().equals(ItemLoader.disc)){
//				NBTTagCompound compound = entity.getEntityData();
//				if (compound.hasKey("Health") && compound.getShort("Health")<5000){
//					compound.setShort("Health", (short) 9999);
//					entity.readEntityFromNBT(compound);
//				};
//			}
//		}
//	}
	
}

package com.lh_lshen.mcbbs.huajiage.client.events;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustom;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventDiscModel {
	 @SubscribeEvent
	    public static void registerModelsDisc(ModelRegistryEvent event) {
		 List<Pair<ModelResourceLocation,String>> models = Lists.newArrayList();
		 
		 models.add(Pair.of(new ModelResourceLocation(new ResourceLocation(HuajiAge.MODID,"disc/"+"disc_"+"null"), "inventory"),"empty"));
		 for(IStand b : HuajiAgeAPI.getStandList()) {
		 String stand = ((StandBase)b).getName();
		 if(StandLoader.STAND_LIST.contains(b)){
		 	models.add(Pair.of(new ModelResourceLocation(new ResourceLocation(HuajiAge.MODID,"disc/"+"disc_"+stand), "inventory"),stand));
		 	}else {
				if(b instanceof StandCustom){
					StandCustom custom = (StandCustom) b;
					StandCustomInfo info = custom.getInfo();
					models.add(Pair.of(new ModelResourceLocation(info.getDisc(), "inventory"), stand));
				}
		 	}
		 }
		 
		 for(Pair<ModelResourceLocation,String> p: models){
		 ModelBakery.registerItemVariants(ItemLoader.disc,Objects.requireNonNull(p.getLeft()));
		 }
//		 ModelResourceLocation mrl = new ModelResourceLocation(ItemLoader.disc.getRegistryName()+"_null", "inventory");
//		 ModelBakery.registerItemVariants(ItemLoader.disc,mrl);
		 ModelLoader.setCustomMeshDefinition(ItemLoader.disc, stack->{
		 String stand = NBTHelper.getTagCompoundSafe(stack).getString("StandId");
		 if(StandLoader.getStand(stand)!=null ) {
			 for(Pair<ModelResourceLocation,String> pair : models) {
 			 if(stand.equals(pair.getRight())) {
 				 return pair.getLeft();}}}
		 	return models.get(0).getLeft();
		 		});
	 		}

}

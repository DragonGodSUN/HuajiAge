package com.lh_lshen.mcbbs.huajiage.client.events;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.tesr.TileEntityNewLandManRenderer;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventDiscModel {
	 @SubscribeEvent
	    public static void registerModelsDisc(ModelRegistryEvent event) {
		 List<Pair<ModelResourceLocation,String>> models = Lists.newArrayList();
		 
		 models.add(Pair.of(new ModelResourceLocation(ItemLoader.disc.getRegistryName()+"_null", "inventory"),"empty"));
		 for(StandBase b : StandLoader.STAND_LIST) {
		 String stand = b.getName();
		 models.add(Pair.of(new ModelResourceLocation(ItemLoader.disc.getRegistryName()+"_"+stand, "inventory"),stand));
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
		 	return models.get(1).getLeft();
		 		});
	 		}

}

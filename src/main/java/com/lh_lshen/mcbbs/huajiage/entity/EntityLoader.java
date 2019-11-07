package com.lh_lshen.mcbbs.huajiage.entity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderHeroArrow;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderMultiKnife;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderPlayer;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderRoadRoller;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderSecondFoil;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArmorStand;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	  private static int nextID = 0;

	    public EntityLoader()
	    {
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "SecondFoil")), EntitySecondFoil.class, "SecondFiol", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "HeroArrow")), EntityHeroArrow.class, "HeroArrow", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "MuliKnife")), EntityMultiKnife.class, "MuliKnife", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "RoadRoller")), EntityRoadRoller.class, "RoadRoller", 80, 3, true);
	        
	    }

	    private static void registerEntity(ResourceLocation nameg,Class<? extends Entity> entityClass, String name, int trackingRange,
	            int updateFrequency, boolean sendsVelocityUpdates)
	    {
	        EntityRegistry.registerModEntity(nameg, entityClass, name, nextID++, HuajiAge.instance, trackingRange, updateFrequency,
	                sendsVelocityUpdates);
	    }

	    @SideOnly(Side.CLIENT)
	    public static void registerRenders()
	    {
	    	RenderingRegistry.registerEntityRenderingHandler(EntitySecondFoil.class, manage -> new RenderSecondFoil(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityHeroArrow.class, manage -> new RenderHeroArrow(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityMultiKnife.class, manage -> new RenderMultiKnife(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityRoadRoller.class, manage -> new RenderRoadRoller(manage));
	    
	    }
}
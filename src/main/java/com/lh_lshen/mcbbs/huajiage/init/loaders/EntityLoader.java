package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderFivePower;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderHeroArrow;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderMultiKnife;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderOrgaHairKnife;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderRoadRoller;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderSecondFoil;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderSheerHeartAttack;
import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderStandBase;
import com.lh_lshen.mcbbs.huajiage.entity.*;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	  private static int nextID = 0;

	    public EntityLoader()
	    {
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "SecondFoil")), EntitySecondFoil.class, "SecondFiol", 150, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "HeroArrow")), EntityHeroArrow.class, "HeroArrow", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "MuliKnife")), EntityMultiKnife.class, "MuliKnife", 100, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "RoadRoller")), EntityRoadRoller.class, "RoadRoller", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "StandBase")), EntityStandBase.class, "StandBase", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "EmeraldBullet")), EntityEmeraldBullet.class, "EmeraldBullet", 100, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "FivePower")), EntityFivePower.class, "FivePower", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "OrgaHairKnife")), EntityOrgaHairKnife.class, "OrgaHairKnife", 80, 3, true);
	        registerEntity((new ResourceLocation(HuajiAge.MODID, "SheerHeartAttack")), EntitySheerHeartAttack.class, "SheerHeartAttack", 100, 3, true);
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
	    	RenderingRegistry.registerEntityRenderingHandler(EntityStandBase.class, manage -> new RenderStandBase(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityEmeraldBullet.class, manage -> new RenderEmeraldBullet(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityFivePower.class, manage -> new RenderFivePower(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntityOrgaHairKnife.class, manage -> new RenderOrgaHairKnife(manage));
	    	RenderingRegistry.registerEntityRenderingHandler(EntitySheerHeartAttack.class, manage -> new RenderSheerHeartAttack(manage));
	    
	    }
}
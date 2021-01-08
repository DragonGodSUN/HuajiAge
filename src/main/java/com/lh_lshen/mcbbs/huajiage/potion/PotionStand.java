package com.lh_lshen.mcbbs.huajiage.potion;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionStand extends Potion {
	public PotionStand() {
		super(false, 0xffffff);
        this.setPotionName("potion.huajiage.stand");
        this.setIconIndex(64, 0);
       
	}
	
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
 
        this.renderHUDEffect(x+2, y+5, effect, mc, 1F);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
     
        mc.getTextureManager().bindTexture(new ResourceLocation(HuajiAge.MODID + ":" + "potions/potion_01.png"));

        Gui.drawModalRectWithCustomSizedTexture(x+2, y+2 , 0, 71, 18, 18, 256, 256);
    }
    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
    	
    	super.performEffect(entityLivingBaseIn, amplifier);
    }
}
	



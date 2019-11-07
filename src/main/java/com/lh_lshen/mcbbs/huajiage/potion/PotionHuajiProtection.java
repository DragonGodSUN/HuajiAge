package com.lh_lshen.mcbbs.huajiage.potion;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionHuajiProtection extends Potion {

	protected PotionHuajiProtection() {
		super(false, 0xFFFF00);
        this.setPotionName("potion.huaji");
        this.setIconIndex(0, 0);
        this.registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, "96bf9890-6a94-11e9-a923-1681be663d3e", 10, 0);
	}
    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
 
        this.renderHUDEffect(x, y+5, effect, mc, 1F);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
     
        mc.getTextureManager().bindTexture(new ResourceLocation(HuajiAge.MODID + ":" + "potions/potion_01.png"));

        Gui.drawModalRectWithCustomSizedTexture(x +5, y , 0, 0, 18, 18, 256, 256);
    }
    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
    	
    	super.performEffect(entityLivingBaseIn, amplifier);
    }
}
	



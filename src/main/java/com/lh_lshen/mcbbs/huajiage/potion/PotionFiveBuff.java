package com.lh_lshen.mcbbs.huajiage.potion;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionFiveBuff extends Potion {

	protected PotionFiveBuff() {
		super(false, 0xFFFF00);
        this.setPotionName("potion.huaji.five");
        this.setIconIndex(0, 0);
//        this.registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, "ebb91868-6aed-11e9-a923-1681be663d3e",5, 1);
//        this.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "05fd4064-6aee-11e9-a923-1681be663d3e",5, 1);
//        this.registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, "1cb6e486-6aee-11e9-a923-1681be663d3e",5, 1);
//        this.registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "1cb6e710-6aee-11e9-a923-1681be663d3e",5, 1);
//        this.registerPotionAttributeModifier(SharedMonsterAttributes.FLYING_SPEED, "1cb6ebb6-6aee-11e9-a923-1681be663d3e",5, 1);
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

        Gui.drawModalRectWithCustomSizedTexture(x , y , 24, 0, 20, 20, 256, 256);
    }
}
	



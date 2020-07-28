package com.lh_lshen.mcbbs.huajiage.client.model.pojo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EntityStandWrapper {
    private EntityPlayer player;
    public float swingProgress;
    public boolean isRiding;

    public void setData(EntityPlayer player, float swingProgress, boolean isRiding) {
        this.player = player;
        this.swingProgress = swingProgress;
        this.isRiding = isRiding;
    }

    public boolean hasHelmet() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
    }

    public boolean hasChestPlate() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
    }

    public boolean hasLeggings() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty();
    }

    public boolean hasBoots() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty();
    }

    public boolean isRiding() {
        return isRiding;
    }


}

package com.lh_lshen.mcbbs.huajiage.client.model.custom;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleplayer
 */

import com.github.tartaricacid.touhoulittlemaid.TouhouLittleMaid;
import com.github.tartaricacid.touhoulittlemaid.entity.item.AbstractEntityTrolley;
import com.github.tartaricacid.touhoulittlemaid.entity.item.EntityMarisaBroom;
import com.github.tartaricacid.touhoulittlemaid.entity.item.EntityPortableAudio;
import com.github.tartaricacid.touhoulittlemaid.init.MaidItems;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class EntityStandWrapper {
    private EntityPlayer player;
    public float swingProgress;
    public boolean isRiding;
    private WorldWrapper world;
    private Biome biome;

    public void setData(EntityPlayer player, float swingProgress, boolean isRiding) {
        this.player = player;
        this.swingProgress = swingProgress;
        this.isRiding = isRiding;
        this.world = new WorldWrapper(player.world);
        this.biome = player.world.getBiome(player.getPosition());
    }

    public WorldWrapper getWorld() {
        return world;
    }

    public String getTask() {
        return new ResourceLocation(TouhouLittleMaid.MOD_ID, "idle").toString();
    }

    public boolean hasHelmet() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
    }

    public String getHelmet() {
        ResourceLocation res = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean hasChestPlate() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty();
    }

    public String getChestPlate() {
        ResourceLocation res = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean hasLeggings() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty();
    }

    public String getLeggings() {
        ResourceLocation res = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean hasBoots() {
        return !player.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty();
    }

    public String getBoots() {
        ResourceLocation res = player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean hasItemMainhand() {
        return player.getHeldItemMainhand().isEmpty();
    }

    public String getItemMainhand() {
        ResourceLocation res = player.getHeldItemMainhand().getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean hasItemOffhand() {
        return player.getHeldItemOffhand().isEmpty();
    }

    public String getItemOffhand() {
        ResourceLocation res = player.getHeldItemOffhand().getItem().getRegistryName();
        if (res != null) {
            return res.toString();
        }
        return "";
    }

    public boolean isBegging() {
        return player.isSneaking();
    }

    public boolean isRiding() {
        return isRiding;
    }

    public boolean isSwingingArms() {
        return player.isSwingInProgress;
    }

    public boolean isSitting() {
        return player.isSilent();
    }

    public boolean hasBackpack() {
        return CommonProxy.ModsLoader.isTouhouMaidLoaded() && player.inventory.hasItemStack(new ItemStack(MaidItems.MAID_BACKPACK));
    }

    public int getBackpackLevel() {
        return (int)player.experienceLevel/10;
    }

    public boolean hasSasimono() {
        return player.isCreative();
    }

    public boolean inWater() {
        return player.isInWater();
    }

    public boolean inRain() {
        return player.world.isRainingAt(player.getPosition());
    }

    public String getAtBiome() {
        ResourceLocation res = biome.getRegistryName();
        if (res != null) {
            return res.getPath();
        }
        return "";
    }

    public String getAtBiomeTemp() {
        return biome.getTempCategory().name();
    }

    public boolean isHoldTrolley() {
        return CommonProxy.ModsLoader.isTouhouMaidLoaded()&&(player.getControllingPassenger() instanceof AbstractEntityTrolley);
    }

    public boolean isRidingMarisaBroom() {
        return CommonProxy.ModsLoader.isTouhouMaidLoaded()&&player.getControllingPassenger() instanceof EntityMarisaBroom;
    }

    public boolean isRidingPlayer() {
        return player.getRidingEntity() instanceof EntityPlayer;
    }

    public boolean isHoldVehicle() {
//        return player.getControllingPassenger() instanceof EntityplayerVehicle;
        return false;
    }

    public boolean isPortableAudioPlay() {
        if (player.getControllingPassenger() instanceof EntityPortableAudio) {
            EntityPortableAudio audio = (EntityPortableAudio) player.getControllingPassenger();
            return audio.isPlaying();
        }
        return false;
    }

    public boolean isSwingLeftHand() {
        return player.swingingHand == EnumHand.OFF_HAND;
    }

    public float getSwingProgress() {
        return swingProgress;
    }

    public float[] getLeftHandRotation() {
//        if (player.getControllingPassenger() instanceof EntityplayerVehicle) {
//            return ((EntityplayerVehicle) player.getControllingPassenger()).getplayerHandRotation(EnumHand.OFF_HAND);
//        }
        return new float[]{0, 0, 0};
    }

    public float[] getRightHandRotation() {
//        if (player.getControllingPassenger() instanceof EntityplayerVehicle) {
//            return ((EntityplayerVehicle) player.getControllingPassenger()).getplayerHandRotation(EnumHand.MAIN_HAND);
//        }
        return new float[]{0, 0, 0};
    }

    public int getDim() {
        return player.dimension;
    }

    public float getHealth() {
        return player.getHealth();
    }

    public float getMaxHealth() {
        return player.getMaxHealth();
    }

    public double getArmorValue() {
        return player.getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();
    }

    public boolean hasAttackTarget() {
        return player.getRevengeTarget() != null;
    }

    public boolean onHurt() {
        return player.hurtTime > 0;
    }

}

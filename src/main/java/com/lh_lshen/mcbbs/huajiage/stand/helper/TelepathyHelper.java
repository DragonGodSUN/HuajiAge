package com.lh_lshen.mcbbs.huajiage.stand.helper;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;

public class TelepathyHelper {

    public static String telepathizeItem(Entity entity, String id){
        String result = "null";
        if(!entity.world.isRemote){
            BlockPos pos = new BlockPos(0,0,0);
            if (Items.ENDER_EYE.getRegistryName().toString().equals(id)) {
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Stronghold",entity.getPosition(),false);
                    result ="enderPortal";
                }else {
                    result ="dimension.miss";
                }
            }else if (Items.EMERALD.getRegistryName().toString().equals(id)){
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Village",entity.getPosition(),false);
                    result ="village";
                }else {
                    result ="dimension.miss";
                }
            }else if (Items.END_CRYSTAL.getRegistryName().toString().equals(id)){
                if (entity.dimension == 1) {
                    pos = ((WorldServer)entity.world).findNearestStructure("EndCity",entity.getPosition(),false);
                    result ="endCity";
                }else {
                    result ="dimension.miss";
                }
            }else if (Blocks.GOLD_BLOCK.getRegistryName().toString().equals(id)){
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Temple",entity.getPosition(),false);
                    result ="temple";
                }else {
                    result ="dimension.miss";
                }
            }else if (Blocks.EMERALD_BLOCK.getRegistryName().toString().equals(id)){
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Mansion",entity.getPosition(),false);
                    result ="mansion";
                }else {
                    result ="dimension.miss";
                }
            }else if (Blocks.BEACON.getRegistryName().toString().equals(id)){
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Monument",entity.getPosition(),false);
                    result ="monument";
                }else {
                    result ="dimension.miss";
                }
            }else if (Items.MINECART.getRegistryName().toString().equals(id)){
                if (entity.dimension == 0) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Mineshaft",entity.getPosition(),false);
                    result ="mineshaft";
                }else {
                    result ="dimension.miss";
                }
            }else if (Blocks.NETHER_BRICK.getRegistryName().toString().equals(id)){
                if (entity.dimension == -1) {
                    pos = ((WorldServer)entity.world).findNearestStructure("Fortress",entity.getPosition(),false);
                    result ="fortress";
                }else {
                    result ="dimension.miss";
                }
            }else {
                entity.sendMessage(new TextComponentTranslation("stand.huajiage.skill.huajiage.hermit_purple.telepathy.item_null"));
            }
            entity.sendMessage(new TextComponentString("***"+pos.getX()+"***"+pos.getY()+"***"+pos.getZ()+"***"));
        }
        return result;

    }

//    public static String telepathizePosition(Entity entity, BlockPos pos, String target,String name, String item, String id, int dimension) {
//        if (item.equals(id)){
//            if (entity.dimension == dimension) {
//                pos = ((WorldServer)entity.world).findNearestStructure(target,entity.getPosition(),false);
//                return name;
//            }else {
//                return "dimension.miss";
//            }
//        }
//        return "null";
//    }
}

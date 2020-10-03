package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventCrazyDiamond {

    @SubscribeEvent
    public static void onStandChangeMat(PlayerInteractEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EnumFacing face = event.getFace();
        boolean isR = event.getClass().equals(PlayerInteractEvent.RightClickBlock.class);
        IBlockState blockState = world.getBlockState(pos);
        StandBase stand = StandUtil.getType(player);
        if( stand!=null && player.isPotionActive(PotionLoader.potionStand))
        {
        String state = StandUtil.getStandState(player);
        StandStateBase stateBase = StandStates.getStandState(stand.getName(),state);
        if(stateBase.hasExtraData(EnumStandTag.StateTags.BLOCK_MOVE.getName())){
            if(player.world.getTileEntity(pos) == null){
                if(!(blockState.getBlock() instanceof BlockAir)){
                    BlockPos newPos = pos;
                    if (face != null) {
                        switch (face){
                            case UP:{
                                newPos = pos.offset(isR?EnumFacing.UP:EnumFacing.DOWN,1);
                                break;
                                }
                            case DOWN:{
                                newPos = pos.offset(isR?EnumFacing.DOWN:EnumFacing.UP,1);
                                break;
                                }
                            case EAST:{
                                newPos = pos.offset(isR?EnumFacing.EAST:EnumFacing.WEST,1);
                                break;
                                }
                            case WEST:{
                                newPos = pos.offset(isR?EnumFacing.WEST:EnumFacing.EAST,1);
                                break;
                                }
                            case NORTH:{
                                newPos = pos.offset(isR?EnumFacing.NORTH:EnumFacing.SOUTH,1);
                                break;
                                }
                            case SOUTH:{
                                newPos = pos.offset(isR?EnumFacing.SOUTH:EnumFacing.NORTH,1);
                                break;
                                }
                        }
                    }

                    if (ConfigHuaji.Stands.allowCrazyDiamondBlock && world.getBlockState(newPos).getBlock().canPlaceBlockAt(world,newPos)) {
                        world.setBlockToAir(pos);
                        world.setBlockState(newPos,blockState);
                        world.notifyNeighborsOfStateChange(pos, Blocks.AIR,true);
                        world.playEvent(2001, pos, Block.getStateId(blockState));
                    }
                }
            }
        }
        }
    }

}

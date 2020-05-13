package com.lh_lshen.mcbbs.huajiage.tileentity;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityNewLandMan extends TileEntity {
    private boolean isRender = false;
    private IBlockState blockState = Blocks.AIR.getDefaultState();
    private List<BlockPos> blockPosList = Lists.newArrayList();

    public void setData(IBlockState blockState, boolean isRender, List<BlockPos> blockPosList) {
        this.isRender = isRender;
        this.blockPosList = blockPosList;
        this.blockState = blockState;
        refresh();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        getTileData().setBoolean(NBT.IS_RENDER.getName(), isRender);
        getTileData().setInteger(NBT.STORAGE_STATE_ID.getName(), Block.getStateId(blockState));
        NBTTagList blockList = new NBTTagList();
        for (BlockPos pos : blockPosList) {
            blockList.appendTag(NBTUtil.createPosTag(pos));
        }
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        isRender = getTileData().getBoolean(NBT.IS_RENDER.getName());
        blockState = Block.getStateById(getTileData().getInteger(NBT.STORAGE_STATE_ID.getName()));
        blockPosList.clear();
        NBTTagList blockList = getTileData().getTagList(NBT.STORAGE_BLOCK_LIST.getName(), Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < blockList.tagCount(); i++) {
            blockPosList.add(NBTUtil.getPosFromTag(blockList.getCompoundTagAt(i)));
        }

    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.add(-1, -4, -1), pos.add(1, 4, 1));
    }

    @Nonnull
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nonnull
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), -1, writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    public void refresh() {
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
        }
    }

    public boolean isRender() {
        return isRender;
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public List<BlockPos> getBlockPosList() {
        return blockPosList;
    }

    public enum NBT {  
        IS_RENDER("IsRender"),
        
        STORAGE_STATE_ID("StorageBlockStateId"),
        
        STORAGE_BLOCK_LIST("StorageBlockList");
    	
        private String name;
    
        NBT(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

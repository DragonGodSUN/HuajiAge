package com.lh_lshen.mcbbs.huajiage.block.mutiblock;

import java.util.List;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.IMultiBlock;
import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
public class MutiBlockNewLandMan implements IMultiBlock {
    private static final ResourceLocation NEW_LAND_MAN = new ResourceLocation(HuajiAge.MODID, "new_land_man");
    private static final BlockPos POS = new BlockPos(0,0,0);

    @Override
    public boolean blockIsSuitable(IBlockState blockState) {
        return blockState.getBlock() == BlockLoader.huajiStarBlockSky;
    }

    @Override
    public boolean isMatch(World worldIn, BlockPos posStart, EnumFacing facing, Template template) {
        for (Template.BlockInfo blockInfo : template.blocks) {
            if (!worldIn.getBlockState(posStart.add(blockInfo.pos)).equals(blockInfo.blockState)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void build(World worldIn, BlockPos posStart, EnumFacing facing, Template template) {
        List<BlockPos> posList = Lists.newArrayList();
        for (Template.BlockInfo blockInfo : template.blocks) {
            posList.add(posStart.add(blockInfo.pos));
        }
        for (Template.BlockInfo blockInfo : template.blocks) {
            BlockPos currentPos = posStart.add(blockInfo.pos);
            worldIn.setBlockState(currentPos, BlockLoader.blockNewLandMan.getDefaultState());
            TileEntity tileEntity = worldIn.getTileEntity(currentPos);
            if (tileEntity instanceof TileEntityNewLandMan) {
                if (currentPos.equals(posStart.subtract(getCenterPos(facing)))) {
                    ((TileEntityNewLandMan) tileEntity).setData(blockInfo.blockState, true,
                           posList);
                }else {
                    ((TileEntityNewLandMan) tileEntity).setData(blockInfo.blockState, false,
                           posList);
                }
            }
        }
    }

    @Override
    public boolean facingIsSuitable(EnumFacing facing) {
        return facing != EnumFacing.DOWN && facing != EnumFacing.UP;
    }

    @Override
    public BlockPos getCenterPos(EnumFacing facing) {
    	return POS;
    }

    @Override
    public Template getTemplate(World worldIn, EnumFacing facing) {
                return getStructureTemplate(worldIn, NEW_LAND_MAN);
    }

    private Template getStructureTemplate(World worldIn, ResourceLocation resourceLocation) {
        return worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), resourceLocation);
    }
}
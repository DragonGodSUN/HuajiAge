package com.lh_lshen.mcbbs.huajiage.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
public interface IMultiBlock {

    boolean blockIsSuitable(IBlockState blockState);

    boolean facingIsSuitable(EnumFacing facing);

    BlockPos getCenterPos(EnumFacing facing);

    Template getTemplate(World worldIn, EnumFacing facing);

    boolean isMatch(World worldIn, BlockPos posStart, EnumFacing facing, Template template);

    void build(World worldIn, BlockPos posStart, EnumFacing facing, Template template);
}

package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IMultiBlock;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

public class ItemHuaji extends Item {
	public ItemHuaji()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
        List<IMultiBlock> multiBlockList = HuajiAgeAPI.getMultiBlockList();
        IBlockState blockState = worldIn.getBlockState(pos);
        for (IMultiBlock multiBlock : multiBlockList) {
            boolean baseConditionIsOkay = hand == EnumHand.MAIN_HAND;
            boolean multiBlockIsOkay = multiBlock.blockIsSuitable(blockState) && multiBlock.facingIsSuitable(facing);
            if (baseConditionIsOkay && multiBlockIsOkay) {
                if (!worldIn.isRemote) {
                    BlockPos posStart = pos.add(multiBlock.getCenterPos(facing));
                    Template template = multiBlock.getTemplate(worldIn, facing);
                    if (multiBlock.isMatch(worldIn, posStart, facing, template)) {
                        multiBlock.build(worldIn, posStart, facing, template);
                    }
                }
                return EnumActionResult.SUCCESS;
            }
        }
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}

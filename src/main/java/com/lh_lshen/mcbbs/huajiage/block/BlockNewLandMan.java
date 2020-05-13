package com.lh_lshen.mcbbs.huajiage.block;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
public class BlockNewLandMan extends Block{

	public BlockNewLandMan() {
		super(Material.ANVIL);
		this.setSoundType(blockSoundType.ANVIL);
		setHardness(1.0f);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	  @Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityNewLandMan();
	}
	    @Override
	    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
	        if (!worldIn.isRemote) {
	            TileEntity tileEntity = worldIn.getTileEntity(pos);
	            if (tileEntity instanceof TileEntityNewLandMan) {
	                restoreStorageBlock(worldIn, pos, ((TileEntityNewLandMan) tileEntity).getBlockPosList());
	                if (!player.isCreative()) {
	                    dropStorageBlock(worldIn, pos, (TileEntityNewLandMan) tileEntity);
	                }
	            }
	        }
	    }

	    private void restoreStorageBlock(World worldIn, BlockPos currentPos, List<BlockPos> posList) {
	        for (BlockPos storagePos : posList) {
	            if (!storagePos.equals(currentPos)) {
	                TileEntity tileEntity = worldIn.getTileEntity(storagePos);
	                if (tileEntity instanceof TileEntityNewLandMan) {
	                    worldIn.setBlockState(storagePos, ((TileEntityNewLandMan) tileEntity).getBlockState());
	                }
	            }
	        }
	    }

	    private void dropStorageBlock(World worldIn, BlockPos pos, TileEntityNewLandMan tileEntityAltar) {
	        Block block = tileEntityAltar.getBlockState().getBlock();
	        Block.spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(block),
	                1, block.getMetaFromState(tileEntityAltar.getBlockState())));
	    }
	    
	    
	  @SideOnly(Side.CLIENT)
	    @Override
	    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
	        TileEntity tileEntity = world.getTileEntity(pos);
	        if (tileEntity instanceof TileEntityNewLandMan) {
	            addBlockDestroyEffects(world, pos, ((TileEntityNewLandMan) tileEntity).getBlockState(), manager);
	        }
	        return true;
	    }

	    @SideOnly(Side.CLIENT)
	    @Override
	    public boolean addHitEffects(IBlockState state, World world, RayTraceResult target, ParticleManager manager) {
	        TileEntity tileEntity = world.getTileEntity(target.getBlockPos());
	        if (tileEntity instanceof TileEntityNewLandMan) {
	            addBlockHitEffects(world, target.getBlockPos(), target.sideHit, ((TileEntityNewLandMan) tileEntity).getBlockState(), manager);
	        }
	        return true;
	    }


	    @SideOnly(Side.CLIENT)
	    private void addBlockDestroyEffects(World world, BlockPos pos, IBlockState state, ParticleManager manager) {
	        if (!state.getBlock().isAir(state, world, pos) && !state.getBlock().addDestroyEffects(world, pos, manager)) {
	            for (int j = 0; j < 4; ++j) {
	                for (int k = 0; k < 4; ++k) {
	                    for (int l = 0; l < 4; ++l) {
	                        double d0 = (j + 0.5D) / 4.0D;
	                        double d1 = (k + 0.5D) / 4.0D;
	                        double d2 = (l + 0.5D) / 4.0D;
	                        manager.spawnEffectParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(),
	                                pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2,
	                                d0 - 0.5D, d1 - 0.5D, d2 - 0.5D, Block.getStateId(state));
	                    }
	                }
	            }
	        }
	    }


	    @SideOnly(Side.CLIENT)
	    private void addBlockHitEffects(World world, BlockPos pos, EnumFacing side, IBlockState iblockstate, ParticleManager manager) {
	        Random rand = Block.RANDOM;
	        if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
	            int i = pos.getX();
	            int j = pos.getY();
	            int k = pos.getZ();
	            AxisAlignedBB axisalignedbb = iblockstate.getBoundingBox(world, pos);
	            double d0 = i + rand.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - 0.20000000298023224D)
	                    + 0.10000000149011612D + axisalignedbb.minX;
	            double d1 = j + rand.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - 0.20000000298023224D)
	                    + 0.10000000149011612D + axisalignedbb.minY;
	            double d2 = k + rand.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - 0.20000000298023224D)
	                    + 0.10000000149011612D + axisalignedbb.minZ;
	            if (side == EnumFacing.DOWN) {
	                d1 = j + axisalignedbb.minY - 0.10000000149011612D;
	            }
	            if (side == EnumFacing.UP) {
	                d1 = j + axisalignedbb.maxY + 0.10000000149011612D;
	            }
	            if (side == EnumFacing.NORTH) {
	                d2 = k + axisalignedbb.minZ - 0.10000000149011612D;
	            }
	            if (side == EnumFacing.SOUTH) {
	                d2 = k + axisalignedbb.maxZ + 0.10000000149011612D;
	            }
	            if (side == EnumFacing.WEST) {
	                d0 = i + axisalignedbb.minX - 0.10000000149011612D;
	            }
	            if (side == EnumFacing.EAST) {
	                d0 = i + axisalignedbb.maxX + 0.10000000149011612D;
	            }
	            manager.spawnEffectParticle(EnumParticleTypes.BLOCK_CRACK.getParticleID(),
	                    d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(iblockstate));
	        }
	    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}

package com.lh_lshen.mcbbs.huajiage.block;

import java.awt.Container;
import java.awt.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.gui.GuiHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiBlenderElementLoader;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class HuajiBlender extends BlockContainer {

	protected HuajiBlender() {
		super(Material.ROCK);
		 this.setHardness(2.5F);
	        this.setLightLevel(1.0f);
	        this.setSoundType(blockSoundType.STONE);
	        this.setCreativeTab(CreativeTabLoader.tabhuaji);

		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntityHuajiBlender();
	}
	@Override
	 public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
	        return Item.getItemFromBlock(BlockLoader.huajiBlender);
	    }
	
	 public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	 public static final PropertyBool BURNING = PropertyBool.create("burning");
	   @Override
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this,FACING,BURNING);
	    }
	   
	  
	   @Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileEntityHuajiBlender) {
				TileEntityHuajiBlender tileInventoryFurnace = (TileEntityHuajiBlender)tileEntity;
				int burning = tileInventoryFurnace.getField(0);
				boolean isBuring=burning>5;
				IBlockState origin = super.getActualState(state, worldIn, pos);
				return origin.withProperty(BURNING,isBuring);
			}
			return state;
		}
	    @Override
	    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	    {
	    	IBlockState origin = super.getStateForPlacement(worldIn, pos, blockFaceClickedOn, hitX, hitY, hitZ, meta, placer);
	        return origin.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	    }
	    @Override
	    public IBlockState getStateFromMeta(int meta)
	    {
	        EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 3);
//	        Integer burning = Integer.valueOf(meta&4);
	        Boolean burning = Boolean.valueOf((meta & 4) != 0);
	        return this.getDefaultState().withProperty(FACING, facing).withProperty(BURNING, burning);
	    }
	    @Override
	    public int getMetaFromState(IBlockState state)
	    {
	        int facing = state.getValue(FACING).getHorizontalIndex();
	        
	        int burning = state.getValue(BURNING).booleanValue() ? 1 : 0;
	        return facing | burning ;
	    }
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void getSubBlocks(CreativeTabs whichTab, NonNullList<ItemStack> items)
	    {
	        items.add(new ItemStack(this, 1));

	    }
	   
	
	    
	    @Override
		public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
			return EnumBlockRenderType.MODEL;
		}
	    @Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
																		EnumFacing side, float hitX, float hitY, float hitZ) {
			// Uses the gui handler registered to your mod to open the gui for the given gui id
			// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
			if (worldIn.isRemote) return true;

			playerIn.openGui(HuajiAge.instance, GuiHuajiBlenderElementLoader.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}

		// This is where you can do something when the block is broken. In this case drop the inventory's contents
		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof IInventory) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileEntity);
			}

//			if (inventory != null){
//				// For each slot in the inventory
//				for (int i = 0; i < inventory.getSizeInventory(); i++){
//					// If the slot is not empty
//					if (inventory.getStackInSlot(i) != null)
//					{
//						// Create a new entity item with the item stack in the slot
//						EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));
	//
//						// Apply some random motion to the item
//						float multiplier = 0.1f;
//						float motionX = worldIn.rand.nextFloat() - 0.5f;
//						float motionY = worldIn.rand.nextFloat() - 0.5f;
//						float motionZ = worldIn.rand.nextFloat() - 0.5f;
	//
//						item.motionX = motionX * multiplier;
//						item.motionY = motionY * multiplier;
//						item.motionZ = motionZ * multiplier;
	//
//						// Spawn the item in the world
//						worldIn.spawnEntityInWorld(item);
//					}
//				}
	//
//				// Clear the inventory so nothing else (such as another mod) can do anything with the items
//				inventory.clear();
//			}

			// Super MUST be called last because it removes the tile entity
			super.breakBlock(worldIn, pos, state);
		}
	    
}

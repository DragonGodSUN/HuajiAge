package com.lh_lshen.mcbbs.huajiage.tileentity;

import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.capability.EnergyStore;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.data.InvWrapperRestricted;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiPolyRecipeList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TileEntityHuajiPolyfurnace extends TileEntityMachineEnergy implements IInventory, ITickable, ISidedInventory{
	
	public TileEntityHuajiPolyfurnace() {
		super();
		this.initEnergy(new EnergyStore(MENERGY),1000);
		invHandler = new InvWrapperRestricted(this);
		inventory=NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	    this.setSlotsForExtract(2);
	    this.setSlotsForInsert(0,1);
	}

	private String costomname;
	 
	InvWrapperRestricted invHandler;
	private NonNullList<ItemStack> inventory;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	private int itemPool;

	 protected void setSlotsForExtract(int slot) {
		    this.setSlotsForExtract(Arrays.asList(slot));
		  }

		  protected void setSlotsForInsert(int slot) {
		    this.setSlotsForInsert(Arrays.asList(slot));
		  }

		  protected void setSlotsForExtract(List<Integer> slots) {
		    invHandler.setSlotsExtract(slots);
		  }

		  protected void setSlotsForExtract(int startInclusive, int endInclusive) {
		    setSlotsForExtract(
		        IntStream.rangeClosed(
		            startInclusive,
		            endInclusive).boxed().collect(Collectors.toList()));
		  }

		  protected void setSlotsForInsert(int startInclusive, int endInclusive) {
		    setSlotsForInsert(
		        IntStream.rangeClosed(
		            startInclusive,
		            endInclusive).boxed().collect(Collectors.toList()));
		  }

		  protected void setSlotsForInsert(List<Integer> slots) {
		    invHandler.setSlotsInsert(slots);
		  }

		  protected void setSlotsForBoth(List<Integer> slots) {
		    invHandler.setSlotsInsert(slots);
		    invHandler.setSlotsExtract(slots);
		  }

		  /**
		   * no input means all slots
		   */
		  protected void setSlotsForBoth() {
		    this.setSlotsForBoth(IntStream.rangeClosed(0, this.getSizeInventory()).boxed().collect(Collectors.toList()));
		  }

		  @Override
		  public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		    return this.isItemValidForSlot(index, itemStackIn)
		        && this.invHandler.canInsert(index);
		  }

		  @Override
		  public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		    return index != this.getEnergyCost() && //override to inv handler: do not extract fuel
		        this.invHandler.canExtract(index);
		  }
		  @Override
		  public int[] getSlotsForFace(EnumFacing side) {
		    return IntStream.range(0, this.getSizeInventory()).toArray();
		  }

	@Override
	public void update() {
//		if (canSmelt()) {
//			int numberOfFuelBurning = burnFuel();
//
//			// If fuel is available, keep cooking the item, otherwise start "uncooking" it at double speed
//			if (numberOfFuelBurning > 0) {
//				cookTime += numberOfFuelBurning;
//			}	else {
//				cookTime -= 2;
//			}
//
//			if (cookTime < 0) cookTime = 0;
//
//			// If cookTime has reached maxCookTime smelt the item and reset cookTime
//			if (cookTime >= totalCookTime) {
//				smeltItem();
//				cookTime = 0;
//			}
//		}	else {
//			cookTime = 0;
//		}
//	
		
		 
		 boolean flag = this.isBurning();
	        boolean flag1 = false;


	        if (!this.world.isRemote)
	        {
				if(this.getBurnTime()<5000) {
						 if (this.getEnergyCost() > 0) {
						      if (this.hasEnoughEnergy()) {
						        this.consumeEnergy();
						        this.currentBurnTime+=1;
						      }
						   }
						 }
		        if(this.itemPool>=ConfigHuaji.Huaji.point_star) {
					ItemStack input  =(ItemStack)this.inventory.get(0);
					ItemStack output =(ItemStack)this.inventory.get(2);
					ItemStack result =(ItemStack)HuajiPolyRecipeList.instance().getSmeltingResult(input);
					if(output.isEmpty()) {
						this.inventory.set(2, result.copy());
					}else if(output.getItem()==result.getItem()) {
						output.grow(result.getCount());
					}
					this.itemPool-=ConfigHuaji.Huaji.point_star;
			        		}
		        ItemStack itemstack = this.inventory.get(1);
	            if (this.isBurning() || !itemstack.isEmpty())
	            {
	                if (this.getBurnTime()<5000)
	                {
	                    this.currentBurnTime += getItemBurnTime(itemstack);
//	                    this.currentItemBurnTime = this.currentBurnTime;

	                    if (this.isBurning())
	                    {
	                        flag1 = true;

	                        if (!itemstack.isEmpty())
	                        {
	                            Item item = itemstack.getItem();
	                            itemstack.shrink(1);

	                            if (itemstack.isEmpty())
	                            {
	                                ItemStack item1 = item.getContainerItem(itemstack);
	                                this.inventory.set(1, item1);
	                            }
	                        }
	                    }
	                }

	                if (this.isBurning() && this.canSmelt())
	                {
	                    ++this.cookTime;
	                    --this.currentBurnTime;

	                    if (this.cookTime == this.totalCookTime)
	                    {
	            			ItemStack input  =(ItemStack)this.inventory.get(0);
	                        this.cookTime = 20;
	                        this.totalCookTime = getCookTime();
	                        this.itemPool +=this.getPoolfuel(input);
	                        this.smeltItem();
	                        flag1 = true;
	                    }
	                }
	                else
	                {
	                    this.cookTime = 0;
	                }		

	            }
	            else if (!this.isBurning() && this.cookTime > 0)
	            {
	                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
	            }

	            if (flag != this.isBurning())
	            {
	                flag1 = true;
	            }
	        }

	        if (flag1)
	        {
	            this.markDirty();
	        }
	if (world.isRemote) {
		
        IBlockState iblockstate = this.world.getBlockState(pos);
        final int FLAGS = 3;  // I'm not sure what these flags do, exactly.
        world.notifyBlockUpdate(pos, iblockstate, iblockstate, FLAGS);
		}
	if(this.isBurning()) {
			
		}
			world.checkLightFor(EnumSkyBlock.BLOCK, pos);
	}
	@Override
	public String getName() {
		return hasCustomName()?costomname:BlockLoader.huajiPolyFurnace.getLocalizedName();
	}
   @Override
   public ITextComponent getDisplayName() {
	return hasCustomName()?new TextComponentString(costomname):new TextComponentTranslation(this.getName());
    }
	@Override
	public boolean hasCustomName() {
		return costomname!=null&&!costomname.isEmpty();
	}


	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack:inventory) {
			if(!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return net.minecraft.inventory.ItemStackHelper.getAndSplit(inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return net.minecraft.inventory.ItemStackHelper.getAndRemove(inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack=inventory.get(index);
		boolean flag=!itemstack.isEmpty()&&stack.isItemEqual(itemstack)&&ItemStack.areItemsEqual(stack, itemstack);
		inventory.set(index, stack);
		if(stack.getCount()>getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		if(index==0&&index+1==1&&!flag) {
			ItemStack stackN=inventory.get(index+1);
			totalCookTime=getCookTime();
			cookTime=0;
			this.markDirty();
			
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory=NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
		net.minecraft.inventory.ItemStackHelper.loadAllItems(compound, inventory);
		this.burnTime=compound.getInteger("BurnTime");
		this.currentBurnTime=compound.getInteger("CurrentBurnTime");
		this.cookTime=compound.getInteger("CookTime");
		this.totalCookTime=compound.getInteger("TotalCookTime");
		this.itemPool=compound.getInteger("ItemPool");
		final byte NBT_TYPE_COMPOUND = 10;       // See NBTBase.createNewByType() for a listing
		NBTTagList dataForAllSlots = compound.getTagList("Items", NBT_TYPE_COMPOUND);
 
		for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			byte slotNumber = dataForOneSlot.getByte("Slot");
			if (slotNumber >= 0 && slotNumber < this.inventory.size()) {
				this.inventory.set(slotNumber,new ItemStack(dataForOneSlot));
			}
		}
		
		if(compound.hasKey("CostomName")) {
			this.costomname=compound.getString("CostomName");
		}
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList dataForAllSlots = new NBTTagList();
		for (int i = 0; i < this.inventory.size(); ++i) {
			if (!this.inventory.get(i).isEmpty()) {  //isEmpty()
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.inventory.get(i).writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		compound.setInteger("BurnTime", this.burnTime);
		compound.setInteger("CurrentBurnTime", this.currentBurnTime);
		compound.setInteger("CookTime", this.cookTime);
		compound.setInteger("TotalCookTime", this.totalCookTime);
		compound.setInteger("ItemPool", this.itemPool);
		compound.setTag("Items", dataForAllSlots);;
		if(hasCustomName()) {
			compound.setString("CostomName", costomname);
		}
		return compound;
	}
	public boolean isBurning() {
		return currentBurnTime>0;
	}
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(2)>0;
	}
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean canSmelt() {
		if(inventory.get(0).isEmpty()) {
			return false;
		}else {
			ItemStack result=HuajiPolyRecipeList.instance().getSmeltingResult(inventory.get(0));
			if(result.isEmpty()) {
				return false;
			}else {
				ItemStack output = inventory.get(2);
				if(output.isEmpty()) {
					return true;
				}else if(!output.isItemEqual(result)) {
					return false;
				}
				int res=output.getCount()+result.getCount();
				return res <=getInventoryStackLimit()&& res<= output.getMaxStackSize();
			}
		}		
	}

	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack input  =(ItemStack)this.inventory.get(0);
			input.shrink(1);
//			this.markDirty();
		}
	}
	/**
	 * Smelt an input item into an output slot, if possible
	 */
//	private void smeltItem() {smelt(true);}
//	public boolean smelt(boolean performSmelt) {
//
//		ItemStack input= inventory.get(0);
//		ItemStack output= inventory.get(2);
//		ItemStack result = ItemStack.EMPTY;  //EMPTY_ITEM
//
//		// finds the first input slot which is smeltable and whose result fits into an output slot (stacking if possible)
//			if (!input.isEmpty()) {  //isEmpty()
//				result = getSmeltingResultForItem(input);
//  			if (!result.isEmpty()) {  //isEmpty()
//					// find the first suitable output slot- either empty, or with identical item that has enough space
//						ItemStack outputStack = output;
//						if (outputStack.isEmpty()) {  //isEmpty()
//
//						if (outputStack.getItem() == result.getItem() && (!outputStack.getHasSubtypes() || outputStack.getMetadata() == outputStack.getMetadata())
//										&& ItemStack.areItemStackTagsEqual(outputStack, result)) {
//						}
//					}
//					if (input != null) {}
//				}
//			}
//
//
////		if (input == null) return false;
//		if (!performSmelt) return true;
//
//		// alter input and output
//		input.shrink(1);  // decreaseStackSize()
//		if (input.getCount() <= 0) {
//      input = ItemStack.EMPTY;  //getStackSize(), EmptyItem
//    }
//		if (output.isEmpty()) {  // isEmpty()
//			output = result.copy(); // Use deep .copy() to avoid altering the recipe
//		} else {
//      int newStackSize = output.getCount() + result.getCount();
//			output.setCount(newStackSize) ;  //setStackSize(), getStackSize()
//		}
//		markDirty();
//		return true;
//	}
	public static ItemStack getSmeltingResultForItem(ItemStack stack) { return HuajiPolyRecipeList.instance().getSmeltingResult(stack); }
////	能量填充
//	private int burnFuel() {
//		int burningCount = 0;
//		boolean inventoryChanged = false;
//		// Iterate over all the fuel slots
//
//		    ItemStack itemStacks=inventory.get(1);
//			if (currentBurnTime > 0) {
//				--currentBurnTime;
//				++burningCount;
//			}
//			if (currentBurnTime== 0) {
//				if (!itemStacks.isEmpty() && getItemBurnTime(itemStacks) > 0) {  // isEmpty()
//					// If the stack in this slot is not null and is fuel, set burnTimeRemaining & burnTimeInitialValue to the
//					// item's burn time and decrease the stack size
//					currentBurnTime  = getItemBurnTime(itemStacks);
//					itemStacks.shrink(1);  // decreaseStackSize()
//					inventoryChanged = true;
//				// If the stack size now equals 0 set the slot contents to the items container item. This is for fuel
//				// items such as lava buckets so that the bucket is not consumed. If the item dose not have
//				// a container item getContainerItem returns null which sets the slot contents to null
//					if (itemStacks.getCount() == 0) {  //getStackSize()
//						itemStacks= itemStacks.getItem().getContainerItem(itemStacks);
//					}
//				}
//			}
//		if (inventoryChanged) markDirty();
//		return burningCount;
//	}
//	// When the world loads from disk, the server needs to send the TileEntity information to the client
//	//  it uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this
	  @Override
	  @Nullable
	  public SPacketUpdateTileEntity getUpdatePacket()
	  {
	    NBTTagCompound updateTagDescribingTileEntityState = getUpdateTag();
	    final int METADATA = 0;
	    return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
	  }
	
	  @Override
	  public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
	    NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
	    handleUpdateTag(updateTagDescribingTileEntityState);
	  }
	
	  /* Creates a tag containing the TileEntity information, used by vanilla to transmit from server to client
	     Warning - although our getUpdatePacket() uses this method, vanilla also calls it directly, so don't remove it.
	   */
	  @Override
	  public NBTTagCompound getUpdateTag()
	  {
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			writeToNBT(nbtTagCompound);
	    return nbtTagCompound;
	  }
	
	  /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
	   Warning - although our onDataPacket() uses this method, vanilla also calls it directly, so don't remove it.
	 */
	  @Override
	  public void handleUpdateTag(NBTTagCompound tag)
	  {
	    this.readFromNBT(tag);
	  }
	  public static int getItemBurnTime(ItemStack stack)
	    {
	        if (stack.isEmpty())
	        {
	            return 0;
	        }
	        else
	        {
	            Item item = stack.getItem();

	            if (item == ItemLoader.huaji)
	            {
	                return 10;
	            }
	           
	            else
	            {
	                return 0;
	            }
	        }
	    }
	  @Override
		public boolean isUsableByPlayer(EntityPlayer player) {
			if (this.world.getTileEntity(this.pos) != this) return false;
			final double X_CENTRE_OFFSET = 0.5;
			final double Y_CENTRE_OFFSET = 0.5;
			final double Z_CENTRE_OFFSET = 0.5;
			final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
			return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
		}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
		switch(slotIndex) {
		case 0:{
			if(!HuajiPolyRecipeList.instance().getSmeltingResult(itemstack).isEmpty()) {
			return true;}
			}
		case 1:{
			if(getItemBurnTime(itemstack)>0) {
			return true;}
			}
		default: {
			return false;
		}
		}
		
	}

	@Override
	public int getField(int id) {
		switch(id) {
		case 1:return this.itemPool;
		case 2:return this.currentBurnTime;
		case 3:return this.cookTime;
		case 4:return this.totalCookTime;
		default:return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		switch(id) {
		case 1:this.itemPool=value;
		break;
		case 2:this.currentBurnTime=value;
		break;
		case 3:this.cookTime=value;
		break;
		case 4:this.totalCookTime=value;
		break;
		
		}
	}

//	public static enum EnumField{
//		BURN_TIME,
//		CURRENT_BURN_TIME,
//		COOK_TIME,
//		TOTAL_COOK_TIME,
//		POOL;
//		}
	@Override
	public int getFieldCount() {

		return 4;
	}

	public double fractionOfCookTimeComplete()
	{
		double fraction = cookTime / (double)getCookTime();
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}
	public double fractionOfFuelRemaining()
	{
		if (currentBurnTime <= 0 ) return 0;
		double fraction = currentBurnTime / (double)5000;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}
	public double fractionOfPool()
	{
		if (this.itemPool <= 0 ) return 0;
		double fractionPool = (double)this.itemPool/(double)(ConfigHuaji.Huaji.point_star);
		return MathHelper.clamp(fractionPool, 0.0, 1.0);
	}
	
	public static int getPoolfuel(ItemStack stack)
	    {
	        if (stack.isEmpty())
	        {
	            return 0;
	        }
	        else
	        {
	            if (!HuajiPolyRecipeList.instance().getSmeltingResult(stack).isEmpty())
	            {
	                return HuajiPolyRecipeList.instance().getPoint(stack)+HuajiPolyRecipeList.instance().getPool(stack);
	            }
	           
	            else
	            {
	                return 0;
	            }
	        }
	    }
	@Override
	public void clear() {
		inventory.clear();
	}
	public int numberOfBurningFuelSlots() {
		
		return isBurning()?1:0;
	}
//	***************************************************
	

//	***************************************************
	public String getCostomname() {
		return costomname;
	}
	public void setCostomname(String costomname) {
		this.costomname = costomname;
	}
	public int getBurnTime() {
		return currentBurnTime;
	}
	public void setBurnTime(int burnTime) {
		this.burnTime = burnTime;
	}
	public int getCookTime() {
		return 100;
	}
	public int getPool() {
		return itemPool;
	}


}

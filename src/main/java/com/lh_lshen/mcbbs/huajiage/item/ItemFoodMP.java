package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFoodMP extends ItemFood{
    private int mp;
	public ItemFoodMP(int amount, float saturation, int mp, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setMp(mp);
	}

	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote)
        {
            IExposedData data = StandUtil.getStandData(player);
            StandHandler chargeHandler = StandUtil.getStandHandler(player);
            if(data!=null && chargeHandler!=null && StandLoader.getStand(data.getStand())!=null){
                chargeHandler.charge(this.getMp());
            }
        }
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }
}

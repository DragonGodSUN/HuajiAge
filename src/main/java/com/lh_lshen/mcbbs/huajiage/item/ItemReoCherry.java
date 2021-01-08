package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class ItemReoCherry extends ItemFood{
    private int mp;
	public ItemReoCherry() {
		super(3, 1f, false);
		setMp(5000);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabJo);
	}

	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            IExposedData data = StandUtil.getStandData(player);
            StandChargeHandler chargeHandler = StandUtil.getChargeHandler(player);
            player.heal(1f);
            player.sendMessage(new TextComponentString(I18n.translateToLocal("message.huajiage.reo_cherry.reo")));
            if(data!=null && chargeHandler!=null && StandLoader.getStand(data.getStand())!=null){
                chargeHandler.charge(mp);
            }

        }
        super.onFoodEaten(stack, worldIn, player);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(I18n.translateToLocal("item.huajiage.reo_cherry.tooltip.1"));
        tooltip.add(I18n.translateToLocal("item.huajiage.reo_cherry.tooltip.2"));
        tooltip.add(I18n.translateToLocal("item.huajiage.reo_cherry.tooltip.3")+"§b"+mp);
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMp() {
        return mp;
    }
}

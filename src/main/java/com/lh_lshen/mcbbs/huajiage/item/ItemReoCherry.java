package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import java.util.List;

public class ItemReoCherry extends ItemFoodMP{
    private int mp;
	public ItemReoCherry() {
		super(3, 1f,20000,false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabJo);
	}

	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote)
        {
            player.heal(1f);
            player.sendMessage(new TextComponentString(I18n.translateToLocal("message.huajiage.reo_cherry.reo")));
        }
        player.playSound(SoundLoader.REO_CHERRY,1f,1f);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
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

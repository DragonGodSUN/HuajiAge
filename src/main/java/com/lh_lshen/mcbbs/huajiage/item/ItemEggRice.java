package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemEggRice extends ItemFood{

	public ItemEggRice() {
		super(15, 1f, false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabhuaji);
		
	
	
	}
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,2400,0));
        	player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,2400,2));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,2400,2));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,2400,2));
            player.heal(10f);
            player.sendMessage(new TextComponentString(I18n.translateToLocal("message.huajiage.reo_cherry.reo")));
            player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL));

        }
        super.onFoodEaten(stack, worldIn, player);
    }

}

package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemBakingGluten extends ItemFood{

	public ItemBakingGluten() {
		super(10, 1f, false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabhuaji);
		
	
	
	}
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,1800,0));
        	player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,1800,2));
        	player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,1800,1));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,1800,2));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,1800,2));

        }
        super.onFoodEaten(stack, worldIn, player);
    }

}

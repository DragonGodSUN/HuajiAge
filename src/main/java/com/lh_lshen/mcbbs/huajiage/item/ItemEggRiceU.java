package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemEggRiceU extends ItemFood{

	public ItemEggRiceU() {
		super(20, 1f, false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabhuaji);
		
	
	
	}
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
        	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,12000,1));
        	player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,12000,5));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED,12000,5));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,12000,3));
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,12000,3));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,12000,3));
            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST,12000,3));
            
            player.heal(20f);
            player.sendMessage(new TextComponentString(I18n.translateToLocal("huajiage.nice")));
            player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL));

        }
        super.onFoodEaten(stack, worldIn, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}

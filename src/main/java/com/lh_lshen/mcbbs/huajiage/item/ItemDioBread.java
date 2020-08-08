package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemDioBread extends ItemFood{

	public ItemDioBread() {
		super(12, 2f, false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabJo);
	}
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		IExposedData data = StandUtil.getStandData(player);
    	StandBase stand =StandLoader.getStand(data.getStand());
        if (!worldIn.isRemote)
        {
        	double rand=Math.random()*100;
        	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,9*20,0));
        	player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,9*20,4));
        	player.addPotionEffect(new PotionEffect(MobEffects.SPEED,9*20,6));
        	player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,9*20,4));
        	player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,9*20,2));
        	 if(stand==null || !stand.equals(StandLoader.STAR_PLATINUM)) {
            player.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
            }else {
            player.sendMessage(new TextComponentTranslation("message.huajiage.the_world_star"));
            }
            player.heal(5f);
            TimeStopHelper.setTimeStop(player, HuajiConstant.Tags.THE_WORLD_TIME);
//            player.getEntityData().setInteger(HuajiConstant.Tags.THE_WORLD, HuajiConstant.Tags.THE_WORLD_TIME);
            if(rand<30d) {
            	player.inventory.addItemStackToInventory(new ItemStack(ItemLoader.roadRoller));
            }

        }
        if(stand==null || !stand.equals(StandLoader.STAR_PLATINUM)) {
	        double rand0=Math.random()*100;
	        if(rand0<25) { 	
	        	player.playSound(SoundLoader.THE_WORLD, 5f,1f);
	        }else if(rand0<50){
	        	player.playSound(SoundLoader.THE_WORLD_1, 5f,1f);
	        }else if(rand0<75){
	        	player.playSound(SoundLoader.THE_WORLD_2, 5f,1f);
	        }else {
	        	player.playSound(SoundLoader.THE_WORLD_3, 5f,1f);
	        }
        }else {
        	player.playSound(SoundLoader.STAR_PLATINUM_THE_WORLD_1, 5f,1f);
        }
        super.onFoodEaten(stack, worldIn, player);
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{	
		tooltip.add(I18n.translateToLocal("item.dio_bread:tooltips.2"));
		}
		else {
		tooltip.add(I18n.translateToLocal("item.dio_bread:tooltips.1"));}
	
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}


}

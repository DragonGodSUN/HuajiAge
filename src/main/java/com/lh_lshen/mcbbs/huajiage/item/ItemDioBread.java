package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.ibm.icu.impl.duration.impl.DataRecord.EUnitVariant;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDioBread extends ItemFood{

	public ItemDioBread() {
		super(12, 2f, false);
		setAlwaysEdible();
		setCreativeTab(CreativeTabLoader.tabJo);
	}
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
		StandBase stand =StandLoader.getStand(standHandler.getStand());
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

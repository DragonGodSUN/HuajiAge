package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHuajiArmor extends ItemArmor
{

	
	public final EntityEquipmentSlot type;
	public static final ArmorMaterial huajiArmorMaterial = EnumHelper.addArmorMaterial("HUAJI",HuajiAge.MODID+":"+ "huaji", 36,
			new int[] { 5, 7, 10, 5 }, 33, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3F);

	public ItemHuajiArmor(EntityEquipmentSlot type) {
		this(type, huajiArmorMaterial);
	}
	public ItemHuajiArmor(EntityEquipmentSlot type, ArmorMaterial mat) {
		super(mat, 0, type);
		this.type = type;
		this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}

	public static class Helmet extends ItemHuajiArmor
    {
        public Helmet()
        {
        	super(EntityEquipmentSlot.HEAD);
            
            this.setCreativeTab(CreativeTabLoader.tabhuaji);
        }
    }

	
    public static class Chestplate extends ItemHuajiArmor
    {
        public Chestplate()
        {
        	super(EntityEquipmentSlot.CHEST);
            
            this.setCreativeTab(CreativeTabLoader.tabhuaji);
        }
      
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        
        	super.onArmorTick(world, player, itemStack);
        if(!world.isRemote) {
        	if(!player.isPotionActive(MobEffects.RESISTANCE)) {
        	player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,200,0));}
        }
        	}
    }

    public static class Leggings extends ItemHuajiArmor
    {
        public Leggings()
        {
        	super(EntityEquipmentSlot.LEGS);
            
            this.setCreativeTab(CreativeTabLoader.tabhuaji);
        }
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
            
        	super.onArmorTick(world, player, itemStack);
        	 if(!world.isRemote) {
        		 if(!player.isPotionActive(MobEffects.SPEED)) {
        	player.addPotionEffect(new PotionEffect(MobEffects.SPEED,200,0));}
        	 }
        }
    }

    public static class Boots extends ItemHuajiArmor
    {
        public Boots()
        {
        	super(EntityEquipmentSlot.FEET);
            
            this.setCreativeTab(CreativeTabLoader.tabhuaji);
        }
        @Override
        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
            
        	super.onArmorTick(world, player, itemStack);
        if(!world.isRemote) {
        	if(!player.isPotionActive(MobEffects.JUMP_BOOST)) {
        	player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,200,0));}
        	 }
        }
    }
}

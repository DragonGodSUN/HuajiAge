package com.lh_lshen.mcbbs.huajiage.item;

import java.util.EnumMap;
import java.util.Map;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelBlanceHelmet;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ModelArmorBase extends ItemArmor {

	protected Map<EntityEquipmentSlot, ModelBiped> models = null;
	public final EntityEquipmentSlot type;
    protected abstract ModelBiped model(EntityEquipmentSlot slot);
   
	public ModelArmorBase(EntityEquipmentSlot type, ArmorMaterial mat) {
		super(mat, 0, type);
		this.type = type;
		this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot) {
		if(models == null) {
			models = new EnumMap<>(EntityEquipmentSlot.class);
				}
		return models.get(slot);
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped provideArmorModelForSlot(ItemStack stack, EntityEquipmentSlot slot) {
		models.put(slot, model(slot));
		return models.get(slot);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original) {
			ModelBiped model = getArmorModelForSlot(entityLiving, itemStack, armorSlot);
			if(model == null) {
				model = provideArmorModelForSlot(itemStack, armorSlot);
				}
			if(model != null) {
				model.setModelAttributes(original);
				return model;
			}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, original);
	}
}



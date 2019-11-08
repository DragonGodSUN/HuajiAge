package com.lh_lshen.mcbbs.huajiage.item;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.IModelRegister;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelBlanceHelmet;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelOrgaHair;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemOrgaArmorBase extends ItemArmor {

	protected Map<EntityEquipmentSlot, ModelBiped> models = null;
	public final EntityEquipmentSlot type;
	public static final ArmorMaterial orgaMaterial = EnumHelper.addArmorMaterial("ORGA",HuajiAge.MODID+":"+ "orga", 150,
			new int[] {5,5,5,5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3F);

	public ItemOrgaArmorBase(EntityEquipmentSlot type) {
		this(type, orgaMaterial);
	}
	
	public ItemOrgaArmorBase(EntityEquipmentSlot type, ArmorMaterial mat) {
		super(mat, 0, type);
		this.type = type;
		this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, EntityEquipmentSlot slot) {
		if(models == null)
			models = new EnumMap<>(EntityEquipmentSlot.class);

		return models.get(slot);
	}

	@SideOnly(Side.CLIENT)
	public ModelBiped provideArmorModelForSlot(ItemStack stack, EntityEquipmentSlot slot) {
		models.put(slot, new ModelOrgaHair(slot));
		return models.get(slot);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original) {
		
			ModelBiped model = getArmorModelForSlot(entityLiving, itemStack, armorSlot);
			if(model == null)
				model = provideArmorModelForSlot(itemStack, armorSlot);

			if(model != null) {
				model.setModelAttributes(original);
				return model;
			}
		

		return super.getArmorModel(entityLiving, itemStack, armorSlot, original);
	}
	 @Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flags) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			addArmorSetDescription(stack, list);
			ItemStack[] stacks = getArmorSetStacks();
			for(int i = 0; i < stacks.length; i++)
				addStringToTooltip((hasArmorSetItem(player, i) ? TextFormatting.YELLOW : "") + " - " + stacks[i].getDisplayName(), list);
		}

		public void addStringToTooltip(String s, List<String> tooltip) {
			tooltip.add(s.replaceAll("&", "\u00a7"));
		}

		public boolean hasArmorSet(EntityPlayer player) {
			return hasArmorSetItem(player, 0) && hasArmorSetItem(player, 1) && hasArmorSetItem(player, 2) && hasArmorSetItem(player, 3);
		}

		
		private static ItemStack[] armorset;

		public ItemStack[] getArmorSetStacks() {
			if(armorset == null)
				armorset = new ItemStack[] {
						new ItemStack(ItemLoader.orgaHair),
						new ItemStack(ItemLoader.orgaChestplate),
						new ItemStack(ItemLoader.orgaLeggings),
						new ItemStack(ItemLoader.orgaBoots)
			};

			return armorset;
		}

		public boolean hasArmorSetItem(EntityPlayer player, int i) {
			if(player == null || player.inventory == null || player.inventory.armorInventory == null)
				return false;
			
			ItemStack stack = player.inventory.armorInventory.get(3 - i);
			if(stack.isEmpty())
				return false;

			switch(i) {
			case 0: return stack.getItem() == ItemLoader.orgaHair ;
			case 1: return stack.getItem() == ItemLoader.orgaChestplate;
			case 2: return stack.getItem() == ItemLoader.orgaLeggings;
			case 3: return stack.getItem() == ItemLoader.orgaBoots;
			}

			return false;
		}
		
		@SideOnly(Side.CLIENT)
		public void addArmorSetDescription(ItemStack stack, List<String> list) {
			addStringToTooltip(I18n.format("huajiage.orga.1"), list);
		}

	 public static class Chestplate extends ItemOrgaArmorBase
	    {
	        public Chestplate()
	        {
	        	super(EntityEquipmentSlot.CHEST);
	            
	            this.setCreativeTab(CreativeTabLoader.tabhuaji);
	        }
	      
	        @Override
	        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	        
	        	super.onArmorTick(world, player, itemStack);
	       
	        	}
	        @Nonnull
	    	@Override
	    	public final String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
	            Entity player = entity;
	            World world = entity.world;
	    			return HuajiAge.MODID+":textures/models/armor/orga.png";
	    	}
	    }

	    public static class Leggings extends ItemOrgaArmorBase
	    {
	        public Leggings()
	        {
	        	super(EntityEquipmentSlot.LEGS);
	            
	            this.setCreativeTab(CreativeTabLoader.tabhuaji);
	        }
	        @Override
	        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	            
	        	super.onArmorTick(world, player, itemStack);
	        	
	        }
	    }

	    public static class Boots extends ItemOrgaArmorBase
	    {
	        public Boots()
	        {
	        	super(EntityEquipmentSlot.FEET);
	            
	            this.setCreativeTab(CreativeTabLoader.tabhuaji);
	        }
	        @Override
	        public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	            
	        	super.onArmorTick(world, player, itemStack);
	       
	        }
	    }
}



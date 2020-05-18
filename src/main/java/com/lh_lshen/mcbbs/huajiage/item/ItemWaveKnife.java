package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageWave;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWaveKnife extends ItemSword {
 private static int tick = 0;
 private static int curColor = 0;
 public  final static int wave_max_init = 10;
 private static TextFormatting[] wave = {TextFormatting.BLUE,TextFormatting.BLUE,TextFormatting.AQUA,TextFormatting.AQUA };
 public static final Item.ToolMaterial WAVE = EnumHelper.addToolMaterial("WAVE", 3,600, 16.0F, 1.0F, 20);
	public ItemWaveKnife()
	{
		 super(WAVE);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}

	@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
			super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
			if (++tick >= 5) {
				tick = 0;
				if (--curColor < 0) {
					curColor = wave.length - 1;
				}
			}
			if(entityIn.ticksExisted%1000==0&&getWavePoint(stack)<getWaveMax(stack)) {
				setWavePoint(stack, getWaveMax(stack));
			}
			if(isWave(stack)) {
				setWave(stack, getWave(stack)-1);
				for(int i=0;i<3;i++) {
					Vec3d vec = entityIn.getLookVec();
//					worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, entityIn.posX+vec.x*2, entityIn.posY+entityIn.getEyeHeight()+vec.y*2, entityIn.posZ+vec.z*2,
//							-(vec.x*Math.cos(2/3*Math.PI)+vec.z*Math.sin(2/3*Math.PI))*6, (i-15)/2, -(-vec.x*Math.sin(2/3*Math.PI)+vec.z*Math.cos(2/3*Math.PI))*6);
					for(int j=0;j<20;j++) {
					Vec3d vec_r_1 = MotionHelper.getVectorForRotation((float) (entityIn.rotationPitch+0.01776*(j-10)*Math.PI/0.017453292F), (float) (entityIn.rotationYaw+0.333*Math.PI/0.017453292F));
					Vec3d vec_r_2 = MotionHelper.getVectorForRotation((float) (entityIn.rotationPitch+0.01776*(j-10)*Math.PI/0.017453292F), (float) (entityIn.rotationYaw-0.333*Math.PI/0.017453292F));
					worldIn.spawnParticle(EnumParticleTypes.WATER_WAKE, 
							entityIn.posX+vec.x*2+entityIn.motionX, entityIn.posY+entityIn.getEyeHeight()+vec.y*2+entityIn.motionY, entityIn.posZ+vec.z*2+entityIn.motionZ,
							-vec_r_1.x, -vec_r_1.y, -vec_r_1.z);
					worldIn.spawnParticle(EnumParticleTypes.WATER_WAKE,  entityIn.posX+vec.x*2, entityIn.posY+entityIn.getEyeHeight()+vec.y*2, entityIn.posZ+vec.z*2,
							-vec_r_2.x, -vec_r_2.y, -vec_r_2.z);
					}
				}
				List<EntityLivingBase> entities = entityIn.world.getEntitiesWithinAABB(EntityLivingBase.class, entityIn.getEntityBoundingBox().grow(2));
				for(EntityLivingBase entity : entities) {
					if(entity!=entityIn) {
					double degree = MotionHelper.getDegreeXZ(entityIn.getLookVec(),MotionHelper.getVectorEntityEye(entityIn, entity));
					boolean flag_degree = degree<=120;
						if(flag_degree) {
							entity.attackEntityFrom(new DamageWave(entityIn), (float) ((180-degree)/20)+8);
						}
					}
				}
			}
		}
	
	public void setWave(ItemStack stack ,int ticks) {
		NBTHelper.getTagCompoundSafe(stack).setInteger("wave", ticks);
	}
	
	public int getWave(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack).getInteger("wave");
	}
	
	public boolean isWave(ItemStack stack) {
		return getWave(stack)>0;
	}
	
	public void setWavePoint(ItemStack stack ,int points) {
		NBTHelper.getTagCompoundSafe(stack).setInteger("wave_point", points);
	}
	
	public int getWavePoint(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack).getInteger("wave_point");
	}
	
	public boolean isWavePointEnough(ItemStack stack) {
		return getWavePoint(stack)>0;
	}
	
	public void setWaveMax(ItemStack stack ,int points) {
		NBTHelper.getTagCompoundSafe(stack).setInteger("wave_max", points);
	}
	
	public int getWaveMax(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack).getInteger("wave_max") + wave_max_init;
	}
	
	public void setWaveCharge(ItemStack stack ,int points) {
		NBTHelper.getTagCompoundSafe(stack).setInteger("wave_charge", points);
	}
	
	public int getWaveCharge(ItemStack stack) {
		return NBTHelper.getTagCompoundSafe(stack).getInteger("wave_charge")+1;
	}
	
	public void WaveCharge(ItemStack stack) {
		int result = getWavePoint(stack)+getWaveCharge(stack);
		if(result<=getWaveMax(stack)) {
		setWavePoint(stack,result );
		}else {
		setWavePoint(stack,getWaveMax(stack));	
		}
	}
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {

		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if (slot == EntityEquipmentSlot.MAINHAND) {
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", 0.5f , 0));
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 5f, 0));
		}
		return multimap;
	}
	@Override
		public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		if(repair.getItem()==ItemLoader.waveCrystal) {
			return true;
			}
		return super.getIsRepairable(toRepair, repair);
		}
	@Override
		public String getItemStackDisplayName(ItemStack stack) {
			String str = super.getItemStackDisplayName(stack);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {
				sb.append(wave[(curColor + i) % wave.length].toString());
				sb.append(str.charAt(i));
			}
			return sb.toString();
		}

	@Override
		public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
			target.attackEntityFrom(new DamageWave(attacker), 5f);
			attacker.addPotionEffect(new PotionEffect(MobEffects.SPEED,200,2));
			attacker.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,200));
			WaveCharge(stack);
			return super.hitEntity(stack, target, attacker);
		}
	@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
			Vec3d vec = playerIn.getLookVec();
			ItemStack stack = playerIn.getHeldItem(handIn);
			if(stack.getItem() instanceof ItemWaveKnife&&NBTHelper.getTagCompoundSafe(stack).hasKey("wave_point")) 
			{
				if(isWavePointEnough(stack)) {
				setWave(stack, 10);
				setWavePoint(stack, getWavePoint(stack)-1);
				playerIn.motionX+=vec.x*1.5;
				playerIn.motionY+=vec.y*1.5;
				playerIn.motionZ+=vec.z*1.5;
				playerIn.fallDistance=0;
				playerIn.swingArm(handIn);

				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.NEUTRAL, 1.5f, 1f);
				worldIn.playSound(playerIn, playerIn.getPosition().add(vec.x, vec.y, vec.z), SoundEvents.ENTITY_BOBBER_SPLASH, SoundCategory.NEUTRAL, 1f, 1f);
				worldIn.playSound(playerIn, playerIn.getPosition().add(vec.x, vec.y, vec.z), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1f, 1f);
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
				}
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
	@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			super.addInformation(stack, worldIn, tooltip, flagIn);
			tooltip.add(I18n.format("item.wave_knife.tooltip.1", getWavePoint(stack)));
			tooltip.add(I18n.format("item.wave_knife.tooltip.2", getWaveMax(stack)));
			tooltip.add(I18n.format("item.wave_knife.tooltip.3",getWaveCharge(stack)));
		}
}

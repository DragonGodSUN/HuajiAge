package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySheerHeartAttack;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.LootTablesLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TelepathyHelper;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class ItemLootTableTest extends Item {
	@Override
	public boolean hasEffect(ItemStack stack) {
		
		return true;
	}
	private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
	    return NBTHelper.getTagCompoundSafe(stack);
	}
	private void modeSwitch(ItemStack stack,int i) {
	    getTagCompoundSafe(stack).setInteger("mode",i);
	}
	private int getmode(ItemStack stack) {
	    return getTagCompoundSafe(stack).getInteger("mode");
	}
	private String getmodeName(ItemStack stack) {
		switch(getTagCompoundSafe(stack).getInteger("mode")) {
		case 0:
			return "Loot Table Test";
		case 1:
			return "Emerald Splash";
		case 2:
			return "Time Stop ---Dio";
		case 3:
			return "Time Stop ---Passerby";
		case 4:
			return "Stand Power!!!";
		case 5:
			return "Time Stop ---Jostar";
		case 6:
			return "Sheer Heart Attack";
		case 7:
			return "Give Broken Items";
//		case 7:
//			return "Clear up the list of Stand Users";
		}
	    return null;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand hand) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if (stack.getItem() == ItemLoader.loottest) {
			IExposedData data = StandUtil.getStandData(playerIn);
			if (data != null) {
				int stage = data.getStage();
				if (playerIn.isSneaking()) {
					int mode = getmode(stack);
					if (mode < 7) {
						modeSwitch(stack, mode + 1);
					} else {
						modeSwitch(stack, 0);
					}
					if (worldIn.isRemote) {
						playerIn.sendMessage(new TextComponentString(getmode(stack) + ":" + getmodeName(stack)));
					}

				}
				if (!playerIn.isSneaking()) {
					switch (getmode(stack)) {
						case 0: {
							playerIn.playSound(SoundLoader.STAND_WHITE_SNAKE_HIT_2, 5f, 1f);
							if (!worldIn.isRemote) {

								final LootTable lootTable = worldIn.getLootTableManager().getLootTableFromLocation(LootTablesLoader.LOOT_TABLE_STAND_TEMPLE);
								final LootContext lootContext = new LootContext.Builder((WorldServer) worldIn).withPlayer(playerIn).build();

								final List<ItemStack> itemStacks = lootTable.generateLootForPools(itemRand, lootContext);
								for (final ItemStack itemStack : itemStacks) {
									ItemHandlerHelper.giveItemToPlayer(playerIn, itemStack);
								}

								playerIn.inventoryContainer.detectAndSendChanges();

								if (!(itemStacks.size() > 0)) {

									playerIn.sendMessage(new TextComponentTranslation("message.huajiage:player_received_loot.no_loot"));
								}
								ItemStack stack_off = playerIn.getHeldItemOffhand();
								String result = TelepathyHelper.telepathizeItem(playerIn,stack_off.getItem().getRegistryName().toString());
								playerIn.sendMessage(new TextComponentTranslation("telepathy.result.position"+"."+result));
								playerIn.sendMessage(new TextComponentTranslation(playerIn.dimension+""));
							}
							break;
						}
						case 1: {
							playerIn.playSound(SoundLoader.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH, 5f, 1f);
							StandHierophantGreen stand = new StandHierophantGreen();
							stand.doStandCapability(playerIn);
							//			playerIn.playSound(SoundLoader.ORGA_FLOWER, 2f, 1f);
							break;
						}
						case 2: {
							if (!playerIn.world.isRemote) {

								//		playerIn.getEntityData().setInteger("huajiage.the_world",9*20);
								TimeStopHelper.setTimeStop(playerIn, HuajiConstant.Tags.THE_WORLD_TIME);
								playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 9 * 20, 0));
								playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 9 * 20, 4));
								playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 9 * 20, 6));
								playerIn.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 9 * 20, 4));
								playerIn.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
								playerIn.heal(15f);
							}
							double rand0 = Math.random() * 100;
							if (rand0 < 25) {
								playerIn.playSound(SoundLoader.THE_WORLD, 5f, 1f);
							} else if (rand0 < 50) {
								playerIn.playSound(SoundLoader.THE_WORLD_1, 5f, 1f);
							} else if (rand0 < 75) {
								playerIn.playSound(SoundLoader.THE_WORLD_2, 5f, 1f);
							} else {
								playerIn.playSound(SoundLoader.THE_WORLD_3, 5f, 1f);
							}
							break;
						}
						case 3: {
							if (!playerIn.world.isRemote) {
								data.setStand(StandLoader.EMPTY);
								data.setStage(0);
								playerIn.getEntityData().setInteger("huajiage.time_stop", 9 * 20);
								playerIn.getEntityData().setDouble("huajiage.time_stop.x", playerIn.posX);
								playerIn.getEntityData().setDouble("huajiage.time_stop.y", playerIn.posY);
								playerIn.getEntityData().setDouble("huajiage.time_stop.z", playerIn.posZ);
								playerIn.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
							}
							break;
						}
						case 4: {
							if (!playerIn.world.isRemote) {
								data.setStand(StandLoader.THE_WORLD.getName());
								playerIn.addPotionEffect(new PotionEffect(PotionLoader.potionStand, StandLoader.THE_WORLD.getDuration()));
							}
							break;
						}
						case 5: {
							TimeStopHelper.setTimeStop(playerIn, 5 * 20);
							playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5 * 20, 0));
							playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 5 * 20, 2));
							playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5 * 20, 1));
							playerIn.sendMessage(new TextComponentTranslation("message.huajiage.the_world_star"));
							playerIn.playSound(SoundLoader.STAR_PLATINUM_THE_WORLD_1, 5f, 1f);
							data.setStage(1);
							break;
						}
						case 6:
							if (!playerIn.world.isRemote) {
								EntitySheerHeartAttack attack = new EntitySheerHeartAttack(playerIn.world);
								Vec3d vec = playerIn.getLookVec();
								attack.setTamedBy(playerIn);
								attack.setDamage(50f);
								attack.setPosition(playerIn.posX, playerIn.posY + 0.5f, playerIn.posZ);
								attack.motionX = vec.x * 0.5;
								attack.motionY = vec.y * 0.5;
								attack.motionZ = vec.z * 0.5;
								playerIn.world.spawnEntity(attack);
							}
							break;
						case 7:
							if (!playerIn.world.isRemote) {
								ItemStack golden_sword = new ItemStack(Items.GOLDEN_SWORD);
								golden_sword.setItemDamage(golden_sword.getMaxDamage()-1);
								playerIn.inventory.addItemStackToInventory(golden_sword);
								ItemStack diamond_sword = new ItemStack(Items.DIAMOND_SWORD);
								diamond_sword.setItemDamage(diamond_sword.getMaxDamage()-1);
								playerIn.inventory.addItemStackToInventory(diamond_sword);
								ItemStack diamond_chest = new ItemStack(Items.DIAMOND_CHESTPLATE);
								diamond_chest.setItemDamage(diamond_chest.getMaxDamage()-1);
								playerIn.inventory.addItemStackToInventory(diamond_chest);
								ItemStack orga_hair = new ItemStack(ItemLoader.orgaHair);
								orga_hair.setItemDamage(orga_hair.getMaxDamage()-1);
								playerIn.inventory.addItemStackToInventory(orga_hair);
								ItemStack star_sword = new ItemStack(ItemLoader.starSword);
								star_sword.setItemDamage(star_sword.getMaxDamage()-1);
								playerIn.inventory.addItemStackToInventory(star_sword);
							}
							break;
					}
				}
			}
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));

	}


}
	
	
	
	
	
	
	


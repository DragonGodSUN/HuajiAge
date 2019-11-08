package com.lh_lshen.mcbbs.huajiage.init;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//This code is mainly based on @Choonster's Test Mod 3
//https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.12.2/src/main/java/choonster/testmod3/event/LootTableEventHandler.java
//Follow the MIT License
@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class LootTableModule {

	/**
	 * When the {@link LootTableList#CHESTS_SIMPLE_DUNGEON} {@link LootTable} is loaded, add a new {@link LootPool} with
	 * a single {@link LootEntryTable} that points to {@link ModLootTables#LOOT_TABLE_TEST}.
	 * <p>
	 * Test for this thread:
	 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2781780-chest-loot
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void lootTableLoad(final LootTableLoadEvent event) {
		if (event.getName().equals(LootTableList.CHESTS_END_CITY_TREASURE)) {
			final String name = LootTablesLoader.LOOT_TABLE_HUAJI.toString();

			final LootEntry entry = new LootEntryTable(LootTablesLoader.LOOT_TABLE_HUAJI, 1, 0,
					new LootCondition[0], name);

			final RandomValueRange rolls = new RandomValueRange(0,1);

			final LootPool pool = new LootPool(new LootEntry[]{entry}, new LootCondition[0], rolls, rolls, name);

			event.getTable().addPool(pool);
		}
	}
}

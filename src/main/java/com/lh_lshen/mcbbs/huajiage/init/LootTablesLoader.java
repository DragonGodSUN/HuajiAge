package com.lh_lshen.mcbbs.huajiage.init;

import java.util.HashSet;
import java.util.Set;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.lh_lshen.mcbbs.huajiage.init.LootTablesLoader.RegistrationHandler.create;
//This code is mainly based on @Choonster's Test Mod 3
//https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.12.2/src/main/java/choonster/testmod3/init/ModLootTables.java
//Follow the MIT License
public class LootTablesLoader {
	public static final ResourceLocation LOOT_TABLE_HUAJI = create("huaji_loot");
	public static final ResourceLocation LOOT_TABLE_STAND_DESERT = create("stand_loot_desert");

	/**
	 * Register this mod's {@link LootTable}s.
	 */
	public static void registerLootTables() {
		RegistrationHandler.LOOT_TABLES.forEach(LootTableList::register);
	}

	public static class RegistrationHandler {

		/**
		 * Stores the IDs of this mod's {@link LootTable}s.
		 */
		private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();

		/**
		 * Create a {@link LootTable} ID.
		 *
		 * @param id The ID of the LootTable without the MODID:
		 * @return The ID of the LootTable
		 */
		protected static ResourceLocation create(final String id) {
			final ResourceLocation lootTable = new ResourceLocation(HuajiAge.MODID, id);
			RegistrationHandler.LOOT_TABLES.add(lootTable);
			return lootTable;
		}
	}
}

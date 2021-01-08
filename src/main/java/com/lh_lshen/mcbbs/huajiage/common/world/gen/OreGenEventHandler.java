package com.lh_lshen.mcbbs.huajiage.common.world.gen;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class OreGenEventHandler {
	
	public static IWorldGenerator huajiGenerator;

   public OreGenEventHandler()
	
    {
        huajiGenerator = new IWorldGenerator()
        {
            public final WorldGenMinable huajiGenerator = new WorldGenMinable(Blocks.GLOWSTONE.getDefaultState(), 4);
            public final WorldGenMinable huajiGenerator2 = new WorldGenMinable(BlockLoader.oreHuaji.getDefaultState(), 4);
           public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                    IChunkProvider chunkProvider)
            {
        	   if (world.provider.getDimension() != 0)
            {
                return;
            }
            {
            	
            	for (int i = 0; i < 2; ++i)
                {
                    int posX = chunkX * 16 + random.nextInt(16);
                    int posY = 0 + random.nextInt(16);
                    int posZ = chunkZ * 16 + random.nextInt(16);
                    BlockPos blockpos = new BlockPos(posX, posY, posZ);
                    Biome biomeGenBase = world.getBiomeForCoordsBody(blockpos);
                    if (biomeGenBase.getRainfall() < random.nextInt(65536))
                    {
                            huajiGenerator.generate(world, random, blockpos);
                            huajiGenerator2.generate(world, random, blockpos);
                        }
                    }
                }
            }
		@Override
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
				IChunkProvider chunkProvider) {
			 if (world.provider.getDimension() != 0)
	            {
	                return;
	            }
	            {
	            	
	            	for (int i = 0; i < 2; ++i)
	                {
	                    int posX = chunkX * 16 + random.nextInt(16);
	                    int posY = 5 + random.nextInt(10);
	                    int posZ = chunkZ * 16 + random.nextInt(16);
	                    BlockPos blockpos = new BlockPos(posX, posY, posZ);
	                    Biome biomeGenBase = world.getBiomeForCoordsBody(blockpos);
	                    if (biomeGenBase.getRainfall() < random.nextInt(65536))
	                    {
	                            huajiGenerator.generate(world, random, blockpos);
	                            huajiGenerator2.generate(world, random, blockpos);
	                        }
	                    }
	                }
			
		}
		
			
        };
        GameRegistry.registerWorldGenerator(huajiGenerator, 1);
    }
}

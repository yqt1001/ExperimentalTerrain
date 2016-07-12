package mc.yqt.experimentalterrain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;

public class TerrainGenerator extends ChunkGenerator {

	private static final int MAGNITUDE = 64;
	private static final int MEDIAN_LEVEL = 70;
	
	@Override
	public short[][] generateExtBlockSections(World world, Random random, int chunkX, int chunkZ, BiomeGrid grid) {
		short chunk[][] = new short[world.getMaxHeight() / 16][];
		
		PerlinOctaveGenerator elevation = new PerlinOctaveGenerator(world.getSeed(), 4);
		elevation.setScale(1/200.0);
		
		PerlinOctaveGenerator detail = new PerlinOctaveGenerator(world.getSeed(), 4);
		detail.setScale(1/30.0);
		
		PerlinOctaveGenerator rough = new PerlinOctaveGenerator(world.getSeed(), 1);
		rough.setScale(1/100.0);
		
		for(int x = 0; x < 16; x++) {
			for(int z = 0; z < 16; z++) {
				int realX = x + chunkX * 16;
				int realZ = z + chunkZ * 16;
				
				double elevationNoise = elevation.noise(realX, realZ, 0.5, 0.5, true);
				double detailNoise = detail.noise(realX, realZ, 0.5, 0.5, true);
				double roughNoise = rough.noise(realX, realZ, 0.5, 0.5, true);
				
				int maxHeight = (int) ((elevationNoise + detailNoise * roughNoise) * MAGNITUDE + MEDIAN_LEVEL);
				if(maxHeight > world.getMaxHeight())
					maxHeight = world.getMaxHeight();
				
				for(int y = 0; y < maxHeight; y++)
					setBlock(chunk, x, y, z, Material.STONE);
				
				if(maxHeight < 64) {
					for(int y = maxHeight; y < 64; y++)
						setBlock(chunk, x, y, z, Material.STATIONARY_WATER);
					
					// underwater sand blocks
					setBlock(chunk, x, maxHeight - 1, z, Material.SAND);
					setBlock(chunk, x, maxHeight - 2, z, Material.SAND);
					if(Math.random() > 0.5)
						setBlock(chunk, x, maxHeight - 3, z, Material.SAND);
				} else {
					// surface grass blocks
					setBlock(chunk, x, maxHeight - 1, z, Material.GRASS);
					setBlock(chunk, x, maxHeight - 2, z, Material.DIRT);
					if(Math.random() > 0.5)
						setBlock(chunk, x, maxHeight - 3, z, Material.DIRT);
				}
				
				grid.setBiome(x, z, Biome.PLAINS);
			}
		}
		
		
		return chunk;
	}
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return new LinkedList<BlockPopulator>();
	}
	
	private void setBlock(short[][] result, int x, int y, int z, Material material) {
		if(result[y >> 4] == null)
        	result[y >> 4] = new short[4096];

        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (short) material.getId();
	}
	
	private Material getBlock(short[][] result, int x, int y, int z) {
		if(result[y >> 4] == null)
			return Material.AIR;
        
		return Material.getMaterial(result[y >> 4][((y & 0xF) << 8) | (z << 4) | x]);
    }
}

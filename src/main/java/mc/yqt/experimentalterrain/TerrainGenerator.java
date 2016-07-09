package mc.yqt.experimentalterrain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class TerrainGenerator extends ChunkGenerator {

	@Override
	public short[][] generateExtBlockSections(World world, Random random, int x, int z, BiomeGrid grid) {
		return new short[0][0];
	}
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return new LinkedList<BlockPopulator>();
	}
}

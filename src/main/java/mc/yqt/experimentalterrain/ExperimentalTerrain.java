package mc.yqt.experimentalterrain;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class ExperimentalTerrain extends JavaPlugin {

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new TerrainGenerator();
	}
}

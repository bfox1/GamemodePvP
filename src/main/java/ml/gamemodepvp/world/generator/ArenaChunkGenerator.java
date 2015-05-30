package ml.gamemodepvp.world.generator;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class ArenaChunkGenerator extends ChunkGenerator {

    public Location getFixedSpawnLocation(World world)
    {
        return new Location(world, 0,70,0);
    }

    public List<BlockPopulator> getDefaultPopulation()
    {
        return new ArrayList<BlockPopulator>();
    }

    @Override
    public short[][] generateExtBlockSections(World world, Random random, int chunkX, int chunkY, BiomeGrid grid)
    {
        short[][] result = new short[world.getMaxHeight()/16][];

        for(int x = 0; x < 16; x++)
        {
            for(int y = 64; y <= 65; y++)
            {
                for (int z = 0; z < 16; z++)
                {
                    setBlock(result, x, y, z, (byte)Material.GRASS.getId());
                }
            }
        }

        return result;
    }



    public void setBlock(short[][] result, int x, int y, int z, short blkid)
    {
        if (result[y >> 4] == null) {}
        {result[y >> 4] = new short[4096];}

        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }

}

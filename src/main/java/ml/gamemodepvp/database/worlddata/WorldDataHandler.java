package ml.gamemodepvp.database.worlddata;

import ml.gamemodepvp.Modules.world.generator.ArenaChunkGenerator;
import ml.gamemodepvp.Modules.world.util.WorldController;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;

/**
 * Created by bfox1 on 5/10/2015.
 */
public class WorldDataHandler {


    private WorldController wc;
    public WorldDataHandler(WorldController wc)
    {
        this.wc = wc;
    }

    public WorldDataHandler()
    {

    }

    public void createWorldData(String name)
    {
        this.wc.setWc(new WorldCreator(name), name);
        this.wc.getWc().environment(World.Environment.NORMAL);
        this.wc.getWc().generator(new ArenaChunkGenerator());
        Bukkit.createWorld(this.wc.getWc());
    }



    public WorldController returnWorldControl()
    {
        return this.wc;
    }
}

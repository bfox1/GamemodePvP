package ml.gamemodepvp.world.util;

import ml.gamemodepvp.world.region.Region;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;

/**
 * Created by bfox1 on 5/9/2015.
 */
public class WorldController implements Serializable{

    private String worldName;
    private World world;
    private transient WorldCreator wc;
    private Region rg;


    public WorldController() {

    }




    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public WorldCreator getWc() {
        return wc;
    }

    public void setWc(WorldCreator wc, String worldName) {
        this.wc = wc;
        this.worldName = worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldName()
    {
        return this.worldName;
    }

    public Region getRg() {
        return rg;
    }

    public void setRg(Region rg) {
        this.rg = rg;
    }
}

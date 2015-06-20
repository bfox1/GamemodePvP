package ml.gamemodepvp.Modules.gamemodes;

import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.Modules.world.util.SerializableLocation;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.MathematicsUtility;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 */
public class SpawnLocations {
    /**
     * SpawnLocations will collect ALL the spawning locations within the Arena it was assigned to.
     * This will figure the Size of the map, if its a team match or free for all and setup random spawn locations
     * through the map separated or congregated depending on the match. It will also handle Spawn swaps if Players
     * are all cluttered in other teams spawn.
     */


    private World world;

    private boolean team;

    private Region region;

    private Map<World, List<Location>> locationsMap = new HashMap<World, List<Location>>();

    public SpawnLocations(World world)
    {
        this.world = world;
    }


    public void setWorld(World world)
    {
        this.world = world;
    }

    public void setTeam(boolean team)
    {
        this.team = team;
    }

    public void setRegion(Region region)
    {
        this.region = region;
    }

    public void setLocations(Location locations)
    {
        //this.locationsMap.put(this.world, locations);
        List<Location> loc = this.locationsMap.get(this.world);
        if(loc == null)
        {
            loc = new ArrayList<Location>();
        }
        int it = 0;
        try
        {
            while(loc.size() >= it)
            {
                it++;
            }
            loc.add(0,locations);
            this.locationsMap.put(this.getWorld(), loc);
        }catch (NullPointerException e)
        {

            DebugCore.returnDebugMessage("SOMETING HAPPENED" + it + locations);
            loc.add(it,locations);
            this.locationsMap.put(this.getWorld(), loc);
        }

    }

    public List<Location> getLocationList()
    {
        return this.locationsMap.get(this.getWorld());
    }

    public World getWorld()
    {
        return this.world;
    }

    private int getWorldBorderSize()
    {
        return (int)this.world.getWorldBorder().getSize();
    }



    public void saveLocations()
    {
        File locationFile = new File("plugins/GamemodePvP/arenadata/", this.getWorld().getName() + ".yml");
        if(!locationFile.exists())
        {
            try {
                locationFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = new YamlConfiguration().loadConfiguration(locationFile);
        for(int i = 0; i < this.getLocationList().size(); i++)
        {
            config.set("SpawnCoordinates", "");
            config.set("SpawnCoordinates." + this.getWorld().getName(), "WorldCoords");
            config.set("SpawnCoordinates." + this.getWorld().getName() + "." + String.valueOf(i) + ".X", this.getLocationList().get(i).getX());
            config.set("SpawnCoordinates." + this.getWorld().getName() + "." + String.valueOf(i) + ".Y", this.getLocationList().get(i).getY());
            config.set("SpawnCoordinates." + this.getWorld().getName() + "." + String.valueOf(i) + ".Z", this.getLocationList().get(i).getZ());
        }

        try {
            config.save(locationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * To determine Team based GamePlay. If position is positve, Team is Red, else is Blue
     * @param location
     * @return
     */
    public boolean ifPositive(Location location)
    {
        return location.getX() > 0;
    }


    public void loadLocations()
    {
        File locationFile = new File("plugins/GamemodePvP/arenadata/", this.getWorld().getName() + ".yml");
        if(!locationFile.exists())
        {
            try
            {

                locationFile.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(locationFile);

        if(config.get("SpawnCoordinates") != null)
        {
            DebugCore.returnDebugMessage("Not null");

            List<Location> list = new ArrayList<Location>();

            boolean isSingler = false;

            if(validatePath("SpawnCoordinates." + this.getWorld().getName(), config))
            {
                isSingler = true;

            }
            if(validateIntListPath("SpawnCoordinates." + this.getWorld().getName(), config) )
            {
                if (!isSingler)
                for (int i = 0; i < config.getIntegerList("SpawnCoordinates." + this.getWorld().getName()).size(); i++)
                {
                    DebugCore.returnDebugMessage("Still made it :)");

                    int index = (Integer) config.getList("SpawnCoordinates." + this.getWorld().getName()).get(i);

                    List<Integer> coords = config.getIntegerList(
                            "SpawnCoordinates." + this.getWorld().getName() + "." + index);

                    if (coords.size() == 3) {

                        SerializableLocation location =
                                new SerializableLocation(coords.get(0), coords.get(1), coords.get(2));

                        list.add(i, location.getLocation());
                    }
                }
            }
            else
            {
                if(isSingler)
                {
                    int index = (Integer)config.getInt("SpawnCoordinates." + this.getWorld().getName());
                    List<Integer> coords = config.getIntegerList(
                            "SpawnCoordinates." + this.getWorld().getName() + "." + index);

                    if(coords.size() == 3)
                    {
                        SerializableLocation location =
                                new SerializableLocation(coords.get(0), coords.get(1), coords.get(2));

                        list.add(index, location.getLocation());
                    }
                }
            }
            this.locationsMap.put(this.world, list);
        }
        else
        {
            this.locationsMap = new HashMap<World, List<Location>>();
        }
    }

    private boolean validateIntListPath(String path, FileConfiguration configuration)
    {
        return configuration.getIntegerList(path) != null;
    }
    private boolean validateListpath(String path, FileConfiguration config)
    {
        return config.getList(path) != null;
    }


    private boolean validatePath(String path, FileConfiguration configuration)
    {
        return configuration.get(path) != null;
    }



}

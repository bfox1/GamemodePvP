package ml.gamemodepvp.Modules.gamemodes;

import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.util.MathematicsUtility;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
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
        int it = 0;
        while(loc.get(it) != null)
        {
            it++;
        }
        loc.add(it,locations);
        this.locationsMap.put(this.getWorld(), loc);
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
        FileConfiguration config = new YamlConfiguration();
        config.set("SpawnCoordinates", locationsMap);
        try {
            config.save(new File("plugins/GamemodePvP/arenadata/" + this.getWorld().getName()));
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
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File("plugins/GamemodePvP/arenadata/" + this.getWorld().getName()));
        this.locationsMap = (HashMap)config.get("SpawnCoordinates");
    }


}

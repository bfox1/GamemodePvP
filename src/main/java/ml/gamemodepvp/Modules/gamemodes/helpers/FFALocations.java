package ml.gamemodepvp.Modules.gamemodes.helpers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 6/7/2015.
 * In God We Trust.
 */
public interface FFALocations {

    public int spawns = 20;

    void setSpawningLocations(Player player);

    boolean LocationOccupied(Location location, Player player);

    void setRespawnLocation(Player player);


}

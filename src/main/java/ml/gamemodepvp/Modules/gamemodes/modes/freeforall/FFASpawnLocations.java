package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;

import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.Modules.gamemodes.helpers.FFALocations;
import net.minecraft.server.v1_8_R2.MathHelper;
import net.minecraft.server.v1_8_R2.RandomPositionGenerator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.math.MathContext;
import java.util.Random;

/**
 * Created by bfox1 on 6/7/15.
 * In God we Trust.
 */
public class FFASpawnLocations extends SpawnLocations implements FFALocations {


    public FFASpawnLocations(World world)
    {
        super(world);
        this.setTeam(false);

    }


    @Override
    public void setSpawningLocations(Player player)
    {

        for(int i = 0; i < this.getLocationList().toArray().length; i++)
        {

            Random rand = new Random();
            Location loc = this.getLocationList().get(rand.nextInt(this.getLocationList().toArray().length));

            if(!LocationOccupied(loc, player))
            {
                player.teleport(loc);
            } else{setSpawningLocations(player);}
        }
    }

    @Override
    public boolean LocationOccupied(Location location, Player player)
    {
        return location == player.getLocation();
    }

    @Override
    public void setRespawnLocation(Player player)
    {
        setSpawningLocations(player);
    }
}

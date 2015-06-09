package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;

import ml.gamemodepvp.Modules.gamemodes.Gamemode;

import ml.gamemodepvp.Modules.gamemodes.Mode;
import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

/**
 * Created by bfox1 on 6/5/15.
 * In God We Trust.
 */
public class FreeForAll extends Gamemode {



    public FreeForAll(World world)
    {
        super(world);
        gameSetup();
    }

    @Override
    public void setLocations(SpawnLocations locations)
    {
        this.locations = locations;
    }

    @Override
    public void setMode(Mode mode) {

    }

    @Override
    public void setScoreManagement(ScoreManagement management)
    {
        this.scoreManagement = management;
    }

    @Override
    public void setTeams(boolean team)
    {

    }

    public void lobbyHold(Player player)
    {
        this.getPlayerMap().put(player.getUniqueId(), player);
    }

    public void lobbyUpdate()
    {
        for(Map.Entry me : this.getPlayerMap().entrySet())
        {
            if(Bukkit.getServer().getPlayer((UUID)me.getKey()) == null || Bukkit.getServer().getPlayer((UUID)me.getKey()).getWorld() != this.world)
            {
                this.getPlayerMap().remove(me.getKey());
            }
        }
    }

    /**
     * Sets the Direct Location of Spawn.
     * @param loc
     */
    public void setLocation(Location loc)
    {
        this.locations.loadLocations();
        this.locations.setLocations(loc);
        this.locations.saveLocations();
    }

    /**
     * To check if the Map has Enough Locations spawns set.
     * @return
     */
    public boolean ifEnoughLocations()
    {
        return this.locations.getLocationList().toArray().length > 10;
    }


    public void gameSetup()
    {
        this.setLocations(new FFASpawnLocations(this.world));
        this.setMode(new FFAMode());
        this.setScoreManagement(new FFAScoreManagement(new Scoreboard(), "FFA"));
        this.setTeams(false);
    }


    public void startGame()
    {
        for(Map.Entry me : this.getPlayerMap().entrySet())
        {
            ((FFASpawnLocations)this.getLocations()).setSpawningLocations((Player)me.getValue());
        }
    }

}

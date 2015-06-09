package ml.gamemodepvp.Modules.gamemodes;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 */
public abstract class Gamemode {

    /**
     * Gamemode gets generated when Lobby Sets up the Arena and Gamemode Definition. Then Gamemode gets called
     * and loads to Generated Gamemode Lobby Event and remains active till the event ends.
     */

    protected SpawnLocations locations;
    protected Mode mode;
    protected ScoreManagement scoreManagement;
    protected boolean teams;
    protected boolean inCreationMode = false;

    protected World world;


    private Map<UUID, Player> playerMap = new HashMap<UUID, Player>();


    public Gamemode(World world)
    {
        this.world = world;
    }


    public abstract void setLocations(SpawnLocations locations);

    public abstract void setMode(Mode mode);

    public abstract void setScoreManagement(ScoreManagement management);

    public abstract void setTeams(boolean team);

    public SpawnLocations getLocations()
    {
        return locations;
    }

    public Map<UUID, Player> getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(Map<UUID, Player> playerMap) {
        this.playerMap = playerMap;
    }
}

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
 * TODO
 */
public class FreeForAll extends Gamemode {

    private SpawnLocations spawnLocations;
    private ScoreManagement scoreManagement;
    private ModeEnumeration enumeration;


    public FreeForAll( SpawnLocations location, ScoreManagement management, ModeEnumeration enumeration) {
        this.setLocations(location);
        this.setScoreManagement(management);
        this.setModeEnumeration(enumeration);


    }

    @Override
    public void setLocations(SpawnLocations locations)
    {
        this.spawnLocations = locations;
    }


    @Override
    public void setScoreManagement(ScoreManagement management)
    {
        this.scoreManagement = management;
    }

    @Override
    public void setModeEnumeration(ModeEnumeration enumeration) {

    }



}

package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;

import ml.gamemodepvp.Modules.gamemodes.Gamemode;

import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;

/**
 * Created by bfox1 on 6/5/15.
 * In God We Trust.
 * TODO
 */
public class FreeForAll extends Gamemode {

    private SpawnLocations spawnLocations;
    private ScoreManagement scoreManagement;
    private ModeProperties modeProperties;


    public FreeForAll( SpawnLocations location, ScoreManagement management, ModeProperties enumeration) {
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
    public void setModeEnumeration(ModeProperties modeProperties)
    {
    this.modeProperties = modeProperties;
    }

    @Override
    public ModeProperties getModeProperties() {
        return this.modeProperties;
    }

    @Override
    public SpawnLocations getLocations() {
        return this.spawnLocations;
    }

    @Override
    public ScoreManagement getScoreManageMent() {
        return scoreManagement;
    }


}

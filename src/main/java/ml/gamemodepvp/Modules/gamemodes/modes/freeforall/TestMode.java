package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;

import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;

/**
 * Created by bfox1 on 6/19/2015.
 * In God We Trust.
 */
public class TestMode extends Gamemode
{
    private SpawnLocations location;
    private ScoreManagement scoreManagement;
    private ModeProperties props;

    public TestMode(SpawnLocations location, ScoreManagement management, ModeProperties props)
    {
        setLocations(location);
        setScoreManagement(management);
        setModeEnumeration(props);
    }
    @Override
    public void setLocations(SpawnLocations locations)
    {
        this.location = locations;
    }

    @Override
    public void setScoreManagement(ScoreManagement management)
    {
        this.scoreManagement = management;
    }

    @Override
    public void setModeEnumeration(ModeProperties enumeration)
    {
        this.props = enumeration;
    }

    @Override
    public ModeProperties getModeProperties() {
        return props;
    }

    @Override
    public SpawnLocations getLocations() {
        return location;
    }

    @Override
    public ScoreManagement getScoreManageMent() {
        return scoreManagement;
    }
}

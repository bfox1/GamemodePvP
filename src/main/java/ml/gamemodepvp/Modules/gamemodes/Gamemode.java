package ml.gamemodepvp.Modules.gamemodes;

/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 */
public abstract class Gamemode {

    /**
     * Gamemode gets generated when Lobby Sets up the Arena and Gamemode Definition. Then Gamemode gets called
     * and loads to Generated Gamemode Lobby Event and remains active till the event ends.
     */

    private SpawnLocations locations;
    private Mode mode;
    private ScoreManagement scoreManagement;
    private boolean teams;


    public abstract void setLocations(SpawnLocations locations);

    public abstract void setMode(Mode mode);

    public abstract void setScoreManagement(ScoreManagement management);

    public abstract void setTeams(boolean team);
}

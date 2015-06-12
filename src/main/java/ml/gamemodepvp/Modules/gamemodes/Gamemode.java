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
    protected ScoreManagement scoreManagement;
    protected ModeEnumeration mode;

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

    public abstract void setEnumMode(ModeEnumeration mode);


    /**
     * Created by bfox1 on 6/11/2015.
     * In God We Trust.
     */
    public enum ModeEnumeration {

        FREEFORALL("Free For All", "FFA", 5, 12, 30, false, 10),
        TEAMDEATHMATCH("Team Deathmatch", "TDM", 8, 16, 75, true, 15),
        CAPTURETHEFLAG("Capture The Flag", "CTF", 8, 16, 300, true, 30);


        public String modeName;
        public String initials;
        private int minPlayerCount;
        private int maxPlayerCount;
        private int scoreVictory;
        private boolean team;
        private int timer;


        ModeEnumeration(String modeName, String initials, int minPlayerCount, int maxPlayerCount, int scoreVictory,
                        boolean team, int timer)
        {
            this.modeName = modeName;
            this.initials = initials;
            this.minPlayerCount = minPlayerCount;
            this.maxPlayerCount = maxPlayerCount;
            this.scoreVictory = scoreVictory;
            this.team = team;
            this.timer = timer;
        }

        public void setModeName(String name)
        {
            this.modeName = name;
        }

        public void setInitials(String in)
        {
            this.initials = in;
        }

        public String getModeName()
        {
            return this.modeName;
        }

        public String getInitials()
        {
            return this.initials;
        }

        public int getMinPlayerCount() {
            return minPlayerCount;
        }

        public void setMinPlayerCount(int minPlayerCount) {
            this.minPlayerCount = minPlayerCount;
        }

        public int getMaxPlayerCount() {
            return maxPlayerCount;
        }

        public void setMaxPlayerCount(int maxPlayerCount) {
            this.maxPlayerCount = maxPlayerCount;
        }

        public int getScoreVictory() {
            return scoreVictory;
        }

        public void setScoreVictory(int scoreVictory) {
            this.scoreVictory = scoreVictory;
        }

        public boolean isTeam() {
            return team;
        }

        public void setTeam(boolean team) {
            this.team = team;
        }

        public int getTimer() {
            return timer;
        }

        public void setTimer(int timer) {
            this.timer = timer;
        }
    }
}

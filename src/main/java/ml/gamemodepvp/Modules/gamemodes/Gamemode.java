package ml.gamemodepvp.Modules.gamemodes;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 * Gamemode gets generated when Lobby Sets up the Arena and Gamemode Definition. Then Gamemode gets called
 * and loads to Generated Gamemode Lobby Event and remains active till the event ends.
 */

public abstract class Gamemode {


    /**
     * To set the Instance of SpawnLocation. This is for Players Positions in the Arena
     * @param locations The Location to set
     */
    public abstract void setLocations(SpawnLocations locations);

    /**
     * To set the Instance of the ScoreManagement. For Putting correct Scoreboard for Player during Game.
     * @param management The ScoreManagement to set
     */
    public abstract void setScoreManagement(ScoreManagement management);


    /**
     * Sets the Type of Mode the Gamemode is
     * @param enumeration the Enumeration to set.
     */
    public abstract void setModeEnumeration(ModeEnumeration enumeration);



    /**
     * Created by bfox1 on 6/11/2015.
     * In God We Trust.
     * Special Enumeration for the Mode that Stores the specific Data for the Gamemodes. These are Hardcoded to prevent
     * the create of others! an API will be implemented for custom configuration and Mode Creation.
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
                        boolean team, int timer) {
            this.modeName = modeName;
            this.initials = initials;
            this.minPlayerCount = minPlayerCount;
            this.maxPlayerCount = maxPlayerCount;
            this.scoreVictory = scoreVictory;
            this.team = team;
            this.timer = timer;
        }

        public void setModeName(String name) {
            this.modeName = name;
        }

        public void setInitials(String in) {
            this.initials = in;
        }

        public String getModeName() {
            return this.modeName;
        }

        public String getInitials() {
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

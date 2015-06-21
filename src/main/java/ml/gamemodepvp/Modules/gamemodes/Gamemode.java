package ml.gamemodepvp.Modules.gamemodes;

import java.util.Timer;
import java.util.TimerTask;

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
    public abstract void setModeEnumeration(ModeProperties enumeration);

    public abstract ModeProperties getModeProperties();

    public abstract SpawnLocations getLocations();

    public abstract ScoreManagement getScoreManageMent();



    /**
     * Created by bfox1 on 6/11/2015.
     * In God We Trust.
     * Special Enumeration for the Mode that Stores the specific Data for the Gamemodes. These are Hardcoded to prevent
     * the create of others! an API will be implemented for custom configuration and Mode Creation.
     */
    public enum ModeProperties {

        TESTLOBBY("TEST", "test", 5, 13, 40, false, 3),
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
        private int waitingTime;


        /**
         * Properties for the Gamemode type.
         * @param modeName GameModes Name.
         * @param initials For easy lookup
         * @param minPlayerCount Minimum count for players to be able to play Game
         * @param maxPlayerCount Maximum count for players to be in lobby
         * @param scoreVictory The amount of Score for the game to end.
         * @param team If gamemode is a Team match. This will also determine if shared score or not.
         * @param timer In minutes, sets the limit if score isnt reached, timer will end the game.
         */
        ModeProperties(String modeName, String initials, int minPlayerCount, int maxPlayerCount, int scoreVictory,
                       boolean team, int timer) {
            this.modeName = modeName;
            this.initials = initials;
            this.minPlayerCount = minPlayerCount;
            this.maxPlayerCount = maxPlayerCount;
            this.scoreVictory = scoreVictory;
            this.team = team;
            this.timer = timer;
            this.waitingTime = 5;
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

        public void extendTime(int addedMinutes)
        {
            this.timer = timer + addedMinutes;
        }

        public int getTicksPer()
        {

            return this.timer*60*20;
        }

        public int getTicksPerWaitingTime()
        {
            return this.waitingTime*60*20;
        }

        public int convertTicksToMinutes(int ticks)
        {

            return ticks/60/20;
        }
    }
}

package ml.gamemodepvp.Modules.gamemodes;

import net.minecraft.server.v1_8_R2.*;
import net.minecraft.server.v1_8_R2.Scoreboard;




/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 */
public class ScoreManagement {

    /**
     * ScoreManagement will handle the ScoreBoard of the Arena it is assigned to. Having access to ALL players located
     * within the arena and being able to send correct ScoreBoard data to player
     */

    private Scoreboard sb;
    private String gamemodeType;
    private String kdr;
    private String score;
    private String killToWin;
    private String teamColor;

    public ScoreManagement(Scoreboard sb)
    {
        this.sb = sb;
    }


    public void setScoreBoard(Scoreboard sb)
    {
        this.sb = sb;
    }

    public Scoreboard getScoreBoard()
    {
        return sb;
    }


    public String getGamemodeType() {
        return gamemodeType;
    }

    public void setGamemodeType(String gamemodeType) {
        this.gamemodeType = gamemodeType;
    }

    public String getKdr() {
        return kdr;
    }

    public void setKdr(String kdr) {
        this.kdr = kdr;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


}

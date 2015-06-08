package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;

import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.helpers.FFAScoreBoardHelper;
import net.minecraft.server.v1_8_R2.Scoreboard;
import net.minecraft.server.v1_8_R2.ScoreboardBaseCriteria;

/**
 * Created by bfox1 on 6/7/2015.
 * In God We Trust.
 */
public class FFAScoreManagement extends ScoreManagement implements FFAScoreBoardHelper{

    private Scoreboard sb;

    public FFAScoreManagement(Scoreboard sb)
    {
        super(sb);
        this.setScoreBoard(constructObjective("FFA"));
    }

    @Override
    public int tillWin() {
        return 75;
    }

    @Override
    public Scoreboard constructObjective(String objectiveName)
    {

        Scoreboard scoreBoard = this.getScoreBoard();
        scoreBoard.registerObjective(objectiveName, new ScoreboardBaseCriteria(objectiveName));
        return scoreBoard;
    }

    @Override
    public void sendPackets(Scoreboard scoreboard) {

    }
}

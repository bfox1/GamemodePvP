package ml.gamemodepvp.Modules.gamemodes.modes.freeforall;


import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.helpers.FFAScoreBoardHelper;
import net.minecraft.server.v1_8_R2.*;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 6/7/2015.
 * In God We Trust.
 */
public class FFAScoreManagement extends ScoreManagement implements FFAScoreBoardHelper{





    public FFAScoreManagement(Scoreboard sb, String modeName)
    {
        super(sb);
        this.setScoreBoard(constructObjective(modeName));
        this.setGamemodeType(modeName);
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
    public void sendPackets(Player player, Packet obj)
    {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(obj);

    }

    private void packetSender(Player player, ScoreboardScore mScoreItem)
    {
        PacketPlayOutScoreboardScore scoreboardScore = new PacketPlayOutScoreboardScore(mScoreItem);
        sendPackets(player, scoreboardScore);
    }


    @Override
    public void createScoreItems(Player player)
    {
        Scoreboard board = this.getScoreBoard();
        PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective(this.getScoreBoard().getObjective(this.getGamemodeType()), 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, this.getScoreBoard().getObjective(this.getGamemodeType()));

        sendPackets(player, packet);
        sendPackets(player, display);

        ScoreboardScore gamemodeType = createItem(this.getGamemodeType(), 0);
        ScoreboardScore kill = createItem("Kills", 0);
        ScoreboardScore assist = createItem("Assists", 0);
        ScoreboardScore deaths = createItem("Deaths", 0);
        ScoreboardScore currentScore = createItem("Score", 0);
        ScoreboardScore scoreToWin = createItem("To Win", this.tillWin());

        packetSender(player, gamemodeType);
        packetSender(player, kill);
        packetSender(player, assist);
        packetSender(player, deaths);
        packetSender(player, currentScore);
        packetSender(player, scoreToWin);
    }

    private ScoreboardScore createItem(String name, int setScore)
    {
        ScoreboardScore scoreboardScore = this.getScoreBoard().getPlayerScoreForObjective(name, this.getScoreBoard().getObjective(this.getGamemodeType()));
        scoreboardScore.setScore(setScore);
        return scoreboardScore;
    }

    @Override
    public void updateScoreItem(Player player,Packet scoreItem) {

    }


    public void setFFAScoreItems()
    {
        //ScoreboardScore scoreItem = this.getScoreBoard().getPlayerScoreForObjective()
    }


}

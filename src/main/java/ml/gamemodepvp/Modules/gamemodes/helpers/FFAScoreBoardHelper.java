package ml.gamemodepvp.Modules.gamemodes.helpers;

import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 6/7/2015.
 * In God We Trust.
 */
public interface FFAScoreBoardHelper {

    int tillWin();

    Scoreboard constructObjective(String objectiveName);

    void sendPackets(Player player, Packet obj);

    void createScoreItems(Player player);

    void updateScoreItem(Player player, Packet scoreItem);



}

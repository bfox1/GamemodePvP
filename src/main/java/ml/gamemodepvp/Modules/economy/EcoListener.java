package ml.gamemodepvp.Modules.economy;

import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.playerdata.PlayerDataHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by bfox1 on 4/29/2015.
 */
public class EcoListener implements Listener {

    private PlayerDataHandler playerDataHandler;
    private CorePlayerData corePlayerData;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(!event.getPlayer().hasPlayedBefore())
        {

        }
    }




}

package ml.gamemodepvp.supervision;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.GamemodePvPMessageUtility;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 * Just a runnable task for Updating Lobby status
 */
public class LobbyTask extends BukkitRunnable {

    private CoreMain main;
    private Lobby lobby;

    public LobbyTask(Lobby lobby, CoreMain main)
    {
        this.main = main;
        this.lobby = lobby;
    }



    @Override
    public void run()
    {
        DebugCore.returnDebugMessage(lobby.getPlayerMapData().size());
        if(lobby.isLobbyEmpty())
        {
            DebugCore.returnDebugMessage("Shutting Down Lobby");
            this.cancel();

        }
        else {
            DebugCore.returnDebugMessage("Running Waiting Lobby...");
        }

    }
}

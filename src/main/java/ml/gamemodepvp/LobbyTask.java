package ml.gamemodepvp;

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
    public void shutDownLobby()
    {
        if(lobby.isActiveGame())
        {
            this.cancel();
        }
        else
        {
            this.cancel();
        }
        LobbyManager.closeLobby(lobby, this.main.getLobbyManager());
    }
    @Override
    public void run()
    {

            if (lobby.isLobbyEmpty()) {
                DebugCore.returnDebugMessage("EMPTY?");
                shutDownLobby();
            }
            GamemodePvPMessageUtility.sendMessageToConsole("Their are " + lobby.getPlayerMapData().size() + "in this lobby!", this.main);
    }
}

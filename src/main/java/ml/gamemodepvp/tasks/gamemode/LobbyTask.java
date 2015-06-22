package ml.gamemodepvp.tasks.gamemode;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.GamemodePvPMessageUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 * Just a runnable task for Updating Lobby status
 */
public class LobbyTask extends BukkitRunnable {

    private CoreMain main;
    private Lobby lobby;
    private int counter;
    private int taskID;


    public LobbyTask(Lobby lobby, CoreMain main)
    {
        this.main = main;
        this.lobby = lobby;
        this.counter = lobby.getGamemode().getModeProperties().getTicksPerWaitingTime();
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
            beginWaitingLobbyCountdown(lobby.getGamemode().getModeProperties().getTicksPerWaitingTime(), 0 , lobby);
            DebugCore.returnDebugMessage("Running Waiting Lobby...");
        }

    }

    public void beginWaitingLobbyCountdown(int start, final int end, Lobby lobby)
    {

        if(start < end) return;
        this.counter = start;
        Bukkit.broadcastMessage(ChatColor.BLUE + "Time: " + counter);
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run()
            {
                counter--;
                Bukkit.broadcastMessage(ChatColor.BLUE + "Time: " + counter);
                if(counter == end)
                {
                    Bukkit.getScheduler().cancelTask(taskID);
                }

            }
        }, 0, 20);
        }

}

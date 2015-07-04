package ml.gamemodepvp.tasks.gamemode;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.util.DebugCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

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
            Bukkit.getScheduler().cancelTask(this.getTaskId());

        }
        else {
            beginWaitingLobbyCountdown(lobby.getGamemode().getModeProperties().getWaitingTime()*60, 0 , lobby);
            DebugCore.returnDebugMessage("Running Waiting Lobby...");
        }

    }

    public void beginWaitingLobbyCountdown(int start, final int end, final Lobby lobby)
    {


        if(start < end) return;
        this.counter = start;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run()
            {
                counter--;

                Bukkit.broadcastMessage(ChatColor.BLUE + "Time: " + counter);
                if(lobby.isLobbyEmpty())
                {
                    Bukkit.getScheduler().cancelTask(taskID);
                }
                if(counter == end)
                {
                    if(lobby.requirementsMet())
                    {

                    }
                }


            }
        }, 0, 20);
        }

}

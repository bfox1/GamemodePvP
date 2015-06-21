package ml.gamemodepvp.Modules.gamemodes.listener;

import ml.gamemodepvp.CoreListener;
import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.TestMode;
import ml.gamemodepvp.debugbox.LobbyNotFoundException;
import ml.gamemodepvp.events.LobbyJoinEvent;
import ml.gamemodepvp.events.LobbyLeaveEvent;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.supervision.LobbyTask;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.LobbyValidate;
import ml.gamemodepvp.util.ModuleChat;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by bfox1 on 6/19/2015.
 * In God We Trust.
 */
public class LobbyListener extends CoreListener {



    public LobbyListener(CoreMain main) {
        super(main);
    }



    @EventHandler
    public void onLobbyJoin(LobbyJoinEvent event)
    {
        Player player = event.getPlayer();
        LobbyValidate lobbyValidation = LobbyManager.getPlayerValidation(player, this.getMain().getLobbyManager());
        if(lobbyValidation.isInLobby())
        {
            lobbyValidation.getLobby().leaveLobby(player);
        }


        try
        {
            Lobby lobby = event.getLobby();
            lobby.joinLobby(player);
        }catch (LobbyNotFoundException e)
        {
            Lobby lobby = new Lobby(event.getLobbyName(), player, this.getMain());

            lobby.joinLobby(player);

            lobby.setGamemode(
                    new TestMode(new SpawnLocations(player.getWorld()),
                            new ScoreManagement(new Scoreboard()),
                            Gamemode.ModeProperties.TESTLOBBY));

            this.getMain().getLobbyManager().addLobby(lobby);

            BukkitTask task = new LobbyTask(lobby,
                    this.getMain()).runTaskTimerAsynchronously(this.getMain(), 0, (lobby.getGamemode().getModeProperties().getTicksPerWaitingTime()));


        }



    }

    @EventHandler
    public void onLobbyLeave(LobbyLeaveEvent event)
    {
        Player player = event.getPlayer();

            Lobby lobby = event.getLobby();
            lobby.leaveLobby(player);
            if(this.getMain().getLobbyManager().getLobbyInfo(event.getLobbyName()).isLobbyEmpty())
            {
                this.getMain().getLobbyManager().closeLobby(lobby);
                DebugCore.returnDebugMessage("Closed Lobby");
            }

    }

}

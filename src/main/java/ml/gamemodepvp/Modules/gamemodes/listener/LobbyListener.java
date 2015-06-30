package ml.gamemodepvp.Modules.gamemodes.listener;

import ml.gamemodepvp.CoreListener;
import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.gamemodes.ScoreManagement;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.TestMode;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.events.LobbyJoinEvent;
import ml.gamemodepvp.events.LobbyLeaveEvent;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.tasks.gamemode.LobbyTask;
import ml.gamemodepvp.util.LobbyValidate;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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



            //Validates if Player is in lobby.//

            LobbyValidate lobbyValidation = LobbyManager.getPlayerValidation(player, this.getMain().getLobbyManager());

            if (lobbyValidation.isInLobby())
            {

                lobbyValidation.getLobby().leaveLobby(player);
            }


        // Will attempt to add Player to Lobby if exists. //

            try {

                Lobby lobby = event.getLobby();
                lobby.joinLobby(player);

            } catch (IllegalArgumentException e)
            {

                // Creates Default Lobby //

                Lobby lobby = new Lobby(event.getLobbyName(), player, this.getMain());

                lobby.setGamemode(
                        new TestMode(new SpawnLocations(player.getWorld()),
                                new ScoreManagement(new Scoreboard()),
                                Gamemode.ModeProperties.TESTLOBBY));

                lobby.joinLobby(player);


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
            }

    }

}

package ml.gamemodepvp.util;

import ml.gamemodepvp.Modules.gamemodes.Lobby;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 */
public class LobbyValidate {

    private boolean isInLobby = false;
    private Player player;
    private boolean isGameActive;
    private Lobby lobby;

    public boolean isInLobby() {
        return isInLobby;
    }

    public void setInLobby(boolean isInLobby) {
        this.isInLobby = isInLobby;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public void setGameActive(boolean isGameActive) {
        this.isGameActive = isGameActive;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}

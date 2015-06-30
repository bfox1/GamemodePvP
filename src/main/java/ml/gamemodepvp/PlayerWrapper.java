package ml.gamemodepvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by bfox1 on 6/22/2015.
 * In God We Trust.
 */
public class PlayerWrapper implements Serializable{

    private final UUID playerID;

    private final String playerName;

    private transient final Player player;

    public PlayerWrapper(Player player)
    {
        this.playerID = player.getUniqueId();
        this.playerName = player.getName();
        this.player = player;

    }

    public UUID getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player getPlayer() {
        return player;
    }

    public static PlayerWrapper getPlayerWrapper(Player player)
    {
        return getPlayerWrapper(player.getUniqueId());
    }

    public static PlayerWrapper getPlayerWrapper(UUID playerID)
    {
        return new PlayerWrapper(Bukkit.getPlayer(playerID));
    }
}

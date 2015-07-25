package ml.gamemodepvp.bukkit;

import ml.gamemodepvp.AbstractPlayer;
import ml.gamemodepvp.PlayerWrapper;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bfox1 on 7/13/2015.
 * In God We Trust.
 */
public class GmpvpPlayer {

    private CoreMain main;

    private final Map<UUID, AbstractPlayer> playerMap;

    public GmpvpPlayer(CoreMain main)
    {
        this.main = main;
        this.playerMap = new HashMap<UUID, AbstractPlayer>();
    }

    public void addPlayer(PlayerWrapper player)
    {
        if(player != null)
        playerMap.put(player.getPlayerUUID(), player);
    }

    public void removePlayer(PlayerWrapper player)
    {
        if(player != null)
        {
            if(playerMap.containsKey(player.getPlayerUUID()))
            {
                playerMap.remove(player.getPlayerUUID());
            }
        }
        else
        {
            throw new NullPointerException("No Player Data Found.");
        }
    }

    public AbstractPlayer getAbstractPlayer(Player player)
    {
        return player != null && playerMap.containsKey(player.getUniqueId()) ?  playerMap.get(player.getUniqueId()) : null;
    }

    public AbstractPlayer getAbstactPlayer(UUID id)
    {
        return id != null && playerMap.containsKey(id) ? playerMap.get(id) : null;
    }



}

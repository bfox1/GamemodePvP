package ml.gamemodepvp;

import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.factory.GmPvPFactory;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by bfox1 on 6/22/2015.
 * In God We Trust.
 */
public class PlayerWrapper extends AbstractPlayer implements Serializable{

    private final UUID playerID;

    private final String playerName;

    private String lobbyName;
    private String regionName;

    private boolean inLobby;
    private boolean inRegion;


    public PlayerWrapper(Player player)
    {
        this.playerID = player.getUniqueId();
        this.playerName = player.getName();

    }


    @Override
    public boolean isInLobby() {
        return inLobby;
    }

    @Override
    public boolean isInRegion() {
        return inRegion;
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(playerID);
    }

    @Override
    public void setLobby(Lobby lobby)
    {
        this.lobbyName = lobby.getLobbyName();
        this.inLobby = true;
    }

    @Override
    public void setRegion(Region region)
    {
        this.regionName = region.getRegionName();
        this.inRegion = true;
    }

    @Override
    public UUID getPlayerUUID() {
        return playerID;
    }

    @Override
    public void kick(String reason)
    {
        if(GmPvPFactory.isOnlinePlayer(playerID))
        {
            Bukkit.getServer().getPlayer(playerID).kickPlayer(reason);
        }
    }

    @Override
    public void ban(String reason)
    {
        if(GmPvPFactory.isOnlinePlayer(playerID))
        {
            Bukkit.getServer().getPlayer(playerID).setBanned(true);
        }
        else if(GmPvPFactory.isOfflinePlayer(playerID))
        {
            Bukkit.getServer().getOfflinePlayer(playerID).setBanned(true);
        }
        else
        {
            try {
                throw new Exception("No player Found.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

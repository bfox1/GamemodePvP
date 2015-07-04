package ml.gamemodepvp.management;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.util.LobbyValidate;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 * <p>Loaded on startup to create a LobbyManager Instance.</p>
 */
public final class LobbyManager {

    private CoreMain main;

    private final int minLobbyCount = 1;
    private final int maxLobbyCount = 5;

    private final Map<String, Lobby> lobbyMap = new HashMap<String, Lobby>();

    public LobbyManager(CoreMain main)
    {
        this.main = main;
    }

    /**
     * Contains information for the existing Lobbies opened.
     */
    public Map<String, Lobby> getLobbyMapInfo()
    {
        return this.lobbyMap;
    }

    /**
     * Gets the Lobby information if Lobby is availiable
     * @param lobbyName The name of the Lobby requested.
     * @return The lobby requested
     * @throws Exception if lobby returns null, returns error message. Lobby CANNOT return NULL.
     */
    public Lobby getLobbyInfo(String lobbyName) throws IllegalArgumentException{
        if(this.lobbyMap.containsKey(lobbyName))
        {
            return this.lobbyMap.get(lobbyName);
        }
        else
        {
            throw new IllegalArgumentException("No such lobby Exists!" + lobbyName);
        }
    }

    /**
     * Closes a lobby
     * @param lobby the lobby instance
     */
    public void closeLobby(Lobby lobby)
    {
        CommandSender sender = Bukkit.getServer().getConsoleSender();
        sender.sendMessage("Lobby has been Closed.");
        this.lobbyMap.remove(lobby.getLobbyName());
    }

    /**
     * To addLobby to the LobbyMap.
     * @param lobby
     */
    public void addLobby(Lobby lobby)
    {
        if(this.lobbyMap.size() <=maxLobbyCount)
        {
            this.lobbyMap.put(lobby.getLobbyName(), lobby);
        }
    }

    /**
     * Get the Max amount of Lobbies.
     * @return maxLobbyCount
     */
    public int getMaxLobbyCount()
    {
        return maxLobbyCount;
    }

    public List<Lobby> getOpenLobbies()
    {
        List<Lobby> lobbyList = new ArrayList<Lobby>();
        for(Map.Entry entry : lobbyMap.entrySet())
        {
            if(entry != null)
                lobbyList.add((Lobby)entry.getValue());
        }
        return lobbyList;
    }


    /**
     * Close a lobby by Static reference.
     * @param lobby
     * @param mng
     */
    public static void closeLobby(Lobby lobby, LobbyManager mng)
    {
        mng.closeLobby(lobby);
    }

    /**
     * Checks to see if Player is within Lobby and returns LobbyValidate instance
     * @param player the player in which you want to check
     * @param lobbyManager the Lobby manager instance
     * @return LobbyValidate
     */
    public static LobbyValidate getPlayerValidation(Player player, LobbyManager lobbyManager)
    {

        LobbyValidate lobbyValidate = new LobbyValidate();

        for(Map.Entry en : lobbyManager.lobbyMap.entrySet())
        {
            if(en.getValue() != null)
            if(((Lobby)en.getValue()).isPlayerInLobby(player))
            {

                lobbyValidate.setLobby((Lobby)en.getValue());
                lobbyValidate.setPlayer(player);
                lobbyValidate.setInLobby(true);

                return lobbyValidate;
            }
        }
        return lobbyValidate;
    }


}

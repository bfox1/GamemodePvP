package ml.gamemodepvp.Modules.gamemodes;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.TestMode;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.tasks.gamemode.LobbyTask;
import ml.gamemodepvp.util.ModuleChat;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bfox1 on 6/11/2015.
 * In God We Trust.
 */
public class Lobby  {

    private boolean isActiveGame;

    private String lobbyName;
    private Gamemode gamemode;
    private CoreMain main;

    private HashMap<UUID, Player> playerMapData = new HashMap<UUID, Player>();

    private HashMap<UUID, Player> redTeam = new HashMap<UUID, Player>();

    private HashMap<UUID, Player> blueTeam = new HashMap<UUID, Player>();

    public Lobby(String lobbyName, Player player, CoreMain main)
    {
        this.lobbyName = lobbyName;
        this.playerMapData.put(player.getUniqueId(), player);
        this.main = main;
        this.isActiveGame = false;
    }

    /**
     * Checks if the Player is in the Lobby.
     * @param player The Player to be searched.
     * @return boolean
     */
    public boolean isPlayerInLobby(Player player)
    {
        return playerMapData.containsKey(player.getUniqueId());
    }

    /**
     * Returns the PlayerMapData.
     * @return Map.
     */
    @Deprecated
    public Map<UUID, Player> getPlayerMapData()
    {
        return this.playerMapData;
    }

    /**
     * Checks to see if the Lobby is empty.
     * @return boolean
     */
    public boolean isLobbyEmpty()
    {
      return this.playerMapData.isEmpty();
    }

    /**
     * Adds the Player to the Lobby. This method will create a new Lobby if the Lobby the player attempted
     * to join is not found. This Method will teleport player to location in which the Lobby has set spawn to be
     * If game is active, it will send player to Waiting Lobby. and When Lobby is in Game, Send player to
     * the ArenaMap Spawn.
     * @param player The player to join Lobby.
     */
    public void joinLobby(Player player)
    {
        if(gamemode.getModeProperties().getMaxPlayerCount() == playerMapData.size())
        {
            LobbyRegion rg = (LobbyRegion)main.getDataManager().getRegion(lobbyName);
            assert rg != null;
            int id = rg.getLOBBYID() + 1;
            try
            {
                Lobby lb = main.getLobbyManager().getLobbyInfo("Lobby." + id);
                lb.joinLobby(player);
            } catch (IllegalArgumentException e)
            {
                Lobby lobby = new Lobby("Lobby." + id, player, main);

                lobby.setGamemode(
                        new TestMode(
                                new SpawnLocations(player.getWorld()),
                                new ScoreManagement(new Scoreboard()),
                                Gamemode.ModeProperties.TESTLOBBY)
                );

                this.main.getLobbyManager().addLobby(lobby);

                BukkitTask task = new LobbyTask(lobby,
                        this.main).runTaskTimerAsynchronously(
                        this.main, 0, (lobby.getGamemode().getModeProperties().getTicksPerWaitingTime()));
            }
        }
        this.playerMapData.put(player.getUniqueId(), player);
        //TODO - Teleport Players to the Waiting Lobby for this Lobby or straight into the Match.
        if(!isActiveGame)
        {
            LobbyRegion region = (LobbyRegion)main.getDataManager().getRegion(lobbyName);
            player.teleport(region.getHandler().getPosition1());
        }
        else
        {

        }
        /**
         * This will most likely require an additional parameters.
         * Prefer to Assign the Lobby Locations and Teleport locations to SpawnLocations.
         */
        //TODO -
        player.sendMessage(ModuleChat.gamemodePrefixToPlayer("You have been added to the Lobby!!"));
    }

    /**
     * Will pull the player out of the Lobby, and teleport him back to spawn.
     * @param player The player.
     */
    public void leaveLobby(Player player)
    {
        this.playerMapData.remove(player.getUniqueId());
        //TODO - Teleport Players back to Waiting Lobby.
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        player.sendMessage(ModuleChat.gamemodePrefixToPlayer("You have left the Lobby"));
    }


    /**
     * Returns the LobbyName
     * @return The Lobby Name.
     */
    public String getLobbyName()
    {
        return this.lobbyName;
    }

    /**
     * Returns the Gamemode of the Lobby.
     * @return The Gamemode.
     */
    public Gamemode getGamemode()
    {
        return this.gamemode;
    }

    /**
     * Returns true if Game is active, and false if it isnt.
     * @return boolean
     */
    public boolean isActiveGame() {
        return isActiveGame;
    }

    /**
     * Sets the Active Game
     * @param isActiveGame boolean
     */
    public void setActiveGame(boolean isActiveGame) {
        this.isActiveGame = isActiveGame;
    }

    /**
     * Sets the Gamemode. This is most valuable for player choice to decide what Gamemode They wish to
     * Play on which Map.
     * @param gamemode The Gamemode.
     */
    public void setGamemode(Gamemode gamemode) {
        this.gamemode = gamemode;
    }

    /**
     * Checks to see if the Current Gamemode and Map have the met Player Requirements to Play the Match.
     * @return boolean
     */
    public boolean requirementsMet()
    {
        return gamemode.getModeProperties().isTeam() || playerMapData.size() >= gamemode.getModeProperties().getMinPlayerCount();

    }

    /**
     * For Team matches only, will check to see if the Player Count on both teams are Even.
     * @return boolean
     */
    private boolean isBalanced()
    {
        int minPlayers = gamemode.getModeProperties().getMinPlayerCount();
        int teamSize = redTeam.size() + blueTeam.size();


        return teamSize >= minPlayers || teamSize >= minPlayers - 1;

    }
}

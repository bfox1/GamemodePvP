package ml.gamemodepvp.Modules.gamemodes;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.TestMode;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.tasks.gamemode.LobbyTask;
import ml.gamemodepvp.util.ChatUtility;
import ml.gamemodepvp.util.GamemodePvPMessageUtility;
import ml.gamemodepvp.util.ModuleChat;
import net.minecraft.server.v1_8_R2.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.math.MathContext;
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

    public Lobby(String lobbyName, Player player, CoreMain main)
    {
        this.lobbyName = lobbyName;
        this.playerMapData.put(player.getUniqueId(), player);
        this.main = main;
        this.isActiveGame = false;
    }

    public boolean isPlayerInLobby(Player player)
    {
        return playerMapData.containsKey(player.getUniqueId());
    }

    public Map<UUID, Player> getPlayerMapData()
    {
        return this.playerMapData;
    }



    public boolean isLobbyEmpty()
    {
      return this.playerMapData.isEmpty();
    }

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
                        new TestMode(new SpawnLocations(player.getWorld()),
                                new ScoreManagement(new Scoreboard()),
                                Gamemode.ModeProperties.TESTLOBBY));

                this.main.getLobbyManager().addLobby(lobby);

                BukkitTask task = new LobbyTask(lobby,
                        this.main).runTaskTimerAsynchronously(this.main, 0, (lobby.getGamemode().getModeProperties().getTicksPerWaitingTime()));
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

    public void leaveLobby(Player player)
    {
        this.playerMapData.remove(player.getUniqueId());
        //TODO - Teleport Players back to Waiting Lobby.
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        player.sendMessage(ModuleChat.gamemodePrefixToPlayer("You have left the Lobby"));
    }

    public void setLobbyName(String name)
    {
        this.lobbyName = name;
    }

    public String getLobbyName()
    {
        return this.lobbyName;
    }

    public Gamemode getGamemode()
    {
        return this.gamemode;
    }


    public boolean isActiveGame() {
        return isActiveGame;
    }

    public void setActiveGame(boolean isActiveGame) {
        this.isActiveGame = isActiveGame;
    }

    public void setGamemode(Gamemode gamemode) {
        this.gamemode = gamemode;
    }
}

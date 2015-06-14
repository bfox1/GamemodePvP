package ml.gamemodepvp.Modules.gamemodes.modes.executor;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.LobbyTask;
import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.FreeForAll;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.LobbyValidate;
import ml.gamemodepvp.util.ModuleChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by bfox1 on 6/8/2015.
 * In God We Trust.
 */
public class FFACommandExecutor implements CommandExecutor {

    private CoreMain main;

    public FFACommandExecutor(CoreMain main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings)
    {

        Player player = (Player)sender;
        if(command.getName().equalsIgnoreCase("setLocation") && sender instanceof Player && player.isOp())
        {
            SpawnLocations loc = new SpawnLocations(player.getWorld());
            loc.loadLocations();
            loc.setLocations(player.getLocation());
            ModuleChat.gamemodePrefixToPlayer("You have added Spawn location!");
            loc.saveLocations();
            return true;
        }

        if(command.getName().equalsIgnoreCase("joinLobby") && strings.length == 1)
        {
            try {
                Lobby lobby = this.main.getLobbyManager().getLobbyInfo(strings[0]);
                lobby.joinLobby(player);

            } catch (Exception e) {

                System.out.println("Generating Now");
                Lobby lobby = new Lobby(strings[0], player, this.main);
                BukkitTask task = new LobbyTask(lobby, this.main).runTaskTimer(this.main, 0, 50L);
                this.main.getLobbyManager().addLobby(lobby);
                lobby.joinLobby(player);
            }
            return true;
        }

        if(command.getName().equalsIgnoreCase("leaveLobby"))
        {
            LobbyValidate validation = LobbyManager.getPlayerValidation(player, this.main.getLobbyManager());
            if(!validation.isInLobby())
            {
                ModuleChat.gamemodePrefixToPlayer("You silly! Your not in a Lobby!");
            }
            try
            {
                Lobby lobby = this.main.getLobbyManager().getLobbyInfo(validation.getLobby().getLobbyName());
                lobby.leaveLobby(player);
            } catch (Exception e) {

            }
            return true;
        }

        DebugCore.returnDebugMessage(command.getName() + " " + s);
        if(command.getName().equalsIgnoreCase("startGame"))
        {

            return true;
        }
        return false;
    }
}

package ml.gamemodepvp.Modules.gamemodes.modes.executor;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.bukkit.events.LobbyJoinEvent;
import ml.gamemodepvp.bukkit.events.LobbyLeaveEvent;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.LobbyValidate;
import ml.gamemodepvp.util.ModuleChat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        if(command.getName().equalsIgnoreCase("setLocation") && sender instanceof Player )
        {

            SpawnLocations loc = new SpawnLocations(player.getWorld());
            loc.loadLocations();
            loc.setLocations(player.getLocation());
            ModuleChat.gamemodePrefixToPlayer("You have added Spawn location!");
            loc.saveLocations();
            return true;
        }

        if(command.getName().equalsIgnoreCase("joinLobby"))
        {

            try {


                String name = LobbyRegion.findLobbyRegion(this.main.getDataManager()).getRegionName();
                Bukkit.getServer().getPluginManager().callEvent(new LobbyJoinEvent(player, name, main));
            } catch (NullPointerException e)
            {
                e.getMessage();
            }





            return true;
        }

        if(command.getName().equalsIgnoreCase("leaveLobby"))
        {
            LobbyValidate validation = LobbyManager.getPlayerValidation(player, this.main.getLobbyManager());
            if(!validation.isInLobby())
            {
                ModuleChat.gamemodePrefixToPlayer("You silly! Your not in a Lobby!");
                return false;
            }
                Bukkit.getServer().getPluginManager().callEvent(new LobbyLeaveEvent(player, validation.getLobby().getLobbyName(), main));

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

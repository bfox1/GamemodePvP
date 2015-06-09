package ml.gamemodepvp.Modules.gamemodes.modes.executor;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.SpawnLocations;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.FreeForAll;
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
        if(command.getName().equalsIgnoreCase("setLocations") && sender instanceof Player && player.isOp())
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

        }

        if(command.getName().equalsIgnoreCase("startGame"))
        {
            BukkitTask task = new GamemodeTask(new FreeForAll(player.getWorld())).runTask(main);
            return true;
        }
        return false;
    }
}

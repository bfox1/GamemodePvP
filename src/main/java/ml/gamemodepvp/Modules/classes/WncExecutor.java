package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.handler.KitGuiHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WncExecutor implements CommandExecutor {
    private  CoreMain plugin;

    public WncExecutor(CoreMain plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {

        if(command.getName().equalsIgnoreCase("wnc"))
        {
            return true;
        }
            if (s.equalsIgnoreCase("class") && isPlayer(commandSender)) {
                Player p = (Player) commandSender;

                KitGuiHandler kit = new KitGuiHandler();
                kit.createGUI();
                kit.openGUI(p);
                return true;
           }


        if(command.getName().equalsIgnoreCase("classMenu") && isPlayer(commandSender))
        {

           //this.plugin.menuInventory.kitGui().openGui((Player)commandSender);
            return true;
        }


        return false;

    }


    private boolean isPlayer(CommandSender sender)
    {
        return sender instanceof Player;
    }
}

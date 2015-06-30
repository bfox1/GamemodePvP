package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;

import ml.gamemodepvp.Modules.classes.gui.InventoryGui;
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


                return true;
           }


        if(command.getName().equalsIgnoreCase("classMenu") && isPlayer(commandSender))
        {

           InventoryGui gui = new InventoryGui(this.plugin.getMenu(), (Player)commandSender);
            gui.openGui();
            return true;
        }


        return false;

    }


    private boolean isPlayer(CommandSender sender)
    {
        return sender instanceof Player;
    }
}

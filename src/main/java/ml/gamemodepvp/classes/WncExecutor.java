package ml.gamemodepvp.classes;


import ml.gamemodepvp.classes.handler.KitGuiHandler;
import ml.gamemodepvp.core.CoreMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WncExecutor implements CommandExecutor {
    private final CoreMain plugin;
    private KitGuiHandler handler;

    public WncExecutor(CoreMain plugin, KitGuiHandler handler)
    {
        this.plugin = plugin;
        this.handler = handler;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {

        if(command.getName().equalsIgnoreCase("wnc"))
        {

        }
        if(commandSender instanceof Player) {
            if (s.equalsIgnoreCase("class")) {
                Player p = (Player) commandSender;

                KitGuiHandler kit = new KitGuiHandler();
                kit.createGUI();
                kit.openGUI(p);
            }
            return true;
        }else
        return false;
    }
}

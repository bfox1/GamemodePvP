package ml.gamemodepvp.ranks;


import ml.gamemodepvp.core.CoreMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class RankExecutor implements CommandExecutor {

    private CoreMain rankCore;
    public RankExecutor(CoreMain core)
    {
        this.rankCore = core;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {



        return false;
    }

}

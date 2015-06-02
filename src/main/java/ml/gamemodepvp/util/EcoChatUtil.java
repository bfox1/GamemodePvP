package ml.gamemodepvp.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
/**
 * Created by bfox1 on 4/29/2015.
 */
public class EcoChatUtil {

    private static String setup = "[" + DARK_GREEN + "Economy" + WHITE + "]" + " - " + WHITE;



    public static String returnMoney(int amount)
    {
        String name = setup + "You have " + DARK_GREEN + "$" + amount + WHITE + " dollars.";
        return name;
    }

    public static String returnElseMoney(int amount)
    {
        String name = setup + "They have " + DARK_GREEN + "$" + amount + WHITE + " dollars.";
        return name;
    }

    public static String addedMoney(int amount)
    {
        String name = setup + DARK_GREEN + "$" + amount + WHITE + " Has been added to your account!";
        return name;
    }

    public static String removeMoney(int amount)
    {
        String name = setup + DARK_GREEN + "$" + amount + WHITE + " Has been removed from your account!";
        return name;
    }

    public static String returnError(Player player)
    {
        String name = setup + GOLD + player.getDisplayName() + WHITE +" does not exist or have account!";
        return name;
    }

    public static String returnError(String string)
    {
        String name = setup + GOLD + string + DARK_GREEN +" does not exist or have account!";
        return name;
    }

    public static String returnError(OfflinePlayer player)
    {
        String name = setup + GOLD + player.getName() + DARK_GREEN +" does not exist or have account!";

        return name;
    }


    public static String returnFatalError(Player player)
    {
        String name = setup + RED + "" + BOLD + "ERROR: Please ensure you did /ecomoney pay <player> <amount> was done correctly!";
        return name;
    }
}

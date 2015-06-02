package ml.gamemodepvp.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

/**
 * Created by bfox1 on 5/3/2015.
 */
public class EcoUtil {


    public static OfflinePlayer returnOfflinePlayer(String name)
    {
        OfflinePlayer[] player = Bukkit.getOfflinePlayers();
        OfflinePlayer mPlayer = null;
        for(OfflinePlayer playyers : player)
        {
            System.out.println(playyers.getName());
            if(playyers.getName().equalsIgnoreCase(name)) {
                mPlayer = playyers;
                break;
            }
        }
        return mPlayer;
    }
}

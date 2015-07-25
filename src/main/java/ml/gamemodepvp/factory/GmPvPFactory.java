package ml.gamemodepvp.factory;

import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.bukkit.action.ItemAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.net.IDN;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Created by bfox1 on 7/4/2015.
 * In God We Trust.
 */
public class GmPvPFactory
{

    /**
     * Returns an Instance of a DisplayStack.
     * <p>material</p>
     * @param material the Material Type.
     * @param displayName The Display Name.
     * @param lore The Lore.
     * @param action The ItemAction
     * @param actionParams The Action Params.
     * @return A new DisplayStack.
     */
    public static DisplayStack getInstance(Material material, String displayName, List<String> lore,
                                           ItemAction action, Object actionParams)
    {
        return new DisplayStack(material, displayName, lore, action, actionParams);
    }

    public static boolean isOfflinePlayer(UUID id)
    {
        return Bukkit.getServer().getOfflinePlayer(id) != null;
    }
    public static boolean isOnlinePlayer(UUID id)
    {
        return Bukkit.getServer().getPlayer(id) != null;
    }



}

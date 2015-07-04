package ml.gamemodepvp.factory;

import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.bukkit.action.ItemAction;
import org.bukkit.Material;

import java.util.Collection;
import java.util.List;

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



}

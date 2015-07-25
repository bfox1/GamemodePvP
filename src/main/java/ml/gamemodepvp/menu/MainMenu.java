package ml.gamemodepvp.menu;

import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.bukkit.action.ItemAction;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 6/29/2015.
 * In God We Trust.
 */
public class MainMenu extends GmpvpInventory {





    public MainMenu(CoreMain main)
    {
        super("Main Menu", 9, main);
        setInventforySlot(buildItemIcon(new ItemStack(Material.GOLD_RECORD),ItemAction.INVENTORY,
                new LobbyGuiGmpvp("Lobby Menu", 9,main),
                ChatColor.DARK_RED + "Lobby Selection", "To find a lobby to join.", "Just testing"));
        GmpvpInventory con = new GmpvpInventory("Class", 9, main);
        con.setKitInventory(con.buildDefaultInventory());

        setInventforySlot(buildItemIcon(new ItemStack(Material.DIAMOND_SWORD), ItemAction.INVENTORY,
                con,
                ChatColor.DARK_AQUA + "Classes",  "To select a class to use."));
    }





    private void setInventforySlot(DisplayStack builder)
    {
        super.getDisplayStackBuilder().add(builder);

        super.setInventorySlot(builder);
    }


}

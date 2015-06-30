package ml.gamemodepvp.menu;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.InventoryConstructor;
import ml.gamemodepvp.Modules.classes.classadditives.DisplayStackBuilder;
import ml.gamemodepvp.Modules.classes.event.ItemAction;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.Main;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bfox1 on 6/29/2015.
 * In God We Trust.
 */
public class MainMenu extends InventoryConstructor {

    private CoreMain main;
    private List<DisplayStackBuilder> displayStackBuilderList;



    public MainMenu(CoreMain main)
    {
        super("Main Menu", 9);
        this.main = main;
        this.displayStackBuilderList = new ArrayList<DisplayStackBuilder>();
        setInventforySlot(buildItemIcon(new ItemStack(Material.GOLD_RECORD),ItemAction.INVENTORY,
                new LobbyGuiConstructor("Lobby Menu", 9,main),
                ChatColor.DARK_RED + "Lobby Selection", "To find a lobby to join.", "Just testing"));
        InventoryConstructor con = new InventoryConstructor("Class", 9);
        con.buildDefaultInventory();
        setInventforySlot(buildItemIcon(new ItemStack(Material.DIAMOND_SWORD), ItemAction.INVENTORY,
                con,
                ChatColor.DARK_AQUA + "Classes",  "To select a class to use."));
    }





    private void setInventforySlot(DisplayStackBuilder builder)
    {
        displayStackBuilderList.add(builder);

        super.setInventorySlot(builder);
    }

    public List<DisplayStackBuilder> getDisplayStackBuilder()
    {
        return displayStackBuilderList;
    }

    public DisplayStackBuilder getDisplayStackBuilderByItemStack(ItemStack stack)
    {
        for (DisplayStackBuilder aDisplayStackBuilderList : displayStackBuilderList)
        {
            if (aDisplayStackBuilderList.getDisplayStack().equals(stack))
            {
                return aDisplayStackBuilderList;
            }
        }
        return null;
    }

    public InventoryConstructor getInventoryConstructorByString(String name)
    {
        for(DisplayStackBuilder builder : displayStackBuilderList)
        {
            if(builder.getAction() == ItemAction.INVENTORY && builder.getActionParams() instanceof InventoryConstructor)
            {
                InventoryConstructor constructor = (InventoryConstructor)builder.getActionParams();
                if(constructor.getInventory().getName().equals(name))
                {
                    return constructor;
                }
            }
        }
        return null;
    }


}

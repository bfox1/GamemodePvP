package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.handler.ItemProvider;
import ml.gamemodepvp.Modules.classes.handler.KitGuiHandler;
import ml.gamemodepvp.Modules.classes.kit.DisplayItemAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WncListener implements Listener {

    private CoreMain main;

    public WncListener(CoreMain main)
    {
        this.main = main;
    }

    @EventHandler
    public void checkSlot(InventoryClickEvent e)
    {
    }

    @EventHandler
    public void menuSlot(InventoryClickEvent e)
    {

        if(e.getInventory() == this.main.menuInventory)
        {
            DisplayItemAction.test2(DisplayItemAction.Action.ACTIVE()).;
        }
    }


}

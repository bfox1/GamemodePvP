package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;

import ml.gamemodepvp.Modules.classes.gui.KitGui;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.ObjectOutput;

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
        KitGui gui = this.main.menuInventory.kitGui();

        if(e.getInventory() == this.main.menuInventory)
        {
            for(int i = 0; i < gui.displayStackList().size(); i++)
            {
                if(e.getCurrentItem() == gui.displayStackList().get(i) && e.getClick().isLeftClick());
                {
                    gui.displayStackList().get(i).fireAction();
                }
            }
        }
    }


}

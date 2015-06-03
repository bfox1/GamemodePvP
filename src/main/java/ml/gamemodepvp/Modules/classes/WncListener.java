package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;

import ml.gamemodepvp.Modules.classes.gui.KitGui;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.entity.Player;
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
        gui.kitPlayer_$eq((Player)e.getWhoClicked());
        if(e.getInventory().getName() == gui.customName())
        {
            for(int i = 0; i < gui.displayStackList().size(); i++)
            {
                DebugCore.returnDebugMessage(e.getClick().isLeftClick());
                if(e.getCurrentItem().equals(gui.displayStackList().get(i).getItemStack()) && e.getClick().isLeftClick())
                {
                    gui.closeGui((Player)e.getWhoClicked());
                    gui.fireItemAction(gui.displayStackList().get(i));
                    e.setCancelled(true);
                }
            }
        }
    }


}

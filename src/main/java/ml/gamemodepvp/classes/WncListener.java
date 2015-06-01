package ml.gamemodepvp.classes;


import ml.gamemodepvp.classes.handler.ItemProvider;
import ml.gamemodepvp.classes.handler.KitGuiHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WncListener implements Listener {

    public KitGuiHandler kit;

    public WncListener(KitGuiHandler handler)
    {
        this.kit = handler;
    }

    @EventHandler
    public void checkSlot(InventoryClickEvent e)
    {
        e.getInventory();
         this.kit = new KitGuiHandler();
         this.kit.createGUI();
        ItemProvider provider = new ItemProvider(this.kit,e);
        provider.provideSetup();
    }

    @EventHandler
    public void menuSlot(InventoryClickEvent e)
    {

        //if(e.getInventory().conta)
    }


}

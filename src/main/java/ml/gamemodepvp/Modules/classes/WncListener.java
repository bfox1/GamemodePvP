package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.bukkit.CoreMain;


import ml.gamemodepvp.bukkit.action.ItemAction;
import ml.gamemodepvp.Modules.classes.kit.Kit;
import ml.gamemodepvp.menu.LobbyGuiGmpvp;
import ml.gamemodepvp.menu.MainMenu;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by bfox1 on 4/24/2015.
 * In God We Trust.
 */
public class WncListener implements Listener {

    private CoreMain main;

    public WncListener(CoreMain main)
    {
        this.main = main;
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryAction(InventoryClickEvent event)
    {

        /**
         * Manages MainMenu Event
         */

        if(event.getClickedInventory() != null && event.getCurrentItem() != null)
        {
            GmpvpInventory inventory = main.getGmpvpInventoryByName(event.getClickedInventory().getName());
            if(inventory != null && event.isLeftClick())
                if(inventory.getInventory().contains(event.getCurrentItem()))
                {
                    DebugCore.returnDebugMessage(inventory.getInventory().getName() + " : " + event.getClickedInventory().getName());
                    DisplayStack stack = inventory.getDisplayStackBuilderByItemStack(event.getCurrentItem());
                    DebugCore.returnDebugMessage(stack);
                    if(stack != null)
                    {
                        stack.fireAction((Player)event.getWhoClicked());
                        event.setCancelled(true);
                    }

                }
        }
    }


}

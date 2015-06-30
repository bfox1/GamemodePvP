package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;


import ml.gamemodepvp.Modules.classes.event.ItemAction;
import ml.gamemodepvp.Modules.classes.kit.KitBuilder;
import ml.gamemodepvp.menu.MainMenu;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

    @EventHandler
    public void checkSlot(InventoryClickEvent e)
    {
    }


    /**
     * This Listener is for Kit Actions
     * @param e
     */
    @EventHandler
    public void onKitAction(InventoryClickEvent e)
    {
       InventoryConstructor constructor = main.getMenu();



        if(e.getClickedInventory() != null && constructor.getKitInventory() != null)
        {
            if (e.getClickedInventory().getName().equals(constructor.getInventory().getName()))
            {
                if (e.isLeftClick() && e.getCurrentItem() != null)
                {
                    for (KitBuilder kits : constructor.getKitInventory()) {

                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(kits.getStack().getDisplayStack().getItemMeta().getDisplayName()))
                        {
                            ItemAction.KIT.fireAction(kits, (Player) e.getWhoClicked());
                        }

                    }
                    e.setCancelled(true);
                }
            }
        }


    }

    @EventHandler
    public void onInventoryAction(InventoryClickEvent event)
    {

        MainMenu inventoryConstructor = main.getMenu();

        /**
         * Manages MainMenu Event
         */
        if(event.getClickedInventory() != null)
        if(event.getClickedInventory().getName().equals(inventoryConstructor.getInventory().getName()))
        {
            if(event.isLeftClick() && event.getCurrentItem() != null)
            {
                for(int i = 0; i < inventoryConstructor.getInventory().getSize(); i++)
                {
                    String displayItem = " ";
                    String currentItem = ".";
                    if(inventoryConstructor.getInventory().getItem(i) != null)
                    {
                         displayItem = inventoryConstructor.getInventory().getItem(i).getItemMeta().getDisplayName();
                         currentItem = event.getCurrentItem().getItemMeta().getDisplayName();
                    }

                    if(currentItem.equals(displayItem))
                    {


                        inventoryConstructor.getDisplayStackBuilderByItemStack(
                                event.getCurrentItem()).fireAction((Player)event.getWhoClicked());
                    }
                }
            }
            event.setCancelled(true);
        }
        else if(inventoryConstructor.getInventoryConstructorByString(event.getClickedInventory().getName()) != null)
        {
            //TODO
        }
    }


}

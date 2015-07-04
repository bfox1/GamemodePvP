package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.bukkit.CoreMain;


import ml.gamemodepvp.bukkit.action.ItemAction;
import ml.gamemodepvp.Modules.classes.kit.Kit;
import ml.gamemodepvp.menu.LobbyGuiGmpvp;
import ml.gamemodepvp.menu.MainMenu;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
       GmpvpInventory constructor = main.getMenu();



        if(e.getClickedInventory() != null && constructor.getKitInventory() != null)
        {
            if (e.getClickedInventory().getName().equals(constructor.getInventory().getName()))
            {
                if (e.isLeftClick() && e.getCurrentItem() != null)
                {
                    for (Kit kits : constructor.getKitInventory()) {

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

        if(event.getClickedInventory() != null && event.getCurrentItem() != null)
        {
            GmpvpInventory inventory = main.getGmpvpInventoryByName(event.getClickedInventory().getName());
            if(inventory != null && event.isLeftClick())
                if(inventory.getInventory().contains(event.getCurrentItem()))
                {
                   // DebugCore.returnDebugMessage(inventory.getInventory().getName() + " : " + event.getClickedInventory().getName());
                    DisplayStack stack = inventory.getDisplayStackBuilderByItemStack(event.getCurrentItem());
                    if(stack != null)
                    {
                        stack.fireAction((Player)event.getWhoClicked());
                        event.setCancelled(true);
                    }

                }
        }
        /*
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
        /**
         * Manages Internal Guis from MainMenu
         *
        else if(inventoryConstructor.getInventoryConstructorByString(event.getClickedInventory().getName()) != null)
        {
            GmpvpInventory constructor = inventoryConstructor.getInventoryConstructorByString(event.getClickedInventory().getName());
            if(constructor instanceof LobbyGuiGmpvp)
                ((LobbyGuiGmpvp) constructor).getDiplayFromItemStack(event.getCurrentItem()).fireAction((Player) event.getWhoClicked());
            event.setCancelled(true);
        }*/


    }


}

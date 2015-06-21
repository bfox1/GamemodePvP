package ml.gamemodepvp.Modules.classes;


import ml.gamemodepvp.CoreMain;


import ml.gamemodepvp.Modules.classes.event.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
    public void onInventoryAction(InventoryClickEvent e)
    {
       // InventoryConstructor gui = this.main.menuInventory.kitGui();

        boolean clickedType = false;
       // if(e.getInventory().getName() == gui.customName())
        {
        //    for(int i = 0; i < gui.displayStackList().size(); i++)
            {
          //      stack = gui.displayStackList().get(i);

             //   if(clickedStack.equals(stack) && e.getClick().isLeftClick() && stack.getAction().equals(ItemAction.INVENTORY))
                {
                   // gui.closeGui((Player)e.getWhoClicked());
                  //  innerInventoryGui = (InventoryConstructor)stack.getActionPerameters();
                   // innerInventoryGui.kitPlayer_$eq((Player)e.getWhoClicked());
                  //  stack.setActionPerameters(innerInventoryGui);
                  //  gui.fireItemAction(stack);
                  //  e.setCancelled(true);
                  //  break;
                }
            }
        }
    }


}

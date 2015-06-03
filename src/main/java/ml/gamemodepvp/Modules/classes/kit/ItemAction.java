package ml.gamemodepvp.Modules.classes.kit;

import ml.gamemodepvp.Modules.classes.gui.KitGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 6/2/2015.
 * In God We Trust.
 */
public enum ItemAction
{
    COMMAND
            {
                /**
                 * Dispatches the Command when Item is LeftClicked
                 * @param object ex(gamemode bfox1 1)
                 */
                @Override
                public void fireAction(Object object)
                {
                    if(object instanceof String)
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), (String)object);
                    else
                    {
                        new Exception("Object wasnt instance of String!");
                    }
                }
            },
    KIT
            {
                @Override
                public void fireAction(Object object)
                {
                    if(object instanceof KitGui)
                    {
                        KitGui gui = (KitGui)object;
                        Player player = gui.kitPlayer();

                        for(int i = 0; i < gui.inventoryChest().getSize(); i++)
                        {
                            player.getInventory().addItem(gui.inventoryChest().getItem(i));
                        }
                       // player.getInventory().addItem();
                    }
                    else
                    {
                        new Exception("Object wasnt instance of Player!");
                    }

                }

            },
    INVENTORY
            {
                 @Override
                 public void fireAction(Object obj)
                 {
                    if(obj instanceof KitGui)
                    {
                        KitGui gui = (KitGui)obj;
                        gui.inventoryChest().addItem(new ItemStack(Material.STAINED_CLAY));
                        gui.openGui(gui.kitPlayer());
                    }
                 }
            },
    ITEM
            {
                @Override
                public void fireAction(Object obj)
                {

                }
            },
    ACTIVE
            {
                @Override
                public void fireAction(Object obj)
                {

                }
            };


    public abstract void fireAction(Object obj);
}

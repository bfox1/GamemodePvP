package ml.gamemodepvp.Modules.classes.event;


import ml.gamemodepvp.Modules.classes.InventoryConstructor;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponBuilder;
import ml.gamemodepvp.Modules.classes.gui.InventoryGui;
import ml.gamemodepvp.Modules.classes.kit.KitBuilder;
import ml.gamemodepvp.menu.LobbyGuiConstructor;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
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
                public void fireAction(Object object, Player player)
                {
                    if(object instanceof String)
                    Bukkit.getServer().dispatchCommand(player, (String)object);
                    else
                    {
                        new Exception("Object wasnt instance of String!");
                    }
                }
            },
    KIT
            {
                @Override
                public void fireAction(Object object, Player player)
                {
                    if(object instanceof KitBuilder)
                    {
                        KitBuilder builder = (KitBuilder)object;
                        player.getInventory().clear();
                        for(WeaponBuilder weaponBuilder : builder.getKitWeaponList())
                        {
                            player.getInventory().addItem(weaponBuilder.getItemStack());
                        }
                        for(ItemStack stack : builder.getOtherAdditives())
                        {
                            player.getInventory().addItem(stack);
                        }
                        player.closeInventory();
                    }

                }

            },
    /**
     * Creates a new InventoryConstructor object and launches it.
     */
    INVENTORY
            {
                 @Override
                 public void fireAction(Object obj,Player player)
                 {
                    if(obj instanceof InventoryConstructor)
                    {
                        if(obj instanceof LobbyGuiConstructor)
                        {
                            ((LobbyGuiConstructor)obj).runTask();
                            DebugCore.returnDebugMessage("YEST");
                        }
                        DebugCore.returnDebugMessage("YUP");
                        InventoryGui gui = new InventoryGui((InventoryConstructor)obj, player);

                        gui.openGui();
                    }
                 }
            },
    ITEM
            {
                @Override
                public void fireAction(Object obj,Player player)
                {

                }
            },
    ACTIVE
            {
                @Override
                public void fireAction(Object obj,Player player)
                {

                }
            };


    public abstract void fireAction(Object obj, Player player);
}

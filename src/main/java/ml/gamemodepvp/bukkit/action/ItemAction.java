package ml.gamemodepvp.bukkit.action;


import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.Modules.classes.classadditives.Weapon;
import ml.gamemodepvp.Modules.classes.kit.Kit;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.menu.LobbyGuiGmpvp;
import ml.gamemodepvp.util.LobbyValidate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

                    if(object instanceof Kit)
                    {
                        Kit builder = (Kit)object;
                        player.getInventory().clear();
                        for(Weapon weapon : builder.getKitWeaponList())
                        {
                            player.getInventory().addItem(weapon.getItemStack());
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
     * Creates a new GmpvpInventory object and launches it.
     */
    INVENTORY
            {
                 @Override
                 public void fireAction(Object obj,Player player)
                 {
                    if(obj instanceof GmpvpInventory)
                    {
                        GmpvpInventory inventory = (GmpvpInventory)obj;
                        if(inventory instanceof LobbyGuiGmpvp)
                        {
                            ((LobbyGuiGmpvp)inventory).runTask();
                        }
                        inventory.getMain().addToInventoryGuis(inventory);
                        inventory.openGui(player);

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
            },
    TELEPORT{
                @Override
                public void fireAction(Object obj, Player player)
                {

                }
            };


    public abstract void fireAction(Object obj, Player player);
}

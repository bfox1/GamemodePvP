package ml.gamemodepvp.Modules.classes.handler;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 4/26/2015.
 */
public class ItemProvider {

    public KitGuiHandler kit;
    public Player player;
    public Inventory inventory;
    private InventoryClickEvent e;
    private ItemStack stack;


    public ItemProvider(KitGuiHandler kit , InventoryClickEvent event )
    {
        this.kit = kit;
        this.player = (Player) event.getWhoClicked();
        this.inventory = event.getInventory();
        this.e = event;
        this.stack = event.getCurrentItem();
    }


    public void provideSetup()
    {
       /* Player player = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        Inventory inventory = e.getInventory();
        kit = new KitGuiHandler();
        kit.createGUI();*/

        if(inventory.getName().equals(kit.getvChest().getName()))
        {
            for (int i = 0; i < kit.getvChest().getContents().length; i++)
            {

                String displayItem;
                System.out.println(this.kit.getvChest().getItem(i) + " : " + this.getClass().toString());
                String clickedItem = stack.getItemMeta().getDisplayName();

                if(this.kit.getvChest().getItem(i) != null) {
                    displayItem = kit.getvChest().getItem(i).getItemMeta().getDisplayName();
                    System.out.println("+++" + clickedItem + displayItem + "+++");
                }

                if (this.kit.getvChest().getItem(i) != null && stack.getItemMeta().getDisplayName().equals(kit.getvChest().getItem(i).getItemMeta().getDisplayName())) {
                    e.setCancelled(true);
                    if (e.isLeftClick()) {
                        player.closeInventory();
                        String name = this.kit.getvChest().getItem(i).getItemMeta().getDisplayName();
                        System.out.println(name.substring(2) + "Next to Kit");
                       // this.kit.setDefaultAssets(player, name.substring(2));
                        //this.kit.setDefaultAssets(player, "Tank-t1");
                        player.sendMessage(ChatColor.DARK_RED + "[GameModePVP] - Classes: " + ChatColor.DARK_GREEN + "You were given the" +
                                this.kit.getvChest().getItem(i).getItemMeta().getDisplayName() + "Class");
                        this.player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 1);

                    }
                }
            }
        }

    }

}

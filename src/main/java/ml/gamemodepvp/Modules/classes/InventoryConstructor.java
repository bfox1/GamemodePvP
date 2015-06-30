package ml.gamemodepvp.Modules.classes;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.classadditives.DisplayStackBuilder;
import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponBuilder;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponEnchants;
import ml.gamemodepvp.Modules.classes.event.ItemAction;
import ml.gamemodepvp.Modules.classes.kit.KitBuilder;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import scala.io.BytePickle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bfox1 on 6/16/2015.
 * In God We Trust.
 *
 * Creator of all Virtual Inventory.
 */
public class InventoryConstructor {


    private Inventory inventory;

    private List<KitBuilder> kitInventory;


    public InventoryConstructor(String inventoryName, int inventoryAmount)
    {
        this.inventory = Bukkit.createInventory(null, inventoryAmount, inventoryName);
        this.kitInventory = new ArrayList<KitBuilder>();
    }


    public void setInventorySlot(DisplayStackBuilder stackBuilder)
    {
        DebugCore.returnDebugMessage(stackBuilder.getDisplayStack().getItemMeta().getDisplayName());
        this.inventory.addItem(stackBuilder.getDisplayStack());
    }

    /**
     * Adds KitBuilder Class to the List
     * @param builder KitBuilder
     */
    public void setKitInventory(KitBuilder builder)
    {
        setInventorySlot(builder.getStack());
        this.kitInventory.add(builder);
    }

    /**
     * Returns the KitBuilder Class if the the ItemStack equals the
     * Display Stack within the kitInventory List.
     * @param stack displayStack
     * @return KitBuilder
     */
    public KitBuilder getBuilderFromDisplayStack(ItemStack stack)
    {
        for(int i = 0; i < kitInventory.size(); i++)
        {
            if(kitInventory.get(i).getStack().getDisplayStack() == stack)
            {
                return this.kitInventory.get(i);
            }
        }
        return null;
    }


    /**
     * To Check if the inventory has ItemStack
     * @param stack ItemStack searching for
     * @return boolean
     */
    public boolean checkItemStack(ItemStack stack)
    {
        for(int i = 0;  i < this.inventory.getSize(); i++)
        {
            if(this.inventory.getItem(i) == stack)
            {
                return true;
            }
        }
        return false;
    }

    public DisplayStackBuilder buildItemIcon(ItemStack stack,ItemAction action, Object actionParams, String itemName,  String... loreListings)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(itemName);
        List<String> Lore = new ArrayList<String>();
        Collections.addAll(Lore, loreListings);
        meta.setLore(Lore);
        stack.setItemMeta(meta);
        return  new DisplayStackBuilder(stack,true, action, actionParams);

    }


    public KitBuilder buildDefaultInventory()
    {
        //InventoryConstructor ic = main.getDefaultKits();

        //Display Stacks

        return new KitBuilder(
                createDisplayStack(new ItemStack(Material.DIAMOND_SWORD), "Assault"),
                createWeaponBuilder(
                        new WeaponBuilder(new ItemStack(Material.WOOD_SWORD), "Assault", true),
                        new WeaponEnchants(Enchantment.DAMAGE_ALL, 1, true)
                ),
                createWeaponBuilder(
                        new WeaponBuilder(new ItemStack(Material.WOOD_AXE), "AxeSir", true),
                        new WeaponEnchants(null,0, false)
                ),
                createWeaponBuilder(
                        new WeaponBuilder(new ItemStack(Material.SNOW_BALL),"FlashBang", true),
                        new WeaponEnchants(null, 0, false)
                ),
                createWeaponBuilder(
                        new WeaponBuilder(new ItemStack(Material.SULPHUR), "Grenade", true),
                        new WeaponEnchants(null, 0, false)
                ),
                new Perks(PotionEffectType.SPEED, 1, 100),
                new ItemStack(Material.BREAD, 5),
                new ItemStack(Material.COOKED_BEEF, 2)
        );



    }


    private ItemStack createDisplayStack(ItemStack stack, String name)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
        return stack;
    }

    /**
     * Static reference for building weapon.
     * @param builder Set WeaponBuilder
     * @param weaponEnchantses WeaponEnchants
     * @return WeaponBuilder
     */
    private WeaponBuilder createWeaponBuilder(WeaponBuilder builder, WeaponEnchants... weaponEnchantses)
    {
        for (WeaponEnchants weaponEnchantse : weaponEnchantses) {
            if (weaponEnchantse != null) {
                builder.addWeaponEnchants(weaponEnchantse);
            }
        }
        return builder;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public List<KitBuilder> getKitInventory()
    {
        return kitInventory;
    }





}



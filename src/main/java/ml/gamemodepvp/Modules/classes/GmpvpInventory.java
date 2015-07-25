package ml.gamemodepvp.Modules.classes;

import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.Weapon;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponEnchants;
import ml.gamemodepvp.Modules.classes.gui.InventoryGui;
import ml.gamemodepvp.Modules.classes.kit.Kit;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.bukkit.action.ItemAction;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bfox1 on 6/16/2015.
 * In God We Trust.
 *
 * Creator of all Virtual Inventory.
 */
public class GmpvpInventory implements InventoryGui {


    private Inventory inventory;

    private List<Kit> kitInventory;

    private CoreMain main;

    private List<DisplayStack> displayStackList;


    public GmpvpInventory(String inventoryName, int inventoryAmount, CoreMain main)
    {
        this.inventory = Bukkit.createInventory(null, inventoryAmount, inventoryName);
        this.kitInventory = new ArrayList<Kit>();
        this.main = main;
        this.displayStackList = new ArrayList<DisplayStack>();
    }


    public void setInventorySlot(DisplayStack stackBuilder)
    {
        DebugCore.returnDebugMessage(stackBuilder.getDisplayStack().getItemMeta().getDisplayName());
        this.inventory.addItem(stackBuilder.getDisplayStack());
    }

    /**
     * Adds Kit Class to the List
     * @param builder Kit
     */
    public void setKitInventory(Kit builder)
    {
        setInventorySlot(builder.getStack());
        this.kitInventory.add(builder);
        this.displayStackList.add(builder.getStack());
    }

    /**
     * Returns the Kit Class if the the ItemStack equals the
     * Display Stack within the kitInventory List.
     * @param stack displayStack
     * @return Kit
     */
    public Kit getKitFromDisplayStack(ItemStack stack)
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
        return inventory.contains(stack);
    }

    public DisplayStack buildItemIcon(ItemStack stack,ItemAction action, Object actionParams, String itemName,  String... loreListings)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(itemName);
        List<String> Lore = new ArrayList<String>();
        Collections.addAll(Lore, loreListings);
        meta.setLore(Lore);
        stack.setItemMeta(meta);
        return  new DisplayStack(stack,true, action, actionParams);

    }


    public void buildDefaultInventory()
    {
        //GmpvpInventory ic = main.getDefaultKits();

        //Display Stacks

         setKitInventory(new Kit(
                createDisplayStack(new ItemStack(Material.DIAMOND_SWORD), "Assault"),
                createWeaponBuilder(
                        new Weapon(new ItemStack(Material.WOOD_SWORD), "Assault", true),
                        new WeaponEnchants(Enchantment.DAMAGE_ALL, 1, true)
                ),
                createWeaponBuilder(
                        new Weapon(new ItemStack(Material.WOOD_AXE), "AxeSir", true),
                        new WeaponEnchants(null,0, false)
                ),
                createWeaponBuilder(
                        new Weapon(new ItemStack(Material.SNOW_BALL),"FlashBang", true),
                        new WeaponEnchants(null, 0, false)
                ),
                createWeaponBuilder(
                        new Weapon(new ItemStack(Material.SULPHUR), "Grenade", true),
                        new WeaponEnchants(null, 0, false)
                ),
                new Perks(PotionEffectType.SPEED, 1, 100),
                new ItemStack(Material.BREAD, 5),
                new ItemStack(Material.COOKED_BEEF, 2)
        ));

        setKitInventory(new Kit(
                createDisplayStack(new ItemStack(Material.DIAMOND_SWORD))
        ));



    }

    public DisplayStack getDisplayStackBuilderByItemStack(ItemStack stack)
    {
        for (DisplayStack aDisplayStackList : displayStackList)
        {
            if (aDisplayStackList.getDisplayStack().equals(stack))
            {
                return aDisplayStackList;
            }
        }
        return null;
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
     * @param builder Set Weapon
     * @param weaponEnchantses WeaponEnchants
     * @return Weapon
     */
    private Weapon createWeaponBuilder(Weapon builder, WeaponEnchants... weaponEnchantses)
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

    public List<Kit> getKitInventory()
    {
        return kitInventory;
    }

    public List<DisplayStack> getDisplayStackBuilder()
    {
        return displayStackList;
    }


    public GmpvpInventory getInventoryConstructorByString(String name)
    {
        for(DisplayStack builder : displayStackList)
        {
            if(builder.getItemAction() == ItemAction.INVENTORY && builder.getActionParams() instanceof GmpvpInventory)
            {
                GmpvpInventory constructor = (GmpvpInventory)builder.getActionParams();
                if(constructor.getInventory().getName().equals(name))
                {
                    return constructor;
                }
            }
        }
        return null;
    }



    @Override
    public void openGui(Player player) {
        player.openInventory(inventory);
    }

    @Override
    public void closeGui(Player player)
    {
        player.closeInventory();
    }

    public CoreMain getMain() {
        return main;
    }
}



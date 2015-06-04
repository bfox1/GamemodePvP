package ml.gamemodepvp.Modules.classes.classadditives;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WeaponBuilder {



    private String itemName;
    private boolean isAvailable = false;
    private boolean hasOthers = false;
    private List itemLore;
    private ItemStack stack;

    public WeaponBuilder(ItemStack stack, String itemName, List lore , boolean isAvailable, boolean hasOthers)
    {

        this.itemName = itemName;
        this.isAvailable = isAvailable;
        this.hasOthers = hasOthers;
        this.itemLore = lore;

        this.stack = constructItemStack(stack);
    }


    public ItemStack constructItemStack(ItemStack stack)
    {

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(this.itemName);
        meta.setLore(this.itemLore);
        stack.setItemMeta(meta);
        return stack;
    }



    public void setItemName(String name)
    {
        this.itemName = name;
    }


    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }


    public String getItemName() {
        return itemName;
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    public void addWeaponEnchants(WeaponEnchants e)
    {

            this.stack.getItemMeta().addEnchant(e.getEnchantment(), e.getStrength(), e.isActive());

    }


    public void removeWeaponEnchants(Enchantment e, ItemMeta meta)
    {
        if(meta.hasEnchant(e))
        {
            meta.removeEnchant(e);
        }
    }
    public void purgeWeaponEnchants(ItemMeta meta)
    {
        if(meta.hasEnchants())
        {
            for(Enchantment chant : Enchantment.values())
            {
                if(meta.hasEnchant(chant))
                {
                    meta.removeEnchant(chant);
                }
            }
        }
    }

    public boolean isHasOthers() {
        return hasOthers;
    }

    public void setHasOthers(boolean hasOthers) {
        this.hasOthers = hasOthers;
    }
}

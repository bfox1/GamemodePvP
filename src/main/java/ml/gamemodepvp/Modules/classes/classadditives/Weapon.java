package ml.gamemodepvp.Modules.classes.classadditives;


import ml.gamemodepvp.builder.WeaponBuilder;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by bfox1 on 4/24/2015.
 * In God We Trust.
 * Weapon Builder is to assist in the creation of items for the kit. This class isnt designed to be used for a
 * Display ItemStack
 */
public class Weapon implements WeaponBuilder {



    private boolean isAvailable = false;
    private boolean hasOthers = false;
    private ItemStack stack;

    public Weapon(ItemStack stack, String itemName, List lore, boolean isAvailable, boolean hasOthers)
    {


        this.isAvailable = isAvailable;
        this.hasOthers = hasOthers;

        this.stack = constructItemStack(stack, itemName, lore);
    }

    public Weapon(ItemStack stack, String itemName, boolean isAvailable)
    {


        this.stack = constructItemStack(stack, itemName, null);
    }


    /**
     * Item Must have Custom name! Lore isnt required, instead type in null
     * @param stack The ItemStack
     * @param itemName Sets the ItemName
     * @param lore Sets the Lore, if null, will not display
     * @return The new ItemStack.
     */
    @Override
    public ItemStack constructItemStack(ItemStack stack, String itemName, List lore)
    {

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(itemName);
        if(lore != null) meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public ItemStack getItemStack()
    {
        return this.stack;
    }



    /**
     * Sets the Weapons ItemStack and reconstructs it.
     * @param stack
     */
    public void setItemStack(ItemStack stack)
    {
        this.stack = constructItemStack(stack, stack.getItemMeta().getDisplayName(), stack.getItemMeta().getLore());
    }


    public void setItemName(String name)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
    }


    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String getItemName() {
        return stack.getItemMeta().getDisplayName();
    }

    @Override
    public int getItemAmount()
    {
        return stack.getAmount();
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Adds Weapons Enchants to Weapon.
     * @param e WeaponEnchants
     */
    public void addWeaponEnchants(WeaponEnchants e)
    {
            if(e.getEnchantment() != null)
            this.stack.getItemMeta().addEnchant(e.getEnchantment(), e.getStrength(), e.isActive());

    }


    /**
     * Removes the Enchants off the weapon
     * @param e
     * @param meta
     */
    public void removeWeaponEnchants(Enchantment e, ItemMeta meta)
    {
        if(meta.hasEnchant(e))
        {
            meta.removeEnchant(e);
        }
    }

    /**
     * Removes ALL Enchants off weapon
     * @param meta
     */
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

    @Override
    public String toString()
    {
        return this.getItemName();
    }
}

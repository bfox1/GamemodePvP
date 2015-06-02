package ml.gamemodepvp.Modules.classes.classadditives;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WeaponBuilder {


    private Material material;
    private String itemName;
    private ItemMeta meta;
    private boolean isAvailable = false;
    private boolean hasOthers = false;

    /*private Enchantment[] ench =
            {
                  Enchantment.DAMAGE_ALL,
                  Enchantment.FIRE_ASPECT,
                  Enchantment.ARROW_DAMAGE,
                    Enchantment.ARROW_FIRE,
                    Enchantment.ARROW_KNOCKBACK,
                    Enchantment.ARROW_INFINITE,
                    Enchantment.DURABILITY,
                    Enchantment.KNOCKBACK
            };

    */




    public WeaponBuilder(Material material, String itemName, ItemMeta meta, boolean isAvailable, boolean hasOthers)
    {
        this.material = material;
        this.itemName = itemName;
        this.meta = meta;
        this.isAvailable = isAvailable;
        this.hasOthers = hasOthers;
    }


    public ItemStack generateItemStack(ItemStack stack)
    {

        meta.setDisplayName(itemName);
        stack.setType(this.material);
        stack.setItemMeta(this.meta);
        return stack;
    }


    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public void setItemName(String name)
    {
        this.itemName = name;
    }

    public void setMeta(ItemMeta meta)
    {
        this.meta = meta;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Material getMaterial() {
        return material;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void addWeaponEnchants(WeaponEnchants[] e)
    {
        for(int i = 0; i < e.length; i++)
        {
            if(e[i] != null)
            this.meta.addEnchant(e[i].getEnchantment(), e[i].getStrength(), e[i].isActive());
        }
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

package ml.gamemodepvp.classes.handler;


import ml.gamemodepvp.classes.classadditives.Food;
import ml.gamemodepvp.classes.classadditives.Perks;
import ml.gamemodepvp.classes.classadditives.WeaponBuilder;
import ml.gamemodepvp.classes.classadditives.WeaponEnchants;
import ml.gamemodepvp.classes.kit.KitBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class KitHandler {


    private ItemStack[] primaryWeapon =
            {
                    new ItemStack(Material.STONE_SWORD),
                    new ItemStack(Material.STONE_AXE),
                    new ItemStack(Material.GOLD_SWORD),
                    new ItemStack(Material.BOW)
            };
    private String[] weaponName =
            {
                    "Assault-t1",
                    "Tank-t1",
                    "Recon-t1",
                    "Archer-t1"
            };

    private WeaponBuilder[] defaultBuilder =
            {


                    new WeaponBuilder(primaryWeapon[0].getType(),ChatColor.AQUA + weaponName[0], primaryWeapon[0].getItemMeta(), true, false),
                    new WeaponBuilder(primaryWeapon[1].getType(),ChatColor.AQUA + weaponName[1], primaryWeapon[1].getItemMeta(), true, false),
                    new WeaponBuilder(primaryWeapon[2].getType(),ChatColor.AQUA + weaponName[2], primaryWeapon[2].getItemMeta(), true, false),
                    new WeaponBuilder(primaryWeapon[3].getType(),ChatColor.AQUA + weaponName[3], primaryWeapon[3].getItemMeta(), false, true)
            };

    private WeaponEnchants[][] weaponEnchants =
            {
                    {new WeaponEnchants(Enchantment.DAMAGE_ALL,1, true), new WeaponEnchants(Enchantment.DURABILITY,1, true)},
                    {new WeaponEnchants(Enchantment.DAMAGE_ALL, 1, true), new WeaponEnchants(Enchantment.KNOCKBACK,1, true)},
                    {new WeaponEnchants(Enchantment.KNOCKBACK, 1,true)},
                    {new WeaponEnchants(Enchantment.ARROW_DAMAGE,1,true)}
            };

    private Food[][] kitFood =
            {
                    {new Food(Material.BREAD, 8), new Food(Material.COOKED_BEEF, 2)},
                    {new Food(Material.BREAD, 10)},
                    {new Food(Material.COOKED_BEEF, 10)},
                    {new Food(Material.COOKED_CHICKEN, 15)}
            };

    private Perks[][] kitPerks =
            {
                    {new Perks(PotionEffectType.INCREASE_DAMAGE, 0, 1500)},
                    {new Perks(PotionEffectType.DAMAGE_RESISTANCE, 0, 1500)},
                    {new Perks(PotionEffectType.SPEED, 0, 1500)},
                    {new Perks(PotionEffectType.INCREASE_DAMAGE, 0, 1500)}
            };

    private ItemStack[][] otherItems =
            {
                    {},
                    {},
                    {},
                    {new ItemStack(Material.ARROW, 64)}
            };



    public Map defaultKit()
    {


        Map<String, KitBuilder> kits = new HashMap<String, KitBuilder>();
        for(int i = 0; i< weaponEnchants.length; i++)
        {
                defaultBuilder[i].addWeaponEnchants(weaponEnchants[i]);

                KitBuilder builder = new KitBuilder(defaultBuilder[i],ChatColor.AQUA +  weaponName[i], kitFood[i], kitPerks[i], otherItems[i]);
            kits.put(weaponName[i], builder);
        }
        return kits;
    }





    public KitBuilder createKit(ItemStack stack, String itemName, Food[] foodMaterial,
                                Perks[] perks, Enchantment[] enchantments)
    {
return null;
    }



}

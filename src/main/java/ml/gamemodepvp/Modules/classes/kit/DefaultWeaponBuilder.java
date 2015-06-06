package ml.gamemodepvp.Modules.classes.kit;

import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 6/5/2015.
 * In God We Trust.
 */
public class DefaultWeaponBuilder {

    private WeaponBuilder primary;
    private WeaponBuilder secondary;
    private WeaponBuilder tactical;
    private WeaponBuilder lethal;
    private Perks perk;
    private KitInventory builder = new KitInventory();



    public void buildKit()
    {
        this.primary = builder.weaponBuilderGenerator(new ItemStack(Material.STONE_SWORD), ChatColor.AQUA + "Shepard's blade", null, null);
        this.secondary = builder.weaponBuilderGenerator(new ItemStack(Material.STONE_AXE), ChatColor.AQUA + "Shepard's Axe", null, null);
        this.tactical = builder.weaponBuilderGenerator(new ItemStack(Material.))
    }



}

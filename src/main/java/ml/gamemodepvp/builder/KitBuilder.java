package ml.gamemodepvp.builder;

import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.Weapon;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 7/4/2015.
 * In God We Trust.
 */
public interface KitBuilder {



    DisplayStack getStack();

    Perks getPerks();

    Weapon[] getKitWeaponList();

    ItemStack[] getOtherAdditives();
}


package ml.gamemodepvp.builder;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by bfox1 on 7/4/2015.
 * In God We Trust.
 */
public interface WeaponBuilder {


    ItemStack constructItemStack(ItemStack stack, String itemName, List lore);

    ItemStack getItemStack();

    String getItemName();

    int getItemAmount();


}

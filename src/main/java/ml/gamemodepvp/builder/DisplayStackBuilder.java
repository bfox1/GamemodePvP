package ml.gamemodepvp.builder;

import ml.gamemodepvp.bukkit.action.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by bfox1 on 7/4/2015.
 * In God We Trust.
 */
public interface DisplayStackBuilder
{
    /**
     * Gets the ItemStack from the DisplayStack.
     * @return The ItemStack from the DisplayStack
     */
    ItemStack getDisplayStack();

    /**
     * Gets the default ItemStack without Modification.
     * @return Default ItemStack
     */
    ItemStack getDefaultStack();

    /**
     * Sets the Lore of the DisplayStack.
     * @param lore The List of the Lore.
     */
    void setLore(List<String> lore);

    /**
     * Gets the ItemAction of the DisplayStack.
     * @return The ItemAction.
     */
    ItemAction getItemAction();

    /**
     * Fires the ItemAction Parameters.
     * @param player The player.
     */
    void fireAction(Player player);

    /**
     * Gets the ActionParams as an Object.
     * @return The ActionParams as an Object.
     */
    Object getActionParams();

}

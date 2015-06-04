package ml.gamemodepvp.Modules.classes.kit;

import ml.gamemodepvp.Modules.classes.event.ItemAction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 6/2/2015.
 * In God We Trust.
 */
public class DisplayStack extends ItemStack
{
    private ItemStack stack;
    private ItemAction action;

    private Object actionPerameters;

    public DisplayStack(Material material)
    {
        this.stack = new ItemStack(material);
    }

    public DisplayStack(Material material, int amount)
    {
        this.stack = new ItemStack(material, amount);
    }

    public DisplayStack(ItemStack stack)
    {
        this.stack = stack;
    }


    public ItemStack getItemStack()
    {
        return stack;
    }


    public ItemAction getAction() {
        return action;
    }

    public void setAction(ItemAction action) {
        this.action = action;
    }

    public void fireAction()
    {
        action.fireAction(actionPerameters);
    }

    public void setActionPerameters(Object actionPerameters) {
        this.actionPerameters = actionPerameters;
    }

    public Object getActionPerameters()
    {
        return this.actionPerameters;
    }

    public void fireActionToPlayer(Player player)
    {

    }
}

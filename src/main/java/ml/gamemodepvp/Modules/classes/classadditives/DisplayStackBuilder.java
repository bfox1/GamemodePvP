package ml.gamemodepvp.Modules.classes.classadditives;

import ml.gamemodepvp.Modules.classes.event.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 6/14/2015.
 * In God We Trust.
 */
public class DisplayStackBuilder {

    private ItemStack defStack;
    private ItemStack displayStack;

    private final ItemAction action;

    private final Object actionParams;

    private String[] lorePrefix =
            {
              "Primary: ", "Secondary: ", "Tactical: ", "Lethal: ", "Others: "
            };

    private List<String> lore;

    private String displayName;


    public DisplayStackBuilder(ItemStack stack, boolean ifDisplayStack, ItemAction action, Object actionParams)
    {
        this.defStack = stack;
        this.action = action;
        this.actionParams = actionParams;
        if(ifDisplayStack)
        {
            ItemMeta meta = stack.getItemMeta();
            this.lore = meta.getLore();
            this.displayName = meta.getDisplayName();
            contructDisplayStack(stack);

        }
    }

    /**
     * Returns default ItemStack
     * @return
     */
    public ItemStack getDefStack() {
        return defStack;
    }

    /**
     * Retruns the Display ItemStack
     * @return
     */
    public ItemStack getDisplayStack() {
        return displayStack;
    }

    /**
     * Sets the Display ItemStack
     * @param displayStack
     */
    public void setDisplayStack(ItemStack displayStack) {
        this.displayStack = displayStack;
    }

    /**
     * Returns the Lore of the Display ItemStack
     * @return
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * Sets the Lore for the Display ItemStack. This is an essential requirement for Weapons!
     * @param lore
     */
    public void setLore(List<String> lore)
    {
        List<String> loref = new ArrayList<String>();
        for(int i = 0; i< lore.size(); i++)
        {
            if(i <=4)
            {
                loref.add(i, lorePrefix[i] + lore.get(i));
            }
            else
            {
                loref.add(i, lore.get(i));
            }
            /*
            try
            {
                String st = this.lorePrefix[i] + lore.get(i);
                loref.add(i, st);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                loref.add(i, lore.get(i));
            }*/
        }
        this.lore = loref;
        contructDisplayStack(defStack);
    }

    /**
     * Returns the Name of the Display ItemStack
     * @return
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the Name of the Display ItemStack
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Builds the DisplayStack from the Information you placed in the Class.
     * @param stack
     */
    private void contructDisplayStack(ItemStack stack)
    {
        ItemMeta meta = stack.getItemMeta();
        meta.setLore(this.lore);
        meta.setDisplayName(this.displayName);
        stack.setItemMeta(meta);
        this.displayStack = stack;
    }

    public ItemAction getAction() {
        return action;
    }

    public void fireAction(Player player)
    {
        action.fireAction(actionParams, player);
    }

    public Object getActionParams() {
        return actionParams;
    }
}

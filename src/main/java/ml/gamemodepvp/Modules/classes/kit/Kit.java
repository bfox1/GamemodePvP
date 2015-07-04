package ml.gamemodepvp.Modules.classes.kit;



import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.Weapon;
import ml.gamemodepvp.builder.KitBuilder;
import ml.gamemodepvp.bukkit.action.ItemAction;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class Kit implements KitBuilder {

    private Weapon primary, secondary, tactical, lethal;
    private ItemStack[] otherAdditives;
    private Perks perks;
    private DisplayStack stack;
    public Kit(ItemStack stack, Weapon primary, Weapon secondary, Weapon tactical, Weapon lethal, Perks perks, ItemStack... otherAdditives)
    {
        this.stack = buildDisplayStack(stack,primary, secondary, tactical, lethal, perks, otherAdditives);
        this.primary = primary;
        this.secondary  = secondary;
        this.tactical = tactical;
        this.lethal = lethal;
        this.perks = perks;
        this.otherAdditives = otherAdditives;
    }


    private DisplayStack buildDisplayStack(
            ItemStack dStack,Weapon primary, Weapon secondary, Weapon tactical,
            Weapon lethal, Perks perks, ItemStack[] otherAdditives)
    {
        DisplayStack stack = new DisplayStack(dStack, true, ItemAction.KIT, null);
        List<String> loreList = new ArrayList<String>();
        loreList.add(0,primary.toString() + primary.getItemAmount());
        loreList.add(1, secondary.toString() + secondary.getItemAmount());
        loreList.add(2, tactical.toString() + tactical.getItemAmount());
        loreList.add(3, lethal.toString() + tactical.getItemAmount());
        loreList.add(4, perks.toString());
        for(int i = 0; i < otherAdditives.length; i++)
        {
            int y = i + 5;
            loreList.add(y, otherAdditives[i].toString());
        }
        stack.setLore(loreList);
        return stack;
    }



    /**
     * Returns the DisplayItemStack Builder
     * @return
     */
    public DisplayStack getStack() {
        return stack;
    }

    /**
     * Sets the DisplayStack
     * @param stack
     */
    public void setStack(DisplayStack stack) {
        this.stack = stack;
    }

    public Perks getPerks() {
        return perks;
    }

    public void setPerks(Perks perks) {
        this.perks = perks;
    }

    /**
     * Returns the List of The Kit weapons.
     * @return Weapon
     */
    @Override
    public Weapon[] getKitWeaponList()
    {
        Weapon[] list =
                {
                        this.primary,
                        this.secondary,
                        this.tactical,
                        this.lethal
                };
        return list;
    }

    /**
     * Sets the weapon Info
     * @param weaponType primary, secondary, tactical or lethal
     * @param stack
     */
    public void setKitWeaponList(String weaponType,ItemStack stack)
    {
        Weapon[] list =
        {
                this.primary,
                this.secondary,
                this.tactical,
                this.lethal
        };

        if(weaponType.equals("primary")) list[0].setItemStack(stack);
        else if(weaponType.equals("secondary")) list[1].setItemStack(stack);
        else if(weaponType.equals("tactical")) list[2].setItemStack(stack);
        else if(weaponType.equals("lethal")) list[3].setItemStack(stack);

    }

    public ItemStack[] getOtherAdditives() {
        return otherAdditives;
    }
}

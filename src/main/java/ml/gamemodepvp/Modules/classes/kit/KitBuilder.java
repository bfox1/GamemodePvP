package ml.gamemodepvp.Modules.classes.kit;



import ml.gamemodepvp.Modules.classes.classadditives.Perks;
import ml.gamemodepvp.Modules.classes.classadditives.WeaponBuilder;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class KitBuilder {

    private WeaponBuilder primary, secondary, tactical, lethal;
    private ItemStack[] otherAdditives;
    private Perks perks;
    private DisplayStack stack;
    protected KitBuilder(DisplayStack stack,WeaponBuilder primary, WeaponBuilder secondary, WeaponBuilder tactical, WeaponBuilder lethal, Perks perks, ItemStack[] otherAdditives)
    {
        this.stack = stack;
        this.primary = primary;
        this.secondary  = secondary;
        this.tactical = tactical;
        this.lethal = lethal;
        this.perks = perks;
        this.otherAdditives = otherAdditives;
    }

    public KitBuilder()
    {

    }


    public DisplayStack getStack() {
        return stack;
    }

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
     * @return WeaponBuilder
     */
    public WeaponBuilder[] getKitWeaponList()
    {
        WeaponBuilder[] list =
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
        WeaponBuilder[] list =
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

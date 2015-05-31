package ml.gamemodepvp.classes.kit;


import ml.gamemodepvp.classes.classadditives.Food;
import ml.gamemodepvp.classes.classadditives.Perks;
import ml.gamemodepvp.classes.classadditives.WeaponBuilder;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class KitBuilder {

    private Food[] foodMap;
    private Perks[] perkMap;
    private WeaponBuilder weapon;
    private String kitName;
    private ItemStack[] otherItems;

    protected KitBuilder(WeaponBuilder weapon, String kitName, Food[] foodMap, Perks[] perkMap, ItemStack[] otherItems)
    {
        this.weapon = weapon;
        this.kitName = kitName;
        this.foodMap = foodMap;
        this.perkMap = perkMap;
        this.otherItems = otherItems;
    }


    public WeaponBuilder getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponBuilder weapon) {
        this.weapon = weapon;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public Food[] getfoodMap() {
        return foodMap;
    }

    public void setfoodMap(Food[] food) {
        this.foodMap = food;
    }

    public Perks[] getPerks() {
        return perkMap;
    }

    public void setPerks(Perks[] perkMap) {
        this.perkMap = perkMap;
    }

    public ItemStack[] getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(ItemStack[] otherItems) {
        this.otherItems = otherItems;
    }
}

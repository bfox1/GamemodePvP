package ml.gamemodepvp.Modules.classes.classadditives;

import org.bukkit.enchantments.Enchantment;

/**
 * Created by bfox1 on 4/26/2015.
 * In God We Trust.
 */
public class WeaponEnchants {

    private Enchantment enchantment;
    private int strength;
    private boolean isActive;


    public WeaponEnchants(Enchantment enchantment, int strength, boolean isActive)
    {
        this.enchantment = enchantment;
        this.strength = strength;
        this.isActive = isActive;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

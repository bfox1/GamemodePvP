package ml.gamemodepvp.Modules.classes.classadditives;

import ml.gamemodepvp.builder.ArmorBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by bfox1 on 7/22/2015.
 * In God We Trust.
 */
public class ArmorKit implements ArmorBuilder {



    private ItemStack helmet;
    private ItemStack chestPlate;
    private ItemStack leggings;
    private ItemStack boots;

    private String armorKitName;

    public ArmorKit(String kitName, Material helmMaterial,
                    Material chestMaterial, Material legMaterial, Material bootMaterial)
    {
        this.armorKitName = kitName;
        this.helmet = new ItemStack(helmMaterial);
        this.chestPlate = new ItemStack(chestMaterial);
        this.leggings = new ItemStack(legMaterial);
        this.boots = new ItemStack(bootMaterial);
    }
    @Override
    public String getArmorName() {
        return null;
    }

    @Override
    public ItemStack getHelmet() {
        return helmet;
    }

    @Override
    public ItemStack getChestPlate() {
         return chestPlate;
    }

    @Override
    public ItemStack getLeggings() {
        return null;
    }

    @Override
    public ItemStack getBoots() {
        return null;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setChestPlate(ItemStack chestPlate) {
        this.chestPlate = chestPlate;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }



    public void setArmorKitName(String armorKitName) {
        this.armorKitName = armorKitName;
    }
}

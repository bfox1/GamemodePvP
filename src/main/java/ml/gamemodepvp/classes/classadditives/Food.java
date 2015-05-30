package ml.gamemodepvp.classes.classadditives;

import org.bukkit.Material;

/**
 * Created by bfox1 on 4/25/2015.
 */
public class Food {


    private Material food1;
    private int amount;

    public Food(Material material, int amount)
    {
        this.food1 = material;
        this.amount = amount;
    }


    public void addFood(Material material, int i)
    {
        this.food1 = material;
        this.amount = i;

    }

    public Material getFood1()
    {
        return this.food1;
    }

    public int getAmount()
    {
        return this.amount;
    }

}

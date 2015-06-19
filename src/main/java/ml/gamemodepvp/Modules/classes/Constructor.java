package ml.gamemodepvp.Modules.classes;

import ml.gamemodepvp.Modules.classes.classadditives.DisplayStackBuilder;
import ml.gamemodepvp.Modules.classes.kit.KitBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by bfox1 on 6/16/2015.
 * In God We Trust.
 *
 * Creator of all Virtual Inventory.
 */
public class Constructor {


    public Inventory inventory;

    public List<KitBuilder> kitInventory;


    public Constructor(String inventoryName, int inventoryAmount)
    {
        this.inventory = Bukkit.createInventory(null, inventoryAmount, inventoryName);
    }


    public void setInventorySlot(DisplayStackBuilder stackBuilder)
    {
        this.inventory.addItem(stackBuilder.getDisplayStack());
    }

    public void setKitInventory(KitBuilder builder)
    {
        setInventorySlot(builder.getStack());
        this.kitInventory.add(builder);
    }

    public KitBuilder getBuilderFromDisplayStack(ItemStack stack)
    {
        for(int i = 0; i < kitInventory.size(); i++)
        {
            if(kitInventory.get(i).getStack().getDisplayStack() == stack)
            {
                return this.kitInventory.get(i);
            }
        }
        return null;
    }



    public boolean hasItemStack(ItemStack stack)
    {
        for(int i = 0;  i < this.inventory.getSize(); i++)
        {
            if(this.inventory.getItem(i) == stack)
            {
                return true;
            }
        }
        return false;
    }




}

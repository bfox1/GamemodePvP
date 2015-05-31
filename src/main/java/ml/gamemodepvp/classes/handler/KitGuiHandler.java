package ml.gamemodepvp.classes.handler;

import ml.gamemodepvp.classes.classadditives.Food;
import ml.gamemodepvp.classes.classadditives.Perks;
import ml.gamemodepvp.classes.kit.KitBuilder;
import ml.gamemodepvp.classes.kit.KitHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.bukkit.ChatColor.*;
/**
 * Created by bfox1 on 4/24/2015.
 */
public class KitGuiHandler {


    private Inventory vChest;

    private Map kitCollection;

    private KitBuilder assault;
    private KitBuilder tank;
    private KitBuilder recon;
    private KitBuilder archer;

    public void defaultKitSetup()
    {
        this.kitCollection = new KitHandler().defaultKit();
    }

    public void setKit()
    {
        Iterator iterator = this.kitCollection.entrySet().iterator();

        while(iterator.hasNext())
        {
            Map.Entry entryData = (Map.Entry) iterator.next();

            if(entryData.getKey() != null)
            {
                System.out.println(entryData.getKey());
                if(entryData.getKey().equals("Assault-t1")) {
                    this.assault = (KitBuilder) entryData.getValue();
                } else if (entryData.getKey().equals("Tank-t1"))
                {
                    this.tank = (KitBuilder) entryData.getValue();
                } else if (entryData.getKey().equals("Recon-t1"))
                {
                    this.recon = (KitBuilder) entryData.getValue();
                } else if(entryData.getKey().equals("Archer-t1"))
                {
                    this.archer = (KitBuilder) entryData.getValue();
                }

            }
            iterator.remove();
        }
    }

    /**
     * This sets the Class Items to Player.
     * for defaults ONLY.
     * @param player
     * @param displayKit
     */
    public void setDefaultAssets(Player player, String displayKit)
    {
        setKit();
        applyAssets(player, displayKit, this.assault);
        applyAssets(player, displayKit, this.tank);
        applyAssets(player, displayKit, this.archer);
        applyAssets(player, displayKit, this.recon);
    }

    private void applyAssets(Player player, String displayKitName, KitBuilder kit)
    {
        if(displayKitName.equalsIgnoreCase(kit.getWeapon().getItemName().substring(2)))
        {
            player.getInventory().clear();
            for(PotionEffect effect : player.getActivePotionEffects())
            {
                player.removePotionEffect(effect.getType());
            }
            player.getInventory().addItem(kit.getWeapon().generateItemStack(new ItemStack(kit.getWeapon().getMaterial())));
            Food[] foods = kit.getfoodMap();
            Perks[] perks = kit.getPerks();
            for(int i = 0; i < foods.length; i++)
            {

                player.getInventory().addItem(new ItemStack(foods[i].getFood1(), foods[i].getAmount()));
            }
            if(perks.length < 0)
            {
                for(Perks perk : perks)
                {
                    player.addPotionEffect(perk.getPotionEffect());
                }
            } else
            {
                player.addPotionEffect(perks[0].getPotionEffect());
            }
            System.out.println(kit.getWeapon().isHasOthers());
            if(kit.getWeapon().isHasOthers())
            {
                ItemStack[] items = kit.getOtherItems();
                System.out.println(items);
                for(ItemStack stack : items)
                {
                    System.out.println(stack);
                    if(stack != null)
                    {

                        player.getInventory().addItem(stack);
                    }
                }
            }
        }
    }





    public void createGUI()
    {

        this.vChest = Bukkit.getServer().createInventory(null, 9, DARK_PURPLE + "Default Kits");
        defaultKitSetup();
        this.vChest.setItem(0, createDisplayItemStack(Material.STONE_SWORD, AQUA, "Assault-t1", "Primary", "Food", "Perks",
                "", "Assault-t1 StoneSword", "10 Bread, 2 Steak", "Absorbsion I - 60 Seconds", ""));

        this.vChest.setItem(1, createDisplayItemStack(Material.STONE_AXE, AQUA, "Tank-t1", "Primary", "Food",
                "Perks", "", "Tank-t1 StoneAxe", "10 Bread", "Resistance 1 - 60 Seconds", ""));

        this.vChest.setItem(2, createDisplayItemStack(Material.GOLD_SWORD, AQUA, "Recon-t1", "Primary", "Food",
                "Perks", "", "Recon-t1 GoldSword", "10 Steak", "Speed 1 - 60 Seconds", ""));

        this.vChest.setItem(3, createDisplayItemStack(Material.BOW, AQUA, "Archer-t1", "Primary", "Food",
                "Perks", "Other", "Archer-t1 Bow", "15 Chicken", "Strength 1 - 60 Seconds", "64 Bows"));

    }

    /**
     *
     * @param material The Material for the DisplayStack.
     * @param chatColor Chat Color for the Weapon Name.
     * @param weaponName The name of the Weapon/Item
     * @param firstSlot The Property of slot(Ex. Primary, Secondary, Food, Potions, Perks.)
     * @param secondSlot The Property of slot(Ex. Primary, Secondary, Food, Potions, Perks.)
     * @param thridSlot The Property of slot(Ex. Primary, Secondary, Food, Potions, Perks.)
     * @param forthSlot The Property of slot(Ex. Primary, Secondary, Food, Potions, Perks.)
     * @param firstMsg Discription for slot
     * @param secondMsg Discription for slot
     * @param thirdMsg Discription for slot
     * @param forthMsg Discription for slot
     * @return
     */
    public ItemStack createDisplayItemStack(Material material, ChatColor chatColor, String weaponName, String firstSlot, String secondSlot, String thridSlot, String forthSlot,
                                            String firstMsg, String secondMsg, String thirdMsg, String forthMsg)
    {
        ItemStack displayStack = new ItemStack(material);
        ItemMeta meta = displayStack.getItemMeta();

        meta.setDisplayName(chatColor + weaponName);

        List<String> list = new ArrayList<String>();

        list.add(0, DARK_RED + "" + BOLD + firstSlot + BLACK + "-" + DARK_GREEN + firstMsg);
        list.add(1,DARK_RED + "" + BOLD + secondSlot + BLACK + "-" + DARK_GREEN + secondMsg);
        list.add(2, DARK_RED + "" + BOLD + thridSlot + BLACK + "-" + DARK_GREEN + thirdMsg);
        list.add(3, DARK_RED + "" + BOLD + forthSlot + BLACK + "-" + DARK_GREEN + forthMsg);
        meta.setLore(list);

        displayStack.setItemMeta(meta);



        return displayStack;
    }


    public Inventory getvChest()
    {
        return this.vChest;
    }

   // public KitBuilder getSet()
   // {
      //  return this.set;
   // }

    public void openGUI(Player player)
    {
        player.openInventory(this.vChest);
    }

}

package ml.gamemodepvp.core;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import ml.gamemodepvp.classes.datasaver.KitSaver;
import ml.gamemodepvp.classes.gui.KitGui;
import ml.gamemodepvp.core.lib.CorePlayerData;
import ml.gamemodepvp.core.lib.PlayerDataHandler;
import ml.gamemodepvp.init.ListenerLoader;
import ml.gamemodepvp.world.region.RegionDataManager;
import ml.gamemodepvp.init.CommandLoader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CoreMain extends JavaPlugin {


    public FileConfiguration plugin = getConfig();

    public final Logger logger = Logger.getLogger("Minecraft");
    public  YamlConfiguration permission;

    private final RegionDataManager manager = new RegionDataManager();
    public final PluginManager pm = this.getServer().getPluginManager();



    private List<CorePlayerData> data;


    @Override
    public void onEnable() {
        this.manager.loadWorldData(this);

        ListenerLoader lLoader = new ListenerLoader(this);
        lLoader.load();

        getConfig().options().copyDefaults(true);

        Bukkit.getServer().getWorlds();
        saveConfig();
        System.out.println(this.toString());

        CommandLoader ld = new CommandLoader(this);
        ld.load();

        //Inventory iv = Bukkit.createInventory(null, 9,"TEST");
        //ItemStack testStack = new ItemStack(Material.DIAMOND_AXE);
        //ItemMeta meta = testStack.getItemMeta();
       // meta.setDisplayName("HELLO");
       // testStack.setItemMeta(meta);
       // iv.addItem(testStack, new ItemStack(Material.DIAMOND), new ItemStack(Material.ANVIL), new ItemStack(Material.ARMOR_STAND));
       // KitSaver sv = new KitSaver(iv);
        //sv.yamlLoad(new File(this.getDataFolder(), "/test.yml"));

        File file = new File(this.getDataFolder(), "/test.yml");
        ItemStack[] ls = new KitSaver().loadKitData(file);

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       // sv.saveKitData(new File(this.getDataFolder(), "/test.yml"));


    }

    @Override
    public void onDisable() {

        this.manager.saveWorldData(this);
        saveData();

    }

    public RegionDataManager getDataManager()
    {
        return this.manager;
    }

    public void saveData()
    {
        if(this.data != null)
        for(int i = 0; i< this.data.size(); i++)
        {
            PlayerDataHandler dataHandler = new PlayerDataHandler(this.data.get(i));
            dataHandler.saveData();
        }
    }

    public void removeFromList(CorePlayerData data1)
    {
        this.data.remove(data1);
    }

    public void addToList(CorePlayerData data1)
    {

        //this.data.add(data1);
    }

    public void setupCommands(String name)
    {

        getCommand(name).setExecutor(new CoreExecutor(this));
    }
}

package ml.gamemodepvp.bukkit;




import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.management.RegionDataManager;
import ml.gamemodepvp.init.CommandLoader;
import ml.gamemodepvp.init.ListenerLoader;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.menu.MainMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;


public class CoreMain extends JavaPlugin {


    public FileConfiguration plugin = getConfig();

    public final Logger logger = Logger.getLogger("Minecraft");
    public  YamlConfiguration permission;
    private final RegionDataManager regionManager = new RegionDataManager();
    public final PluginManager pm = this.getServer().getPluginManager();


    private final HashMap<String, GmpvpInventory> inventoryHashMap = new HashMap<String, GmpvpInventory>();


    public final LobbyManager lobbyManager = new LobbyManager(this);


    @Override
    public void onEnable() {

        this.regionManager.loadWorldData(this);
        logger.info("Region data has been successfully loaded");

        ListenerLoader lLoader = new ListenerLoader(this);
        lLoader.load();
        logger.info("Listeners have been successfully loaded");

        getConfig().options().copyDefaults(true);
        saveConfig();

        CommandLoader ld = new CommandLoader(this);
        ld.load();
        logger.info("Commands have been successfully loaded");
        inventoryHashMap.put(new MainMenu(this).getInventory().getName(), new MainMenu(this));




    }

    @Override
    public void onDisable() {

        this.regionManager.saveWorldData(this);


    }

    public RegionDataManager getDataManager()
    {
        return this.regionManager;
    }



    public LobbyManager getLobbyManager()
    {
        return this.lobbyManager;
    }


    public MainMenu getMenu() {
        if(inventoryHashMap.containsKey("Main Menu"))
        return (MainMenu)inventoryHashMap.get("Main Menu");
        return null;
    }

    /**
     * Adds Inventory to Map to be globally accessed.
     * @param inventory
     */
    public void addToInventoryGuis(GmpvpInventory inventory)
    {
        inventoryHashMap.put(inventory.getInventory().getName(), inventory);
    }



    public GmpvpInventory getGmpvpInventoryByName(String name)
    {
        if(inventoryHashMap.containsKey(name))
        {
            return inventoryHashMap.get(name);
        }
        return null;
    }

}

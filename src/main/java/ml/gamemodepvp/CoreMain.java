package ml.gamemodepvp;




import ml.gamemodepvp.management.RegionDataManager;
import ml.gamemodepvp.init.CommandLoader;
import ml.gamemodepvp.init.ListenerLoader;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.menu.MainMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class CoreMain extends JavaPlugin {


    public FileConfiguration plugin = getConfig();

    public final Logger logger = Logger.getLogger("Minecraft");
    public  YamlConfiguration permission;
    private final RegionDataManager regionManager = new RegionDataManager();
    public final PluginManager pm = this.getServer().getPluginManager();

    //private final InventoryConstructor defaultKits = new InventoryConstructor("Default Kit",9);
    private final MainMenu menu = new MainMenu(this);


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


       // defaultKits.setKitInventory(defaultKits.buildDefaultInventory());




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
        return menu;
    }
}

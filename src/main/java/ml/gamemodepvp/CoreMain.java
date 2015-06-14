package ml.gamemodepvp;


import ml.gamemodepvp.Modules.classes.kit.InventoryConstructor;

import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.regiondata.RegionDataManager;
import ml.gamemodepvp.init.CommandLoader;
import ml.gamemodepvp.init.ListenerLoader;
import ml.gamemodepvp.management.LobbyManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.logging.Logger;


public class CoreMain extends JavaPlugin {


    public FileConfiguration plugin = getConfig();

    public final Logger logger = Logger.getLogger("Minecraft");
    public  YamlConfiguration permission;
    private final RegionDataManager regionManager = new RegionDataManager();
    public final PluginManager pm = this.getServer().getPluginManager();

    public final InventoryConstructor menuInventory = new InventoryConstructor();
    public final LobbyManager lobbyManager = new LobbyManager(this);



    private List<CorePlayerData> data;


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




    }

    @Override
    public void onDisable() {

        this.regionManager.saveWorldData(this);


    }

    public RegionDataManager getDataManager()
    {
        return this.regionManager;
    }

    public InventoryConstructor getDefaultInventory()
    {
        return this.menuInventory;
    }

    public LobbyManager getLobbyManager()
    {
        return this.lobbyManager;
    }


}

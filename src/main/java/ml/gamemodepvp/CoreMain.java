package ml.gamemodepvp;


import ml.gamemodepvp.Modules.classes.kit.InventoryConstructor;

import ml.gamemodepvp.Modules.classes.kit.KitBase;
import ml.gamemodepvp.Modules.core.CoreExecutor;
import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.playerdata.PlayerDataHandler;
import ml.gamemodepvp.database.regiondata.RegionDataManager;
import ml.gamemodepvp.init.CommandLoader;
import ml.gamemodepvp.init.ListenerLoader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;


public class CoreMain extends JavaPlugin {


    public FileConfiguration plugin = getConfig();

    public final Logger logger = Logger.getLogger("Minecraft");
    public  YamlConfiguration permission;

    private final RegionDataManager manager = new RegionDataManager();
    public final PluginManager pm = this.getServer().getPluginManager();

    public  KitBase menuInventory;



    private List<CorePlayerData> data;


    @Override
    public void onEnable() {
        this.manager.loadWorldData(this);

        InventoryConstructor gui = new InventoryConstructor();

        this.menuInventory = new KitBase(gui);
        ListenerLoader lLoader = new ListenerLoader(this);
        lLoader.load();

        getConfig().options().copyDefaults(true);

        Bukkit.getServer().getWorlds();
        saveConfig();
        System.out.println(this.toString());

        CommandLoader ld = new CommandLoader(this);
        ld.load();

    }

    @Override
    public void onDisable() {

        this.manager.saveWorldData(this);


    }

    public RegionDataManager getDataManager()
    {
        return this.manager;
    }


}

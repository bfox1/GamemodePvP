package ml.gamemodepvp.Modules.core;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreHelper {

    protected static Server srv;
    protected static JavaPlugin plg;
    protected static FileConfiguration cfg;
    protected static PluginDescriptionFile desc;

    protected static boolean debug;

    public static void i(String msg) {
        srv.getLogger().info   (desc.getName() + " // " + msg);
    }

    public static void w(String msg) {
        srv.getLogger().warning(desc.getName() + " // " + msg);
    }

    public static void s(String msg) {
        srv.getLogger().severe (desc.getName() + " // " + msg);
    }

    public void setUpConfig()
    {

    }

}

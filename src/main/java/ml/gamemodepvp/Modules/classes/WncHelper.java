package ml.gamemodepvp.Modules.classes;

import ml.gamemodepvp.CoreMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import sun.security.krb5.Config;

import java.util.logging.Logger;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class WncHelper {


       public static final Logger srv = Logger.getLogger("Minecraft");
       protected static Config plg;
       protected static FileConfiguration cfg;
       protected static PluginDescriptionFile desc;
    protected static PluginManager mng;
        private CoreMain wnc;

        public WncHelper(CoreMain wnc)
        {
            this.wnc = wnc;
            this.desc = wnc.getDescription();
            this.mng = wnc.getServer().getPluginManager();
        }

       protected static boolean debug;

        protected static void i(String msg)
        {
           // srv.info(desc.getName() + " V" + desc.getVersion() + "//" + msg);
        }

         protected static void w(String msg)
         {
             srv.warning(desc.getName() + "  " + msg);
         }

         protected static void s(String msg)
         {
             srv.severe (desc.getName() + " " + msg);
         }

        private static Object setDebugMessage(Object obj)
        {
            return obj;

        }
}

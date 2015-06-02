package ml.gamemodepvp.init;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.WncExecutor;
import ml.gamemodepvp.Modules.classes.handler.KitGuiHandler;
import ml.gamemodepvp.Modules.core.CoreExecutor;
import ml.gamemodepvp.Modules.economy.EcoExecutor;
import ml.gamemodepvp.Modules.world.WorldExecutor;
import org.bukkit.command.CommandExecutor;

/**
 * Created by bfox1 on 5/23/2015.
 * In God We Trust.
 */
public class CommandLoader{

    private CoreMain main;

    public CommandLoader(CoreMain main)
    {
        this.main = main;
    }


    public void load()
    {

        //Core Commands

        setupCommands("savedata", new CoreExecutor(main));
        setupCommands("loaddata", new CoreExecutor(main));
        setupCommands("register", new CoreExecutor(main));
        //Rank Commands
        setupCommands("setplayerrank", new CoreExecutor(main));
        setupCommands("removePlayerRank", new CoreExecutor(main));

        //Economy Commands

        setupCommands("ecomoney", new EcoExecutor(main));
        //World Commands
        setupCommands("createNewArena", new WorldExecutor(main));
        setupCommands("teleportToWorld", new WorldExecutor(main));
        setupCommands("deleteworld", new WorldExecutor(main));
        setupCommands("setMapSize", new WorldExecutor(main));
        setupCommands("removeMapRegion", new WorldExecutor(main));
        setupCommands("createRegion", new WorldExecutor(main));
        setupCommands("setupRegion", new WorldExecutor(main));
        setupCommands("getRegionList", new WorldExecutor(main));
        setupCommands("removeregion", new WorldExecutor(main));
        setupCommands("setRegionFlag", new WorldExecutor(main));
        setupCommands("setPlayerFlag", new WorldExecutor(main));

        //Classes Commands
        setupCommands("class", new WncExecutor(main, new KitGuiHandler()));
    }

    public void setupCommands(String name, CommandExecutor executor)
    {
        main.getCommand(name).setExecutor(executor);
    }
}

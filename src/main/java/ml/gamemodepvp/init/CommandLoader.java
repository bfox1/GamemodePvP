package ml.gamemodepvp.init;

import ml.gamemodepvp.CommandList;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.classes.WncExecutor;

import ml.gamemodepvp.Modules.core.CoreExecutor;
import ml.gamemodepvp.Modules.economy.EcoExecutor;
import ml.gamemodepvp.Modules.gamemodes.executor.LobbyRegionExecutor;
import ml.gamemodepvp.Modules.gamemodes.modes.executor.LobbyCommandExecutor;
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
        setupCommands("class", new WncExecutor(main));
        setupCommands("classMenu", new WncExecutor(main));

        setupCommands("setlocation", new LobbyCommandExecutor(main));
        setupCommands("joinLobby", new LobbyCommandExecutor(main));
        setupCommands("leaveLobby", new LobbyCommandExecutor(main));

        setupCommands(CommandList.LobbyCommands.SETLOBBYZONE, new LobbyRegionExecutor(main));
        setupCommands(CommandList.LobbyCommands.CREATELOBBYZONE, new LobbyRegionExecutor(main));
    }

    public void setupCommands(String name, CommandExecutor executor)
    {
        main.getCommand(name).setExecutor(executor);
    }
}

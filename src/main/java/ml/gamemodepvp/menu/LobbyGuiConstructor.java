package ml.gamemodepvp.menu;

import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.classes.InventoryConstructor;
import ml.gamemodepvp.Modules.classes.classadditives.DisplayStackBuilder;
import ml.gamemodepvp.Modules.classes.event.ItemAction;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.gamemodes.listener.LobbyListener;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import sun.security.ssl.Debug;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 6/30/2015.
 * In God We Trust.
 */
public class LobbyGuiConstructor extends InventoryConstructor {


    private int taskID;

    private List<DisplayStackBuilder> displayStackBuilderList;

    private int lobbiesOpened;

    private CoreMain main;

    public LobbyGuiConstructor(String inventoryName, int inventoryAmount,  CoreMain main)
    {
        super(inventoryName, inventoryAmount);
        this.displayStackBuilderList = new ArrayList<DisplayStackBuilder>();
        this.main = main;
    }

    public void runTask()
    {
        List<Lobby> lobbyList;
        try
        {
            lobbyList = main.getLobbyManager().getOpenLobbies();
        }catch (NullPointerException e)
        {
            lobbyList = null;
        }
        if(lobbyList == null)
        {
            this.lobbiesOpened = 0;
        }
        else
        {
            this.lobbiesOpened = lobbyList.size();
        }

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {


               /* if (lobbiesOpened != main.getLobbyManager().getOpenLobbies().size())
                {
                    establishLobbyGui(main);
                    DebugCore.returnDebugMessage("Testing Past");
                    Bukkit.getScheduler().cancelTask(taskID);
                } else if(main.getLobbyManager().getOpenLobbies().size() == 0)
                {
                    DebugCore.returnDebugMessage("Testing Failed");
                    establishLobbyGui(main);
                    Bukkit.getScheduler().cancelTask(taskID);

                }*/
                establishLobbyGui(main);
                Bukkit.getScheduler().cancelTask(taskID);
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                DebugCore.returnDebugMessage(stackTraceElements);
            }
        }, 0, 50);
    }


    public void establishLobbyGui(CoreMain main)
    {
        if(this.getInventory().getContents().length > 0)
        {
            this.getInventory().clear();
        }
        LobbyManager manger = main.getLobbyManager();
        List<Lobby> lobbyList = manger.getOpenLobbies();
        int it = 0;
        DebugCore.returnDebugMessage("In LobbyGui" + lobbyList.size());
        if(lobbyList.size() != 0)
        {
            for (Lobby lobby : lobbyList) {
                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData()),
                        ItemAction.COMMAND,
                        "/joinLobby ",
                        lobby.getLobbyName(),
                        " "
                ));
                it++;

            }

            for (int i = 0; i < manger.getMaxLobbyCount() - lobbyList.size(); i++) {
                Map<String, Lobby> mp = manger.getLobbyMapInfo();

                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.RED.getData()),
                        ItemAction.COMMAND,
                        "/joinLobby ",
                        "Lobby." + it,
                        " "
                ));
                it++;
            }

        }
        else
        {
            for (int i = 0; i < manger.getMaxLobbyCount(); i++) {
                Map<String, Lobby> mp = manger.getLobbyMapInfo();

                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.RED.getData()),
                        ItemAction.COMMAND,
                        "/joinLobby ",
                        "Lobby." + it,
                        " "
                ));
                it++;
            }
        }
    }

    private void setInventforySlot(DisplayStackBuilder builder)
    {
        displayStackBuilderList.add(builder);
        super.setInventorySlot(builder);
    }
}

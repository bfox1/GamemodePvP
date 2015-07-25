package ml.gamemodepvp.menu;

import ml.gamemodepvp.Modules.classes.DisplayStack;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import ml.gamemodepvp.bukkit.action.ItemAction;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 6/30/2015.
 * In God We Trust.
 */
public class LobbyGuiGmpvp extends GmpvpInventory {


    private int taskID;


    private int lobbiesOpened;


    public LobbyGuiGmpvp(String inventoryName, int inventoryAmount, CoreMain main)
    {
        super(inventoryName, inventoryAmount, main);

    }

    public void runTask()
    {
        List<Lobby> lobbyList;
        try
        {
            lobbyList = getMain().getLobbyManager().getOpenLobbies();
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

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getMain(), new Runnable() {
            @Override
            public void run() {

                establishLobbyGui(getMain());
                Bukkit.getScheduler().cancelTask(taskID);
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
        int it = 1;
        DebugCore.returnDebugMessage("In LobbyGui" + lobbyList.size());
        if(lobbyList.size() != 0)
        {
            for (Lobby lobby : lobbyList) {
                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData()),
                        ItemAction.TELEPORT,
                        "joinLobby ",
                        lobby.getLobbyName(),
                        " "
                ));
                it++;

            }

            for (int i = 0; i < manger.getMaxLobbyCount() - lobbyList.size(); i++) {
                //Map<String, Lobby> mp = manger.getLobbyMapInfo();

                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.RED.getData()),
                        ItemAction.COMMAND,
                        "joinLobby ",
                        "Lobby-" + it,
                        " "
                ));
                it++;
            }

        }
        else
        {
            for (int i = 0; i < manger.getMaxLobbyCount(); i++) {
                //Map<String, Lobby> mp = manger.getLobbyMapInfo();

                setInventforySlot(buildItemIcon(
                        new ItemStack(Material.WOOL, 1, DyeColor.RED.getData()),
                        ItemAction.COMMAND,
                        "joinLobby ",
                        "Lobby-" + it,
                        " "
                ));
                it++;
            }
        }
    }


    public DisplayStack getDiplayFromItemStack(ItemStack stack)
    {
        for(DisplayStack builder : this.getDisplayStackBuilder())
        {
            if(builder.getDisplayStack().equals(stack))
            {
                return builder;
            }
        }
        return null;
    }


    private void setInventforySlot(DisplayStack builder)
    {
        this.getDisplayStackBuilder().add(builder);
        super.setInventorySlot(builder);
    }
}

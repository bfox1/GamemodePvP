package ml.gamemodepvp.bukkit.events;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.Lobby;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by bfox1 on 6/19/2015.
 * In God We Trust.
 */
public class LobbyLeaveEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private String lobbyName;
    private CoreMain main;

    public LobbyLeaveEvent(Player player, String lobbyName, CoreMain main)
    {
        this.player = player;
        this.lobbyName = lobbyName;
        this.main = main;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Lobby getLobby()
    {
        return main.getLobbyManager().getLobbyInfo(lobbyName);
    }


    public String getLobbyName() {
        return lobbyName;
    }


    public CoreMain getMain() {
        return main;
    }

}

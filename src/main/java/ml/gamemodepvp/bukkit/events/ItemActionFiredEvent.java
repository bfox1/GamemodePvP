package ml.gamemodepvp.bukkit.events;

import ml.gamemodepvp.Modules.classes.GmpvpInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by bfox1 on 7/1/2015.
 * In God We Trust.
 */
public class ItemActionFiredEvent extends Event {

    private Player player;
    private GmpvpInventory inventory;

    private static final HandlerList handlers = new HandlerList();

    public ItemActionFiredEvent(GmpvpInventory constructor, Player player)
    {
        if(player != null && constructor != null)
        {
            this.player = player;
            this.inventory = constructor;
        }
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

    public GmpvpInventory getInventory() {
        return inventory;
    }
}

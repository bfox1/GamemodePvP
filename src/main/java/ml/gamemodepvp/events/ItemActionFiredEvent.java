package ml.gamemodepvp.events;

import ml.gamemodepvp.Modules.classes.InventoryConstructor;
import ml.gamemodepvp.Modules.classes.event.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by bfox1 on 7/1/2015.
 * In God We Trust.
 */
public class ItemActionFiredEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public ItemActionFiredEvent(InventoryConstructor constructor, Player player)
    {

    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}

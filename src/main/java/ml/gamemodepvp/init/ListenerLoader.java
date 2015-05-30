package ml.gamemodepvp.init;

import ml.gamemodepvp.classes.WncListener;
import ml.gamemodepvp.classes.handler.KitGuiHandler;
import ml.gamemodepvp.core.listeners.CoreListener;
import ml.gamemodepvp.core.CoreMain;
import ml.gamemodepvp.core.listeners.LoadDataListener;
import ml.gamemodepvp.economy.EcoListener;
import ml.gamemodepvp.ranks.RankListener;
import ml.gamemodepvp.world.WorldListener;
import org.bukkit.event.Listener;

/**
 * Created by bfox1 on 5/23/2015.
 * In God We Trust.
 */
public class ListenerLoader {

    private final CoreMain main;

    public ListenerLoader(CoreMain main)
    {
        this.main = main;
    }

    public void load()
    {
        setupListeners(new RankListener());
        setupListeners(new CoreListener(this.main));
        setupListeners(new WorldListener(this.main));
        setupListeners(new EcoListener());
        setupListeners(new LoadDataListener(this.main));
        //setupListeners(new WncListener(this.main));
    }

    private void setupListeners(Listener ls)
    {
        main.pm.registerEvents(ls, this.main);
    }
}

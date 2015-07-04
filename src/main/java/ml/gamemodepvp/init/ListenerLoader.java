package ml.gamemodepvp.init;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.classes.WncListener;
import ml.gamemodepvp.Modules.core.listeners.CoreSubListener;
import ml.gamemodepvp.Modules.core.listeners.LoadDataListener;
import ml.gamemodepvp.Modules.economy.EcoListener;
import ml.gamemodepvp.Modules.gamemodes.listener.LobbyListener;
import ml.gamemodepvp.Modules.ranks.RankListener;
import ml.gamemodepvp.Modules.world.WorldListener;
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
        setupListeners(new CoreSubListener(this.main));
        setupListeners(new WorldListener(this.main));
        setupListeners(new EcoListener());
        setupListeners(new LoadDataListener(this.main));
        setupListeners(new WncListener(this.main));
        setupListeners(new LobbyListener(main));

    }

    private void setupListeners(Listener ls)
    {
        main.pm.registerEvents(ls, this.main);
    }
}

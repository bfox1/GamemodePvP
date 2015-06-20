package ml.gamemodepvp;

import org.bukkit.event.Listener;

/**
 * Created by bfox1 on 6/19/2015.
 * In God We Trust.
 */
public class CoreListener implements Listener {

    private CoreMain main;

    public CoreListener(CoreMain main)
    {
        this.main = main;
    }

    public CoreMain getMain() {
        return main;
    }
}

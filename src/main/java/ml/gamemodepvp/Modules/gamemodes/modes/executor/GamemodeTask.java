package ml.gamemodepvp.Modules.gamemodes.modes.executor;

import ml.gamemodepvp.Modules.gamemodes.Gamemode;
import ml.gamemodepvp.Modules.gamemodes.modes.freeforall.FreeForAll;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by bfox1 on 6/8/2015.
 * In God We Trust.
 */
public class GamemodeTask extends BukkitRunnable {

    Gamemode mode;

    public GamemodeTask(Gamemode gamemode)
    {
        this.mode = gamemode;
    }

    @Override
    public void run()
    {

        if(mode instanceof FreeForAll)
        {
             FreeForAll ffa = (FreeForAll)mode;
             ffa.lobbyUpdate();
             ffa.startGame();
        }
    }
}

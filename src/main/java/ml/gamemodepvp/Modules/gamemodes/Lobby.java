package ml.gamemodepvp.Modules.gamemodes;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bfox1 on 6/11/2015.
 * In God We Trust.
 */
public class Lobby {

    private boolean flag;

    private boolean disable = false;

    private Map<UUID, Player> playerMap = new HashMap<UUID, Player>();

    private Map<String, Player> teamMap = new HashMap<String, Player>();

    private Gamemode gamemode;


    public Lobby(boolean flag)
    {
        this.flag = flag;
    }


    public void createLobby()
    {
        if(flag)
        {
            monitorGameLobbyActivity();
        } else
        {
            monitorWaitingLobbyActivity();
        }


    }

    public void startGame()
    {
        if(hasEnoughPlayers())
        {

        }

    }

    private boolean hasEnoughPlayers()
    {
        return gamemode.mode.getMinPlayerCount() > gamemode.getPlayerMap().size();
    }

    private int dividePlayers()
    {
        double playerSize = gamemode.getPlayerMap().size()/2;
        return 0;
    }








    public void monitorGameLobbyActivity() {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run()
            {
                if(disable)
                {
                    cancel();
                }

            }
        };
        runnable.run();
    }

    public void monitorWaitingLobbyActivity()
    {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run()
            {
                if(disable)
                {
                    cancel();
                }

            }
        };

        runnable.run();
    }


    public static Lobby getLobby(LobbyEnumeration enumeration)
    {
        if(enumeration == LobbyEnumeration.GAMELOBBY)
        {
            return new Lobby(true);
        }
        return new Lobby(false);

    }
}

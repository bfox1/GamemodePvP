package ml.gamemodepvp;

import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.world.region.Region;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by bfox1 on 7/13/2015.
 * In God We Trust.
 */
public abstract class AbstractPlayer {

    /**
     * If the player is in a Lobby.
     * @return boolean.
     */
    public abstract boolean isInLobby();

    /**
     * If the player is in a Region
     * @return boolean
     */
    public abstract boolean isInRegion();

    /**
     * Gets the Player from the PlayerWrapper
     * @return Player
     */
    public abstract Player getPlayer();

    /**
     * Sets the Lobby the Player is currently in.
     * @param lobby
     */
    public abstract void setLobby(Lobby lobby);

    /**
     * Sets the Region the Player is currently in.
     * @param region
     */
    public abstract void setRegion(Region region);

    /**
     * Gets the Players UUID from the PlayerWrapper
     * @return UUID
     */
    public abstract UUID getPlayerUUID();

    public abstract void kick(String reason);

    public abstract void ban(String reason);


}

package ml.gamemodepvp;

import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 7/13/2015.
 * In God We Trust.
 */
public class RegionPlayer extends PlayerWrapper {


    private boolean canUse;
    private boolean canBreak;
    private boolean canPlace;
    private boolean canEnter;
    private boolean canLeave;

    /**
     * Intended to be used in sequence with Region Handling.
     * This class will hold all the special data from PlayerWrapper and including
     * Special Region Flags for the Player specifics. For when necessary you can downcast back to PlayerWrapper.
     * RegionPlayer Class will be saved along with the Region Data Files.
     * @param player
     */
    public RegionPlayer(Player player)
    {
        super(player);
        this.canUse = false;
        this.canBreak = false;
        this.canPlace = false;
        this.canEnter = false;
        this.canLeave = false;
    }

    /**
     * Checks to see if Player can Use In the Region.
     * @return boolean
     */
    public boolean isCanUse() {
        return canUse;
    }

    /**
     * Set the canUse Flag for the Player.
     * This Flag will Override Regions Flag Permission for the Player Only.
     * @param canUse
     */
    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    /**
     * Checks to see if the Player can Break in Region.
     * @return boolean
     */
    public boolean isCanBreak() {
        return canBreak;
    }

    /**
     * Set the canBreak Flag for the Player.
     * This Flag will Override Regions Flag Permission for the Player Only.
     * @param canBreak
     */
    public void setCanBreak(boolean canBreak) {
        this.canBreak = canBreak;
    }

    /**
     * Checks to see if the Player can Place in Region.
     * @return boolean
     */
    public boolean isCanPlace() {
        return canPlace;
    }

    /**
     * Set the canPlace Flag for the Player.
     * This Flag will Override Regions Flag Permission for the Player Only.
     * @param canPlace
     */
    public void setCanPlace(boolean canPlace) {
        this.canPlace = canPlace;
    }

    /**
     * Checks to see if the Player can Enter the Region.
     * @return boolean
     */
    public boolean isCanEnter() {
        return canEnter;
    }

    /**
     * Set the canEnter Flag for the Player.
     * This Flag will Override Regions Flag Permission for the Player Only.
     * @param canEnter
     */
    public void setCanEnter(boolean canEnter) {
        this.canEnter = canEnter;
    }

    /**
     * Checks to see if the Player can Leave the Region.
     * @return boolean.
     */
    public boolean isCanLeave() {
        return canLeave;
    }

    /**
     * Set the canLeave Flag for the Player.
     * This Flag will Override Regions Flag Permission for the Player Only.
     * @param canLeave
     */
    public void setCanLeave(boolean canLeave) {
        this.canLeave = canLeave;
    }
}

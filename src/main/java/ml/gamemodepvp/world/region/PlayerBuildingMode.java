package ml.gamemodepvp.world.region;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 5/26/2015.
 * In God We Trust.
 */
public class PlayerBuildingMode
{
    private Player player;
    private Location pos1;
    private Location pos2;
    private boolean buildMode = false;
    public PlayerBuildingMode(Player player)
    {
        this.player = player;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public boolean isBuildMode() {
        return buildMode;
    }

    public void setBuildMode(boolean buildMode) {
        this.buildMode = buildMode;
    }
}

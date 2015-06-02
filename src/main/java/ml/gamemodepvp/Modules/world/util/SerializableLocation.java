package ml.gamemodepvp.Modules.world.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

/**
 * Created by bfox1 on 5/20/2015.
 * In God We Trust.
 */
public class SerializableLocation implements Serializable {

    static final long serialVersionUID = 1337L;

    private int x, y, z;
    private String world;

    public SerializableLocation(Location loc)
    {
        this.x = (int)loc.getX();
        this.y = (int)loc.getY();
        this.z = (int)loc.getZ();
        this.world = loc.getWorld().getName();
    }

    public Location getLocation()
    {
        World w = Bukkit.getWorld(this.world);
        if(w == null) return null;
        Location toRet = new Location(w ,this.x, this.y, this.z);
        return toRet;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public double getZ()
    {
        return this.z;
    }


}

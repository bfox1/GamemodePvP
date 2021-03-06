package ml.gamemodepvp.Modules.world.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by bfox1 on 5/20/2015.
 * In God We Trust.
 */
public class SerializableLocation implements Serializable, ConfigurationSerializable {

    static final long serialVersionUID = 1337L;

    private int x, y, z;

    private float yaw, pitch;
    private String world;

    public SerializableLocation(Location loc)
    {
        this.x = (int)loc.getX();
        this.y = (int)loc.getY();
        this.z = (int)loc.getZ();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
        this.world = loc.getWorld().getName();
    }

    public SerializableLocation(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location getLocation()
    {
        World w = Bukkit.getWorld(this.world);
        if(w == null) return null;
        Location toRet = new Location(w ,this.x, this.y, this.z, this.yaw, this.pitch);
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


    @Override
    public Map<String, Object> serialize() {
        return null;
    }
}

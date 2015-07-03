package ml.gamemodepvp.Modules.gamemodes.region;

import ml.gamemodepvp.Modules.world.handler.RegionHandler;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.Modules.world.util.SerializableLocation;
import ml.gamemodepvp.management.RegionDataManager;
import org.bukkit.Location;

/**
 * Created by bfox1 on 6/21/2015.
 * In God We Trust.
 */
public class LobbyRegion extends Region {

    public final int LOBBYID;
    private SerializableLocation serializableLocation;

    public LobbyRegion(int id, RegionHandler handler)
    {
        super("Lobby." + id, handler);
        this.LOBBYID = id;

    }

    public int getLOBBYID()
    {
        return LOBBYID;
    }


    public static LobbyRegion findLobbyRegion(RegionDataManager manager)
    {
        for(LobbyRegionName name : LobbyRegionName.values())
        {

            if(manager.getRegion(name.getLobbyName()) != null)
            {
                return (LobbyRegion)manager.getRegion(name.getLobbyName());
            }
        }
        throw new NullPointerException("No Lobby found! ");
    }


    public Location getLocation() {
        return new Location(this.getWorld(), serializableLocation.getX(), serializableLocation.getY(), serializableLocation.getZ());
    }

    public void setLocation(Location location) {
        this.serializableLocation = new SerializableLocation((int)location.getX(), (int)location.getY(), (int)location.getZ());
    }
}


/**
 * The Lobby Names for the Region. The Lobby Task will assign a player to a designated region.
 */
 enum LobbyRegionName
{
    LOBBYIDONE("Lobby.0"),
    LOBBYIDTWO("Lobby.1"),
    LOBBYIDTHREE("Lobby.2"),
    LOBBYIDFOUR("Lobby.3"),
    LOBBYIDFIVE("Lobby.4");



    private String lobbyName;

    LobbyRegionName(String name)
    {
        this.lobbyName = name;
    }


    public String getLobbyName() {
        return lobbyName;
    }
}
package ml.gamemodepvp.Modules.world.region;

import ml.gamemodepvp.Modules.core.lib.InternalVersionID;
import ml.gamemodepvp.Modules.world.handler.RegionHandler;
import ml.gamemodepvp.Modules.world.util.SerializableLocation;
import ml.gamemodepvp.PlayerWrapper;
import ml.gamemodepvp.database.regiondata.RegionPlayerProperties;
import ml.gamemodepvp.util.DebugCore;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by bfox1 on 5/10/2015.
 */
public class Region {


    private RegionHandler handler;

    private String regionName;

    private boolean canPlace = true;
    private boolean canUse = true;
    private boolean canLeave = true;
    private boolean canEnter = true;
    private boolean canBreak = true;
    private boolean canPvp = false;

    private final boolean lobbyFlag;

    private SerializableLocation lobbySpawnLocation;

    private final int regionID;


    private HashMap<PlayerWrapper, RegionPlayerProperties> playerProperties = new HashMap<PlayerWrapper, RegionPlayerProperties>();

    /**
     * This is the Constructor to handle the Basic Regions as normal
     * @param regionName set region Name.
     * @param handler set RegionHandler.
     */
    public Region(String regionName, RegionHandler handler) {
        this.handler = handler;
        this.regionName = regionName;
        this.playerProperties = new HashMap<PlayerWrapper, RegionPlayerProperties>();
        this.lobbyFlag = false;
        this.regionID = 0;
    }

    /**
     * This is the Constructor to handle Lobby Region data Only
     * @param index set the Lobby Index.
     * @param handler set RegionHandler.
     */
    public Region(int index, RegionHandler handler)
    {
        if(index == 0) throw new IllegalArgumentException("Lobby Region Cant have 0 as index!");
        this.handler = handler;
        this.regionName = "Lobby-" + index;
        this.lobbyFlag = true;
        this.regionID = index;
        this.canBreak = false;
        this.canLeave = false;
        this.canPlace = false;
        this.canUse = false;
        this.canPvp = false;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public RegionHandler getHandler() {
        return handler;
    }

    public void setHandler(RegionHandler handler) {
        this.handler = handler;
    }

    public Map<PlayerWrapper, RegionPlayerProperties> getPlayerProperties() {
        return playerProperties;
    }

    /**
     * Sets player properties into Map.
     * @param player Set the Player
     * @param region Set the Region.
     */
    @Deprecated
    public void setPlayerProperties(Player player, Region region) {


        RegionPlayerProperties prop = new RegionPlayerProperties(PlayerWrapper.getPlayerWrapper(player), region);
        DebugCore.returnDebugMessage(region.getRegionName() + prop.toString());
        this.playerProperties.put(PlayerWrapper.getPlayerWrapper(player), prop);
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return canUse boolean
     */
    public boolean isCanUse() {
        return canUse;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @param canUse to set the boolean flag
     */
    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return canLeave boolean
     */
    public boolean isCanLeave() {
        return canLeave;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @param canLeave to set the boolean flag
     */
    public void setCanLeave(boolean canLeave) {
        this.canLeave = canLeave;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public boolean isCanBreak() {
        return canBreak;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public void setCanBreak(boolean canBreak) {
        this.canBreak = canBreak;
    }

    public void setCanPvp(boolean b)
    {
        this.canPvp = b;
    }

    public boolean isCanPvp()
    {
        return canPvp;
    }
    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public World getWorld()
    {
        return this.getHandler().getWorld();
    }


    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public boolean isCanEnter() {
        return canEnter;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public void setCanEnter(boolean canEnter) {
        this.canEnter = canEnter;
    }

    public void setCanPlace(boolean canPlace){this.canPlace = canPlace;}

    public boolean isCanPlace()
    {
        return this.canPlace;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public RegionPlayerProperties getPlayerProperties(Player player)
    {
        return playerProperties.get(PlayerWrapper.getPlayerWrapper(player));
    }

    /**
     * This sets flag Regions. If you want to set Player Flags, use getPlayerProperties
     * @return
     */
    public boolean setFlagType(String string, String flagString)
    {
        boolean flag;
        if(flagString.equalsIgnoreCase("true")) flag = true;
        else if(flagString.equalsIgnoreCase("false")) flag = false;
        else return false;

        if(string.equalsIgnoreCase("canuse")) {this.canUse = flag; return true;}
        else if(string.equalsIgnoreCase("canPlace")){setCanPlace(flag); return true;}
        else if(string.equalsIgnoreCase("canBreak")){setCanPlace(flag); return true;}
        else if(string.equalsIgnoreCase("canenter")){this.canEnter = flag; return true;}
        else if(string.equalsIgnoreCase("canleave")){setCanLeave(flag); return true;}

        return false;
    }

    /**
     * Removes Player data from Map.
     * @param player
     */
    @Deprecated
    public void removePlayerFromRegion(Player player)
    {
        if(getPlayerProperties().containsKey(PlayerWrapper.getPlayerWrapper(player)))
            getPlayerProperties().remove(PlayerWrapper.getPlayerWrapper(player));
    }

    /**
     * Set Player Flag for region.
     * @param string
     * @param string1
     * @param player
     * @return
     */
    @Deprecated
    public boolean setPlayerFlag(String string, String string1, Player player) {
        boolean flag;

        if(string1.equalsIgnoreCase("true"))
        {
            flag = true;
        }
        else if(string1.equalsIgnoreCase("false")) {
            flag = false;
        } else return false;

        if(string.equalsIgnoreCase("canbuild")) {getPlayerProperties(player).canBuild_$eq(flag); return true;}
        if(string.equalsIgnoreCase("canenter")) {getPlayerProperties(player).canEnter_$eq(flag); return true;}
        if(string.equalsIgnoreCase("canuse")) {getPlayerProperties(player).canUse_$eq(flag); return true;}
        if(string.equalsIgnoreCase("canleave")) {getPlayerProperties(player).canLeave_$eq(flag); return true;}

        return false;
    }

    @Deprecated
    public boolean getPlayerFlag(String flagName, Player player)
    {

        if(flagName.equalsIgnoreCase("canbuild")) {getPlayerProperties(player).canBuild(); return true;}
        if(flagName.equalsIgnoreCase("canenter")) {getPlayerProperties(player).canEnter(); return true;}
        if(flagName.equalsIgnoreCase("canuse")) {getPlayerProperties(player).canUse(); return true;}
        if(flagName.equalsIgnoreCase("canleave")) {getPlayerProperties(player).canLeave(); return true;}

        return false;
    }

    public boolean isLobbyFlag() {
        return lobbyFlag;
    }

    public int getRegionID() {
        return regionID;
    }

    public SerializableLocation getLobbySpawnLocation() {
        return lobbySpawnLocation;
    }

    public void setLobbySpawnLocation(SerializableLocation lobbySpawnLocation) {
        this.lobbySpawnLocation = lobbySpawnLocation;
    }
}

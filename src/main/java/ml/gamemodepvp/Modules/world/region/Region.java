package ml.gamemodepvp.Modules.world.region;

import ml.gamemodepvp.Modules.core.lib.InternalVersionID;
import ml.gamemodepvp.Modules.world.handler.RegionHandler;
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
public class Region implements Serializable, InternalVersionID{

    static final long serialVersionUID = 5042014L;


    private RegionHandler handler;

    private String regionName;

    private boolean canPlace = true;

    private boolean canUse = true;

    private boolean canLeave = true;

    private boolean canEnter = true;

    private boolean canBreak = true;

    private HashMap<PlayerWrapper, RegionPlayerProperties> playerProperties = new HashMap<PlayerWrapper, RegionPlayerProperties>();

    public Region(String regionName, RegionHandler handler)
    {
        this.handler = handler;
        this.regionName = regionName;
        this.playerProperties = new HashMap<PlayerWrapper, RegionPlayerProperties>();
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
     * @param player
     * @param region
     */
    @Deprecated
    public void setPlayerProperties(Player player, Region region) {


        RegionPlayerProperties prop = new RegionPlayerProperties(PlayerWrapper.getPlayerWrapper(player), region);
        DebugCore.returnDebugMessage(region.getRegionName() + prop.toString());
        this.playerProperties.put(PlayerWrapper.getPlayerWrapper(player), prop);
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public boolean isCanUse() {
        return canUse;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public boolean isCanLeave() {
        return canLeave;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
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

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public World getWorld()
    {
        return this.getHandler().getWorld();
    }

    @Override
    public long getInternalVersionID() {
        return this.INTERNAL_VERSION_ID;
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

    public void setCanPlace(boolean canPlace){this.canBreak = canPlace;}

    public boolean isCanPlace()
    {
        return this.canBreak;
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


}

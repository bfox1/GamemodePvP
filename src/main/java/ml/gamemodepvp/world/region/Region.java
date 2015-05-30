package ml.gamemodepvp.world.region;

import ml.gamemodepvp.world.handler.RegionHandler;
import ml.gamemodepvp.core.util.InternalVersionID;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by bfox1 on 5/10/2015.
 */
public class Region implements Serializable, InternalVersionID{

    static final long serialVersionUID = 5042014L;


    private RegionHandler handler;

    private String regionName;

    private boolean canBuild = true;

    private boolean canUse = true;

    private boolean canLeave = true;

    private boolean canEnter = true;



    private Map<Player, RegionPlayerProperties> playerProperties;





    public Region(String regionName, RegionHandler handler)
    {
        this.handler = handler;
        this.regionName = regionName;
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

    public Map<Player, RegionPlayerProperties> getPlayerProperties() {
        return playerProperties;
    }

    public void setPlayerProperties(Map<Player, RegionPlayerProperties> playerProperties) {

        this.playerProperties = playerProperties;
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
    public boolean isCanBuild() {
        return canBuild;
    }

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
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

    /**
     * This is a Region Flag, To access Player, use getPlayerProperties()
     * @return
     */
    public RegionPlayerProperties getPlayerProperties(Player player)
    {
        return playerProperties.get(player);
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
        else if(string.equalsIgnoreCase("canbuild")){setCanBuild(flag); return true;}
        else if(string.equalsIgnoreCase("canenter")){this.canEnter = flag; return true;}
        else if(string.equalsIgnoreCase("canleave")){setCanLeave(flag); return true;}

        return false;
    }
}

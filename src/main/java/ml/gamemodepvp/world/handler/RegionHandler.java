package ml.gamemodepvp.world.handler;

import ml.gamemodepvp.core.util.DirectionVector;
import ml.gamemodepvp.world.util.SerializableLocation;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import scala.Array;

import java.io.Serializable;

/**
 * Created by bfox1 on 5/10/2015.
 */
public class RegionHandler implements Serializable{

    static final long serialVersionUID = 1337L;

    private transient World world;

    private SerializableLocation position1;

    private SerializableLocation position2;

    private transient boolean isWithinX, isWithinY, isWithinZ;


    public RegionHandler(World world)
    {
        this.world = world;
    }


    public Location getPosition1() {
        return position1.getLocation();
    }

    public void setPosition1(SerializableLocation position1) {
        this.position1 = position1;
    }

    public Location getPosition2() {
        return position2.getLocation();
    }

    public void setPosition2(SerializableLocation position2) {
        this.position2 = position2;
    }

    private Location setCenter()
    {
        Location lc = new Location(this.world, 0, 64, 0);
        return lc;
    }


    public boolean checkBoundary(int xPos, int y, int z)
    {
         this.isWithinX = false;
         this.isWithinY = false;
         this.isWithinZ = false;

        //if Player Coordinate within X Region//
        if(isXPosGreater(xPos)) isWithinX = true;
        else if(isXPosLesser(xPos)) isWithinX = true;

        //If Player Coordinate within Y Region//
        if(isYPosGreater(y)) isWithinY = true;
        else if(isYPosLesser(y)) isWithinY = true;

        //If Player Coordinate within Z Region//
        if(isZPosGreater(z)) isWithinZ = true;
        else if(isZPosLesser(z)) isWithinZ = true;


        return isWithinX && isWithinY && isWithinZ;

    }



    private boolean isXPosGreater(int Xpos)
    {
        if(Xpos >= position1.getX() && Xpos <= position2.getX())
        {
            return true;
        }
        return false;
    }

    private boolean isXPosLesser(int xPos)
    {
        if(xPos <= position1.getX() && xPos >= position2.getX())
        {
            return true;
        }
        return false;
    }
    private boolean isYPosGreater(int yPos)
    {
        if(yPos >= position1.getY() && yPos <= position2.getY()) return true;
        return false;
    }
    private boolean isYPosLesser(int yPos)
    {
        if(yPos <= position1.getY() && yPos >= position2.getY()) return true;
        return false;
    }

    private boolean isZPosGreater(int zPos)
    {
        if(zPos >= position1.getZ() && zPos <= position2.getZ()) return true;
        return false;
    }
    private boolean isZPosLesser(int zPos)
    {
        if(zPos <= position1.getZ() && zPos >= position2.getZ()) return true;
        return false;
    }


    public void removeMapArena()
    {
        this.world.getWorldBorder().reset();
    }

    public void setSmallMap()
    {
        this.world.getWorldBorder().setCenter(setCenter());
        this.world.getWorldBorder().setSize(125);
        this.world.getWorldBorder().setWarningDistance(110);
        this.world.getWorldBorder().setWarningTime(10);
        this.world.getWorldBorder().setDamageAmount(1.5);
    }

    public void setMediumMap()
    {
        this.world.getWorldBorder().setCenter(setCenter());
        this.world.getWorldBorder().setSize(250);
        this.world.getWorldBorder().setWarningDistance(225);
        this.world.getWorldBorder().setDamageAmount(1.5);
    }

    public void setLargeMap()
    {
        this.world.getWorldBorder().setCenter(setCenter());
        this.world.getWorldBorder().setSize(500);
        this.world.getWorldBorder().setWarningDistance(475);
        this.world.getWorldBorder().setDamageAmount(1.5);
    }

    public World getWorld()
    {
        return this.world;
    }

    private boolean isPositive(int x)
    {
        if(x > 0) return true;
        else if(x == 0) return false;
        else return false;
    }




    public void bounceBack(Location getFrom, Location getTo, Player player)
    {
        if(!isWithinX) DirectionVector.getDirectionPath(getFrom, getTo, player, "x");
        else if(!isWithinY) DirectionVector.getDirectionPath(getFrom, getTo, player, "y");
        else if(!isWithinZ) DirectionVector.getDirectionPath(getFrom, getTo, player, "z");
    }

}

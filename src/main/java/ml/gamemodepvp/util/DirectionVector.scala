package ml.gamemodepvp.util

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.util.Vector

/**
 * Created by bfox1 on 5/25/2015.
 * In God We Trust.
 */
object DirectionVector {

  def getDirectionPath(getFrom:Location, getTo:Location, player:Player, string:String):Unit=
  {
    if(string.eq("x"))
    if(getFrom.getX.toInt  < getTo.getX.toInt ) player.setVelocity(new Vector(-0.7, 0.0, 0.0))
    else if(getFrom.getX.toInt > getTo.getX.toInt ) player.setVelocity(new Vector(0.7, 0.0,0.0))

    if(string.eq("y"))
    if(getFrom.getY.toInt  < getTo.getY.toInt )player.setVelocity(new Vector(0.0, -0.7, 0.0))
    else if(getFrom.getY.toInt  > getTo.getY.toInt )  player.setVelocity(new Vector(0.0, 0.7, 0.0))

    if(string.eq("z"))
    if(getFrom.getZ.toInt < getTo.getZ.toInt ) player.setVelocity(new Vector(0.0, 0.0, -0.7))
    else if(getFrom.getZ.toInt > getTo.getZ.toInt ) player.setVelocity(new Vector(0.0, 0.0, 0.7))



  }

}

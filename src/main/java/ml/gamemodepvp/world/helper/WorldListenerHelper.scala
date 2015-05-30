package ml.gamemodepvp.world.helper


import java.util

import ml.gamemodepvp.world.region.{RegionDataManager, RegionPlayerProperties, Region}
import org.bukkit.entity.Player


import scala.collection.JavaConversions._

/**
 * Created by bfox1 on 5/24/2015.
 * In God We Trust.
 */
object WorldListenerHelper {

  def canLeave(region:Region, playerProperties:RegionPlayerProperties) = {if(region.isCanLeave || playerProperties.canLeave) true}

  def canEnter(region:Region, playerProperties:RegionPlayerProperties) ={ if(region.isCanEnter || playerProperties.canEnter) true}

  def canBuild(region:Region, playerProp:RegionPlayerProperties) = {if(region.isCanBuild || playerProp.canBuild) true}

  def canUse(region:Region, playerProp:RegionPlayerProperties) ={if(region.isCanUse || playerProp.canUse) true}


  def isMember(playerProp:RegionPlayerProperties) =
  {
     playerProp.isMember
  }

  def isBuilder(playerProp:RegionPlayerProperties) =
  {
     playerProp.isBuilder
  }

  def isInRegion(manager:RegionDataManager, player:Player): Boolean =
  {

    val jmap:java.util.Map[String, Region] = manager.loadRegionList(player)

    jmap.foreach(jmap =>
    if(manager.checkIfRegion(jmap._2, player.getLocation))return true)
     false
  }

  def getInRegion(manager:RegionDataManager, player:Player): Region =
  {
    val jmap:java.util.Map[String, Region] = manager.loadRegionList(player)

    jmap.foreach(jmap =>
      if(manager.checkIfRegion(jmap._2, player.getLocation))
      {return jmap._2})

    return null
  }






}

package ml.gamemodepvp.util

import ml.gamemodepvp.Modules.world.region.Region
import ml.gamemodepvp.database.regiondata.{RegionDataManager, RegionPlayerProperties}
import org.bukkit.entity.Player

import scala.collection.JavaConversions._

/**
 * Created by bfox1 on 5/24/2015.
 * In God We Trust.
 */
object RegionTestUtility {

  def canLeave(region:Region, playerProperties:RegionPlayerProperties): Boolean =
  {
    if(playerProperties == null)
    {
      return region.isCanLeave
    }

    if(region.isCanLeave || playerProperties.canLeave)
  {
    true
  }
    false
  }

  def canEnter(region:Region, playerProperties:RegionPlayerProperties): Boolean =
  {
    if(region.isCanEnter || playerProperties.canEnter) true
    false
  }

  def canBuild(region:Region, playerProp:RegionPlayerProperties):Boolean = {

      if(playerProp == null)
      {
        return region.isCanLeave
      }

      region.isCanBuild || playerProp.canBuild
  }

  def canUse(region:Region, playerProp:RegionPlayerProperties):Boolean ={
    if (region.isCanUse || playerProp.canUse) true
    false
  }


  def isMember(playerProp:RegionPlayerProperties):Boolean =
  {
     playerProp.isMember
  }

  def isBuilder(playerProp:RegionPlayerProperties):Boolean =
  {
     playerProp.isBuilder
  }

  def isInRegion(manager:RegionDataManager, player:Player): Boolean =
  {

    val jmap:java.util.Map[String, Region] = manager.loadRegionList(player)

    try {
      jmap.foreach(jmap =>
        if (manager.checkIfRegion(jmap._2, player.getLocation)) return true)
    }catch {
      case e: Exception => println("NO Region Located")
    }
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

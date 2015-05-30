package ml.gamemodepvp.world.region

import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * Created by bfox1 on 5/24/2015.
 * In God We Trust.
 */
 class RegionPlayerProperties(player:Player, region:Region)  {
  var isBuilder: Boolean = false


  val nPlayer:Player = player
  val nRegion:Region = region

  var isMember:Boolean = false
  var canLeave: Boolean = false
  var canEnter: Boolean = false

  var canBuild:Boolean = false
  var canUse:Boolean = false



  def becomeMember(): Unit =
  {
    this.isMember = true
    this.canEnter = true
    this.canLeave = true
  }

  def becomeBuilder(): Unit =
  {
    becomeMember()
    this.isBuilder = true
    this.canBuild = true
    this.canUse = true
  }




}

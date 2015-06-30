package ml.gamemodepvp.database.regiondata

import ml.gamemodepvp.Modules.world.region.Region
import ml.gamemodepvp.PlayerWrapper
import org.bukkit.entity.Player

/**
 * Created by bfox1 on 5/24/2015.
 * In God We Trust.
 */
@deprecated
 class RegionPlayerProperties(player: PlayerWrapper, region:Region) extends Serializable {
  var isBuilder: Boolean = false


  val nPlayer: PlayerWrapper = player
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

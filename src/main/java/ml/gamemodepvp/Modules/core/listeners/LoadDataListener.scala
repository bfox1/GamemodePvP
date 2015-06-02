package ml.gamemodepvp.Modules.core.listeners

import ml.gamemodepvp.CoreMain
import ml.gamemodepvp.database.playerdata.{CorePlayerData, PlayerDataHandler}
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Created by bfox1 on 5/23/2015.
 * In God We Trust.
 */
 class LoadDataListener(main:CoreMain) extends org.bukkit.event.Listener
{



  @EventHandler
    def onPlayerJoin(event: PlayerJoinEvent)
  {
    if(event.getPlayer.hasPlayedBefore)
    {
      val handler: PlayerDataHandler = new PlayerDataHandler(new CorePlayerData(event.getPlayer))
      val playerData: CorePlayerData = handler.loadData()
      this.main.addToList(playerData)
    }
    else
    {
      onPlayerJoin(event)
    }
  }
}

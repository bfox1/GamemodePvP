package ml.gamemodepvp.Modules.classes.kit

import io.netty.handler.codec.serialization.ObjectEncoderOutputStream
import ml.gamemodepvp.Modules.classes.kit.InventoryConstructor
import ml.gamemodepvp.database.playerdata.{PlayerDataHandler, CorePlayerData}
import org.bukkit.inventory.Inventory

/**
 * Created by bfox1 on 6/2/2015.
 * In God We Trust.
 */
class KitBase(gui:InventoryConstructor) {


  val kitGui = gui


  def saveKitData(data:CorePlayerData): Unit =
  {
    data.setBase(this.kitGui)
    val outputData = new PlayerDataHandler(data)
    outputData.saveData()

  }



}

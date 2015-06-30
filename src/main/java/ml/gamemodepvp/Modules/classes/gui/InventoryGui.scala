package ml.gamemodepvp.Modules.classes.gui


import java.util

import ml.gamemodepvp.Modules.classes.InventoryConstructor
import ml.gamemodepvp.Modules.classes.kit.KitBuilder
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by bfox1 on 6/3/2015.
 * In God We Trust.
 */
class InventoryGui(inventoryConstructor:InventoryConstructor, player:Player) {




  def openGui(): Unit =
  {
    player.openInventory(inventoryConstructor.getInventory)
  }

  def closeGui(player:Player): Unit =
  {
    player.closeInventory()
  }






}

package ml.gamemodepvp.Modules.classes.gui

import ml.gamemodepvp.Modules.classes.kit.{DisplayStack, InventoryConstructor}
import org.bukkit.entity.Player

/**
 * Created by bfox1 on 6/3/2015.
 * In God We Trust.
 */
class InventoryGui(kitConstructor:InventoryConstructor) {

  val player = kitConstructor.kitPlayer

  val inventory = kitConstructor.inventoryChest

  val displayStackList = kitConstructor.displayStackList

  def openGui(player:Player): Unit =
  {
    player.openInventory(kitConstructor.inventoryChest)
  }

  def closeGui(player:Player): Unit =
  {
    player.closeInventory()
  }


  def setInventoryDisplay(displayStack :DisplayStack): Unit =
  {
    this.inventory.addItem(displayStack.getItemStack)
    this.displayStackList.add(displayStack)
  }



}

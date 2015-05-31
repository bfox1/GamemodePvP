package ml.gamemodepvp.classes.gui

import java.util

import org.bukkit.{Material, Bukkit}
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by bfox1 on 5/30/2015.
 * In God We Trust.
 */
class KitGui(player:Player) {

  var customName = "Custom Classes"
  var inventoryChest = Bukkit.createInventory(null, 9,customName)

  def setCustomName(name:String):Boolean =
  {
    this.customName = name
    true
  }

  def setMainGuiDisplay(): Unit =
  {

  }

  def setItemDisplayer(): Unit =
  {
    this.inventoryChest.set
  }
  def createCustomItem(material:Material, displayName:String, lore:util.List[String]):ItemStack =
  {
    var stack = new ItemStack(material)
    var itemMeta = stack.getItemMeta
    itemMeta.setDisplayName(displayName)
    itemMeta.setLore(lore)
  }
  def createCustomItem(material:Material, displayName:String):ItemStack =
  {

  }

  def setLore(lin)
}

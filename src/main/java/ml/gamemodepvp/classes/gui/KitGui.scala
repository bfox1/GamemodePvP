package ml.gamemodepvp.classes.gui

import java.util

import org.bukkit.{ChatColor, Material, Bukkit}
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

  /**
   * This Opens up the main Gui for All players.
   */
  def setMainGuiDisplay(): Unit =
  {

    setItemDisplayer(0, createCustomItem(Material.COMPASS, ChatColor.DARK_RED +"CreateWeaponClass",
    setLore("Click here to create a new WeaponClass")))
  }

  def setItemDisplayer(slotIndex:Int, stack:ItemStack): Unit =
  {
    this.inventoryChest.setItem(slotIndex, stack)
  }
  def createCustomItem(material:Material, displayName:String, lore:util.List[String]):ItemStack =
  {
    var stack = new ItemStack(material)
    var itemMeta = stack.getItemMeta
    itemMeta.setDisplayName(displayName)
    itemMeta.setLore(lore)
    stack.setItemMeta(itemMeta)
    return stack
  }
  def createCustomItem(material:Material, displayName:String):ItemStack =
  {

  }

  def setLore(isWeaponItem:Boolean,primary:String, secondary:String,tactical:String,grenade:String, perk:String):util.List[String] =
  {

    var list = new util.ArrayList[String]()
    if(isWeaponItem) {
      list.add(ChatColor.DARK_RED + "Primary: " + primary)
      list.add(ChatColor.DARK_RED + "Secondary: " + secondary)
      list.add(ChatColor.DARK_RED + "Tactical: " + tactical)
      list.add(ChatColor.DARK_RED + "Grenade: " + grenade)
      list.add(ChatColor.DARK_RED + "Perk: " + perk)
    }
    else
    {
      list.add(primary)
      list.add(secondary)
      list.add(tactical)
      list.add(grenade)
      list.add(perk)
    }
     list
  }
  def setLore(primary:String):util.List[String] =
  {
    var list = new util.ArrayList[String]()

    list.add(primary)
    list
  }
}

package ml.gamemodepvp.Modules.classes.kit

import java.util

import ml.gamemodepvp.Modules.classes.event.ItemAction
import ml.gamemodepvp.database.playerdata.{PlayerDataHandler, CorePlayerData}
import org.bukkit.entity.Player
import org.bukkit.inventory.{Inventory, ItemStack}
import org.bukkit.{Bukkit, ChatColor, Material}

/**
 * Created by bfox1 on 5/30/2015.
 * In God We Trust.
 */
 class InventoryConstructor(player:Player) {

  def this()
  {
    this(null)
  }



  var customName = "Default Classes"

  var inventoryChest = Bukkit.createInventory(null, 9,customName)

  var displayStackList = new util.ArrayList[DisplayStack]()

  var kitPlayer = player

  def fireItemAction(displayStack:DisplayStack): Unit =
  {

    displayStack.fireAction()

  }
  def fireItemAction(displayStack:DisplayStack, player:Player): Unit =
  {
    displayStack.fireActionToPlayer(player)
  }


  /**
   * Self reasoned.
   * @param size
   */
  def setInventorySize(size:Int): Unit =
  {
    if(size == 9 || size == 27 || size ==  54)
    this.inventoryChest = Bukkit.createInventory(null, size, customName)
    else
    {
      throw new Exception("Size of inventory isnt allowed! only 9, 27, 54")
    }
  }



  /**
   * Returns the Custom Item for Display in a Gui.
   * If display Item isnt in need of a Lore, Use the Second option.
   * @param material
   * @param displayName
   * @param lore
   * @param itemAction The ItemAction Type
   * @param objAction The Action to be Fired with corresponding ActionType. (ex. if itemAction = COMMAND, objAction = gamemode bfox1 1,
   *                                                                              if itemAction = INVENTORY, objAction = kitgui.openGUI())
   * @return
   */
  def createCustomItem(material:Material, displayName:String, lore:util.List[String], itemAction:ItemAction, objAction:Object):DisplayStack =
  {
    val stack = new ItemStack(material)
    val itemMeta = stack.getItemMeta
    itemMeta.setDisplayName(displayName)
    itemMeta.setLore(lore)
    stack.setItemMeta(itemMeta)
     stack
    val displayStack = new DisplayStack(stack)
    displayStack.setAction(itemAction)
    displayStack.setActionPerameters(objAction)
    displayStack
  }
  def createCustomItem(material:Material, displayName:String):ItemStack =
  {
       null
  }

  /**
   * Saves kit data to Player independently. You must have already required to load preexisting player data
   * before useing this method.
   * @param playerData
   * @param ivConstructor
   */

  def saveKitData(playerData:CorePlayerData, ivConstructor:InventoryConstructor): Unit =
  {
    playerData.setInventoryConstructor(ivConstructor)
    val  handler = new PlayerDataHandler(playerData)
    handler.saveData()
  }

  def loadKitData(playerData:CorePlayerData): InventoryConstructor =
  {
    playerData.getInventoryConstructor
  }

  /**
   * For the sake of simplicity, This sets the Lore for Weapon Items. or Any other item that will require
   * 5 lines of Text. For a more simpler lore, use the Single String setLore Method.
   * @param isWeaponItem If Item is is a WeaponItem
   * @param primary Primary Item this display weapon will give
   * @param secondary Secondary Item this display item will give
   * @param tactical Tactical Item this display item will give
   * @param grenade Grenade Item this display item will give
   * @param perk The Perk the player will have access to
   * @return ArrayList
   */
  def setLore(isWeaponItem:Boolean,primary:String, secondary:String,tactical:String,grenade:String, perk:String):util.List[String] =
  {

    val list = new util.ArrayList[String]()
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
    val list = new util.ArrayList[String]()

    list.add(primary)
    list
  }
}

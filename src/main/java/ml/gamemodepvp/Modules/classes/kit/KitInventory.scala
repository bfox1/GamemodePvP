package ml.gamemodepvp.Modules.classes.kit

import java.util

import ml.gamemodepvp.Modules.classes.classadditives.{Perks, WeaponEnchants, WeaponBuilder}
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by bfox1 on 6/3/2015.
 * In God We Trust.
 */
class KitInventory(player:Player) extends InventoryConstructor(player)
{

  var playerKitInventory = Array[KitBuilder]()

  def this()
  {
    this(null)
  }


  /**
   * KitInventory Generates the inventory Gui of player by itemStack. This does NOT open gui. Refer to Inventory gui
   * for accessing chest.
   */


  def createKit(): Unit =
  {

  }

  /**
   * This generator will add Display Item and save the Kit to that Item in the inventory.
   * @param displayStack
   * @param primary
   * @param secondary
   * @param tactical
   * @param lethal
   * @param perks
   * @param stack
   * @return
   */

  def kitGenerator(displayStack:DisplayStack, primary:WeaponBuilder, secondary:WeaponBuilder, tactical:WeaponBuilder, lethal:WeaponBuilder, perks:Perks, stack:Array[ItemStack]): Unit =
  {
    val kitBuilder = new KitBuilder(displayStack,primary, secondary, tactical, lethal, perks, stack)

    this.displayStackList.add(displayStack)
    this.inventoryChest.addItem(displayStack.getItemStack)
    var it = 0
    while (this.playerKitInventory(it) != null)
    {
      it += 1
    }
    this.playerKitInventory(it) = kitBuilder
  }



  def weaponBuilderGenerator(stack:ItemStack, name:String, lore:util.List[_], enchants:Array[WeaponEnchants]): WeaponBuilder =
  {

    val weaponBuilder = new WeaponBuilder(stack, name, lore, false,false)


    if(enchants != null)
    for(x<-enchants)
    {
      weaponBuilder.addWeaponEnchants(x)
    }
    weaponBuilder
  }

  def addEnchants(enchant:WeaponEnchants):  Array[WeaponEnchants] =
  {
    val ls:Array[WeaponEnchants] = new Array(0)
    ls(0) = enchant

    ls
  }
  def addEnchants(enchant:WeaponEnchants, enchant2:WeaponEnchants): Array[WeaponEnchants] =
    {
      val ls:Array[WeaponEnchants] = new Array(1)
      ls(0) = enchant; ls(1) = enchant2

      ls
    }
  def addEnchants(enchant:WeaponEnchants, enchant2:WeaponEnchants, enchant3:WeaponEnchants): Array[WeaponEnchants] =
  {
    val ls:Array[WeaponEnchants] = new Array(2)
    ls(0) = enchant; ls(1) = enchant2; ls(2) = enchant3

    ls
  }

  def applyClass(builder:KitBuilder): Unit =
  {
    for(x <- builder.getKitWeaponList)
    {
      if(x != null)
      player.getInventory.addItem(x.getItemStack)
    }
    if(builder.getOtherAdditives != null)
    for(x <-builder.getOtherAdditives)
    {
      if(x != null)
      player.getInventory.addItem(x)
    }

  }

}

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
  /**
   * KitInventory Generates the inventory Gui of player by itemStack. This does NOT open gui. Refer to Inventory gui
   * for accessing chest.
   */


  def createKit(): Unit =
  {

  }


  def kitGenerator(stack:DisplayStack, primary:WeaponBuilder, secondary:WeaponBuilder, tactical:WeaponBuilder, lethal:WeaponBuilder, perks:Perks): Unit =
  {
    val kitBuilder = new KitBuilder(stack,primary, secondary, tactical, lethal, perks)

    val builder = kitBuilder.getKitWeaponList
    for(x<-builder)
    {
      this.inventoryChest.addItem(x.getItemStack)
    }

  }



  def weaponBuilderGenerator(stack:ItemStack, name:String, lore:util.List[_], enchants:Array[WeaponEnchants]): WeaponBuilder =
  {

    val weaponBuilder = new WeaponBuilder(stack, name, lore, false,false)


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

  def kitGeneratorFromPlayer(): Unit =
  {

  }

}

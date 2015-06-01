package ml.gamemodepvp.classes.datasaver

import java.io.{FileNotFoundException, File}
import java.util

import org.bukkit.{Bukkit, configuration}
import org.bukkit.configuration.file.{FileConfiguration, YamlConfiguration}
import org.bukkit.inventory.{ItemStack, Inventory}



/**
 * Created by cherry-bfox on 5/31/15.
 */
class KitSaver(inventory:Inventory) {

  def this()
  {
    this(Bukkit.createInventory(null, 9))
  }


  val kitInvo = inventory
  def yamlLoad(file:File):YamlConfiguration =
  {
    try
    {
      YamlConfiguration.loadConfiguration(file)
    } catch
      {
        case e: Exception =>
          println(file.getName + " Not found. Creating one for you.")
          val yml = new YamlConfiguration
          yml.save(file)
          yml
      }

  }

  def saveKitData(file:File): Unit =
  {
    val yaml : FileConfiguration = yamlLoad(file)
    val stack = kitInvo.getContents
    yaml.set("Inventory", "Inventory")
    yaml.set("Inventory.Item", "Item")
    yaml.set("Inventory.Item.lore", "Lore")
    yaml.set("Inventory.Item", stack)
    yaml.save(file)

  }

  def loadKitData(file:File): Array[ItemStack] =
  {
    val yaml:FileConfiguration = YamlConfiguration.loadConfiguration(file)

    //println(yaml.getList("Inventory.Item"))
     val ls = yaml.getList("Inventory.Item")
    val itemList:Array[ItemStack] = new Array[ItemStack](ls.size())
    var it = 0
    for(x <- ls)
    {
      val f :ItemStack = x
      if(f != null) {
        itemList(it) = f
        it += 1
      }

    }
    itemList
  }



}

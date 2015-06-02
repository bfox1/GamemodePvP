package ml.gamemodepvp.Modules.classes.datasaver

import java.io.File
import java.util

import org.bukkit.Bukkit
import org.bukkit.configuration.file.{FileConfiguration, YamlConfiguration}
import org.bukkit.inventory.Inventory





/**
 * Created by cherry-bfox on 5/31/15.
 */
class KitSaver(inventory:Inventory) {

  def this() {
    this(Bukkit.createInventory(null, 9))
  }


  val kitInvo = inventory

  def yamlLoad(file: File): YamlConfiguration = {
    try {
      YamlConfiguration.loadConfiguration(file)
    } catch {
      case e: Exception =>
        println(file.getName + " Not found. Creating one for you.")
        val yml = new YamlConfiguration
        yml.save(file)
        yml
    }

  }

  def saveKitData(file: File): Unit = {
    val yaml: FileConfiguration = yamlLoad(file)
    val stack = kitInvo.getContents
    yaml.set("Inventory", "Inventory")
    yaml.set("Inventory.Item", "Item")
    yaml.set("Inventory.Item.lore", "Lore")
    //yaml.set("Iventory", kitInvo)
    for(x <- stack)
    {
      yaml.set("Inventory.Item", x)
      yaml.set("Iventory.Item." + x.toString, x)
    }
    yaml.save(file)

  }


  def loadKitData(file: File): util.List[_] = {
    val yaml: FileConfiguration = YamlConfiguration.loadConfiguration(file)

    //println(yaml.get("Inventory"))
    val ls = yaml.getList("Inventory.Item")


    ls
  }
}

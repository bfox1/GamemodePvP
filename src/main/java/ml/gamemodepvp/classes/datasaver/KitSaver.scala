package ml.gamemodepvp.classes.datasaver

import java.io.{FileNotFoundException, File}

import org.bukkit.configuration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.Inventory



/**
 * Created by cherry-bfox on 5/31/15.
 */
class KitSaver(inventory:Inventory) {

  def yamlLoad(file:File):YamlConfiguration =
  {
    try
    {
      YamlConfiguration.loadConfiguration(file)
    } catch
      {
        case e: Exception =>
          println(file.getName + " Not found. Creating one for you.")
          var yml = new YamlConfiguration
          yml.save(file)
          yml
      }

  }

  def saveKitData(file:File): Unit =
  {
    val yaml = yamlLoad(file)


  }

}

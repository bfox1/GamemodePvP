package ml.gamemodepvp.Modules.classes.datasaver

import java.io.{ByteArrayInputStream, IOException, ByteArrayOutputStream}

import org.bukkit.Bukkit

import org.bukkit.inventory.{ItemStack, Inventory}
import org.bukkit.util.io.{BukkitObjectInputStream, BukkitObjectOutputStream}
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder


/**
 * Created by cherry-bfox on 5/31/15.
 */
class KitSaver(inventory:Inventory) {

  def this() {
    this(Bukkit.createInventory(null, 9))
  }


  val kitInvo = inventory





}


object BukkitSerialization
{
 /* def toBase64(invetory:Inventory):String =
  {
    try
    {
      val outputStream = new ByteArrayOutputStream()
      val dataOutput = new BukkitObjectOutputStream(outputStream)
      dataOutput.writeInt(invetory.getSize)

      for(i<- invetory.getSize)
      {
        dataOutput.writeObject(invetory.getItem(i))
      }
      dataOutput.close()

       Base64Coder.encodeLines(outputStream.toByteArray)

    } catch
      {
        case e:Exception => "ERROR"
      }
  }


  def fromBase64(data:String):Inventory =
  {
    try {
      val inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data))
      val dataInput = new BukkitObjectInputStream(inputStream)
      val inventory = Bukkit.getServer().createInventory(null, dataInput.readInt())

      // Read the serialized inventory
      for (i <- inventory.getSize) {
        case it : ItemStack => dataInput.readObject()
        inventory.setItem(i,  it)
    }
    dataInput.close();
     inventory;
  }
    catch
      {
        case e:ClassNotFoundException => throw new IOException("UNable to decode class", e)
      }
  }*/

}

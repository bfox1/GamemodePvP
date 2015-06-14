package ml.gamemodepvp.util

import org.bukkit.{Bukkit, ChatColor}

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 */
object GamemodePvPMessageUtility {

  def sendMessageToConsole(msg: String, clazz: Object ): Unit = {
    print("[" + clazz.toString + "]" + " " + msg)
  }

  def sendSpecialCodeToConsole(msg: String, clazz: Object ): Unit = {
    val server = Bukkit.getServer
    val console = server.getConsoleSender
    console.sendMessage(ChatColor.DARK_RED + "[" + clazz.toString + "]" + " " + msg)
  }

  def returnErrorMessage(clazz: Object , string: String): String = {

    createPrefix(clazz) + " " + ChatColor.DARK_RED + string
  }

  private def createPrefix(clazz: Object): String = {
    ChatColor.DARK_BLUE + clazz.toString
  }

  def ERRORMESSAGE(msg: String, clazz: Object): Unit = {

    val console = Bukkit.getServer.getConsoleSender
    console.sendMessage(this.createPrefix(clazz) + ChatColor.DARK_RED + "ERROR! " + msg)
  }

}

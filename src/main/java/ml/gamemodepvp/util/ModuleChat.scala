package ml.gamemodepvp.util

import org.bukkit.ChatColor


/**
 * Created by bfox1 on 5/24/2015.
 * In God We Trust.
 */
object ModuleChat {

  import org.bukkit.ChatColor._

  def modulePrefix(module:String, chatColor:ChatColor):String =
  {
      "[" + chatColor + BOLD + module + WHITE + "] - " + chatColor + BOLD
  }

  def corePrefixToPlayer(msg:String) = modulePrefix("CORE", RED) + msg
  def worldPrefixToPlayer(msg:String):String = modulePrefix("WORLD", GOLD) + msg
  def rankPrefixToPlayer(msg:String):String = modulePrefix("RANK", DARK_BLUE) + msg
  def ecoPrefixToPlayer(msg:String):String = modulePrefix("EcoMoney", DARK_GREEN) + msg
  def permissionPrefixToPlayer(msg:String):String = modulePrefix("Permission", DARK_RED) + msg


}

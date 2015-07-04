package ml.gamemodepvp.Modules.classes.gui


import java.util

import ml.gamemodepvp.Modules.classes.GmpvpInventory
import ml.gamemodepvp.Modules.classes.kit.Kit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Created by bfox1 on 6/3/2015.
 * In God We Trust.
 */
 trait InventoryGui {


  /**
   * To open Player Gui from
   * @param player To set the Player
   */
  def openGui(player:Player)

  /**
   * Closes Player Gui.
   * @param player to set the Player
   */
  def closeGui(player:Player)






}

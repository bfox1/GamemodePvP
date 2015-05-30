package ml.gamemodepvp.world;

import ml.gamemodepvp.core.CoreMain;
import ml.gamemodepvp.core.lib.DebugCore;
import ml.gamemodepvp.core.util.DirectionVector;
import ml.gamemodepvp.lib.ModuleChat;
import ml.gamemodepvp.world.helper.WorldListenerHelper;
import ml.gamemodepvp.world.region.PlayerBuildingMode;
import ml.gamemodepvp.world.region.Region;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by bfox1 on 5/7/2015.
 */
public class WorldListener implements Listener  {

    private CoreMain core;
    public WorldListener(CoreMain core)
    {
        this.core = core;
    }


    @EventHandler
    public void onPlayerPosition(PlayerMoveEvent e)
    {

        if(WorldListenerHelper.isInRegion(this.core.getDataManager(), e.getPlayer()))
        {
            Region rg = WorldListenerHelper.getInRegion(this.core.getDataManager(), e.getPlayer());

            if (!rg.isCanLeave() && !e.getPlayer().isOp())
            {

                if (!rg.getHandler().checkBoundary((int)e.getTo().getX(), (int)e.getTo().getY(), (int)e.getTo().getZ()))
                {

                   rg.getHandler().bounceBack(e.getFrom(), e.getTo(), e.getPlayer());
                    e.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer(ChatColor.RED + "" + "Sorry, you are not allowed to leave this region!"));
                    e.setCancelled(true);


                }
            }
        }

    }

    @EventHandler
    public void isPlayerPlacedBlock(BlockPlaceEvent e)
    {
        Player player = e.getPlayer();
    }

    @EventHandler
    public void onWandClick(PlayerInteractEvent e)
    {
        try
        {

            if(this.core.getDataManager().getRegionBuildingMode(e.getPlayer()))
            {
                //System.out.println(e.getClickedBlock());
                if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand")) {this.core.getDataManager().setRegionPosition1(e.getPlayer(), e.getClickedBlock().getLocation()); e.setCancelled(true);}
                else if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand")) {this.core.getDataManager().setRegionPosition2(e.getPlayer(), e.getClickedBlock().getLocation()); e.setCancelled(true);}
            }
        }catch (NullPointerException n)
        {
        }
    }
}

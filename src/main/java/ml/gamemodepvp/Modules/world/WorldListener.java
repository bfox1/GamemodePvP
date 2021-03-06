package ml.gamemodepvp.Modules.world;

import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.LobbyValidate;
import ml.gamemodepvp.util.ModuleChat;
import ml.gamemodepvp.util.RegionTestUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

/**
 * Created by bfox1 on 5/7/2015.
 * In God We Trust.
 */
public class WorldListener implements Listener  {

    private CoreMain core;
    public WorldListener(CoreMain core)
    {
        this.core = core;
    }


    @EventHandler
    public void checkPlayerRegion(PlayerMoveEvent e)
    {

        if(!e.getPlayer().isOp())
        if(RegionTestUtility.isInRegion(this.core.getDataManager(), e.getPlayer()))
        {
            Region rg = RegionTestUtility.getInRegion(this.core.getDataManager(), e.getPlayer());

            if (!RegionTestUtility.canLeave(rg, rg.getPlayerProperties(e.getPlayer())))
            {

                if (!rg.getHandler().checkBoundary((int)e.getTo().getX(), (int)e.getTo().getY(), (int)e.getTo().getZ()))
                {

                   rg.getHandler().bounceBack(e.getFrom(), e.getTo(), e.getPlayer());
                   // e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), e.getFrom().getX(), e.getFrom().getY(), e.getFrom().getZ()));
                    e.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer(ChatColor.RED + "" + "Sorry, you are not allowed to leave this region!"));
                    e.setCancelled(true);


                }
            }

        }


    }

    @EventHandler
    public void playerPlaceBlock(BlockPlaceEvent e)
    {
        Player player = e.getPlayer();
        //region.setPlayerProperties(player, new RegionPlayerProperties(player, region));
        if(!player.isOp())
        {
            Region region = null;
            try {
                region = this.core.getDataManager().getCurrentRegion(player);
            } catch (Exception e1) {

            }
            if (!region.isCanPlace()) {
                try {
                    if (!region.getPlayerProperties(player).canBuild()) {
                        e.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer("Sorry, but you are not allowed to place here!"));

                    } else {
                        e.setCancelled(false);
                    }
                } catch (NullPointerException ignored) {

                }
                e.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer("Sorry, but you are not allowed to build here!"));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerBreakBlock(BlockBreakEvent event)
    {
        Player player = event.getPlayer();

        if(!player.isOp())
        {
            Region region = null;
            try {
                region = this.core.getDataManager().getCurrentRegion(player);
            } catch (Exception e) {

            }

            if(!region.isCanPlace())
            {
                player.sendMessage(ModuleChat.worldPrefixToPlayer("Sorry, but you are not allowed to break here!"));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerPvPEvent(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player)
        {

            Player player = (Player)event.getDamager();

            if(!player.isOp())
            {
                Region region = null;
                try
                {
                    region = core.getDataManager().getCurrentRegion(player);
                } catch(Exception e)
                {
                    //
                }
                if(!region.isCanPvp())
                {
                    player.sendMessage(ModuleChat.worldPrefixToPlayer("Sorry, but you are not allowed to fight here!"));
                }
            }
        }
    }



    @EventHandler
    public void onWandClick(PlayerInteractEvent e)
    {
        try
        {

            if(this.core.getDataManager().getRegionBuildingMode(e.getPlayer()) && e.getPlayer().isOp())
            {
                //System.out.println(e.getClickedBlock());
                if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand"))
                {
                    this.core.getDataManager().setRegionPosition1(e.getPlayer(), e.getClickedBlock().getLocation());
                    e.getPlayer().sendMessage(
                            ModuleChat.worldPrefixToPlayer("Position 1 has been set at "
                                    + e.getClickedBlock().getLocation().toVector().toString()));
                    e.setCancelled(true);
                }
                else if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand"))
                {
                    this.core.getDataManager().setRegionPosition2(e.getPlayer(), e.getClickedBlock().getLocation());
                    e.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer("Position 2 has been set at "
                            + e.getClickedBlock().getLocation().toVector().toString()));

                    e.setCancelled(true);
                }
            }
        }catch (NullPointerException ignored)
        {
        }

    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDropEvent(PlayerDropItemEvent event)
    {
        if(!event.getPlayer().isOp())
        {
            if(!LobbyManager.getPlayerValidation(event.getPlayer(), core.getLobbyManager()).isGameActive())
            {
                event.getPlayer().sendMessage(ModuleChat.worldPrefixToPlayer("Sorry, but you can drop items out of a match!"));
                event.setCancelled(true);

            }
        }
    }

}

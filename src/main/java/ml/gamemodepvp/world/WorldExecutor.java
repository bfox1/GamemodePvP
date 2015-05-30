package ml.gamemodepvp.world;

import ml.gamemodepvp.core.CoreMain;
import ml.gamemodepvp.lib.ModuleChat;
import ml.gamemodepvp.world.handler.RegionHandler;
import ml.gamemodepvp.world.handler.WorldDataHandler;
import ml.gamemodepvp.world.region.Region;
import ml.gamemodepvp.world.region.RegionDataManager;
import ml.gamemodepvp.world.util.SerializableLocation;
import ml.gamemodepvp.world.util.WorldController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bfox1 on 5/7/2015.
 */
public class WorldExecutor implements CommandExecutor {
    private CoreMain core;


    private RegionDataManager manager;

    public WorldExecutor(CoreMain worldCore) {
this.core = worldCore;
        this.manager = this.core.getDataManager();
    }



    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = null;
        if(!isConsole(commandSender))
        {
             player = (Player) commandSender;
        }
        if(command.getName().equalsIgnoreCase("createNewArena"))
        {
            WorldController wConfig = new WorldController();

            WorldDataHandler hd = new WorldDataHandler(wConfig);
            hd.createWorldData(strings[0]);

            hd.saveWorldData();
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You have created a new world arena!"));
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You can now use /teleportToWorld " + strings[0]));
            return true;
        }
        if(command.getName().equalsIgnoreCase("teleporttoworld") && commandSender instanceof Player)
        {

            WorldDataHandler hd = new WorldDataHandler(new WorldController());
            hd.createWorldData(strings[0]);
            player.teleport(Bukkit.getWorld(strings[0]).getSpawnLocation());
            return true;
        }
        if(command.getName().equalsIgnoreCase("deleteworld"))
        {
            Bukkit.unloadWorld(strings[0], true);

            File f = new File(strings[0]);
            f.delete();
            f.deleteOnExit();
            return true;
        }

        if(command.getName().equalsIgnoreCase("setMapSize") && commandSender instanceof Player)
        {
            Region rg = new Region(player.getWorld().getName(), new RegionHandler(player.getWorld()));
            if(strings[0].equalsIgnoreCase("Small"))
            {
                rg.getHandler().setSmallMap();
            }
            else if(strings[0].equalsIgnoreCase("medium"))
            {
                rg.getHandler().setMediumMap();
            }
            else if(strings[0].equalsIgnoreCase("large"))
            {
                rg.getHandler().setLargeMap();
            }
            else
            {
                return false;
            }


            WorldDataHandler hd = new WorldDataHandler(new WorldController());
            hd.generateData(player.getWorld().getName());
            hd.saveWorldData();
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You have created a " + strings[0] + " region for your Arena!"));
            return true;
        }
        if(command.getName().equalsIgnoreCase("removeMapRegion") && commandSender instanceof Player)
        {
            Region rg = new Region(player.getWorld().getName(), new RegionHandler(player.getWorld()));
            rg.getHandler().removeMapArena();
            WorldDataHandler hd = new WorldDataHandler(new WorldController());
            hd.generateData(player.getWorld().getName());
            hd.saveWorldData();
            player.sendMessage(ModuleChat.worldPrefixToPlayer("Removed Arena Region!"));
            return true;
            
        }

        if(command.getName().equalsIgnoreCase("setupRegion") && commandSender instanceof Player)
        {
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You are now in Region Building Mode!"));
            this.core.getDataManager().setRegionBuildingMode(true, player);
            //player.sendMessage(ModuleChat.worldPrefixToPlayer("Use /pos1 and /pos2 to set Positions"));
            ItemStack wand = new ItemStack(Material.STICK);
            ItemMeta meta = wand.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_PURPLE + "wand");
            wand.setItemMeta(meta);
            player.getInventory().addItem(wand);
            return true;
        }
        if(command.getName().equalsIgnoreCase("createRegion")&& commandSender instanceof Player && this.manager.getRegionBuildingMode(player))
        {
            HashMap<String, Region> hm = this.manager.loadRegionList(player);
            Region rg = new Region(strings[0], new RegionHandler(player.getWorld()));

            rg.getHandler().setPosition1(new SerializableLocation(this.manager.getRegionCuboid(player)[0]));
            rg.getHandler().setPosition2(new SerializableLocation(this.manager.getRegionCuboid(player)[1]));
            if(hm == null)
            {
                hm = new HashMap<String, Region>();
            }
            hm.put(strings[0], rg);
            this.manager.addNewWorldRegion(hm, player.getWorld());
            player.sendMessage(ModuleChat.worldPrefixToPlayer("YOU HAVE SUCCESSFULLY CREATED NEW REGION!"));
            this.manager.setRegionBuildingMode(false, player);
            for(int i = 0; i< player.getInventory().getSize(); i++)
            {
                if(player.getInventory().getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand")) player.getInventory().setItem(i, null);
            }
            return true;
        }
        if(command.getName().equalsIgnoreCase("removeRegion"))
        {
            HashMap<String, Region> hm = this.manager.loadRegionList(player);
            if(hm.containsKey(strings[0]))
            {
                player.sendMessage(ModuleChat.worldPrefixToPlayer("Successfully Removed Region!"));
                this.manager.removeWorldRegion(hm.get(strings[0]).getRegionName(), player.getWorld().getName());
                return true;
            }
            else player.sendMessage(ModuleChat.worldPrefixToPlayer("Unable to find Region!"));
        }

        if(command.getName().equalsIgnoreCase("setRegionFlag") && strings.length >= 3)
        {
            HashMap<String, Region> hashMap;
            if(!isConsole(commandSender))
            {
                hashMap = this.manager.loadRegionList(player);
            }
            else
            {
                hashMap = this.manager.loadRegionList(strings[3]);
            }
            if(hashMap.containsKey(strings[0]))
            {
                Region rg = hashMap.get(strings[0]);
                if(rg.setFlagType(strings[1], strings[2]))
                {
                    hashMap.put(rg.getRegionName(), rg);
                    //this.manager.addNewWorldRegion(hashMap, player.getWorld());
                    player.sendMessage(ModuleChat.worldPrefixToPlayer("You have set the " + strings[0] + " flag to " + strings[1] + "!"));
                    return true;
                }
                player.sendMessage(ModuleChat.worldPrefixToPlayer("You must have Inputed the wrong info."));
            }
            return false;
        }

        if(command.getName().equalsIgnoreCase("getRegionList"))
        {
            HashMap<String, Region> hashMap;
            if(!isConsole(commandSender))
            {hashMap = this.manager.loadRegionList(player); player.sendMessage(ModuleChat.worldPrefixToPlayer(player.getWorld().getName()));}
            else if(strings.length == 1) {hashMap = this.manager.loadRegionList(strings[0]);player.sendMessage(ModuleChat.worldPrefixToPlayer(strings[0]));}
            else return false;
            ArrayList<String> ls = new ArrayList<String>();
            for(Map.Entry me : hashMap.entrySet())
            {
                ls.add( (String)me.getKey());
            }
            String[] st = ls.toArray(new String[ls.size()]);


            player.sendMessage(st);
            return true;
        }

        /*if(command.getName().equalsIgnoreCase("setPlayerFlag") && strings.length >= 2)
        {
            HashMap<String, Region> hashMap = new HashMap<String, Region>();
            if(!isConsole(commandSender)) hashMap = this.manager.loadRegionList(player);
            if(hashMap.containsKey(strings[0]))
            {
                if(hashMap.get(strings[0]).setFlagType(strings[0], strings[1]));
            }

        }*/
        player.sendMessage("USAGE: /command <possibility1> <possiblility2> etc.");
        return false;
    }

    private boolean isConsole(CommandSender sender)
    {
        if(sender instanceof Player) return false;

        return true;
    }
}

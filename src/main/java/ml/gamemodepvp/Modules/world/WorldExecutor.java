package ml.gamemodepvp.Modules.world;

import ml.gamemodepvp.Modules.gamemodes.Lobby;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.world.handler.RegionHandler;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.Modules.world.util.SerializableLocation;
import ml.gamemodepvp.Modules.world.util.WorldController;
import ml.gamemodepvp.PlayerWrapper;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.management.RegionDataManager;
import ml.gamemodepvp.database.worlddata.WorldDataHandler;
import ml.gamemodepvp.util.LobbyValidate;
import ml.gamemodepvp.util.ModuleChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.persistence.Lob;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player)commandSender;

        //Creates Arena//
        if(command.getName().equalsIgnoreCase("createNewArena") && player.hasPermission("gamemodepvp.world.createworld"))
        {
            WorldController wConfig = new WorldController();

            WorldDataHandler hd = new WorldDataHandler(wConfig);
            hd.createWorldData(strings[0]);

            player.sendMessage(ModuleChat.worldPrefixToPlayer("You have created a new world arena!"));
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You can now use /teleportToWorld " + strings[0]));
            return true;
        }
        //Special Teleports to World Command//
        if(command.getName().equalsIgnoreCase("teleporttoworld") && commandSender instanceof Player)
        {

            WorldDataHandler hd = new WorldDataHandler(new WorldController());
            hd.createWorldData(strings[0]);
            player.teleport(Bukkit.getWorld(strings[0]).getSpawnLocation());
            return true;
        }
        //Deletes World//
        if(command.getName().equalsIgnoreCase("deleteworld"))
        {
            Bukkit.unloadWorld(strings[0], true);

            File f = new File(strings[0]);
            f.delete();
            f.deleteOnExit();
            return true;
        }

        //Set Arena Map Size
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
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You have created a " + strings[0] + " region for your Arena!"));
            return true;
        }
        //Removes Arena Map Border
        if(command.getName().equalsIgnoreCase("removeMapRegion") && commandSender instanceof Player)
        {
            Region rg = new Region(player.getWorld().getName(), new RegionHandler(player.getWorld()));
            rg.getHandler().removeMapArena();
            player.sendMessage(ModuleChat.worldPrefixToPlayer("Removed Arena Region!"));
            return true;
            
        }
        //Sets up Special Region
        if(command.getName().equalsIgnoreCase("setupRegion") && commandSender instanceof Player)
        {
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You are now in Region Building Mode!"));
            this.core.getDataManager().setRegionBuildingMode(true, player);
            ItemStack wand = new ItemStack(Material.STICK);
            ItemMeta meta = wand.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_PURPLE + "wand");
            wand.setItemMeta(meta);
            player.getInventory().addItem(wand);
            return true;
        }
        //Creates the New Region
        if(command.getName().equalsIgnoreCase("createRegion")&& commandSender instanceof Player && this.manager.getRegionBuildingMode(player) && strings.length == 1)
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
                if(player.getInventory().getItem(i) != null)
                if(player.getInventory().getItem(i).getItemMeta().hasDisplayName() && player.getInventory().getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "wand"))
                {
                    ItemStack stack = player.getInventory().getItem(i);
                    player.getInventory().removeItem(stack);
                }
            }
            return true;
        }
        //Removes Special Region
        if(command.getName().equalsIgnoreCase("removeRegion") && strings.length == 1)
        {
            HashMap<String, Region> hm = this.manager.loadRegionList(player);
            if(hasPermission(player, "gamemodepvp.world.removeregion"))
            if(hm.containsKey(strings[0]))
            {
                Region region = hm.get(strings[0]);
                if(region.isLobbyFlag())
                {

                    core.getLobbyManager().closeLobby(core.getLobbyManager().getLobbyInfo(strings[0]));
                }
                this.manager.removeWorldRegion(hm.get(strings[0]).getRegionName(), player.getWorld().getName());
                return true;
            }
            else player.sendMessage(ModuleChat.worldPrefixToPlayer("Unable to find Region!"));
        }

        //Sets Region Flag

        if(onCommandCreation(command, "setRegionFlag", player, strings.length, 3))
        {
            HashMap<String, Region> hashMap = this.core.getDataManager().loadRegionList(player);
            if(hasPermission(player, "gamemodepvp.world.setregionflag"))
            if(hashMap.containsKey(strings[0]))
            {
                Region rg = hashMap.get(strings[0]);
                if(rg.setFlagType(strings[1], strings[2]))
                {
                    hashMap.put(rg.getRegionName(), rg);
                    //this.manager.addNewWorldRegion(hashMap, player.getWorld());
                    player.sendMessage(ModuleChat.worldPrefixToPlayer("You have set the " + strings[0] + " flag to " + strings[2] + "!"));
                    return true;
                }
                player.sendMessage(ModuleChat.worldPrefixToPlayer("You must have Inputed the wrong info."));
            }
            return false;
        }

        //Returns list of Regions for map
        if(command.getName().equalsIgnoreCase("getRegionList"))
        {
            HashMap<String, Region> hashMap;

            if(!isConsole(commandSender))
            {hashMap = this.manager.loadRegionList(player); player.sendMessage(ModuleChat.worldPrefixToPlayer(player.getWorld().getName()));}
            else if(strings.length == 1) {hashMap = this.manager.loadRegionList(strings[0]);player.sendMessage(ModuleChat.worldPrefixToPlayer(strings[0]));}
            else return false;

            ArrayList<String> ls = new ArrayList<String>();
            if(hashMap.entrySet() != null)
            for(Map.Entry me : hashMap.entrySet())
            {
                ls.add( (String)me.getKey());
            }
            String[] st = ls.toArray(new String[ls.size()]);


            player.sendMessage(st);
            return true;
        }

        //Sets playerFlags
        if(command.getName().equalsIgnoreCase("setPlayerFlag") && strings.length == 3)
        {
            HashMap<String, Region> hashMap = this.core.getDataManager().loadRegionList(player);
            if(hashMap.containsKey(strings[0]))
            {
            }
            player.sendMessage(ModuleChat.worldPrefixToPlayer(ChatColor.RED + "No region Found"));
            return false;

        }

        player.sendMessage("USAGE: /command <possibility1> <possiblility2> etc.");
        return false;
    }

    //NON-Related command methods//

    private boolean isConsole(CommandSender sender)
    {
        return !(sender instanceof Player);

    }

    private boolean onCommandCreation(Command command,String commandName,Player player)
    {
        return onCommandCreation(command, commandName,player,0, 0);
    }

    private boolean onCommandCreation(Command command, String commandName, Player player, int arrayAmount, int stringArgs)
    {
        if(stringArgs == 0) {
             return command.getName().equalsIgnoreCase(commandName);
        }
        else return command.getName().equalsIgnoreCase(commandName) && arrayAmount == stringArgs;
    }


    private boolean hasPermission(Player player, String permissionName)
    {
        if(!player.hasPermission(permissionName))
        {
            player.sendMessage(ModuleChat.permissionPrefixToPlayer("Sorry but you do not have permission to use this command."));
            return false;
        }
        return true;
    }
}

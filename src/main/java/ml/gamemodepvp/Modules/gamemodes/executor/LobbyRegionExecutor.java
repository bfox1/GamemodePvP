package ml.gamemodepvp.Modules.gamemodes.executor;

import ml.gamemodepvp.CommandList;
import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.gamemodes.region.LobbyRegion;
import ml.gamemodepvp.Modules.world.handler.RegionHandler;
import ml.gamemodepvp.Modules.world.region.Region;
import ml.gamemodepvp.Modules.world.util.SerializableLocation;
import ml.gamemodepvp.util.ModuleChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by bfox1 on 6/21/2015.
 * In God We Trust.
 */
public class LobbyRegionExecutor implements CommandExecutor {

    private CoreMain main;

    public LobbyRegionExecutor(CoreMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        Player player = (Player)commandSender;

        //Creates special Lobby Zone//

        if(command.getName().equalsIgnoreCase(CommandList.LobbyCommands.CREATELOBBYZONE) && player != null )
        {
            int index = 1;
            HashMap<String, Region> hm = this.main.getDataManager().loadRegionList(player);
            while(this.main.getDataManager().getRegion("Lobby-" + index) != null)
            {
                index++;
            }
            if(index >=5)
            {
                player.sendMessage(ModuleChat.worldPrefixToPlayer("Cannot create lobby! Only limited to 5!"));
                return false;
            }
            Region rg = new Region(index, new RegionHandler(player.getWorld()));


            rg.getHandler().setPosition1(new SerializableLocation(this.main.getDataManager().getRegionCuboid(player)[0]));
            rg.getHandler().setPosition2(new SerializableLocation(this.main.getDataManager().getRegionCuboid(player)[1]));

            if(hm == null)
            {
                hm = new HashMap<String, Region>();
            }
            hm.put(rg.getRegionName(), rg);
            this.main.getDataManager().addNewWorldRegion(hm, player.getWorld());
            player.sendMessage(ModuleChat.worldPrefixToPlayer("You have create a new Lobby Region"));

            this.main.getDataManager().setRegionBuildingMode(false, player);
            rg.setLobbySpawnLocation(new SerializableLocation(player.getLocation()));
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


        return false;
    }
}

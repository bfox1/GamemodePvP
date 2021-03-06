package ml.gamemodepvp.Modules.core.listeners;


import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.core.lib.TitleLib;
import ml.gamemodepvp.Modules.ranks.Rank;
import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.playerdata.PlayerDataHandler;
import ml.gamemodepvp.management.LobbyManager;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.LobbyValidate;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;


public class CoreSubListener implements Listener {

    private PlayerDataHandler playerDataHandler;
    private CorePlayerData corePlayerData;

    private CoreMain main;

    public CoreSubListener(CoreMain main)
    {
        this.main = main;

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void getPlayerJoin(PlayerJoinEvent event)
    {
        displayTitle(event.getPlayer(), event);
        corePlayerData = new PlayerDataHandler(new CorePlayerData(event.getPlayer())).loadData();
        try {
            corePlayerData.getEmailAddress().equals(null);
        }catch (NullPointerException e)
        {
            createEssentials(event);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "Please remember to setup your Player info by /register <Email> <password> \n " +
                    "for compatability throughout our GamemodePVP Network! Plus be able to \n" +
                    "access our forums!");
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        LobbyValidate validate = LobbyManager.getPlayerValidation(event.getPlayer(), this.main.getLobbyManager());

        if(validate.isInLobby())
        {
            validate.getLobby().leaveLobby(event.getPlayer());
        }
    }


public void createEssentials(PlayerJoinEvent event)
{
    corePlayerData = new CorePlayerData(event.getPlayer());
    corePlayerData.setMoney(1000);
    corePlayerData.setIpAddress(event.getPlayer().getAddress().getAddress().getHostAddress());
    corePlayerData.setRank(new Rank(event.getPlayer().getUniqueId()));

    playerDataHandler = new PlayerDataHandler(this.corePlayerData);
    playerDataHandler.saveData();
}

    public void setPlayerRank(Player player)
    {
        corePlayerData = new PlayerDataHandler(new CorePlayerData(player)).loadData();
        PermissionUser user = PermissionsEx.getUser(player);
        user.addGroup(corePlayerData.getRank().getGroupName());
    }

    public void displayTitle(Player player, PlayerJoinEvent event)
    {
        TitleLib title = new TitleLib();

        if(player.hasPlayedBefore())
        {
            int players = Bukkit.getServer().getOnlinePlayers().size();
            title.TitleCreate(player, PacketPlayOutTitle.EnumTitleAction.TITLE, PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                    "Welcome back!", "There are " + players + " Online.", 10, 60, 10, ChatColor.AQUA, ChatColor.DARK_AQUA);
            corePlayerData = new CorePlayerData(player);

            try
            {
                corePlayerData.getRank();
            }
            catch(NullPointerException e)
            {
                DebugCore.returnDebugMessage(player.getName() + "'s Has outdated PlayerData. Updating now!");
                corePlayerData.setRank(new Rank(player.getUniqueId()));
                playerDataHandler = new PlayerDataHandler(corePlayerData);
                playerDataHandler.saveData();
                DebugCore.returnDebugMessage(player.getName() + "'s PlayerData has been successfully updated!");
            }
        } else {
            title.TitleCreate(player, PacketPlayOutTitle.EnumTitleAction.TITLE, PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                    "Welcome to GameModePVP ", "Hope you enjoy your stay.", 10, 60, 10, ChatColor.AQUA, ChatColor.DARK_AQUA);
            createEssentials(event);


        }
    }
}

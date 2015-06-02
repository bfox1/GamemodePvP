package ml.gamemodepvp.Modules.core;


import ml.gamemodepvp.CoreMain;
import ml.gamemodepvp.Modules.ranks.Rank;
import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class CoreExecutor implements CommandExecutor {

    private CoreMain main;

    public CoreExecutor(CoreMain main)
    {
        this.main = main;
    }
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gamemodepvp")) {

        }


        if(cmd.getName().equalsIgnoreCase("savedata") && s instanceof Player)
        {
            Player player = (Player)s;

            CorePlayerData data = new CorePlayerData(player);
            data.setMoney(1337);
            data.setEmailAddress("testData@tester.com");
            data.setPassword("passwordTest");
            data.setSSN(1525);
            data.setRank(new Rank(player.getUniqueId()));

            PlayerDataHandler handler = new PlayerDataHandler(data);
            handler.saveData();
            player.sendMessage("Player data Saved!");

            return true;
        }

        if(cmd.getName().equalsIgnoreCase("loaddata") && s instanceof Player)
        {
            Player player = (Player)s;

            CorePlayerData data = new CorePlayerData(player);


            PlayerDataHandler handler = new PlayerDataHandler(data);
            data = handler.loadData();

           s.sendMessage(data.getMoney() + "\n" + data.getEmailAddress() + "\n" + data.getId() + "\n" + data.getPassword());
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("register") && s instanceof Player)
        {
            Player player = (Player)s;
            if(isWithinBounds(args)) {
                CorePlayerData data = new PlayerDataHandler(new CorePlayerData(player)).loadData();
                data.setEmailAddress(args[1]);
                data.setPassword(args[2]);
                data.setHasSetup();
                PlayerDataHandler handler = new PlayerDataHandler(data);
                handler.saveData();
                player.sendMessage("Thank you for registering!");
            }
            else
            {
                player.sendMessage("Something went wrong? try /register <Email> <Password>");
            }


        }

        if(cmd.getName().equalsIgnoreCase("setPlayerRank"))
        {

                String playerName = args[0];
                String groupName = args[1];

                Player player = Bukkit.getPlayer(playerName);
                CorePlayerData data = new PlayerDataHandler(new CorePlayerData(player)).loadData();

                data.getRank().setGroupName(groupName);
                PermissionUser user = PermissionsEx.getUser(player);
                user.addGroup(data.getRank().getGroupName());
                PlayerDataHandler handler = new PlayerDataHandler(data);
                handler.saveData();
                if(groupName.equalsIgnoreCase("admin"))
                {
                    user.setPrefix("",null);
                }
        }

        if(cmd.getName().equalsIgnoreCase("removePlayerRank"))
        {
            String playerName = args[0];

            Player player = Bukkit.getPlayer(playerName);
            CorePlayerData data = new PlayerDataHandler(new CorePlayerData(player)).loadData();

            data.getRank().setGroupName("default");
            PermissionUser user = PermissionsEx.getUser(player);
            user.removeGroup(data.getRank().getGroupName());
            PlayerDataHandler handler = new PlayerDataHandler(data);
            handler.saveData();
        }



        return false;
    }



        public boolean isWithinBounds(String[] array) {

        boolean isBounds = (2 >= 0) && (2 < array.length);
        return isBounds;

    }

}

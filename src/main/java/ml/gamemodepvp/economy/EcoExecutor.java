package ml.gamemodepvp.economy;

import ml.gamemodepvp.core.CoreMain;
import ml.gamemodepvp.core.lib.CorePlayerData;
import ml.gamemodepvp.core.lib.DebugCore;
import ml.gamemodepvp.core.lib.PlayerDataHandler;
import ml.gamemodepvp.economy.handler.MoneyHandler;
import ml.gamemodepvp.economy.util.EcoChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 4/29/2015.
 */
public class EcoExecutor implements CommandExecutor {

    private CorePlayerData data = null;
    private CorePlayerData data2 = null;

    private PlayerDataHandler dataHandler = null;
    private PlayerDataHandler dataHandler2 = null;

    private CoreMain main;

    public EcoExecutor(CoreMain main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if (command.getName().equalsIgnoreCase("Economy")) {

        }
        if (commandSender instanceof Player) {
            if (s.equalsIgnoreCase("ecomoney")) {
                Player p = (Player) commandSender;

                if (strings.length == 0) {
                    dataHandler = new PlayerDataHandler(new CorePlayerData(p));
                    this.data = dataHandler.loadData();

                    p.sendMessage(EcoChatUtil.returnMoney(this.data.getMoney()));
                } else if (!isPlayerOnline(strings[0]) && strings.length == 1) {
                    boolean isFound = false;
                    OfflinePlayer[] player = Bukkit.getOfflinePlayers();
                    for (OfflinePlayer offPlayer : player) {
                        if (offPlayer.getName().equalsIgnoreCase(strings[0])) {

                            dataHandler = new PlayerDataHandler(new CorePlayerData(offPlayer));

                            this.data = dataHandler.loadData();

                            p.sendMessage(EcoChatUtil.returnElseMoney(this.data.getMoney()));
                            isFound = true;
                            break;
                        }
                    }
                    if (!isFound) {
                        p.sendMessage(EcoChatUtil.returnError(strings[0]));
                    }


                } else if (strings[0].equalsIgnoreCase("pay")) {
                    if (!isWithinBounds(strings)) {
                        p.sendMessage(EcoChatUtil.returnFatalError(p));

                    } else {
                        if (strings[1] == null || !isRealPlayer(strings[1]) || !isParsable(strings[2]) || strings[1] == p.getDisplayName()) {

                            p.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR! Please read Usage of command! /<Command> <Pay> <Player> <amount>");
                        } else {
                            if (!isPlayerOnline(strings[1]))
                            {
                                OfflinePlayer offplayer = null;

                                OfflinePlayer[] player = Bukkit.getOfflinePlayers();
                                for (OfflinePlayer players : player) {
                                    if (players.getName().equalsIgnoreCase(strings[1])) ;
                                    offplayer = players;
                                }
                                int amount = Integer.parseInt(strings[2]);
                                DebugCore.returnDebugMessage("I made it this far!");

                                MoneyHandler handler = new MoneyHandler(new PlayerDataHandler(new CorePlayerData(p)), new PlayerDataHandler(new CorePlayerData(offplayer)));
                                handler.loadMoney();

                                handler.payPlayer(p, offplayer, amount);


                            } else {
                                int amount = Integer.parseInt(strings[2]);
                                Player playerB = Bukkit.getPlayer(strings[1]);
                                MoneyHandler handler = new MoneyHandler(new PlayerDataHandler(new CorePlayerData(p)), new PlayerDataHandler(new CorePlayerData(playerB)));
                                handler.loadMoney();
                                handler.payPlayer(p, playerB, amount);


                            }
                        }
                    }
                } else if (isPlayerOnline(strings[0])) {

                    Player nP = Bukkit.getServer().getPlayer(strings[0]);
                    MoneyHandler handler = new MoneyHandler(new PlayerDataHandler(new CorePlayerData(nP)));
                    handler.loadMoney();
                    p.sendMessage(EcoChatUtil.returnElseMoney(handler.getMoney()));
                }


            }
            return true;
        } else {
            return false;
        }
    }


    public boolean isParsable(String input) {
        boolean parsable = true;

        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            parsable = false;
        }
        return parsable;
    }

    public boolean isPlayerOnline(String string) {
        boolean isOnline = true;
        if (Bukkit.getServer().getPlayer(string) == null) isOnline = false;
        return isOnline;
    }

    public boolean isRealPlayer(String name) {
        boolean isReal = false;
        if (!isPlayerOnline(name)) {

            OfflinePlayer[] player = Bukkit.getOfflinePlayers();
            for (OfflinePlayer playyers : player) {
                System.out.println(playyers.getName());
                if (playyers.getName().equalsIgnoreCase(name)) {
                    DebugCore.returnDebugMessage(playyers.getName());
                    isReal = true;
                    break;
                }
            }
        } else {
            isReal = true;
        }


        System.out.println(name + isReal);
        return isReal;
    }


    public boolean isWithinBounds(String[] array) {

        boolean isBounds = (2 >= 0) && (2 < array.length);
        return isBounds;

    }
}


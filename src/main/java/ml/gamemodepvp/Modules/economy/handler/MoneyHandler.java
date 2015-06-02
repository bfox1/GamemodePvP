package ml.gamemodepvp.Modules.economy.handler;

import ml.gamemodepvp.database.playerdata.CorePlayerData;
import ml.gamemodepvp.database.playerdata.PlayerDataHandler;
import ml.gamemodepvp.util.DebugCore;
import ml.gamemodepvp.util.EcoChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 4/29/2015.
 */
public class MoneyHandler {


    private int playerMoneyA;
    private int playerMOneyB;

    private PlayerDataHandler dataHandler;
    private CorePlayerData playerData;

    private PlayerDataHandler dataHandler2;
    private CorePlayerData playerData2;

    private boolean isHandlingTwo = false;



    public MoneyHandler(PlayerDataHandler handler)
    {
        this.dataHandler = handler;
        this.playerData = this.dataHandler.loadData();

    }


    public MoneyHandler(PlayerDataHandler handler, PlayerDataHandler handler2)
    {
        this.dataHandler = handler;
        this.dataHandler2 = handler2;

        this.playerData = this.dataHandler.loadData();
        this.playerData2 = this.dataHandler2.loadData();
        this.isHandlingTwo = true;
    }

    public void saveMoney()
    {
        if(this.isHandlingTwo)
        {
            this.playerData.setMoney(this.playerMoneyA);
            this.playerData2.setMoney(this.playerMOneyB);

            DebugCore.returnDebugMessage(String.valueOf(this.playerMoneyA));

            PlayerDataHandler handler = new PlayerDataHandler(this.playerData);
            PlayerDataHandler handler1 = new PlayerDataHandler(this.playerData2);

            handler.saveData();
            handler1.saveData();
        }
        else
        {
            this.playerData.setMoney(this.playerMoneyA);
            PlayerDataHandler handler = new PlayerDataHandler(this.playerData);
            handler.saveData();
        }
    }

    public void loadMoney()
    {
        if(this.isHandlingTwo)
        {
            this.playerMoneyA = this.playerData.getMoney();
            this.playerMOneyB = this.playerData2.getMoney();
        } else
        {
            this.playerMoneyA = this.playerData.getMoney();
        }
    }

//For PlayerDataA ONLY!!! for paired go below!//

    public void setMoney(int amount)
    {
        this.playerMoneyA = amount;
    }

    public void addMoney(int amount)
    {
        this.playerMoneyA = this.playerMoneyA + amount;
    }

    public void removeMoney(int amount)
    {
        this.playerMoneyA = playerMoneyA - amount;
    }

    public int getMoney()
    {
        return this.playerMoneyA;
    }


    public void payPlayer(Player player, Player playerB, int amount)
    {

        if(hasAcceptableAmount(player, amount))
        {
            this.playerMoneyA = playerMoneyA - amount;
            this.playerMOneyB = playerMOneyB + amount;
            player.sendMessage(EcoChatUtil.removeMoney(amount));
            playerB.sendMessage(EcoChatUtil.addedMoney(amount));
            DebugCore.returnDebugMessage(String.valueOf(this.playerMoneyA));
            saveMoney();
        }
    }

    public void payPlayer(Player player, OfflinePlayer playerB, int amount)
    {

        if(hasAcceptableAmount(player, amount))
        {
            this.playerMoneyA = playerMoneyA - amount;
            this.playerMOneyB = playerMOneyB + amount;
            player.sendMessage(EcoChatUtil.removeMoney(amount));
            DebugCore.returnDebugMessage(String.valueOf(this.playerMoneyA));
            saveMoney();
        }
    }

    public boolean hasAcceptableAmount(Player playerA, int amount)
    {
        if (this.playerMoneyA < amount) {
            playerA.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR! You do not have " + amount + "!");
            return false;
        }
        return true;
    }





}

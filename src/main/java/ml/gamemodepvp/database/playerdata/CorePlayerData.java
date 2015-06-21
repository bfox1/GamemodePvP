package ml.gamemodepvp.database.playerdata;


import ml.gamemodepvp.Modules.ranks.Rank;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by bfox1 on 5/4/2015.
 */
public class CorePlayerData implements Serializable{

    private transient Player player;
    private transient OfflinePlayer offlinePlayer;

    private int money;

    private Rank rank;



    private UUID id;

    private boolean hasSetup = false;

    private String ipAddress;
    private String emailAddress;
    private String password;

    private transient int SSN;


    public CorePlayerData(Player player)
    {
        this.player = player;
        this.id = player.getUniqueId();
        this.hasSetup = false;
    }

    public CorePlayerData(OfflinePlayer player)
    {
        this.offlinePlayer = player;
        this.id = player.getUniqueId();
        this.hasSetup = false;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }


    public boolean isHasSetup() {
        return this.hasSetup;
    }

    public void setHasSetup()
    {
        this.hasSetup = true;
    }

    public Rank getRank()
    {
        return this.rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }


}

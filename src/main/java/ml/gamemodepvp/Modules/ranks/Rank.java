package ml.gamemodepvp.Modules.ranks;

import ml.gamemodepvp.Modules.ranks.handler.ExperienceHandler;
import ml.gamemodepvp.Modules.ranks.handler.LevelHandler;
import org.bukkit.ChatColor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class Rank implements Serializable{

    private LevelHandler level;

    private int prestige;

    private ExperienceHandler exp;

    private String groupName;

    private ChatColor color;

    private UUID uuid;


    public Rank(UUID uuid)
    {
        this.uuid = uuid;
        this.exp = new ExperienceHandler(0);
        this.level = new LevelHandler(1, this.exp);
        this.groupName = "default";

    }



    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LevelHandler getLevel() {
        return level;
    }

    public void setLevel(LevelHandler level) {
        this.level = level;
    }

    public ExperienceHandler getExp() {
        return exp;
    }

    public void setExp(ExperienceHandler exp) {
        this.exp = exp;
    }
}

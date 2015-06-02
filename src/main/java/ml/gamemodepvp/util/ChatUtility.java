package ml.gamemodepvp.util;

import org.bukkit.ChatColor;

import static org.bukkit.ChatColor.*;


/**
 * Created by bfox1 on 5/10/2015.
 */
public enum ChatUtility {

    rank("Rank", WHITE, WHITE, DARK_RED),
    adminRank("Admin", DARK_AQUA, DARK_RED, DARK_RED),
    modRank("Mod", GOLD, DARK_PURPLE, DARK_GREEN),
    devMin("DevMin", DARK_PURPLE, DARK_RED, DARK_PURPLE),
    defaultRank("recruit", YELLOW, WHITE, WHITE);





    private String rankName;
    private ChatColor nameColor;
    private ChatColor msgColor;
    private String custom = "custom";

     ChatUtility(String rankName, ChatColor nameColor, ChatColor msgColor, ChatColor prefixColor)
    {
        this.rankName = "[" + prefixColor + rankName + WHITE + "]";
        this.nameColor = nameColor;
        this.msgColor = msgColor;
    }

    public String getRankName() {
        String name = this.rankName;
        if(!custom.equalsIgnoreCase(custom))
        {
            name = "[" + custom + WHITE + "]";
        }
        return name;
    }


    public ChatColor getNameColor() {
        return nameColor;
    }

    public void setNameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public ChatColor getMsgColor() {
        return msgColor;
    }

    public void setMsgColor(ChatColor msgColor) {
        this.msgColor = msgColor;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}

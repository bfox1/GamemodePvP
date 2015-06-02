package ml.gamemodepvp.Modules.core.lib;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by bfox1 on 4/27/2015.
 */
public class TitleLib {

    public TitleLib()
    {

    }

    /**
     * To apply title to player
     * @param player The player receiving title.
     * @param titleType EX. EnumTitleAction TITLE or SUBTITLE
     * @param text Text you want to be included
     * @param fadeInTime How many seconds till appears
     * @param showTime How long till Out
     * @param fadeOutTime How many secnods till disappears
     * @param color The color of the Chat.
     */
    public void TitleCreate(Player player, PacketPlayOutTitle.EnumTitleAction titleType, String text, int fadeInTime, int showTime, int fadeOutTime, ChatColor color)
    {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\",color:" + color.name().toLowerCase() + "}");

        if(titleType != PacketPlayOutTitle.EnumTitleAction.TITLE && titleType != PacketPlayOutTitle.EnumTitleAction.SUBTITLE) return;
        PacketPlayOutTitle title = new PacketPlayOutTitle(titleType, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInTime, showTime ,fadeOutTime);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }

    public void TitleCreate(Player player, PacketPlayOutTitle.EnumTitleAction title,PacketPlayOutTitle.EnumTitleAction subTitle,
                    String titleTex, String subTitleText, int fadeInTime, int showTime, int fadeOutTime, ChatColor colorTitle, ChatColor colorSubTitle)
    {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + titleTex + "\",color:" + colorTitle.name().toLowerCase() + "}");
        IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitleText + "\",color:" + colorSubTitle.name().toLowerCase() + "}");

        if(title != PacketPlayOutTitle.EnumTitleAction.TITLE) return;
        if(subTitle != PacketPlayOutTitle.EnumTitleAction.SUBTITLE) return;
        PacketPlayOutTitle disPplayTitle = new PacketPlayOutTitle(title, chatTitle);
        PacketPlayOutTitle displaySubTitle = new PacketPlayOutTitle(subTitle, chatSubTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInTime, showTime ,fadeOutTime);

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(disPplayTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(displaySubTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }
}

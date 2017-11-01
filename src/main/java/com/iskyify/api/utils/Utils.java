package com.iskyify.api.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.iskyify.core.iCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {


    public static void connect(Player player, String server) {
        player.sendMessage(ChatColor.GOLD + "Sending you to " + server);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(iCore.getInstance(), "BungeeCord", out.toByteArray());
    }
}

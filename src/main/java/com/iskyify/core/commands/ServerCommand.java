package com.iskyify.core.commands;

import com.iskyify.api.user.IUser;
import com.iskyify.api.utils.Utils;

public class ServerCommand extends CommandBase {

    public ServerCommand() {
        super("server");
    }

    public boolean execute(IUser user, String label, String[] args) {
        if (args.length <= 0) {
            user.sendMessage("&cWrong usage:");
            user.sendMessage("&c/server <server>");
        } else if (args.length == 1) {
            Utils.connect(user.getPlayer(), args[0]);
        }
        return true;
    }
}
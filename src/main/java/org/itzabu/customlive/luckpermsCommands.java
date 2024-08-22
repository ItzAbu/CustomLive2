package org.itzabu.customlive;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;

public class luckpermsCommands {

    static LuckPerms luckPerms = CustomLive.getLuckPerms();


    public static boolean hasPermission(User user, String permission) {
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    public static boolean haveGroup(User user) {
        boolean haveGroup = false;
        for (Node node : user.data().toCollection()) {
            if (node.getKey().equals("group.liveon")) {
                haveGroup = true;
            }
        }
        return haveGroup;
    }

    public static void addGroup(User user) {
        user.data().add(Node.builder("group.liveon").build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static void removeGoup(User user) {
        user.data().remove(Node.builder("group.liveon").build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static void removeEveryone() {
        for (User user : luckPerms.getUserManager().getLoadedUsers()) {
            if (haveGroup(user)) {
                removeGoup(user);
            }
        }
    }

}

package org.itzabu.customlive;

import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.itzabu.customlive.luckpermsCommands.luckPerms;

public class CommandLiveListerner implements Listener {

    @EventHandler
    public void onServerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        User player = luckPerms.getUserManager().getUser(p.getUniqueId());
        if(player == null) return;
        if (luckpermsCommands.haveGroup(player)) {
            luckpermsCommands.removeGoup(player);
        }
    }

}

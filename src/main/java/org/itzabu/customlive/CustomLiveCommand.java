package org.itzabu.customlive;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomLiveCommand implements CommandExecutor {


    private final CustomLive customLive;

    private final LuckPerms luckPerms = CustomLive.getLuckPerms();

    public static final String CommandLive = "CustomLiveOn";
    public static final String COMMANDUsage = "CustomLiveOn.usage";

    public CustomLiveCommand(CustomLive customLive) {
        this.customLive = customLive;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            User p = luckPerms.getUserManager().getUser(player.getUniqueId());
            if (!luckpermsCommands.hasPermission(p, COMMANDUsage) && !luckpermsCommands.hasPermission(p, CommandLive)) {
                sender.sendMessage("§cDevi avere un rank pari o superiore a Content Creator ");
                return false;
            }
            User user = luckPerms.getUserManager().getUser(p.getUniqueId());
            if (args.length == 0) {
                if (luckpermsCommands.haveGroup(user)) {
                    luckpermsCommands.removeGoup(user);
                    player.sendMessage("§cDisattivato");
                } else {
                    luckpermsCommands.addGroup(user);
                    player.sendMessage("§aAttivato");
                }
            } else if (args.length == 1) {
                if (!luckpermsCommands.hasPermission(p, CommandLive)) {
                    sender.sendMessage("§cDevi essere un amministratore per usare questo comando ");
                    return false;
                }
                Player target = player.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§cGiocatore non trovato");
                    return false;
                }
                User targetUser = luckPerms.getUserManager().getUser(target.getUniqueId());

                if (luckpermsCommands.haveGroup(targetUser)) {
                    luckpermsCommands.removeGoup(targetUser);
                    sender.sendMessage("§cDisattivato a " + target.getName());
                } else {
                    luckpermsCommands.addGroup(targetUser);
                    sender.sendMessage("§aAttivato a " + target.getName());
                }

            } else {
                sender.sendMessage("§cUso: /live");
                return false;

            }
        } else {
            if (args == null) {
                sender.sendMessage("§cUso: /live <giocatore>");
                return false;
            } else if (args.length == 1) {
                Player target = sender.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage("§cGiocatore non trovato");
                    return false;
                }
                User targetUser = luckPerms.getUserManager().getUser(target.getUniqueId());

                if (luckpermsCommands.haveGroup(targetUser)) {
                    luckpermsCommands.removeGoup(targetUser);
                    sender.sendMessage("§cDisattivato a " + target.getName());
                } else {
                    luckpermsCommands.addGroup(targetUser);
                    sender.sendMessage("§aAttivato a " + target.getName());
                }
            } else {
                sender.sendMessage("§cUso: /live <giocatore>");
                return false;
            }
        }
        return false;
    }
}

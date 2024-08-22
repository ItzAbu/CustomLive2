package org.itzabu.customlive;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomLive extends JavaPlugin {

    private static LuckPerms api;


    @Override
    public void onEnable() {

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            api = provider.getProvider();

        } else {
            getLogger().severe("LuckPerms not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println("CustomLive attivato con successo!");
        //comandi
        getCommand("live").setExecutor(new CustomLiveCommand(this));

        //Listeners
        getServer().getPluginManager().registerEvents(new CommandLiveListerner(), this);

    }

    @Override
    public void onDisable() {
        luckpermsCommands.removeEveryone();
        // Plugin shutdown logic
    }

    public static LuckPerms getLuckPerms() {
        return api;
    }
}

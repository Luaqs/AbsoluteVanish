package zone.luaq.av;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import zone.luaq.av.commands.Vanish;
import zone.luaq.av.commands.VanishCheck;
import zone.luaq.av.commands.VanishReload;
import zone.luaq.av.events.ChatHandlers;
import zone.luaq.av.events.HealthHandlers;
import zone.luaq.av.events.InteractionHandlers;
import zone.luaq.av.events.JoinHandlers;
import zone.luaq.av.utils.AVSettings;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;
import zone.luaq.utils.command.LCommandUtils;

import java.util.UUID;

public class AbsoluteVanish extends JavaPlugin {

    private static AVSettings settings;

    public static AVSettings getSettings() {
        return settings;
    }

    public void onEnable() {
        startSchedule();

        registerCommands();
        registerEvents();
        registerConfig();

        settings = new AVSettings(this);
    }

    private void startSchedule(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(UUID uuid : AVUtils.getVanishedPlayers()){
                    Player player = LUtils.getPlayer(uuid);
                    if(player != null) LUtils.sendActionBar(player, (String)settings.getValue("messages.actionbarMessage"));
                }
            }
        }, 0, 20L);
    }

    private void registerCommands(){
        LCommandUtils.register(this, "vanish", new Vanish(), true);
        LCommandUtils.register(this, "vanishcheck", new VanishCheck());
        LCommandUtils.register(this, "vanishreload", new VanishReload());
    }

    private void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinHandlers(), this);
        pm.registerEvents(new ChatHandlers(), this);
        pm.registerEvents(new HealthHandlers(), this);
        pm.registerEvents(new InteractionHandlers(), this);
    }

    private void registerConfig(){
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

}

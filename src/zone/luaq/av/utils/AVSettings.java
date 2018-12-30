package zone.luaq.av.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class AVSettings {

    private Plugin plugin;

    public AVSettings(Plugin plugin){
        this.plugin = plugin;
    }

    public Object getValue(String option){
        return getConfig().get("settings."+option+".value");
    }

    public String getDescription(String option){
        return (String)getConfig().get("settings."+option+".description");
    }

    public FileConfiguration getConfig(){
        return plugin.getConfig();
    }

    public Plugin getPlugin() {
        return plugin;
    }

}

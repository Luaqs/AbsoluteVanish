package zone.luaq.av.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import zone.luaq.av.AbsoluteVanish;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;

import java.util.UUID;

public class JoinHandlers implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        for(UUID uuid : AVUtils.getVanishedPlayers()){
            Player p = LUtils.getPlayer(uuid);
            player.hidePlayer(p);
        }

        if(AVUtils.isVanished(player)) {
            for(Player p : Bukkit.getOnlinePlayers()) if(!p.hasPermission("av.cansee")) p.hidePlayer(player);
            player.sendMessage(LUtils.color((String)AbsoluteVanish.getSettings().getValue("messages.joinMessage")));
            event.setJoinMessage("");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(AVUtils.isVanished(player)) event.setQuitMessage("");
    }

}

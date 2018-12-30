package zone.luaq.av.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import zone.luaq.av.AbsoluteVanish;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;

public class ChatHandlers implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if((boolean)AbsoluteVanish.getSettings().getValue("allowChat")) return;

        Player player = event.getPlayer();
        if(AVUtils.isVanished(player)){
            event.setCancelled(true);
            player.sendMessage(LUtils.color((String)AbsoluteVanish.getSettings().getValue("messages.talkInVanishMessage")));
        }
    }

}

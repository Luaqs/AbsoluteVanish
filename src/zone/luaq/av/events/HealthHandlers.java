package zone.luaq.av.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import zone.luaq.av.AbsoluteVanish;
import zone.luaq.av.utils.AVUtils;

public class HealthHandlers implements Listener {

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)) return;
        Player player = (Player)event.getDamager();
        if((boolean)AbsoluteVanish.getSettings().getValue("allowPvp")) return;
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if((boolean)AbsoluteVanish.getSettings().getValue("allowDamage")) return;
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        Player player = (Player)event.getEntity();
        if((boolean)AbsoluteVanish.getSettings().getValue("allowHunger")) return;
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent event){
        if(!(event.getTarget() instanceof Player)) return;
        Player player = (Player)event.getTarget();
        if((boolean)AbsoluteVanish.getSettings().getValue("allowMobTargeting")) return;
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

}

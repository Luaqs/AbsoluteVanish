package zone.luaq.av.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import zone.luaq.av.AbsoluteVanish;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;

import java.util.HashMap;
import java.util.UUID;

public class InteractionHandlers implements Listener {

    final private static HashMap<UUID, GameMode> players = new HashMap<>();

    @EventHandler
    public void chestOpenHandling(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;
        if((boolean)AbsoluteVanish.getSettings().getValue("allowRegularChestOpen")) return;
        if(event.getClickedBlock() == null || event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if(event.getClickedBlock().getType().toString().contains("CHEST")) {
            players.put(player.getUniqueId(), player.getGameMode());
            player.setGameMode(GameMode.SPECTATOR);
            if((boolean)AbsoluteVanish.getSettings().getValue("safeChestOpen.sendMessage")) player.sendMessage(LUtils.color("&aOpening chest safely."));
        }
    }

    @EventHandler
    public void pressurePlateHandling(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;
        if((boolean)AbsoluteVanish.getSettings().getValue("allowPressurePlateUse")) return;
        if(event.getClickedBlock() == null || event.getAction() != Action.PHYSICAL) return;

        if(event.getClickedBlock().getType().toString().contains("PLATE")) event.setCancelled(true);

    }

    @EventHandler
    public void chestClose(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        if(players.containsKey(player.getUniqueId())){
            player.setGameMode(players.get(player.getUniqueId()));
            players.remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event){
        if(!(event.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player)event.getEntity().getShooter();
        if((boolean)AbsoluteVanish.getSettings().getValue("allowThrowing")) return;
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event){
        if((boolean)AbsoluteVanish.getSettings().getValue("allowBuilding")) return;
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBuild(BlockBreakEvent event){
        if((boolean)AbsoluteVanish.getSettings().getValue("allowBuilding")) return;
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if((boolean)AbsoluteVanish.getSettings().getValue("allowItemDrop")) return;
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event){
        if((boolean)AbsoluteVanish.getSettings().getValue("allowItemPickUp")) return;
        Player player = event.getPlayer();
        if(!AVUtils.isVanished(player)) return;

        event.setCancelled(true);
    }

}

package zone.luaq.av.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import zone.luaq.av.AbsoluteVanish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AVUtils {

    final private static List<UUID> vanishedPlayers = new ArrayList<>();
    final private static HashMap<UUID, AVPlayerState> states = new HashMap<>();

    public static void toggleVanish(Player player){
        setVanished(player, !isVanished(player));
    }

    public static void setVanished(Player player, boolean isVanished){
        if(!isVanished) {
            for(Player p : Bukkit.getOnlinePlayers()) p.showPlayer(player);
            vanishedPlayers.remove(player.getUniqueId());
        }
        else if (!vanishedPlayers.contains(player.getUniqueId())) {
            for(Player p : Bukkit.getOnlinePlayers()) if(!p.hasPermission("av.cansee")) p.hidePlayer(player);
            vanishedPlayers.add(player.getUniqueId());
        }

        if((boolean)AbsoluteVanish.getSettings().getValue("saveStateMode")){

            boolean saveInventory = (boolean)AbsoluteVanish.getSettings().getValue("saveStateMode.saveInventoryState");
            boolean saveFlight = (boolean)AbsoluteVanish.getSettings().getValue("saveStateMode.saveFlightState");
            boolean saveLocation = (boolean)AbsoluteVanish.getSettings().getValue("saveStateMode.saveLocationState");
            boolean saveGameMode = (boolean)AbsoluteVanish.getSettings().getValue("saveStateMode.saveGameModeState");

            if(AVUtils.isVanished(player)) {
                AVPlayerState state = new AVPlayerState(player, saveInventory, saveFlight, saveLocation, saveGameMode);
                states.put(player.getUniqueId(), state);
                if(saveInventory) player.getInventory().clear();
                if(saveInventory) player.getInventory().setArmorContents(null);
                if(saveFlight) player.setAllowFlight(true);
            } else {
                AVPlayerState state = states.get(player.getUniqueId());

                if(state.getInventoryItems() != null) player.getInventory().setContents(state.getInventoryItems());
                if(state.getArmorItems() != null) player.getInventory().setArmorContents(state.getArmorItems());
                if(state.getInventoryItems() != null) player.setAllowFlight(state.getAllowFlight());
                if(state.getLocation() != null) player.teleport(state.getLocation());
                if(state.getGameMode() != null) player.setGameMode(state.getGameMode());

                states.remove(player.getUniqueId());
            }
        }
    }

    public static boolean isVanished(Player player){
        return vanishedPlayers.contains(player.getUniqueId());
    }

    public static int getFakePlayerCount(){
        return Bukkit.getOnlinePlayers().size() - vanishedPlayers.size();
    }

    public static List<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }
}

package zone.luaq.av.utils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import zone.luaq.utils.LUtils;

import java.util.UUID;

public class AVPlayerState {

    private UUID player;
    private ItemStack[] inventoryItems = null;
    private ItemStack[] armorItems = null;
    private Location location = null;
    private boolean allowFlight;
    private GameMode gamemode = null;

    public AVPlayerState(Player player, boolean saveInventory, boolean saveFlight, boolean saveLocation, boolean saveGameMode){
        this.player = player.getUniqueId();
        if(saveInventory){
            this.inventoryItems = player.getInventory().getContents();
            this.armorItems = player.getInventory().getArmorContents();
        }
        if(saveGameMode) this.gamemode = player.getGameMode();
        if(saveLocation) this.location = player.getLocation();
        if(saveFlight) this.allowFlight = player.getAllowFlight();
    }

    public Player getPlayer() {
        return LUtils.getPlayer(this.player);
    }

    public Location getLocation() {
        return location;
    }

    public ItemStack[] getInventoryItems() {
        return inventoryItems;
    }

    public ItemStack[] getArmorItems() {
        return armorItems;
    }

    public boolean getAllowFlight() {
        return allowFlight;
    }

    public GameMode getGameMode() {
        return gamemode;
    }

    public boolean isPlayer(Player player){
        return player.getUniqueId().equals(this.player);
    }

}

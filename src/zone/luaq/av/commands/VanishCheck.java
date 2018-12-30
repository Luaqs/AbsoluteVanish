package zone.luaq.av.commands;

import org.bukkit.entity.Player;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;
import zone.luaq.utils.command.LCommandExecutor;
import zone.luaq.utils.command.data.LCommandData;
import zone.luaq.utils.command.data.LCommandSender;

public class VanishCheck implements LCommandExecutor {

    @Override
    public void onCommand(LCommandData data, LCommandSender sender, String[] args) {
        if(args.length >= 1){
            Player target = LUtils.getPlayer(args[0]);
            if(target != null){
                final boolean isVanished = AVUtils.isVanished(target);
                sender.send("&6"+target.getName()+" &ais "+((isVanished) ? "&6" : "&cnot ")+"vanished&a.");
            } else sender.send("&cSorry, but it appears as if this player is offline.");
        } else sender.send("&cUsage: "+data.getUsage());
    }

}

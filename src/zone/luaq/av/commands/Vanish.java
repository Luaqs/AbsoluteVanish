package zone.luaq.av.commands;

import org.bukkit.entity.Player;
import zone.luaq.av.AbsoluteVanish;
import zone.luaq.av.utils.AVUtils;
import zone.luaq.utils.LUtils;
import zone.luaq.utils.command.LCommandExecutor;
import zone.luaq.utils.command.data.LCommandData;
import zone.luaq.utils.command.data.LCommandSender;

public class Vanish implements LCommandExecutor {

    @Override
    public void onCommand(LCommandData data, LCommandSender sender, String[] args) {
        Player player = sender.getPlayer();
        if(args.length >= 1){
            if(player.hasPermission("av.togglevanish.others")){
                Player target = LUtils.getPlayer(args[0]);
                if(target != null){
                    AVUtils.toggleVanish(target);
                    String str = (AVUtils.isVanished(target) ? "on" : "off");

                    sender.send(
                            String.format((String)AbsoluteVanish.getSettings().getValue("messages.vanishToggle.other"), target.getName(), str));
                    target.sendMessage(LUtils.color(
                            String.format((String)AbsoluteVanish.getSettings().getValue("messages.vanishToggle.targetMessage"), sender.getPlayer().getName(), str)));
                } else sender.send((String)AbsoluteVanish.getSettings().getValue("messages.playerOfflineMessage"));
            } else sender.send("&cSorry, but you do not have permission to toggle others vanish.");
        } else {
            AVUtils.toggleVanish(player);
            sender.send(String.format((String)AbsoluteVanish.getSettings().getValue("messages.vanishToggle"), (AVUtils.isVanished(player) ? "on" : "off")));
        }
    }

}

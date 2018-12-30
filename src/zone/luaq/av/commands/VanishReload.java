package zone.luaq.av.commands;

import zone.luaq.av.AbsoluteVanish;
import zone.luaq.utils.command.LCommandExecutor;
import zone.luaq.utils.command.data.LCommandData;
import zone.luaq.utils.command.data.LCommandSender;

public class VanishReload implements LCommandExecutor {

    @Override
    public void onCommand(LCommandData data, LCommandSender sender, String[] args) {
        sender.send("&aReloading the config...");
        AbsoluteVanish.getSettings().getPlugin().reloadConfig();
        sender.send("&aSuccessfully reloaded the config!");
    }

}

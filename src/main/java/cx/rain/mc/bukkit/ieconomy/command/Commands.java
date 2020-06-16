package cx.rain.mc.bukkit.ieconomy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
    public Commands(JavaPlugin plugin) {
        plugin.getCommand("ieconomy").setExecutor(new CommandIEconomy());
    }
}

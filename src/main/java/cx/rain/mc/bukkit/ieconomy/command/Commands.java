package cx.rain.mc.bukkit.ieconomy.command;

import me.lucko.commodore.Commodore;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands {
    public Commands(Commodore commodore, JavaPlugin plugin) {
        new CommandIEconomy(commodore, plugin);
    }
}

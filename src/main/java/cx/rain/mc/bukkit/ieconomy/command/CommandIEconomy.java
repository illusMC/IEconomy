package cx.rain.mc.bukkit.ieconomy.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.lucko.commodore.Commodore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandIEconomy implements CommandExecutor {
    public CommandIEconomy(Commodore commodore, JavaPlugin plugin) {
        plugin.getCommand("ieconomy").setExecutor(this);

        /*
        commodore.register(plugin.getCommand("ieconomy"),
                LiteralArgumentBuilder.literal("ieconomy")
                        .executes()
        );
         */
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}

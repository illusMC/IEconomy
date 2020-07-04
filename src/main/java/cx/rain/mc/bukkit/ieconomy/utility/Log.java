package cx.rain.mc.bukkit.ieconomy.utility;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {
    private static final String FORMAT =
            ChatColor.translateAlternateColorCodes('&',"&f[&6IEconomy&f] %");

    public static void info(String msg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%", ChatColor.WHITE + msg));
    }

    public static void warn(String msg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%", ChatColor.YELLOW + msg));
    }

    public static void debug(String msg){
        if (IEconomy.DEBUG) {
            Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%", ChatColor.AQUA + msg));
        }
    }

    public static void error(String msg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%", ChatColor.RED + msg));
    }

    public static void fatal(String msg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%", ChatColor.DARK_RED + msg));
    }
}

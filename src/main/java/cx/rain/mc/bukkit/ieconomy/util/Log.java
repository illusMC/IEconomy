package cx.rain.mc.bukkit.ieconomy.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {
    private static final String FORMAT= ChatColor.translateAlternateColorCodes('&',"&f[&6iEconomy&f] %");
    public static void info(String mesg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%",mesg));
    }
    public static void warn(String mesg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%",ChatColor.RED+mesg));
    }
    public static void debug(String mesg){
        Bukkit.getConsoleSender().sendMessage(FORMAT.replaceAll("%",ChatColor.UNDERLINE+mesg));
    }
}

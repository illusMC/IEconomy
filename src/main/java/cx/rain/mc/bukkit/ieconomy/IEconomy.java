package cx.rain.mc.bukkit.ieconomy;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class IEconomy extends JavaPlugin {
    @Getter
    protected static IEconomy instance;
    public static boolean DEBUG=false;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance =this;
        new Loader().runTaskAsynchronously(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

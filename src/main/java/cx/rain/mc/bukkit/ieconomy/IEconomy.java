package cx.rain.mc.bukkit.ieconomy;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IEconomy extends JavaPlugin {
    protected static IEconomy INSTANCE = null;

    private Logger log = getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public Logger getLog() {
        return log;
    }
}

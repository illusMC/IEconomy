package cx.rain.mc.bukkit.ieconomy;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IEconomy extends JavaPlugin {
    @Getter
    protected static IEconomy inst;

    @Override
    public void onEnable() {
        // Plugin startup logic
        inst=this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

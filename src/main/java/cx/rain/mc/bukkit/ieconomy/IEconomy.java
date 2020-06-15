package cx.rain.mc.bukkit.ieconomy;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.nutz.dao.Dao;

public final class IEconomy extends JavaPlugin {
    @Getter
    protected static IEconomy instance;
    @Getter
    protected Dao dao;
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

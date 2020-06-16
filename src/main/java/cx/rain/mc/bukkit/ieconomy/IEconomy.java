package cx.rain.mc.bukkit.ieconomy;

import cx.rain.mc.bukkit.ieconomy.command.Commands;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.bukkit.ieconomy.utility.Log;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nutz.dao.Dao;

import java.io.File;

public final class IEconomy extends JavaPlugin {
    @Getter
    protected static IEconomy instance;
    @Getter
    protected Dao dao;
    public static boolean DEBUG = false;

    private YamlConfiguration config = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        IEconomy.getInstance().getDataFolder().mkdir();
        Log.info("Loading Configs..");
        loadConfigs();

        Log.info("Coming soon..");
        new Commands(IEconomy.getInstance());

        new AsyncLoader().runTaskAsynchronously(this);
    }

    private void loadConfigs() {
        File configDir = new File(IEconomy.getInstance().getDataFolder().getAbsolutePath());
        if (!configDir.exists()) {
            configDir.mkdir();
        }

        File configFile = new File(configDir.getAbsolutePath() + "/config.yml");
        if (!configFile.exists()) {
            saveConfig();
        }

        config = (YamlConfiguration) getConfig();

        String[] localeString = config.getString("general.language").split("_");
        assert localeString.length == 2;
        new I18n(localeString[0], localeString[1]);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}

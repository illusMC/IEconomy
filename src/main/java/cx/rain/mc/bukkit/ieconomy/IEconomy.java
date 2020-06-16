package cx.rain.mc.bukkit.ieconomy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cx.rain.mc.bukkit.ieconomy.command.Commands;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.bukkit.ieconomy.utility.Log;
import lombok.Getter;
import lombok.SneakyThrows;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

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
        Log.info("Loading configs...");
        loadConfigs();

        Log.info("Loading database...");
        loadDatabase();

        Log.info("Coming soon...");

        if (!CommodoreProvider.isSupported()) {
            Log.fatal("Not supported!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Commodore commodore = CommodoreProvider.getCommodore(this);
        new Commands(commodore, IEconomy.getInstance());

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

    @SneakyThrows
    private void loadDatabase() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(IEconomy.getInstance().getConfig().getString("database.jdbcUrl"));
        hikariConfig.setUsername(IEconomy.getInstance().getConfig().getString("database.username"));
        hikariConfig.setPassword(IEconomy.getInstance().getConfig().getString("database.password"));
        hikariConfig.setDriverClassName(IEconomy.getInstance().getConfig().getString("database.driver"));
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Dao dao = new NutDao(ds);
        this.dao = dao;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Log.info("Goodbye.");
    }
}

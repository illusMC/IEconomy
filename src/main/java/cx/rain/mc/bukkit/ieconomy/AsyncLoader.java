package cx.rain.mc.bukkit.ieconomy;

import cc.sfclub.util.common.UpdateChecker;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.bukkit.ieconomy.utility.Log;
import lombok.SneakyThrows;
import org.bukkit.scheduler.BukkitRunnable;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import java.util.Optional;

public class AsyncLoader extends BukkitRunnable {

    @Override
    public void run() {
        Log.info("Loading Database..");
        loadDatabase();
        Log.info("Checking Updates..");
        checkUpdate();
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
        IEconomy.getInstance().dao = dao;
    }

    private void checkUpdate() {
        UpdateChecker updateChecker = new UpdateChecker("illusMC", "IEconomy", "master");
        Optional<UpdateChecker.ReleaseInfo> res = updateChecker.check();
        if (res.isPresent()) {
            UpdateChecker.ReleaseInfo rel = res.get();
            if (rel.tag_name.equals(IEconomy.getInstance().getDescription().getVersion())) return;
            Log.info(I18n.format("update.new_version", rel.tag_name, rel.published_at));
        }
    }
}

package cx.rain.mc.bukkit.ieconomy;

import cc.sfclub.util.common.SimpleFile;
import cc.sfclub.util.common.UpdateChecker;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cx.rain.mc.bukkit.ieconomy.config.Config;
import cx.rain.mc.bukkit.ieconomy.config.I18N;
import cx.rain.mc.bukkit.ieconomy.util.Log;
import lombok.SneakyThrows;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Optional;

public class Loader extends BukkitRunnable {

    @Override
    public void run() {
        IEconomy.getInst().getDataFolder().mkdir();
        Log.info("Loading Configs..");
        loadConfigs();
        Log.info("Checking Updates.");
        checkUpdate();
        Log.info("Loading Database..");
        loadDatabase();

    }
    private void loadConfigs(){
        Config config=new Config(IEconomy.getInst().getDataFolder().getAbsolutePath());
        if(!SimpleFile.exists(IEconomy.getInst().getDataFolder().getAbsolutePath()+"/config.json")){
            config.saveConfig();
        }
        config=(Config)config.reloadConfig();
        Config.setInst(config);
        Log.info("Loading Language..");
        I18N i18N=new I18N(IEconomy.getInst().getDataFolder().getAbsolutePath());
        if(!SimpleFile.exists(IEconomy.getInst().getDataFolder().getAbsolutePath()+"/i18n.json")){
            i18N.saveConfig();
        }
        i18N=(I18N)i18N.reloadConfig();
        I18N.setInst(i18N);
    }
    @SneakyThrows
    private void loadDatabase() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(Config.get().dbInfo.jdbcUrl);
        hikariConfig.setUsername(Config.get().dbInfo.user);
        hikariConfig.setPassword(Config.get().dbInfo.password);
        hikariConfig.setDriverClassName(Config.get().dbInfo.driver);
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        //todo dao here
    }
    private void checkUpdate(){
        UpdateChecker updateChecker=new UpdateChecker("illusMC","IEconomy","master");
        Optional<UpdateChecker.ReleaseInfo> res=updateChecker.check();
        if(res.isPresent()){
            UpdateChecker.ReleaseInfo rel=res.get();
            if(rel.tag_name.equals(IEconomy.getInst().getDescription().getVersion()))return;
            Log.info(I18N.get().new_version_published.replaceAll("%v",rel.tag_name).replaceAll("%date",rel.published_at));
        }
    }
}

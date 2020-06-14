package cx.rain.mc.bukkit.ieconomy;

import cc.sfclub.util.common.SimpleFile;
import cc.sfclub.util.common.UpdateChecker;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import cx.rain.mc.bukkit.ieconomy.config.Config;
import cx.rain.mc.bukkit.ieconomy.config.I18n;
import cx.rain.mc.bukkit.ieconomy.utility.Log;
import lombok.SneakyThrows;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Optional;

public class Loader extends BukkitRunnable {

    @Override
    public void run() {
        IEconomy.getInstance().getDataFolder().mkdir();
        Log.info("Loading Configs..");
        loadConfigs();
        Log.info("Checking Updates.");
        checkUpdate();
        Log.info("Loading Database..");
        loadDatabase();

    }

    private void loadConfigs(){
        Config config=new Config(IEconomy.getInstance().getDataFolder().getAbsolutePath());
        if(!SimpleFile.exists(IEconomy.getInstance().getDataFolder().getAbsolutePath()+"/config.json")){
            config.saveConfig();
        }
        config=(Config)config.reloadConfig();
        Config.setInst(config);
        Log.info("Loading Language..");
        File lang=new File(IEconomy.getInstance().getDataFolder().getAbsolutePath()+"/lang");
        lang.mkdir();
        I18n i18N=new I18n(IEconomy.getInstance().getDataFolder().getAbsolutePath()+"/lang",config.locale);
        if(!SimpleFile.exists(IEconomy.getInstance().getDataFolder().getAbsolutePath()+"/lang/"+config.locale+".json")){
            i18N.saveConfig();
        }
        i18N=(I18n)i18N.reloadConfig();
        I18n.setInst(i18N);
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
            if(rel.tag_name.equals(IEconomy.getInstance().getDescription().getVersion()))return;
            Log.info(I18n.get().new_version_published.replaceAll("%v",rel.tag_name).replaceAll("%date",rel.published_at));
        }
    }
}

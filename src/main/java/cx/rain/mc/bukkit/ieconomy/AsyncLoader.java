package cx.rain.mc.bukkit.ieconomy;

import cc.sfclub.util.common.UpdateChecker;
import cx.rain.mc.bukkit.ieconomy.utility.I18n;
import cx.rain.mc.bukkit.ieconomy.utility.Log;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class AsyncLoader extends BukkitRunnable {
    @Override
    public void run() {
        Log.info("Checking updates...");
        checkUpdate();
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

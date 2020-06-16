package cx.rain.mc.bukkit.ieconomy.utility;

import cx.rain.mc.bukkit.ieconomy.IEconomy;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;

public class I18n {
    private Locale locale = null;
    @Getter
    private static I18n instance = null;

    private YamlConfiguration language = null;

    public I18n(String lang, String country) {
        locale = new Locale(lang, country);

        Log.info("Loading Language..");
        File langDir = new File(IEconomy.getInstance().getDataFolder().getAbsolutePath() + "/lang");
        if (!langDir.exists()) {
            langDir.mkdir();
        }

        String path = IEconomy.getInstance().getDataFolder().getAbsolutePath() + "/lang/" + locale.toString() + ".yml";
        File langFile = new File(path);
        if (!langFile.exists()) {
            IEconomy.getInstance().saveResource("/lang/" + locale.toString() + ".yml", true);
        }

        language = YamlConfiguration.loadConfiguration(langFile);
        instance = this;
    }

    public static YamlConfiguration getLang() {
        return getInstance().language;
    }

    public static String format(String key, Object... params) {
        String str = getLang().getString(key);
        if (str == null || str.isEmpty()) {
            str = "§cMissing key!";
        }
        return String.format(str, params);
    }
}

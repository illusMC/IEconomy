package cx.rain.mc.bukkit.ieconomy.config;

import cc.sfclub.util.common.JsonConfig;
import lombok.Setter;

public class I18n extends JsonConfig {
    @Setter
    private static I18n inst;

    public I18n(String rootDir) {
        super(rootDir);
    }

    public static I18n get(){
        return inst;
    }
    public String new_version_published="Version %v released on %date.\nUpdate it: https://github.com/illusMC/IEconomy/releases";
}

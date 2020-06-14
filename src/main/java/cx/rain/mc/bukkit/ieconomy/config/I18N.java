package cx.rain.mc.bukkit.ieconomy.config;

import cc.sfclub.util.common.JsonConfig;
import lombok.Setter;

public class I18N extends JsonConfig {
    @Setter
    private static I18N inst;

    public I18N(String rootDir) {
        super(rootDir);
    }

    public static I18N get(){
        return inst;
    }
    public String new_version_published="Version %v released on %date.\nUpdate it: https://github.com/illusMC/IEconomy/releases";
}

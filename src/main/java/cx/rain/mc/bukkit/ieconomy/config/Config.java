package cx.rain.mc.bukkit.ieconomy.config;

import cc.sfclub.util.common.JsonConfig;
import lombok.Setter;

import java.util.UUID;

public class Config extends JsonConfig {

    public Database dbInfo = new Database();
    public String locale ="en_US";
    @Setter
    private static Config inst;
    public static Config get(){
        return inst;
    }
    public Config(String rootDir) {
        super(rootDir);
    }
    public static class Database{
        public String jdbcUrl = "jdbc:sqlite:ieco.db";
        public String user = "root";
        public String password = UUID.randomUUID().toString();
        public String driver = "org.sqlite.JDBC";
    }
}

package cx.rain.mc.bukkit.ieconomy.core.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    public String Uuid = "";
    public Map<String, Double> Money = new LinkedHashMap<>();
    public Map<String, Integer> TaxPeriod = new LinkedHashMap<>();
}

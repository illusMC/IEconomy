package cx.rain.mc.bukkit.ieconomy.database.query;

public interface IQueryBuilder {
    IQueryBuilder from();
    IQueryBuilder select();
    IQueryBuilder as();
    IQueryBuilder join();
    IQueryBuilder where();
    void build();
}

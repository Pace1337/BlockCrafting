package insurgence.network.blockcrafting;

import insurgence.network.blockcrafting.commands.CraftReload;
import insurgence.network.blockcrafting.handler.CraftHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockCrafting extends JavaPlugin {

    public static BlockCrafting instance = null;
    public static FileConfiguration config;
    private CraftHandler handler;

    public void reloadConfiguration() {
        this.reloadConfig();
        config = this.getConfig();
    }

    @Override
    public void onEnable() {
        BlockCrafting.instance = this;
        BlockCrafting.config = this.getConfig();
        BlockCrafting.config.options().copyDefaults(true);
        this.saveDefaultConfig();
        getCommand("craftreload").setExecutor(new CraftReload());
        this.getServer().getPluginManager().registerEvents(handler = new CraftHandler(this), this);
    }

    public static BlockCrafting getInstance() {
        return instance;
    }

    public CraftHandler getHandler() {
        return this.handler;
    }
}
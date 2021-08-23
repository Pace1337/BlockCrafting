package insurgence.network.blockcrafting;

import insurgence.network.blockcrafting.handler.CraftHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockCrafting extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new CraftHandler(this), this);
    }
}
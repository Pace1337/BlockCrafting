package insurgence.network.blockcrafting.handler;

import insurgence.network.blockcrafting.BlockCrafting;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public class CraftHandler implements Listener {

    private final List<String> allBlockedTypes;

    public CraftHandler(final BlockCrafting instance) {
        (this.allBlockedTypes = new LinkedList<>()).addAll(instance.getConfig().getStringList("blocked"));
    }

    public void refresh() {
        this.allBlockedTypes.clear();
        this.allBlockedTypes.addAll(BlockCrafting.getInstance().getConfig().getStringList("blocked"));
    }

    @EventHandler
    void onCraft(final PrepareItemCraftEvent e) {
        Player player = (Player) e.getView().getPlayer();
        if (e.getRecipe() == null) {
            return;
        }
        ItemStack a = e.getRecipe().getResult();
        String typo = a.getType().name();
        if (this.allBlockedTypes.contains(typo)) {
            ItemMeta m = a.getItemMeta();
            m.setDisplayName(ChatColor.translateAlternateColorCodes('&', BlockCrafting.getInstance().getConfig().getString("messages.blockedmessage")));
            a.setItemMeta(m);
            e.getInventory().setResult(a);
            player.updateInventory();
            if (!BlockCrafting.getInstance().getConfig().getBoolean("chatmessage-enabled")) return;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', BlockCrafting.getInstance().getConfig().getString("messages.chatmessage")));
        }
    }

    @EventHandler
    void onCraftBlocked(final CraftItemEvent e) {
        ItemStack a = e.getRecipe().getResult();
        String typo = a.getType().name();
        if (allBlockedTypes.contains(typo)) {
            e.setCancelled(true);
        }
    }
}

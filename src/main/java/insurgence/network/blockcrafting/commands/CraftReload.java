package insurgence.network.blockcrafting.commands;

import insurgence.network.blockcrafting.BlockCrafting;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CraftReload implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            // Be able to do /screload from console too.
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            BlockCrafting.getInstance().reloadConfiguration();
            BlockCrafting.getInstance().getHandler().refresh();
            console.sendMessage(ChatColor.GREEN + "Config has been reloaded!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("cubecrafting.reload") || !player.isOp()) {
            player.sendMessage(ChatColor.RED + "You are not allowed to execute this command. Contact a server administrator if you believe this is an error.");
            return true;
        }
        try {
            BlockCrafting.getInstance().reloadConfiguration();
            BlockCrafting.getInstance().getHandler().refresh();
            sender.sendMessage(ChatColor.GREEN + "Config has been reloaded!");
        } catch (NullPointerException e) {
            sender.sendMessage(ChatColor.DARK_RED + "Error! Check console for more details.");
        }
        return true;
    }
}

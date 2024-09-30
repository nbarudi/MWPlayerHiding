package ca.bungo.playerhiding;

import ca.bungo.playerhiding.commands.CommandToggleHidePlayers;
import ca.bungo.playerhiding.events.ConnectionEvents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlayerHiding extends JavaPlugin {

    private static PlayerHiding instance;

    public List<String> hidingPlayers;

    @Override
    public void onEnable() {
        instance = this;

        hidingPlayers = new ArrayList<>();

        this.getServer().getCommandMap().register("playerhiding", new CommandToggleHidePlayers("hideplayers"));
        this.getServer().getPluginManager().registerEvents(new ConnectionEvents(), this);
    }

    public void hideAllPlayers(Player player) {
        for(Player other : this.getServer().getOnlinePlayers()) {
            if(other == player) continue;
            player.hidePlayer(this, other);
        }
    }

    public void showAllPlayers(Player player) {
        for(Player other : this.getServer().getOnlinePlayers()) {
            if(other == player) continue;
            player.showPlayer(this, other);
        }
    }

    public static PlayerHiding getInstance() {
        return instance;
    }

}

package ca.bungo.playerhiding.events;

import ca.bungo.playerhiding.PlayerHiding;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class ConnectionEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(PlayerHiding.getInstance().hidingPlayers.contains(player.getUniqueId().toString())){
            player.sendMessage(
                    Component.text("You are still hiding all players!")
                            .style(
                                    Style.style(
                                            TextColor.color(255,0,0)
                                    )
                            )
            );
            PlayerHiding.getInstance().hideAllPlayers(player);
        }


        for(String uuid : PlayerHiding.getInstance().hidingPlayers){
            Player target = Bukkit.getPlayer(UUID.fromString(uuid));

            if(target != null && target.isOnline())
                target.hidePlayer(PlayerHiding.getInstance(), player);
        }
    }

}

package ca.bungo.playerhiding.commands;

import ca.bungo.playerhiding.PlayerHiding;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandToggleHidePlayers extends Command {

    public CommandToggleHidePlayers(@NotNull String name) {
        super(name);
        this.description = "Toggles if players are hidden";
        this.usageMessage = "/hideplayers";
        this.setAliases(List.of("showplayers", "togglehide"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;

        if(label.equalsIgnoreCase("showplayers")){
            PlayerHiding.getInstance().hidingPlayers.remove(player.getUniqueId().toString());
            player.sendMessage(
                    Component.text("Now showing all players!")
                            .style(
                                    Style.style(
                                            TextColor.color(0,255,0)
                                    )
                            )
            );
            PlayerHiding.getInstance().showAllPlayers(player);
            return true;
        }
        else if(label.equalsIgnoreCase("hideplayers")){
            if(!PlayerHiding.getInstance().hidingPlayers.contains(player.getUniqueId().toString())){
                PlayerHiding.getInstance().hidingPlayers.add(player.getUniqueId().toString());
                PlayerHiding.getInstance().hideAllPlayers(player);
            }

            player.sendMessage(
                    Component.text("Now hiding all players!")
                            .style(
                                    Style.style(
                                            TextColor.color(255,0,0)
                                    )
                            )
            );

            return true;
        }
        else {
            if(PlayerHiding.getInstance().hidingPlayers.contains(player.getUniqueId().toString())){
                PlayerHiding.getInstance().hidingPlayers.remove(player.getUniqueId().toString());
                player.sendMessage(
                        Component.text("Now showing all players!")
                                .style(
                                        Style.style(
                                                TextColor.color(0,255,0)
                                        )
                                )
                );
                PlayerHiding.getInstance().showAllPlayers(player);
            } else {
                PlayerHiding.getInstance().hidingPlayers.add(player.getUniqueId().toString());
                player.sendMessage(
                        Component.text("Now hiding all players!")
                                .style(
                                        Style.style(
                                                TextColor.color(255,0,0)
                                        )
                                )
                );
                PlayerHiding.getInstance().hideAllPlayers(player);
            }

            return true;
        }
    }
}

package me.jules.helpme.util;

import me.jules.helpme.HelpMe;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void sendMessage(CommandSender sender, String message) {
        if (message == null || message.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(message));
    }

    public static void sendHelpRequest(Player admin, Player requester, HelpMe plugin) {
        String buttonText = plugin.getConfigManager().getHandleButtonText();
        String buttonColor = plugin.getConfigManager().getHandleButtonColor();
        boolean underlined = plugin.getConfigManager().isHandleButtonUnderlined();
        String hoverText = plugin.getConfigManager().getHandleButtonHover();

        TextColor color = NamedTextColor.NAMES.value(buttonColor.toLowerCase());
        if (color == null) {
            color = NamedTextColor.GREEN;
        }

        Component message = Component.text()
                .append(Component.text(requester.getName() + " needs help. "))
                .append(Component.text(buttonText)
                        .color(color)
                        .decoration(TextDecoration.UNDERLINED, underlined)
                        .clickEvent(ClickEvent.runCommand("/helpmehandle " + requester.getName()))
                        .hoverEvent(HoverEvent.showText(miniMessage.deserialize(hoverText))))
                .build();

        admin.sendMessage(message);
    }
}

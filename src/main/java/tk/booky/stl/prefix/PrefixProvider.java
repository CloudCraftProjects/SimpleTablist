package tk.booky.stl.prefix;
// Created by booky10 in SimpleTablist (16:03 30.06.21)

import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface PrefixProvider {

    @NotNull Component getPrefix(Player player);

    @NotNull Component getSuffix(Player player);
}
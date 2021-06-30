package tk.booky.stl.prefix;
// Created by booky10 in SimpleTablist (16:04 30.06.21)

import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;

public class DummyPrefixProvider implements PrefixProvider {

    @Override
    public @NotNull Component getPrefix(Player player) {
        return empty();
    }

    @Override
    public @NotNull Component getSuffix(Player player) {
        return empty();
    }
}
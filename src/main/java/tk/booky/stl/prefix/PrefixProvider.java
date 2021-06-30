package tk.booky.stl.prefix;
// Created by booky10 in SimpleTablist (16:03 30.06.21)

import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import tk.booky.stl.prefix.luckperms.LuckPermsPrefixProvider;

public interface PrefixProvider {

    @NotNull Component getPrefix(Player player);

    @NotNull Component getSuffix(Player player);

    static PrefixProvider getDefaultProvider(PluginManager manager) {
        return manager.isLoaded("luckperms") ? new LuckPermsPrefixProvider() : new DummyPrefixProvider();
    }
}
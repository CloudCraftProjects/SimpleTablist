package tk.booky.stl.utils;
// Created by booky10 in SimpleTablist (15:36 30.06.21)

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.player.TabListEntry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import tk.booky.stl.SimpleTablistMain;
import tk.booky.stl.config.TabListConfiguration;
import tk.booky.stl.prefix.PrefixProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SimpleTablistManager {

    private final ProxyServer server;
    private final PrefixProvider provider;
    private Component header, footer;
    private TabListConfiguration config;

    public SimpleTablistManager(ProxyServer server, TabListConfiguration config) {
        this.server = server;
        this.config = config;

        provider = PrefixProvider.getDefaultProvider(server.getPluginManager());
    }

    public SimpleTablistManager reloadConfiguration() {
        SimpleTablistMain main = SimpleTablistMain.getInstance();
        config = main.getReader().read(main.getDataDirectory(), "config.toml", new TabListConfiguration());
        main.setConfig(config);
        return this;
    }

    public SimpleTablistManager reloadHeaderFooter() {
        LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();
        header = serializer.deserialize(config.header.replace("\\n", "\n"));
        footer = serializer.deserialize(config.footer.replace("\\n", "\n"));
        return this;
    }

    public SimpleTablistManager reloadAllTablists() {
        server.getAllPlayers().forEach(this::updateTablist);
        return this;
    }

    public void updateTablist(Player player) {
        Set<UUID> updated = new HashSet<>();

        for (Player target : server.getAllPlayers()) {
            updated.add(target.getUniqueId());
            update(target, player);
        }

        for (TabListEntry entry : player.getTabList().getEntries()) {
            if (updated.contains(entry.getProfile().getId())) continue;
            player.getTabList().removeEntry(entry.getProfile().getId());
        }

        player.sendPlayerListHeaderAndFooter(header, footer);
    }

    public void remove(Player target, Player player) {
        player.getTabList().removeEntry(target.getUniqueId());
    }

    public void update(Player target, Player player) {
        Optional<ServerConnection> targetServer = target.getCurrentServer(), playerServer = player.getCurrentServer();
        if (targetServer.isEmpty() || playerServer.isEmpty()) return;

        Component prefix = provider.getPrefix(target), suffix = provider.getSuffix(target);
        Component name = Component.text(target.getUsername(), NamedTextColor.GRAY);
        Component displayName = prefix.append(name).append(suffix);

        int gamemode = targetServer.get().getServerInfo().equals(playerServer.get().getServerInfo()) ? 1 : 3;
        Optional<TabListEntry> entry = player.getTabList().getEntry(target.getUniqueId());

        if (entry.isPresent()) {
            entry.get().setDisplayName(displayName).setGameMode(gamemode);
        } else {
            TabListEntry.Builder builder = TabListEntry.builder();

            builder.latency(42).profile(target.getGameProfile());
            builder.displayName(displayName).gameMode(gamemode);

            player.getTabList().addEntry(builder.tabList(player.getTabList()).build());
        }
    }
}
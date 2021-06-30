package tk.booky.stl.prefix.luckperms;
// Created by booky10 in SimpleTablist (16:04 30.06.21)

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.jetbrains.annotations.NotNull;
import tk.booky.stl.prefix.PrefixProvider;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class LuckPermsPrefixProvider implements PrefixProvider {

    private static final Duration CACHE_TIME = Duration.of(30, ChronoUnit.SECONDS);
    private static final CacheBuilder<Object, Object> BUILDER = CacheBuilder.newBuilder().expireAfterWrite(CACHE_TIME);

    private static final LoadingCache<UUID, Component> PREFIX = BUILDER.build(new LuckPermsLoader(CachedMetaData::getPrefix));
    private static final LoadingCache<UUID, Component> SUFFIX = BUILDER.build(new LuckPermsLoader(CachedMetaData::getSuffix));

    @Override
    public @NotNull Component getPrefix(Player player) {
        try {
            return PREFIX.get(player.getUniqueId());
        } catch (ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public @NotNull Component getSuffix(Player player) {
        try {
            return SUFFIX.get(player.getUniqueId());
        } catch (ExecutionException exception) {
            throw new RuntimeException(exception);
        }
    }
}
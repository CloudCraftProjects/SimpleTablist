package tk.booky.stl.prefix.luckperms;
// Created by booky10 in SimpleTablist (16:17 30.06.21)

import com.google.common.cache.CacheLoader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.function.Function;

public class LuckPermsLoader extends CacheLoader<UUID, Component> {

    private static final UserManager USER_MANAGER = LuckPermsProvider.get().getUserManager();
    private final Function<CachedMetaData, String> function;

    public LuckPermsLoader(Function<CachedMetaData, String> function) {
        this.function = function;
    }

    @Override
    public Component load(@NotNull UUID uuid) {
        User user = USER_MANAGER.getUser(uuid);

        if (user == null) {
            return Component.empty();
        } else {
            String prefix = function.apply(user.getCachedData().getMetaData());

            if (prefix == null) {
                return Component.empty();
            } else {
                return LegacyComponentSerializer.legacyAmpersand().deserialize(prefix);
            }
        }
    }
}
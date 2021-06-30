package tk.booky.stl.commands;
// Created by booky10 in SimpleTablist (15:41 30.06.21)

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.RawCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import tk.booky.stl.SimpleTablistMain;
import tk.booky.stl.utils.SimpleTablistManager;

import java.util.Collections;
import java.util.List;

public class ReloadCommand implements RawCommand {

    public static CommandMeta constructMeta(CommandManager manager) {
        return manager.metaBuilder("reload-stl").build();
    }

    @Override
    public void execute(Invocation invocation) {
        SimpleTablistManager manager = SimpleTablistMain.getInstance().getManager();

        manager.reloadConfiguration();
        manager.reloadHeaderFooter();
        manager.reloadAllTablists();

        invocation.source().sendMessage(Component.text("The header, footer and display names have been updated!", NamedTextColor.GREEN));
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return Collections.emptyList();
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("stl.command.reload");
    }
}
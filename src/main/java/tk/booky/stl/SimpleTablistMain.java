package tk.booky.stl;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;
import tk.booky.stl.commands.ReloadCommand;
import tk.booky.stl.config.ConfigurationReader;
import tk.booky.stl.config.TabListConfiguration;
import tk.booky.stl.listener.ServerConnectedListener;
import tk.booky.stl.listener.TabListUpdateListener;
import tk.booky.stl.utils.SimpleTablistManager;
import tk.booky.stl.utils.TablistUpdaterRunnable;

import java.nio.file.Path;

@Plugin(
        id = "simpletablist",
        name = "SimpleTablist",
        version = "@version@",
        description = "Just a simple Tablist plugin",
        url = "https://booky.tk/",
        authors = "booky10"
)
public class SimpleTablistMain {

    private static SimpleTablistMain instance;
    private final Logger logger;
    private final ProxyServer server;
    private final Path dataDirectory;
    private final ConfigurationReader<TabListConfiguration> reader = new ConfigurationReader<>();
    private TabListConfiguration config;
    private SimpleTablistManager manager;

    @Inject
    public SimpleTablistMain(Logger logger, ProxyServer server, @DataDirectory Path dataDirectory) {
        this.logger = logger;
        this.server = server;
        this.dataDirectory = dataDirectory;

        instance = this;
    }

    public static SimpleTablistMain getInstance() {
        return instance;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register(ReloadCommand.constructMeta(server.getCommandManager()), new ReloadCommand());

        server.getEventManager().register(this, new TabListUpdateListener());
        server.getEventManager().register(this, new ServerConnectedListener());

        manager = new SimpleTablistManager(server, null);
        manager.reloadConfiguration().reloadHeaderFooter();

        new TablistUpdaterRunnable(manager).start(this, server.getScheduler());
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getServer() {
        return server;
    }

    public Path getDataDirectory() {
        return dataDirectory;
    }

    public SimpleTablistManager getManager() {
        return manager;
    }

    public ConfigurationReader<TabListConfiguration> getReader() {
        return reader;
    }

    public TabListConfiguration getConfig() {
        return config;
    }

    public void setConfig(TabListConfiguration config) {
        this.config = config;
    }
}

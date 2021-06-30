package tk.booky.stl.listener;
// Created by booky10 in SimpleTablist (15:22 30.06.21)

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerPostConnectEvent;
import tk.booky.stl.SimpleTablistMain;

public class ServerConnectedListener {

    @Subscribe
    public void onServerConnected(ServerPostConnectEvent event) {
        SimpleTablistMain.getInstance().getManager().updateTablist(event.getPlayer());
    }
}
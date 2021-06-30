package tk.booky.stl.listener;
// Created by booky10 in SimpleTablist (16:52 30.06.21)

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.TabListUpdateEvent;

public class TabListUpdateListener {

    @Subscribe
    public void onTabListUpdate(TabListUpdateEvent event) {
        event.setResult(ResultedEvent.GenericResult.denied());
    }
}
package tk.booky.stl.listener;
// Created by booky10 in SimpleTablist (16:52 30.06.21)

import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.TabListUpdateEvent;

public class TabListUpdateListener {

    @Subscribe
    public void onTabListUpdate(TabListUpdateEvent event) {
        switch (event.getAction()) {
            case ADD_PLAYER:
            case REMOVE_PLAYER:
                event.setResult(ResultedEvent.GenericResult.allowed());
                break;
            case UPDATE_GAMEMODE:
            case UPDATE_LATENCY:
            case UPDATE_DISPLAY_NAME:
                event.setResult(ResultedEvent.GenericResult.denied());
                break;
            default:
                throw new IllegalStateException("You have completed the challenge [How Did We Get Here?]");
        }
    }
}
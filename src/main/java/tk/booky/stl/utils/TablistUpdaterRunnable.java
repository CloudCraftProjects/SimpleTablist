package tk.booky.stl.utils;
// Created by booky10 in SimpleTablist (17:17 30.06.21)

import com.velocitypowered.api.scheduler.ScheduledTask;
import com.velocitypowered.api.scheduler.Scheduler;

import java.util.concurrent.TimeUnit;

public class TablistUpdaterRunnable implements Runnable {

    private final SimpleTablistManager manager;

    public TablistUpdaterRunnable(SimpleTablistManager manager) {
        this.manager = manager;
    }

    public ScheduledTask start(Object plugin, Scheduler scheduler) {
        return scheduler.buildTask(plugin, this).repeat(3, TimeUnit.SECONDS).schedule();
    }

    @Override
    public void run() {
        manager.reloadAllTablists();
    }
}
package com.iskyify.core.timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class Timer {

    private int time;
    private Plugin plugin;
    private BukkitRunnable task;

    public Timer(Plugin plugin, int time) {
        this.plugin = plugin;
        this.time = time;
    }

    public void start() {
        task = new BukkitRunnable() {
            public void run() {
                if (time <= 0) {
                    onEnd();
                    cancel();
                    return;
                }
                onExecute(time--);
            }
        };
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, time, time);
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(task.getTaskId());
    }

    public abstract void onStart();
    public abstract void onEnd();
    public abstract void onExecute(int time);
}
package com.iskyify.core.timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Used to create tasks or run something async
 *
 * @author Majrly
 * @since 0.0.1
 */
public abstract class Task {

    private int interval;
    private int delay;
    private Plugin plugin;
    private BukkitRunnable task;

    public Task(Plugin plugin, int interval, int delay) {
        this.plugin = plugin;
        this.interval = interval;
        this.delay = delay;
    }

    public void init() {
        task = new BukkitRunnable() {
            public void run() {
                exec();
            }
        };
    }

    public void start() {
        this.task.runTaskTimer(plugin, interval, delay);
    }

    public void cancel() {
        this.task.cancel();
    }

    public abstract void exec();
}
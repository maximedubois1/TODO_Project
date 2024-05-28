package com.sp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PeriodicEnergyRegain {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Regain energy");
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, 10, java.util.concurrent.TimeUnit.SECONDS);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            scheduler.shutdown();
        }
    }

}

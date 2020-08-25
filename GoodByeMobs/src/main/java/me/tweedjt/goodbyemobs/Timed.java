package me.tweedjt.goodbyemobs;

import me.tweedjt.goodbyemobs.mobs.Near;

public class Timed {
    public static void Run() {

        // Process any nearby entities
        Near.process();

    }
}

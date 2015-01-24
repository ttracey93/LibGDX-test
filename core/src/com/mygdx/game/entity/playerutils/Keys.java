package com.mygdx.game.entity.playerutils;

import java.util.Arrays;

/**
 * Created by Dubforce on 1/22/2015.
 */
public class Keys {
    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final Integer LEFT = 0;
    public static final Integer RIGHT = 1;
    public static final Integer JUMP = 2;

    public static final Integer numKeys = 3;

    static {
        keys = new boolean[numKeys];
        pkeys = new boolean[numKeys];
    }

    public static void update() {
        System.arraycopy(keys, 0, pkeys, 0, numKeys);
    }

    public static void setKeyState(int keyCode, boolean state) {
        keys[keyCode] = state;
    }

    public static boolean keyDown(int keyCode) {
        return keys[keyCode];
    }

    public static boolean keyPressed(int keyCode) {
        return keys[keyCode] && !pkeys[keyCode];
    }

    public static boolean keyReleased(int keyCode) {
        return !keys[keyCode] && pkeys[keyCode];
    }
}

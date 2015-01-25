package com.mygdx.game.collision;

/**
 * Created by dubforce on 1/24/15.
 */
public interface ICollisionMask {
    public static final short PLAYER = 0x0001;
    public static final short GROUND = 0x0002;
    public static final short WALL = 0x0004;
    public static final short ITEM = 0x0008;
    public static final short ENEMY = 0x0016;
    public static final short DOOR = 0x0032;
}

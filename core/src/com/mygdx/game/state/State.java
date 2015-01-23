package com.mygdx.game.state;

import com.mygdx.game.Level;

/**
 * Created by Dubforce on 1/21/2015.
 */
public abstract class State {
    protected Level level;

    public Level getLevel() {
        return level;
    }
}

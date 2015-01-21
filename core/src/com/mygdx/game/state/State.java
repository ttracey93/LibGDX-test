package com.mygdx.game.state;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dubforce on 1/21/2015.
 */
public abstract class State implements InputProcessor
{
    public abstract void update(float deltaTime);
    public abstract void draw();
}

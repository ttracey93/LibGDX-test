package com.mygdx.game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Created by Dubforce on 1/21/2015.
 */
public abstract class Entity {
    private Body body;
    private FixtureDef fixtureDef;
    private Sprite sprite;

    public abstract void update(float deltaTime);
    public abstract void draw();
}

package com.mygdx.game.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;

/**
 * Created by dubforce on 1/15/15.
 */
public class GameObject implements IGameObject {
    protected Boolean simiulatePhysics;
    protected Boolean canCollide;
    protected Shape2D collider;

    @Override
    public void update(float elapsedTime) {

    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}

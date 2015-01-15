package com.mygdx.game.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dubforce on 1/15/15.
 */
public interface IGameObject {
    void update(float elapsedTime);
    void draw(SpriteBatch batch);
}

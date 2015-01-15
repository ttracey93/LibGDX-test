package com.mygdx.game.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.component.subcomponent.Player;

/**
 * Created by dubforce on 1/15/15.
 */
public class World implements IGameObject{
    private Player player;

    public World() {
        player = new Player();
    }

    //elapsed time since last call
    public void update(float elapsedTime) {
        player.update(elapsedTime);
    }

    //batch is already opened and will be ended later
    public void draw(SpriteBatch batch) {
        player.draw(batch);
    }
}

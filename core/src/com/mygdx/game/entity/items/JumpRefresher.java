package com.mygdx.game.entity.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.entity.Entity;

/**
 * Created by dubforce on 1/25/15.
 */
public class JumpRefresher extends Entity{
    public JumpRefresher(MapObject mapObject) {
        Texture texture = new Texture(Gdx.files.internal("Base/Tiles32/particleStar.png"));
        setSprite(new Sprite(texture));



        texture.dispose();
    }

    @Override
    public void update(float deltaTime) {
        //do nothing so far
    }

    @Override
    public void draw() {

    }
}

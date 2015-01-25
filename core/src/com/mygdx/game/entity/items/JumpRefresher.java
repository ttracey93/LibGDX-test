package com.mygdx.game.entity.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Level;
import com.mygdx.game.collision.ICollisionMask;
import com.mygdx.game.entity.Entity;

/**
 * Created by dubforce on 1/25/15.
 */
public class JumpRefresher extends Entity{
    public JumpRefresher(MapObject mapObject, World world) {
        Texture texture = new Texture(Gdx.files.internal("Base/Tiles32/particleStar.png"));
        setSprite(new Sprite(texture));

        Shape shape = Level.getRectangle((RectangleMapObject)mapObject);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bd);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = ICollisionMask.ITEM;
        fixtureDef.filter.maskBits = ICollisionMask.PLAYER;

        body.createFixture(fixtureDef);

        body.getFixtureList().first().setFriction(0);

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

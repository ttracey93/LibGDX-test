package com.mygdx.game.entity.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Level;
import com.mygdx.game.collision.ICollisionMask;
import com.mygdx.game.entity.Entity;

import java.util.Map;

/**
 * Created by dubforce on 1/25/15.
 */
public class JumpRefresher extends Entity{
    private SpriteBatch spriteBatch;

    public JumpRefresher(MapObject mapObject, World world, SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;

        RectangleMapObject rectangleMapObject = (RectangleMapObject)mapObject;

        Texture texture = new Texture(Gdx.files.internal("Base/Items/star.png"));
        setSprite(new Sprite(texture));

        Shape shape;
        shape = Level.getRectangle((RectangleMapObject)mapObject);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bd);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = ICollisionMask.ITEM;
        fixtureDef.filter.maskBits = ICollisionMask.PLAYER;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);

        body.getFixtureList().first().setFriction(0);
        shape.dispose();

        sprite.setPosition(rectangleMapObject.getRectangle().getX(), rectangleMapObject.getRectangle().getY());
    }

    @Override
    public void update(float deltaTime) {
        //do nothing so far
    }

    @Override
    public void draw() {
        spriteBatch.draw(sprite.getTexture(), sprite.getX(), sprite.getY());
    }
}

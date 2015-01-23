package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Level;
import com.mygdx.game.entity.playerutils.Keys;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Player extends Entity {
    enum STATE{standing, walkingLeft, walkingRight}
    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture texture;
    STATE movementState;
    private Animation animation;
    private TextureAtlas textureAtlas;
    private float elapsedTime = 0;
    private Body body;
    private float jumpForce = 300f;
    private float doubleJumpForce = 500f;

    public Player(SpriteBatch spriteBatch)
    {
        texture = new Texture(Gdx.files.internal("Base/Player/p1_front.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas"));
        animation = new Animation(1/30f,textureAtlas.getRegions());
        sprite = new Sprite(texture);
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void update(float deltaTime) {
        //Update players location
        if(Keys.keyPressed(Keys.JUMP)) {
            body.applyForceToCenter(0, jumpForce, true);
        }

        float x = (body.getPosition().x / Level.METERS_PER_PIXEL) - sprite.getWidth()/2;
        float y = (body.getPosition().y / Level.METERS_PER_PIXEL) - sprite.getHeight()/2;

        System.out.println("x: " + x);
        System.out.println("y: " + y);

        sprite.setPosition(x, y);
    }

    @Override
    public void draw() {
        spriteBatch.begin();

        spriteBatch.draw(sprite, sprite.getX(), sprite.getY());

        spriteBatch.end();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }
}

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
import com.mygdx.game.entity.playerutils.Keys;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Player extends Entity implements InputProcessor {

    enum STATE{standing, walkingLeft, walkingRight}
    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture texture;
    STATE movementState;
    private Animation animation;
    private TextureAtlas textureAtlas;
    private float elapsedTime = 0;
    private Body body;
    private static float MAX_VELOCITY = 100.0f;
    private boolean[] keys;

    public Player(SpriteBatch spriteBatch)
    {
        texture = new Texture(Gdx.files.internal("Base/Player/p1_front.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas"));
        animation = new Animation(1/30f,textureAtlas.getRegions());
        sprite = new Sprite(texture);
        this.spriteBatch = spriteBatch;

        keys = new boolean[Keys.numKeys];

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float deltaTime) {
        //Update players location
        Vector2 velocity = body.getLinearVelocity();
        Vector2 position = body.getPosition();

        if(keys[Keys.RIGHT] && velocity.x < MAX_VELOCITY) {
            body.applyLinearImpulse(800f, 0, position.x, position.y, true);
        }
        else if(keys[Keys.LEFT] && velocity.x > -MAX_VELOCITY) {
            body.applyLinearImpulse(-800f, 0, position.x, position.y, true);
        }

        sprite.setPosition(body.getPosition().x - sprite.getWidth()/2,
                body.getPosition().y - sprite.getHeight()/2);
    }

    @Override
    public void draw() {
        spriteBatch.begin();

        spriteBatch.draw(sprite, body.getPosition().x, body.getPosition().y);

        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT)
            keys[Keys.RIGHT] = true;
        else if(keycode == Input.Keys.LEFT)
            keys[Keys.LEFT] = true;
        else if(keycode == Input.Keys.SPACE)
            body.applyForceToCenter(0, 300, true);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.RIGHT)
            keys[Keys.RIGHT] = false;
        else if(keycode == Input.Keys.LEFT)
            keys[Keys.LEFT] = false;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
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

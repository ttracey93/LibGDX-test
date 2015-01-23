package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Console;

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

    public Player(SpriteBatch spriteBatch)
    {
        texture = new Texture(Gdx.files.internal("Base/Player/p1_front.png"));
        textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas"));
        animation = new Animation(1/30f,textureAtlas.getRegions());
        sprite = new Sprite(texture);
        this.spriteBatch = spriteBatch;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float deltaTime) {
        //Update players location
        if(movementState == STATE.walkingRight) {
            body.setLinearVelocity(100f, 0f);
        }
        if(movementState == STATE.walkingLeft) {
            body.setLinearVelocity(-100f, 0f);
        }

        sprite.setPosition(body.getPosition().x - sprite.getWidth()/2,
                body.getPosition().y - sprite.getHeight()/2);
    }

    @Override
    public void draw() {
        spriteBatch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(movementState == STATE.walkingRight || movementState == STATE.walkingLeft) {
            spriteBatch.draw(animation.getKeyFrame(elapsedTime, true), sprite.getX() , sprite.getY());
        }
        else
        {
            sprite.draw(spriteBatch);
        }
        //sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.RIGHT)
            movementState = STATE.walkingRight;
        else if(keycode == Input.Keys.LEFT)
            movementState = STATE.walkingLeft;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT) {
            body.setLinearVelocity(0f,0f);
            movementState = STATE.standing;
        }
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

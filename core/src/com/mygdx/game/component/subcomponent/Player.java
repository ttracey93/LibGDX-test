package com.mygdx.game.component.subcomponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.component.GameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dubforce on 1/15/15.
 */
public class Player extends GameObject implements InputProcessor {
    private Map<String,Animation> animations = new HashMap<String, Animation>();
    private float totalElapsedTime = 0.0f;

    public Player() {
        FileHandle file = Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas");
        TextureAtlas textureAtlas = new TextureAtlas(file);

        Animation walkingAnimation = new Animation(1/20f, textureAtlas.getRegions());
        animations.put("walking", walkingAnimation);
    }

    @Override
    public void update(float elapsedTime) {
        //nothing yet
        totalElapsedTime += elapsedTime;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animations.get("walking").getKeyFrame(totalElapsedTime, true), 0, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
}

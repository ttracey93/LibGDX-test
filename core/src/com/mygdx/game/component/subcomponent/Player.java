package com.mygdx.game.component.subcomponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.component.GameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dubforce on 1/15/15.
 */
public class Player extends GameObject implements InputProcessor {
    public enum State {
        STANDING,WALKING,JUMPING,FALLING,HIT,CROUCHING,DYING,DEAD
    }

    private Map<String,Animation> animations = new HashMap<String, Animation>();
    private Texture standingTexture;
    private float totalElapsedTime = 0.0f;
    private State state;
    private Boolean left = false;
    private Boolean right = false;
    private Sound jumpSound;

    public Player() {
        state = State.STANDING;

        FileHandle file = Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas");
        TextureAtlas textureAtlas = new TextureAtlas(file);

        Animation walkingAnimation = new Animation(1/20f, textureAtlas.getRegions());
        animations.put("walking", walkingAnimation);

        standingTexture = new Texture(Gdx.files.internal("Base/Player/p1_stand.png"));

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("../Sounds/jump.wav"));

        setLocation(new Vector2(0, 70));
        setMoveSpeed(5);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float elapsedTime) {
        //nothing yet
        totalElapsedTime += elapsedTime;

        //TODO: add switch?
        switch(state) {
            case WALKING:
                Double walkingX = Math.floor(left ? getMoveSpeed() * -1 : right ? getMoveSpeed() : 0);
                location.add(new Vector2(walkingX.intValue(), 0));
        }

        //todo: remove
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            state = State.STANDING;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        switch(state) {
            case STANDING:
                batch.draw(standingTexture, getLocation().x, getLocation().y);
                break;
            case WALKING:
                TextureRegion frame = animations.get("walking").getKeyFrame(totalElapsedTime, true);

                float x = left ? getLocation().x + frame.getRegionWidth() : getLocation().x;

                batch.draw(frame, x, getLocation().y, getLocation().x, getLocation().y,
                        left ? -frame.getRegionWidth() : frame.getRegionWidth(), (float)frame.getRegionHeight(), 1.0f, 1.0f, 0.0f);
                break;
            default:
                batch.draw(standingTexture, getLocation().x, getLocation().y);
                break;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown");

        switch(keycode) {
            case Input.Keys.LEFT:
                left = true;
                state = State.WALKING;
                break;
            case Input.Keys.RIGHT:
                right = true;
                state = State.WALKING;
                break;
            case Input.Keys.SPACE:
                jumpSound.play();
                break;
            default:
                //do nothing yet
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                left = false;
                break;
            case Input.Keys.RIGHT:
                right = false;
                break;
            default:
                //do nothing yet
                break;
        }

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

    //Accessors/Mutators
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

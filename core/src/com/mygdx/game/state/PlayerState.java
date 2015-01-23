package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Level;
import com.mygdx.game.SaltFactory;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class PlayerState extends State {
    private SaltFactory parent;

    public PlayerState(SaltFactory parent, String levelName)
    {
        this.parent = parent;
        level = new Level(levelName);
        level.initializePlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE)
            parent.startMenu();
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

    public void stop()
    {
        //Stop the game. This happens when the user decides to leave.
    }
}

package com.mygdx.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.ILevelName;
import com.mygdx.game.Level;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class MainMenu extends State {
    public MainMenu() {
        level = new Level(ILevelName.MAIN_MENU);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float deltaTime) {
        level.getCamera().update();
    }

    @Override
    public void draw() {
        level.getRenderer().render();
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
        if(button == Input.Buttons.LEFT) {
            level = new Level(ILevelName.TEST2);
        }

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

package com.mygdx.game.state.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Playing extends GameState {
    public Playing() {
        setCamera(new OrthographicCamera());
        getCamera().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getCamera().update();
        setMap(new TmxMapLoader().load("../Maps/test.tmx"));
        setRenderer(new OrthogonalTiledMapRenderer(getMap()));
    }

    @Override
    public void update(float deltaTime) {
        //no update code yet
    }

    @Override
    public void draw() {
        getCamera().update();
        getRenderer().setView(getCamera());
        getRenderer().render();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if(keycode == Input.Keys.LEFT)
            getCamera().translate(-32, 0);
        if(keycode == Input.Keys.RIGHT)
            getCamera().translate(32, 0);

        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}

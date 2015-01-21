package com.mygdx.game.state.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.state.manager.GameStateManager;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class MainMenu extends GameState {
    public MainMenu(GameStateManager gameStateManager) {
        super(gameStateManager);
        Texture texture = new Texture(Gdx.files.internal("../Images/mainMenu.png"));
        setBackground(texture);
    }

    @Override
    public void update(float deltaTime) {
        //update code
    }

    @Override
    public void draw() {
        //open spritebatch for drawing
        spriteBatch.begin();

        spriteBatch.draw(getBackground(), 0, 0);

        //close spritebatch
        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode)
    {
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
        if(button == Input.Buttons.LEFT) {
            gameStateManager.setCurrentState(new Playing());
        }

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

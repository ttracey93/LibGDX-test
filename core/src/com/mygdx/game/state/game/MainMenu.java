package com.mygdx.game.state.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class MainMenu extends GameState {
    public MainMenu() {
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
}

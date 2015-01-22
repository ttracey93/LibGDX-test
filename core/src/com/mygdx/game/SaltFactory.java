package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.state.MainMenu;
import com.mygdx.game.state.PlayerState;
import com.mygdx.game.state.State;

public class SaltFactory extends ApplicationAdapter {
	private SpriteBatch batch;
	private State gameState;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = new MainMenu(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameState.update(Gdx.graphics.getDeltaTime());
		gameState.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.graphics.setDisplayMode(width, height, false);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	public void startGame(String levelName)
	{
		gameState = new PlayerState(this, levelName);
	}

	public void startMenu()
	{
		gameState = new MainMenu(this);
	}

}

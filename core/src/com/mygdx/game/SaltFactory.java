package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.state.manager.GameStateManager;

public class SaltFactory extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gameStateManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//allow manager to update state
		gameStateManager.update(Gdx.graphics.getDeltaTime());
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
}

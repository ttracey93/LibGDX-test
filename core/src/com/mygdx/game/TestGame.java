package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.component.World;

public class TestGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private World world;

	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//open spritebatch for batch rendering
		batch.begin();

		world.update(Gdx.graphics.getDeltaTime());
		world.draw(batch);

		//close spritebatch
		batch.end();
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

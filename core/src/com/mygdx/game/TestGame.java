package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TestGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime;
	private int xTranslation = 1;

	@Override
	public void create () {
		batch = new SpriteBatch();
		textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas"));
		animation = new Animation(1/20f, textureAtlas.getRegions());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//open spritebatch for batch rendering
		batch.begin();

		//draw appropriate animation frame
		elapsedTime += Gdx.graphics.getDeltaTime();

		handleInput();

		batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);

		//close spritebatch
		batch.end();
	}

	private void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			animation.getKeyFrame(elapsedTime).flip(true, false);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			animation.getKeyFrame(elapsedTime).flip(false, false);
		}
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
		textureAtlas.dispose();
	}
}

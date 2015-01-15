package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class TestGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Sprite sprite;
	private int currentFrame = 1;
	private String currentAtlasKey = "0001";

	@Override
	public void create () {
		batch = new SpriteBatch();

		textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/p1_walk/PNG/test/spritesheet.atlas"));
		TextureAtlas.AtlasRegion region = textureAtlas.findRegion(currentAtlasKey);
		sprite = new Sprite(region);

		Timer.schedule(new Task() {
			@Override
			public void run() {
				currentFrame++;
				if (currentFrame > 11) {
					currentFrame = 1;
				}

				// ATTENTION! String.format() doesnt work under GWT for god knows why...
				currentAtlasKey = String.format("%04d", currentFrame);
				sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
			}
		} ,0,1/20.0f); // 1/30.0f = 30 frames per second
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//open spritebatch for batch rendering
		batch.begin();

		//this seems odd
		//what it does is call the batch's draw function
		//with the internal texture and some other info
		sprite.draw(batch);

		//close spritebatch
		batch.end();
	}
}

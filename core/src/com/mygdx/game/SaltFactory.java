package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.playerutils.Keys;
import com.mygdx.game.state.PlayerState;
import com.mygdx.game.state.State;

public class SaltFactory extends ApplicationAdapter {
	private SpriteBatch batch;
	private State gameState;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = new PlayerState(this,ILevelName.WORLD1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//update/draw level
		gameState.getLevel().update(Gdx.graphics.getDeltaTime());
		gameState.getLevel().draw();

		//handle input
		Keys.update();

		if(gameState.getLevel().doorOpen) {
			if(Player.levelToLoad != null) {
				startGame(Player.levelToLoad);
			}
		}
	}

	public void startGame(String levelName) {
		gameState = new PlayerState(this, levelName);
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

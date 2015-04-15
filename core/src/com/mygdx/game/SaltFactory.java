package com.mygdx.game;

import Menu.MainMenuScreen;
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
	private boolean mainMenu;
	private MainMenuScreen MenuScreen;
	public static int numDeaths = 0;


	@Override
	public void create () {

		mainMenu = true;
		batch = new SpriteBatch();
		gameState = new PlayerState(this, ILevelName.HUBWORLD);
		this.MenuScreen = new MainMenuScreen();
	}

	@Override
	public void render () {
		if(mainMenu){
			MenuScreen.draw();
			MenuScreen.update(Gdx.graphics.getDeltaTime());

			if(MenuScreen.checkEscape())mainMenu = false;
		}else {
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			//update/draw level
			gameState.getLevel().update(Gdx.graphics.getDeltaTime());
			gameState.getLevel().draw();

			//handle input
			Keys.update();

			if (gameState.getLevel().doorOpen) {
				if (Player.levelToLoad != null) {
					gameState.getLevel().audio.stopMusic();
					startGame(Player.levelToLoad);
				}

			}
			if (gameState.getLevel().dead) {
				gameState.getLevel().audio.stopMusic();
				numDeaths++;
				startGame(gameState.getLevel().currentLevel);
			}

			if (Keys.keyDown(Keys.MENU)) {
				gameState.getLevel().audio.stopMusic();
				startGame(ILevelName.HUBWORLD);
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

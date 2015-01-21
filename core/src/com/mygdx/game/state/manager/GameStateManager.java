package com.mygdx.game.state.manager;

import com.mygdx.game.state.game.GameState;
import com.mygdx.game.state.game.MainMenu;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class GameStateManager extends StateManager {
    private GameState currentState;

    public GameStateManager() {
        currentState = new MainMenu();
    }

    @Override
    public void update(float deltaTime)
    {
        currentState.update(deltaTime);
        currentState.draw();
    }

    public GameState getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(GameState currentState)
    {
        this.currentState = currentState;
    }
}

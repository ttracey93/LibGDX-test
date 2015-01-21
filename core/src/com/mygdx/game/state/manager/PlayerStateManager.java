package com.mygdx.game.state.manager;

import com.mygdx.game.state.player.PlayerState;
import com.mygdx.game.state.player.Uninitialized;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class PlayerStateManager {
    private PlayerState playerState;

    public PlayerStateManager()
    {
        playerState = new Uninitialized();
    }
}

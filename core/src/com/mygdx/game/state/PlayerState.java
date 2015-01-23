package com.mygdx.game.state;

import com.mygdx.game.Level;
import com.mygdx.game.SaltFactory;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class PlayerState extends State {
    private SaltFactory parent;

    public PlayerState(SaltFactory parent, String levelName)
    {
        this.parent = parent;
        level = new Level(levelName);
        level.initializePlayer();
    }

    public void stop()
    {
        //Stop the game. This happens when the user decides to leave.
    }
}

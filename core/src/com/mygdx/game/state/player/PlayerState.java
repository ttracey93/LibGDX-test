package com.mygdx.game.state.player;// File:            PlayerState.java

import com.mygdx.game.state.State;

// Created:         1/21/15
// Last Modified:   $Date$
// Revision:        $Rev$
// Author:          <a href="mailto:ttracey@etranscor.com>">Tyler "TBone" Tracey</a>
//
// (c) 2015 Transcor, Inc.
public abstract class PlayerState extends State
{
    public enum Orientation {
        LEFT,RIGHT,FORWARD,BACKWARD
    }

    private Orientation orientation;

    public PlayerState(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }
}

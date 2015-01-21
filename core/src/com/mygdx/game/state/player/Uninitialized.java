package com.mygdx.game.state.player;// File:            Uninitialized.java

// Created:         1/21/15
// Last Modified:   $Date$
// Revision:        $Rev$
// Author:          <a href="mailto:ttracey@etranscor.com>">Tyler "TBone" Tracey</a>
//
// (c) 2015 Transcor, Inc.
public class Uninitialized extends PlayerState
{
    @Override
    public void update(float deltaTime)
    {
        //player does not update if uninitialized
    }

    @Override
    public void draw()
    {
        //player will not be drawn if uninitialized
    }

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}

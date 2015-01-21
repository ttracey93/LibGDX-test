package com.mygdx.game.state.game;// File:            GameState.java

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.state.State;

// Created:         1/21/15
// Last Modified:   $Date$
// Revision:        $Rev$
// Author:          <a href="mailto:ttracey@etranscor.com>">Tyler "TBone" Tracey</a>
//
// (c) 2015 Transcor, Inc.
public abstract class GameState extends State {
    //camera/art assets
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //separate gdx batch
    protected SpriteBatch spriteBatch;

    //world/physics assets
    private World world;
    private Texture background;

    public GameState() {
        //construct/initialize object
        spriteBatch = new SpriteBatch();
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

    public void setCamera(OrthographicCamera camera)
    {
        this.camera = camera;
    }

    public TiledMap getMap()
    {
        return map;
    }

    public void setMap(TiledMap map)
    {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getRenderer()
    {
        return renderer;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer)
    {
        this.renderer = renderer;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public Texture getBackground()
    {
        return background;
    }

    public void setBackground(Texture background)
    {
        this.background = background;
    }
}

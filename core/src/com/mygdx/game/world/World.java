package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.component.IGameObject;
import com.mygdx.game.component.subcomponent.Player;

/**
 * Created by dubforce on 1/15/15.
 */
public class World implements IGameObject {
    private Player player;
    private Level level;
    private TiledMap map;
    private OrthographicCamera camera;
    private TiledMapRenderer renderer;

    public World() {
        player = new Player();
        map = new TiledMap();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        map = new TmxMapLoader().load("../Maps/test.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
    }

    //elapsed time since last call
    public void update(float elapsedTime) {
        player.update(elapsedTime);
    }

    //batch is already opened and will be ended later
    public void draw(SpriteBatch batch) {
        camera.update();
        renderer.setView(camera);
        renderer.render();

        SpriteBatch b = new SpriteBatch();

        b.begin();

        player.draw(b);

        b.end();
    }
}

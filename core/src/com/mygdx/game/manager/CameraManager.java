package com.mygdx.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dubforce on 1/23/15.
 */
public class CameraManager {
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private static Integer tilePixelWidth;
    private static Integer tilePixelHeight;
    private static Integer mapWidth;
    private static Integer mapHeight;
    private static Integer worldWidth;
    private static Integer worldHeight;

    private static Integer viewBoundsOffset = 100;

    public CameraManager(OrthographicCamera camera, OrthogonalTiledMapRenderer renderer) {
        this.camera = camera;
        this.renderer = renderer;
        initializeMapVariables(renderer.getMap());
    }

    public void updateCamera(float x, float y) {
        if(x < camera.viewportWidth / 2) {
            x = camera.viewportWidth / 2;
        }

        if(y < camera.viewportHeight / 2) {
            y = camera.viewportHeight / 2;
        }

        if(x > worldWidth - camera.viewportWidth / 2) {
            x = worldWidth - camera.viewportWidth / 2;
        }

        if(y > worldHeight - camera.viewportHeight / 2) {
            y = worldHeight - camera.viewportHeight / 2;
        }

        camera.position.set(new Vector2(x, y), 0.0f);
        camera.update();

        renderer.setView(camera);
        renderer.getViewBounds().setWidth(Gdx.graphics.getWidth());
        renderer.getViewBounds().setHeight(Gdx.graphics.getHeight());
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setMap(TiledMap map) {
        renderer.setMap(map);
        initializeMapVariables(map);
    }

    private void initializeMapVariables(TiledMap map) {
        MapProperties properties = map.getProperties();

        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);

        mapWidth = properties.get("width", Integer.class);
        mapHeight = properties.get("height", Integer.class);

        worldWidth = tilePixelWidth * mapWidth;
        worldHeight = tilePixelHeight * mapHeight;
    }
}

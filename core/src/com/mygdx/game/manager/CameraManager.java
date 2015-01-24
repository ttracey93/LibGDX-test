package com.mygdx.game.manager;

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

    public CameraManager(OrthographicCamera camera, OrthogonalTiledMapRenderer renderer) {
        this.camera = camera;
        this.renderer = renderer;
        initializeMapVariables(renderer.getMap());
    }

    public void updateCamera(float x, float y) {
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;

        if(x > worldWidth - camera.viewportWidth) {
            x = worldWidth - camera.viewportWidth;
        }

        if(y > worldHeight - camera.viewportHeight) {
            y = worldHeight - camera.viewportHeight;
        }

        System.out.println("final x,y : " + x + ", " + y);

        camera.position.set(new Vector2(x, y), 0.0f);
        camera.update();

        renderer.setView(camera);
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

        System.out.println("tilePixelWidth: " + tilePixelWidth);
        System.out.println("mapWidth = " + mapWidth);
        worldWidth = tilePixelWidth * mapWidth;
        worldHeight = tilePixelHeight * mapHeight;
    }
}

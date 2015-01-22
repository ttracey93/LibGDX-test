package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Player;

import java.util.List;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Level {
    //world
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    //physics
    private World world;
    private static float ppt = 70;

    //entities
    private List<Body> entities;
    private Player player;
    private Body playerBody;

    public Level(String fileName) {
        map = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        renderer.setView(camera);
        camera.update();
        spriteBatch = new SpriteBatch();

        world = new World(new Vector2(0,-98f),false);
        //Create physics bodies for the ground.

        try {
            MapObjects ground = map.getLayers().get("Ground").getObjects();

            for(MapObject object : ground)
            {
                Shape shape;

                if (object instanceof RectangleMapObject) {
                    shape = getRectangle((RectangleMapObject)object);
                }
                else
                    continue;

                BodyDef bd = new BodyDef();
                bd.type = BodyDef.BodyType.StaticBody;
                Body body = world.createBody(bd);
                body.createFixture(shape,1);
                entities.add(body);
            }
        }catch(Exception e){}

        debugRenderer = new Box2DDebugRenderer();
    }

    public void update(float deltaTime)
    {
        if(player != null) {
            world.step(deltaTime,6,2);
            player.update(deltaTime);
        }
        //Update other entities
    }

    public void draw()
    {
        debugMatrix = spriteBatch.getProjectionMatrix().cpy();
        if(player != null) {
            player.getSprite().setPosition(playerBody.getPosition().x - (player.getSprite().getWidth()/2), playerBody.getPosition().y - (player.getSprite().getHeight()/2));
            player.draw();
        }
        debugRenderer.render(world, debugMatrix);


        //Draw other entities
    }


    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void initializePlayer()
    {
        player = new Player(spriteBatch);

        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(100,200);
        playerBody = world.createBody(playerBodyDef);
        PolygonShape playerBox = new PolygonShape();
        playerBox.setAsBox(player.getSprite().getWidth() / 2, player.getSprite().getHeight() /2);
        playerBody.createFixture(playerBox, 0.0f);
        playerBox.dispose();
        player.setBody(playerBody);
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(rectangle.width, rectangle.height);
        return polygon;
    }
}

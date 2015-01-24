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
import com.mygdx.game.entity.playerutils.Keys;
import com.mygdx.game.listeners.InputListener;
import com.mygdx.game.manager.CameraManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Level {
    //scaling factor
    public static float PIXELS_PER_METER = 50;
    public static float METERS_PER_PIXEL = 1/PIXELS_PER_METER;

    //world
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthographicCamera box2DCamera;
    private SpriteBatch spriteBatch;
    private CameraManager cameraManager;
    //private Box2DDebugRenderer debugRenderer;

    Player player;

    //physics
    private World world;

    //entities
    private List<Entity> entities;

    public Level(String fileName) {
        TiledMap map = new TmxMapLoader().load(fileName);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight() / PIXELS_PER_METER);

        renderer.setView(camera);
        camera.update();
        spriteBatch = new SpriteBatch();

        cameraManager = new CameraManager(camera, renderer);

        entities = new ArrayList<Entity>();

        world = new World(new Vector2(0,-50f), true);

        //Create physics bodies for the ground.
        try {
            MapObjects ground = map.getLayers().get("solid").getObjects();


            for(MapObject object : ground)
            {

                if (object instanceof RectangleMapObject) {
                    Shape shape;
                    shape = getRectangle((RectangleMapObject)object);
                    BodyDef bd = new BodyDef();
                    bd.type = BodyDef.BodyType.StaticBody;
                    Body body = world.createBody(bd);
                    body.createFixture(shape, 1);
                    body.getFixtureList().first().setFriction(0);

                }
                else
                    continue;


                //bd.position.set(new Vector2(((RectangleMapObject) object).getRectangle().x / PIXELS_PER_METER, ((RectangleMapObject) object).getRectangle().y / PIXELS_PER_METER));
                //bd.position.set(((RectangleMapObject) object).getRectangle().getX(), ((RectangleMapObject) object).getRectangle().getY());

                //entities.add(body);
            }
        } catch(Exception e){
            System.out.println(e.toString());
        }

      //  debugRenderer = new Box2DDebugRenderer();
    }

    public void update(float deltaTime)
    {
        camera.update();

        world.step(deltaTime,6,2);

        //Update other entities
        for(Entity entity : entities) {
            entity.update(deltaTime);
        }
    }

    public void draw()
    {
        renderer.render();

        renderer.getBatch().begin();

        for(Entity entity : entities) {
            entity.draw();
        }

        renderer.getBatch().end();

//        debugRenderer.render(world, box2DCamera.combined);
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
        player = new Player((SpriteBatch)renderer.getBatch(), cameraManager);

        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(100 / PIXELS_PER_METER, 200 / PIXELS_PER_METER);

        Body playerBody = world.createBody(playerBodyDef);

        PolygonShape playerBox = new PolygonShape();
        playerBox.setAsBox((player.getSprite().getWidth() / 2)/PIXELS_PER_METER, (player.getSprite().getHeight() /2)/PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = playerBox;

        playerBody.createFixture(fixtureDef);
        playerBox.dispose();

        player.setBody(playerBody);

        entities.add(player);

        world.setContactListener(player);
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / PIXELS_PER_METER,
                (rectangle.y + rectangle.height * 0.5f ) / PIXELS_PER_METER);
        polygon.setAsBox(rectangle.width * 0.5f / PIXELS_PER_METER,
                rectangle.height * 0.5f / PIXELS_PER_METER,
                size,
                0.0f);


        //polygon.setAsBox(rectangle.width / PIXELS_PER_METER, rectangle.height / PIXELS_PER_METER);
        //rectangle.setPosition(rectangleObject.getRectangle().x, rectangleObject.getRectangle().y);
        return polygon;
    }
}

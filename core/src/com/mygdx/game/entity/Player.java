package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Level;
import com.mygdx.game.entity.playerutils.Keys;
import com.mygdx.game.manager.CameraManager;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Player extends Entity {
    enum STATE{standing, walkingLeft, walkingRight}
    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture texture;
    STATE movementState;
    private Animation animationRight, animationLeft;
    private TextureAtlas textureAtlas, textureAtlasLeft;
    private float elapsedTime = 0;
    private Body body;
    private float jumpForce = 300f;
    private float doubleJumpForce = 500f;
    private CameraManager cameraManager;
    private float moveForce = 300f;

    public Player(SpriteBatch spriteBatch, CameraManager cameraManager)
    {
        texture = new Texture(Gdx.files.internal("Base/Player/morton/morton_walking1.png"));

        textureAtlas = new TextureAtlas(Gdx.files.internal("Base/Player/morton/right/spritesheet.atlas"));
        animationRight = new Animation(1/6f,textureAtlas.getRegions());

        textureAtlasLeft = new TextureAtlas(Gdx.files.internal("Base/Player/morton/left/spritesheet.atlas"));
        animationLeft = new Animation(1/6f, textureAtlasLeft.getRegions());


        sprite = new Sprite(texture);
        this.spriteBatch = spriteBatch;
        this.cameraManager = cameraManager;
    }

    @Override
    public void update(float deltaTime) {
        //Update players location
        if(Keys.keyPressed(Keys.JUMP)) {
            body.applyForceToCenter(0, jumpForce, true);
        }
        if(Keys.keyDown(Keys.LEFT)) {
            if(body.getLinearVelocity().x > -10)
            body.applyForceToCenter(-moveForce, 0, true);

        }
        if(Keys.keyDown(Keys.RIGHT)) {
            if(body.getLinearVelocity().x < 10)
            body.applyForceToCenter(moveForce, 0, true);
        }

        if(Keys.keyReleased(Keys.LEFT) || Keys.keyReleased(Keys.RIGHT))
        {
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }



        float x = (body.getPosition().x / Level.METERS_PER_PIXEL) - sprite.getWidth()/2;
        float y = (body.getPosition().y / Level.METERS_PER_PIXEL) - sprite.getHeight()/2;

        System.out.println("x: " + x);
        System.out.println("y: " + y);

        sprite.setPosition(x, y);

        //update cameras location
        cameraManager.updateCamera(x, y);
    }

    @Override
    public void draw() {
        //spriteBatch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(Keys.keyDown(Keys.RIGHT))
            spriteBatch.draw(animationRight.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else if(Keys.keyDown(Keys.LEFT))
            spriteBatch.draw(animationLeft.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else
            spriteBatch.draw(sprite, sprite.getX(), sprite.getY());
        //spriteBatch.end();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }
}

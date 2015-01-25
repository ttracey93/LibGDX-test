package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.ILevelName;
import com.mygdx.game.Level;
import com.mygdx.game.collision.ICollisionMask;
import com.mygdx.game.entity.playerutils.Keys;
import com.mygdx.game.listeners.InputListener;
import com.mygdx.game.manager.CameraManager;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Player extends Entity implements ContactListener {
    Sprite sprite;
    SpriteBatch spriteBatch;
    Texture texture;
    private Animation animationRight, animationLeft, animationIdleLeft, animationIdleRight, animationJumpRight, animationJumpLeft;
    private TextureAtlas textureAtlasLeft, textureAtlasIdleLeft, textureAtlasRight, textureAtlasJumpLeft, textureAtlasJumpRight, textureAtlasIdleRight;
    private float elapsedTime = 0;
    private Body body;
    private float jumpVelocity = 12f;
    private float doubleJumpVelocity = 17f;
    private CameraManager cameraManager;
    private float moveForce = 100f;
    private float maxVelocity = 10f;
    private float x, y;
    Level level;
    public boolean dead = false;
    public boolean jumpSound;
    boolean facingRight = false;

    private boolean ignoreGravity = false;
    private boolean onGround = true;
    private boolean canDoubleJump = true;
    private boolean inADoor = false;

    public boolean onDoor = false;
    public static String levelToLoad;

    public Player(Level level,SpriteBatch spriteBatch, CameraManager cameraManager)
    {
        this.level = level;
        texture = new Texture(Gdx.files.internal("Base/Player/morton/idle/idle.png"));

        textureAtlasRight = new TextureAtlas(Gdx.files.internal("Base/Player/morton/right/spritesheet.atlas"));
        animationRight = new Animation(1/6f, textureAtlasRight.getRegions());

        textureAtlasLeft = new TextureAtlas(Gdx.files.internal("Base/Player/morton/left/spritesheet.atlas"));
        animationLeft = new Animation(1/6f, textureAtlasLeft.getRegions());


        textureAtlasIdleLeft = new TextureAtlas(Gdx.files.internal("Base/Player/morton/idle/left/spritesheet.atlas"));
        animationIdleLeft = new Animation(1/2f, textureAtlasIdleLeft.getRegions());

        textureAtlasIdleRight = new TextureAtlas(Gdx.files.internal("Base/Player/morton/idle/spritesheet.atlas"));
        animationIdleRight = new Animation(1/2f, textureAtlasIdleRight.getRegions());


        textureAtlasJumpRight = new TextureAtlas(Gdx.files.internal("Base/Player/morton/jump/spritesheet.atlas"));
        animationJumpRight = new Animation(1/3f, textureAtlasJumpRight.getRegions());

        textureAtlasJumpLeft = new TextureAtlas(Gdx.files.internal("Base/Player/morton/jump/left/spritesheet.atlas"));
        animationJumpLeft = new Animation(1/3f, textureAtlasJumpLeft.getRegions());



        sprite = new Sprite(texture);
        this.spriteBatch = spriteBatch;
        this.cameraManager = cameraManager;

        Gdx.input.setInputProcessor(new InputListener(this));
    }

    @Override
    public void update(float deltaTime) {
        jumpSound = false;
        if(ignoreGravity) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            jumpSound = true;
        }

        //Update players location
        if(Keys.keyPressed(Keys.JUMP)) {
            if(onGround) {
                body.setLinearVelocity(body.getLinearVelocity().x, jumpVelocity);
                jumpSound = true;
                onGround = false;
            }
            else if(canDoubleJump) {
                canDoubleJump = false;
                body.setLinearVelocity(body.getLinearVelocity().x, doubleJumpVelocity);
                jumpSound = true;
            }
        }
        if(Keys.keyDown(Keys.LEFT)) {
            if(body.getLinearVelocity().x > -maxVelocity)
            body.applyForceToCenter(-moveForce, 0, true);
            facingRight = false;
        }
        if(Keys.keyDown(Keys.RIGHT)) {
            if(body.getLinearVelocity().x < maxVelocity)
            body.applyForceToCenter(moveForce, 0, true);
            facingRight = true;
        }

        if(Keys.keyReleased(Keys.LEFT) || Keys.keyReleased(Keys.RIGHT))
        {
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }

        x = (body.getPosition().x / Level.METERS_PER_PIXEL) - sprite.getWidth()/2;
        y = (body.getPosition().y / Level.METERS_PER_PIXEL) - sprite.getHeight()/2;
    }

    @Override
    public void draw() {
        //spriteBatch.begin();
        elapsedTime += Gdx.graphics.getDeltaTime();

        if(!onGround && Keys.keyDown(Keys.LEFT))
            spriteBatch.draw(animationJumpLeft.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else if(!onGround)
            spriteBatch.draw(animationJumpRight.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else if(Keys.keyDown(Keys.RIGHT))
            spriteBatch.draw(animationRight.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else if(Keys.keyDown(Keys.LEFT))
            spriteBatch.draw(animationLeft.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else if(!facingRight)
            spriteBatch.draw(animationIdleLeft.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        else
            spriteBatch.draw(animationIdleRight.getKeyFrame(elapsedTime, true), sprite.getX(), sprite.getY());
        sprite.setPosition(x, y);

        //update cameras location
        cameraManager.updateCamera(x, y);
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

    //contact handling

    @Override
    public void beginContact(Contact contact) {
        Fixture playerFixture = null;
        Fixture opposingFixture = null;

        System.out.println("FA.c: " + contact.getFixtureA().getFilterData().categoryBits);
        System.out.println("FB.c: " + contact.getFixtureB().getFilterData().categoryBits);

        if(contact.getFixtureA().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            playerFixture = contact.getFixtureA();
            opposingFixture = contact.getFixtureB();
        }
        else if(contact.getFixtureB().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            playerFixture = contact.getFixtureB();
            opposingFixture = contact.getFixtureA();

            if(opposingFixture.getFilterData().categoryBits == ICollisionMask.ENEMY) {
                reset();
            }
        }

        Vector2 normalVector = contact.getWorldManifold().getNormal();

        if(playerFixture != null) {
            System.out.println("player category: " + playerFixture.getFilterData().categoryBits);
            System.out.println("opposing category: " + playerFixture.getFilterData().categoryBits);

            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.GROUND) {
                if(normalVector.y > 0) {
                    onGround = true;
                    canDoubleJump = true;
                }
            }

            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.DOOR) {
                onDoor = true;

                try {
                    levelToLoad = opposingFixture.getUserData().toString();
                }
                catch(Exception e) {
                    levelToLoad = ILevelName.HUBWORLD;
                }
            }

            if(opposingFixture.getFilterData().categoryBits == ICollisionMask.ITEM) {
                onGround = true;
                canDoubleJump = true;
            }
            if(opposingFixture.getFilterData().categoryBits == ICollisionMask.ENEMY) {
                dead = true;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture playerFixture = null;
        Fixture opposingFixture = null;

        if(contact.getFixtureA().getFilterData().categoryBits == ICollisionMask.PLAYER) {

            playerFixture = contact.getFixtureA();
            opposingFixture = contact.getFixtureB();
        }
        else if(contact.getFixtureB().getFilterData().categoryBits == ICollisionMask.PLAYER) {

            playerFixture = contact.getFixtureB();
            opposingFixture = contact.getFixtureA();
        }

        if(playerFixture != null) {
            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.GROUND) {
                onGround = false;
            }

            if(opposingFixture.getFilterData().categoryBits == ICollisionMask.DOOR) {
                onDoor = false;
            }
        }
    }

    public void reset()
    {
        //body.setTransform(level.getStartLocation().x, level.getStartLocation().y,0);
        //level.reload();
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

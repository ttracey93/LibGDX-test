package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Level;
import com.mygdx.game.collision.ICollisionMask;
import com.mygdx.game.entity.playerutils.Keys;
import com.mygdx.game.listeners.InputListener;
import com.mygdx.game.manager.CameraManager;

/**
 * Created by Dubforce on 1/21/2015.
 */
public class Player extends Entity implements ContactListener {
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
    private float jumpVelocity = 12f;
    private float doubleJumpVelocity = 17f;
    private CameraManager cameraManager;
    private float moveForce = 100f;
    private float maxVelocity = 10f;

    private boolean ignoreGravity = false;
    private boolean onGround = true;
    private boolean canDoubleJump = true;
    private boolean inADoor = false;

    public boolean onDoor = false;

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

        Gdx.input.setInputProcessor(new InputListener(this));
    }

    @Override
    public void update(float deltaTime) {
        if(ignoreGravity) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
        }

        //Update players location
        if(Keys.keyPressed(Keys.JUMP)) {
            if(onGround) {
                body.setLinearVelocity(body.getLinearVelocity().x, jumpVelocity);
            }
            else if(canDoubleJump) {
                canDoubleJump = false;
                body.setLinearVelocity(body.getLinearVelocity().x, doubleJumpVelocity);
            }
        }
        if(Keys.keyDown(Keys.LEFT)) {
            if(body.getLinearVelocity().x > -maxVelocity)
            body.applyForceToCenter(-moveForce, 0, true);

        }
        if(Keys.keyDown(Keys.RIGHT)) {
            if(body.getLinearVelocity().x < maxVelocity)
            body.applyForceToCenter(moveForce, 0, true);
        }

        if(Keys.keyReleased(Keys.LEFT) || Keys.keyReleased(Keys.RIGHT))
        {
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }

        float x = (body.getPosition().x / Level.METERS_PER_PIXEL) - sprite.getWidth()/2;
        float y = (body.getPosition().y / Level.METERS_PER_PIXEL) - sprite.getHeight()/2;

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

    //contact handling

    @Override
    public void beginContact(Contact contact) {

        System.out.println("player ocllision mask: " + ICollisionMask.PLAYER);
        System.out.println("ground collision mask: " + ICollisionMask.GROUND);
        System.out.println("A collision set: " + contact.getFixtureA().getFilterData().categoryBits);
        System.out.println("B collision set: " + contact.getFixtureB().getFilterData().categoryBits);

        Fixture playerFixture = null;
        Fixture opposingFixture = null;

        if(contact.getFixtureA().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            System.out.println("A is a player");

            playerFixture = contact.getFixtureA();
            opposingFixture = contact.getFixtureB();
        }
        else if(contact.getFixtureB().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            System.out.println("B is a player");

            playerFixture = contact.getFixtureB();
            opposingFixture = contact.getFixtureA();

            if(opposingFixture.getFilterData().categoryBits == ICollisionMask.DOOR) {
                System.out.println("collider is a door");
            }
        }


        if(playerFixture != null) {
            System.out.println("player fixture is not null");

            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.GROUND) {
                System.out.println("opposing force is ground");

                onGround = true;
                canDoubleJump = true;
            }

            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.DOOR) {
                System.out.print("hitting door");
                onDoor = true;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture playerFixture = null;
        Fixture opposingFixture = null;

        if(contact.getFixtureA().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            System.out.println("A is a player");

            playerFixture = contact.getFixtureA();
            opposingFixture = contact.getFixtureB();
        }
        else if(contact.getFixtureB().getFilterData().categoryBits == ICollisionMask.PLAYER) {
            System.out.println("B is a player");

            playerFixture = contact.getFixtureB();
            opposingFixture = contact.getFixtureA();
        }

        if(playerFixture != null) {
            System.out.println("player fixture is not null");

            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.GROUND) {
                System.out.println("opposing force is ground");
                onGround = false;
            }
            if (opposingFixture.getFilterData().categoryBits == ICollisionMask.DOOR) {
                onDoor = false;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

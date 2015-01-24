package com.mygdx.game.collision;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by dubforce on 1/24/15.
 */
public class PlayerContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        Fixture playerFixture = null;

        if(a.getFilterData().categoryBits == ICollisionMask.PLAYER) {
            playerFixture = a;
        }
        else if(b.getFilterData().categoryBits == ICollisionMask.PLAYER) {
            playerFixture = b;
            b = a;
        }

        if(playerFixture != null) {

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    /////////////////////////////////ignore presolve/postsolve for now

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

package com.mygdx.game.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dubforce on 1/15/15.
 */
public class GameObject implements IGameObject {
    protected Shape2D collider;
    protected Vector2 location;
    protected Vector2 speed;
    protected Integer moveSpeed;
    protected Boolean canMove;
    protected Boolean canCollide;
    protected Boolean simiulatePhysics;

    @Override
    public void update(float elapsedTime) {

    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    public Shape2D getCollider() {
        return collider;
    }

    public void setCollider(Shape2D collider) {
        this.collider = collider;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public Integer getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(Integer moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public Boolean getCanCollide() {
        return canCollide;
    }

    public void setCanCollide(Boolean canCollide) {
        this.canCollide = canCollide;
    }

    public Boolean getSimiulatePhysics() {
        return simiulatePhysics;
    }

    public void setSimiulatePhysics(Boolean simiulatePhysics) {
        this.simiulatePhysics = simiulatePhysics;
    }
}

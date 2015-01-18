package com.mygdx.game.world;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Dubforce on 1/17/2015.
 */
public class Tile {
    private Integer tileWidth;
    private Integer tileHeight;
    private Texture texture;

    public Integer getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(Integer tileWidth) {
        this.tileWidth = tileWidth;
    }

    public Integer getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(Integer tileHeight) {
        this.tileHeight = tileHeight;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}

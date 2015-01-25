package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

/**
 * Created by max on 1/24/2015.
 */
public class Trigger {

    MapObjects doors;
    public Trigger(MapObjects doors){
        this.doors = doors;
        MapObject door = doors.get(0);

    }
}

package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;


/**
 * Created by Brian on 2/20/2015.
 */
public class MenuParticle {

    private int x,y;
    private float fallSpeed;

    public MenuParticle(int x, int y, float fallSpeed){
        this.x = x;
        this.y = y;
        this.fallSpeed = fallSpeed;
        System.out.println("test " + x +" " + y);
    }

    public void render(ShapeRenderer renderer){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.circle(x,y,4);
        renderer.end();


    }
    public void update(float delta){
        this.y -= Math.round(fallSpeed/2 * delta);
        this.x += Math.sin(y*30) * 5;
        if(y <0){
            y = Gdx.graphics.getHeight() + (int)(Math.random()*2309 %300);
            this.x = (int)((Math.random() *4239)% Gdx.graphics.getWidth  ());
        }
    }
}

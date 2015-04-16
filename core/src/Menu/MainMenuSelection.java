package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by Brian on 2/20/2015.
 */
public class MainMenuSelection {

    private int x,y;
    private int height,width;
    private String text;

    public MainMenuSelection(int x, int y, int width, int height,String text){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.text = text;
    }
    public void render(ShapeRenderer renderer,BitmapFont font,SpriteBatch batch){
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.rect(x, y, width, height);
        renderer.end();
        batch.begin();
        font.setColor(Color.WHITE);
        font.setScale(5, 3);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.draw(batch,this.getText(),x+ 50,y+height*3/4);
        batch.end();
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public String getText(){return text;}
    public boolean isSelected(int mouseX, int mouseY){
        if((mouseX >x && mouseX <x + width) && (mouseY <(Gdx.graphics.getHeight()-y) && mouseY >(Gdx.graphics.getHeight()-y-height)))return true;
        else return false;
    }
    public ArrayList<MainMenuSelection> getActions(){
        ArrayList<MainMenuSelection> list = new ArrayList<MainMenuSelection>();

        return list;
    }


}

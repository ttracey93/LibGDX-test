package Menu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brian on 2/20/2015.
 */
public class MainMenuScreen{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Boolean escape;
    private ArrayList<MainMenuSelection> selections = new ArrayList<MainMenuSelection>();
    private ArrayList<MenuParticle> particles = new ArrayList<MenuParticle>();
    public MainMenuScreen(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        escape = false;
        selections.add(new MainMenuSelection(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*3/8,Gdx.graphics.getWidth()*3/4,100,"New Game"));
        //selections.add(new MainMenuSelection(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()*3/8,Gdx.graphics.getWidth()*3/4,100,"About"));
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        for(int i = 0 ; i <30; i++)particles.add(new MenuParticle((int)rand.nextInt()%Gdx.graphics.getWidth(),(int)rand.nextInt()%Gdx.graphics.getHeight(),300));
    }
    public void draw(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        //draw Background
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),Color.ORANGE,Color.ORANGE,Color.YELLOW,Color.YELLOW);
        shapeRenderer.end();
        for(int i = 0 ;i< particles.size();i++)particles.get(i).render(shapeRenderer);
        for(int i = 0; i<selections.size();i++)selections.get(i).render(shapeRenderer,font,batch);

        batch.begin();
        font.setScale(5, 3);
        font.setColor(Color.RED);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.draw(batch,"Salt Factory",Gdx.graphics.getWidth()*1/4,Gdx.graphics.getHeight()*6/8);
        batch.end();
    }
    public void update(float delta){
        if(Gdx.input.isButtonPressed(Input.Keys.LEFT)){
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            for(int i = 0; i<selections.size();i++)
                if(selections.get(i).isSelected(mouseX,mouseY))escape = true;

        }
        for(int i = 0 ; i< particles.size();i++)particles.get(i).update(delta);

    }
    public boolean checkEscape(){
        return escape;
    }


}

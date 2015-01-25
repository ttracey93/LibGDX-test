package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.audio.Ogg;
import com.mygdx.game.entity.Player;
import com.badlogic.gdx.audio.Music;

/**
 * Created by max on 1/23/2015.
 */
public class Audio {

    public static Sound helloSound;
    public static Sound jumpEffect;
    public static Player player;
    public static Music background, backgroundloop;
    public static float loopTime = 0;
    public int dicks = 0;
    //public static Player player;

    public Audio(String filename){

        jumpEffect = Gdx.audio.newSound(Gdx.files.internal("Audio/jumpOGGtest.ogg "));

        if(filename == "../Maps/hubworld.tmx") {
            String backgroundPath = "Audio/homeTheme_full.wav";
            background = Gdx.audio.newMusic(Gdx.files.internal(backgroundPath));
            backgroundloop = Gdx.audio.newMusic(Gdx.files.internal("Audio/homeTheme_shortBuild.wav"));
        }else{
            String backgroundPath = "Audio/loopingDemo.wav";
            background = Gdx.audio.newMusic(Gdx.files.internal(backgroundPath));
            backgroundloop = Gdx.audio.newMusic(Gdx.files.internal("Audio/loopingDemo.wav"));
        }
    }



    public static void playMusic(){
        background.play();
    }

    public static void update(float deltaTime){
        String nothing = "debug";
        if(player != null){
            if(player.jumpSound) {
                jumpEffect.stop();
                jumpEffect.play();
            }

        loopTime += deltaTime;

        if(!background.isPlaying() && !backgroundloop.isPlaying()){
            background.dispose();
            backgroundloop.play();
            backgroundloop.isLooping();
        }

        }
    }

    public static void getPlayer(Player thisPlayer){
        //helloSound.play(1.0f);
        player = thisPlayer;
    }

    public void stopMusic(){
        background.stop();
        background.dispose();
        backgroundloop.stop();
        backgroundloop.dispose();
    }
}

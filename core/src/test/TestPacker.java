package test;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by dubforce on 1/15/15.
 */
public class TestPacker {
    private static final String inputDir = "/Users/dubforce/GameDev/test/core/assets/Platformer/Base/Player/p1_walk/PNG";
    private static final String outputDir = "/Users/dubforce/GameDev/test/core/assets/Platformer/Base/Player/p1_walk/PNG/test";
    private static final String packFileName = "spritesheet";

    public static void main (String[] args) throws Exception {
        TexturePacker.process(inputDir, outputDir, packFileName);
    }
}

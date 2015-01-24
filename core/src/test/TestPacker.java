package test;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by dubforce on 1/15/15.
 */
public class TestPacker {
    private static final String inputDir = "/Users/justingagnon/IdeaProjects/libGDX-test/core/assets/Platformer/Base/Player/morton/left";
    private static final String outputDir = "/Users/justingagnon/IdeaProjects/libGDX-test/core/assets/Platformer/Base/Player/morton/left/atlas";
    private static final String packFileName = "spritesheet";

    public static void main (String[] args) throws Exception {
        TexturePacker.process(inputDir, outputDir, packFileName);
    }
}

package ru.vasic2000.gravity.classes;

import java.util.ArrayList;

import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.gravity.utilites.UtilResourse;

public class LoaderAssets {
    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpriteEnemy(graphicsFW);
        loadShieldHitEnemy(graphicsFW);
    }

    private void loadShieldHitEnemy(GraphicsFW graphicsFW) {
        UtilResourse.shieldHitEnamy = graphicsFW.newSprite(UtilResourse.textureAtlas,
                0,128, 64,64);
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResourse.spritePlayer = new ArrayList<>();
        UtilResourse.spritePlayerBoost = new ArrayList<>();

        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                0, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                64, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                128, 0, 64, 64));
        UtilResourse.spritePlayer.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                192, 0, 64, 64));

        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                0, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                64, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                128, 64, 64, 64));
        UtilResourse.spritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                192, 64, 64, 64));

    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        UtilResourse.spriteEnemy = new ArrayList<>();
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                256, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                320, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                384, 0, 64, 64));
        UtilResourse.spriteEnemy.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                448, 0, 64, 64));
    }
}

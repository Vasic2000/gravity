package ru.vasic2000.gravity.classes;

import java.util.ArrayList;

import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.gravity.utilites.UtilResourse;

public class LoaderAssets {

    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadSpriteEnemy(graphicsFW);
        loadShieldHitEnemy(graphicsFW);
        loadSpritePlayerShieldsOn(graphicsFW);
        loadGifts(graphicsFW);
        loadAudio(coreFW);
        loadScore(coreFW);
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.sGameMusic = coreFW.getAudioFW().newMusic("music.mp3");
        UtilResourse.sHit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.sExplode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.sTouch = coreFW.getAudioFW().newSound("touch.ogg");
    }

    private void loadScore(CoreFW coreFW) {
        SettingsGame.loadScore(coreFW);
    }

    private void loadShieldHitEnemy(GraphicsFW graphicsFW) {
        UtilResourse.sShieldHitEnamy = graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                0,128, 64,64);
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.sTextureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResourse.sSpritePlayer = new ArrayList<>();
        UtilResourse.sSpritePlayerBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerExplose = new ArrayList<>();

        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                0, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                64, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                128, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                192, 0, 64, 64));

        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                0, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                64, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                128, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                192, 64, 64, 64));

        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                256, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                320, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                384, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                448, 256, 64, 64));

    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteEnemy = new ArrayList<>();
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                256, 0, 64, 64));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                320, 0, 64, 64));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                384, 0, 64, 64));
        UtilResourse.sSpriteEnemy.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                448, 0, 64, 64));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {

        UtilResourse.sSpritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                0, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                64, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                128, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                192, 128, 64, 64));

        UtilResourse.sSpritePlayerShieldsBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                0, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                64, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                128, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                192, 192, 64, 64));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteProtector = new ArrayList<>();
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                256, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                288, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                320, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas,
                352, 192, 32, 32));
    }

}

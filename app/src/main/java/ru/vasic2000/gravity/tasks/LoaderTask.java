package ru.vasic2000.gravity.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import ru.vasic2000.gravity.interfaces.TaskCompleteListener;
import ru.vasic2000.gravity.scenes.LoaderResourcesScene;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;

public class LoaderTask extends AsyncTask<Void, Integer, Void> {
    private TaskCompleteListener mTaskCompleteListener;
    private CoreFW mCoreFW;

    public LoaderTask(CoreFW coreFW, TaskCompleteListener taskCompleteListener) {
        mTaskCompleteListener = taskCompleteListener;
        mCoreFW = coreFW;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        LoaderResourcesScene.setmProgressLoader(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTaskCompleteListener.onComplete();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loaderAssets();
        return null;
    }

    private void loaderAssets() {
        loadTexture(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(100);
        loadSpritePlayer(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(200);
        loadSpriteEnemy(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(300);
        loadShieldHitEnemy(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(400);
        loadSpritePlayerShieldsOn(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(500);
        loadGifts(mCoreFW.getGraphicsFW());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(600);
        loadAudio(mCoreFW);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(700);
        loadScore(mCoreFW);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(800);
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.gameMusic = coreFW.getAudioFW().newMusic("music.mp3");
        UtilResourse.hit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.explode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.touch = coreFW.getAudioFW().newSound("touch.ogg");
    }

    private void loadScore(CoreFW coreFW) {
        SettingsGame.loadScore(coreFW);
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
        UtilResourse.spritePlayerExplose = new ArrayList<>();

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

        UtilResourse.spritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                256, 256, 64, 64));
        UtilResourse.spritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                320, 256, 64, 64));
        UtilResourse.spritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                384, 256, 64, 64));
        UtilResourse.spritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                448, 256, 64, 64));

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

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {

        UtilResourse.spritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                0, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                64, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                128, 128, 64, 64));
        UtilResourse.spritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                192, 128, 64, 64));

        UtilResourse.spritePlayerShieldsBoost = new ArrayList<>();
        UtilResourse.spritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                0, 192, 64, 64));
        UtilResourse.spritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                64, 192, 64, 64));
        UtilResourse.spritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                128, 192, 64, 64));
        UtilResourse.spritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                192, 192, 64, 64));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.spriteProtector = new ArrayList<>();
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                256, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                288, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                320, 192, 32, 32));
        UtilResourse.spriteProtector.add(graphicsFW.newSprite(UtilResourse.textureAtlas,
                352, 192, 32, 32));
    }

}

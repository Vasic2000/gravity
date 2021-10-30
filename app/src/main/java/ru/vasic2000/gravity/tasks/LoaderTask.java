package ru.vasic2000.gravity.tasks;

import android.os.AsyncTask;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.interfaces.TaskCompleteListener;
import ru.vasic2000.gravity.scenes.LoaderResourcesScene;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.GraphicsFW;

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
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(100);
        loadSpritePlayer(mCoreFW.getGraphicsFW());
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(200);
        loadSpriteEnemy(mCoreFW.getGraphicsFW());
//        try {
//            Thread.sleep(150);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(300);
        loadOther(mCoreFW.getGraphicsFW());
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(400);
        loadSpritePlayerShieldsOn(mCoreFW.getGraphicsFW());
//        try {
//            Thread.sleep(150);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(500);
        loadGifts(mCoreFW.getGraphicsFW());
        loadBullets(mCoreFW.getGraphicsFW());
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(600);
        loadAudio(mCoreFW);
//        try {
//            Thread.sleep(150);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(700);
        loadScore(mCoreFW);
//        try {
//            Thread.sleep(150);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        publishProgress(800);
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResourse.sGameMusic = coreFW.getAudioFW().newMusic("music.mp3");

        UtilResourse.sSchieldSound = coreFW.getAudioFW().newSound("schield2.mp3");
        UtilResourse.sLooseSound = coreFW.getAudioFW().newSound("loose.mp3");
        UtilResourse.sHit = coreFW.getAudioFW().newSound("hit.ogg");
        UtilResourse.sExplode = coreFW.getAudioFW().newSound("explode.ogg");
        UtilResourse.sTouch = coreFW.getAudioFW().newSound("touch.ogg");
        UtilResourse.sTakeSound = coreFW.getAudioFW().newSound("take.mp3");
    }

    private void loadScore(CoreFW coreFW) {
        SettingsGame.loadScore(coreFW);
    }

    private void loadOther(GraphicsFW graphicsFW) {
        UtilResourse.sShieldHitEnamy = graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                0,128, 64,64);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            UtilResourse.sMainMenuFont = mCoreFW.getResources().getFont(R.font.roboto_black);
        } else {
            UtilResourse.sMainMenuFont = ResourcesCompat.getFont(mCoreFW.getApplicationContext(),
                    R.font.roboto_black);
        }
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourse.sTextureAtlas = graphicsFW.newTexture("texture_atlas.png");
        UtilResourse.sTextureAtlas2 = graphicsFW.newTexture("texture_atlas2.png");
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResourse.sSpritePlayer = new ArrayList<>();
        UtilResourse.sSpritePlayerBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerExplose = new ArrayList<>();

        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                0, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                64, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                128, 0, 64, 64));
        UtilResourse.sSpritePlayer.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                192, 0, 64, 64));

        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                0, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                64, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                128, 64, 64, 64));
        UtilResourse.sSpritePlayerBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                192, 64, 64, 64));

        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                256, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                320, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                384, 256, 64, 64));
        UtilResourse.sSpritePlayerExplose.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                448, 256, 64, 64));

    }

    private void loadSpriteEnemy(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteEnemy1 = new ArrayList<>();
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                768, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 0, 64, 64));
        UtilResourse.sSpriteEnemy1.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 0, 64, 64));

        UtilResourse.sSpriteEnemy2 = new ArrayList<>();
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                768, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 64, 64, 64));
        UtilResourse.sSpriteEnemy2.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 64, 64, 64));

        UtilResourse.sSpriteEnemy3 = new ArrayList<>();
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                768, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                704, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                640, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                576, 128, 64, 64));
        UtilResourse.sSpriteEnemy3.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                512, 128, 64, 64));
    }

    private void loadSpritePlayerShieldsOn(GraphicsFW graphicsFW) {

        UtilResourse.sSpritePlayerShieldsOn = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                0, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                64, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                128, 128, 64, 64));
        UtilResourse.sSpritePlayerShieldsOn.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                192, 128, 64, 64));

        UtilResourse.sSpritePlayerShieldsBoost = new ArrayList<>();
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                0, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                64, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                128, 192, 64, 64));
        UtilResourse.sSpritePlayerShieldsBoost.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                192, 192, 64, 64));
    }

    private void loadGifts(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteProtector = new ArrayList<>();
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                256, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                288, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                320, 192, 32, 32));
        UtilResourse.sSpriteProtector.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                352, 192, 32, 32));

        UtilResourse.sSpriteAddShield = new ArrayList<>();
        UtilResourse.sSpriteAddShield.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                256, 224, 32, 32));
        UtilResourse.sSpriteAddShield.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                288, 224, 32, 32));
        UtilResourse.sSpriteAddShield.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                320, 224, 32, 32));
        UtilResourse.sSpriteAddShield.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                352, 224, 32, 32));
    }

    private void loadBullets(GraphicsFW graphicsFW) {
        UtilResourse.sSpriteBullet = new ArrayList<>();
        UtilResourse.sSpriteBullet.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                384, 224, 32, 32));
        UtilResourse.sSpriteBullet.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                416, 224, 32, 32));
        UtilResourse.sSpriteBullet.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                448, 224, 32, 32));
        UtilResourse.sSpriteBullet.add(graphicsFW.newSprite(UtilResourse.sTextureAtlas2,
                480, 224, 32, 32));
    }

}

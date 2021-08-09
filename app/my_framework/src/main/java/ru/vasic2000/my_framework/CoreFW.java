package ru.vasic2000.my_framework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private final int FRAME_BUFFER_WIDTH = 800;
    private final int FRAME_BUFFER_HEIGHT = 600;

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;

   private  AudioFW audioFW;

    private Bitmap frameBuffer;
    private SceneFW sceneFW;

    private float sceneWidth;
    private float sceneHeight;

    private SharedPreferences sharedPreferences;

    private boolean stateOnPause;
    private boolean stateOnResume;

    private final String SETTINGS = "settings";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        frameBuffer = Bitmap.createBitmap(FRAME_BUFFER_WIDTH,
                FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);

        sceneWidth = (float) FRAME_BUFFER_WIDTH / getResources().getDisplayMetrics().widthPixels;
        sceneHeight = (float) FRAME_BUFFER_HEIGHT / getResources().getDisplayMetrics().heightPixels;

        audioFW = new AudioFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();

        stateOnPause = false;
        stateOnResume = false;
        setContentView(loopFW);
    }

    public CoreFW() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        if(isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return graphicsFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return touchListenerFW;
    }

    public void setScene(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Беда со сценой");
        }
        this.sceneFW.pause();
        this.sceneFW.dispose();
        this.sceneFW.resume();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        return sceneFW;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public AudioFW getAudioFW() {
        return audioFW;
    }
}

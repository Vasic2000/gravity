package ru.vasic2000.my_framework.core;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private final int FRAME_BUFFER_WIDTH = 800;
    private final int FRAME_BUFFER_HEIGHT = 600;

    private LoopFW mLoopFW;
    private GraphicsFW mGraphicsFW;
    private TouchListenerFW mTouchListenerFW;

    private AudioFW mAudioFW;

    private Bitmap mFrameBuffer;
    private SceneFW mSceneFW;

    private float mSceneWidth;
    private float mSceneHeight;

    private SharedPreferences mSharedPreferences;

    private final String SETTINGS = "settings";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
        setContentView(mLoopFW);
    }

    private void init() {
        mFrameBuffer = Bitmap.createBitmap(FRAME_BUFFER_WIDTH,
                FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);

        mSceneWidth = (float) FRAME_BUFFER_WIDTH / getResources().getDisplayMetrics().widthPixels;
        mSceneHeight = (float) FRAME_BUFFER_HEIGHT / getResources().getDisplayMetrics().heightPixels;

        mAudioFW = new AudioFW(this);

        mLoopFW = new LoopFW(this, mFrameBuffer);
        mGraphicsFW = new GraphicsFW(getAssets(), mFrameBuffer);
        mTouchListenerFW = new TouchListenerFW(mLoopFW, mSceneWidth, mSceneHeight);

        mSceneFW = getStartScene();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSceneFW.resume();
        mLoopFW.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSceneFW.pause();
        mLoopFW.stopGame();
        if(isFinishing()) {
            mSceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return mGraphicsFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return mTouchListenerFW;
    }

    public void setScene(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Беда со сценой");
        }
        this.mSceneFW.pause();
//        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.mSceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return mSceneFW;
    }

    public SceneFW getStartScene() {
        return mSceneFW;
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public AudioFW getAudioFW() {
        return mAudioFW;
    }
}

package ru.vasic2000.my_framework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private final int FRAME_BUFFER_WIDTH = 800;
    private final int FRAME_BUFFER_HEIGHT = 600;

    private LoopFW loopFW;

    private GraphicsFW graphicsFW;

    private Display display;
    private Point sizeDisplay;
    private Bitmap frameBuffer;

    private SceneFW sceneFW;

    private float sceneWidth;
    private float sceneHeight;

    private boolean stateOnPause;
    private boolean stateOnResume;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getCurrentWindowMetrics();
//        System.out.println("x = " + metrics.heightPixels + " y = " + metrics.widthPixels);

        frameBuffer = Bitmap.createBitmap(FRAME_BUFFER_WIDTH,
                FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);

        sceneWidth = FRAME_BUFFER_WIDTH/sizeDisplay.x;
        sceneHeight = FRAME_BUFFER_HEIGHT/sizeDisplay.y;

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);

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

    public void setScene(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Беда со сценой");
        }
        this.sceneFW.pause();
        this.sceneFW.dispose();
        sceneFW.resume();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        return sceneFW;
    }
}

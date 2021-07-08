package ru.vasic2000.my_framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

class GraphicsFW {
    private AssetManager assetManagerGame;
    private Bitmap frameBufferGame;
    private Canvas canvasGame;
    private Paint paintGame;
    private Bitmap textureGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame = assetManagerGame;
        this.frameBufferGame = frameBufferGame;
        this.canvasGame = new Canvas(frameBufferGame);
        this.paintGame = new Paint();
    }
}

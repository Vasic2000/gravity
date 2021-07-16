package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.generators.BacgroundGenerator;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class GameScene extends SceneFW {

    enum GameState {
        READY,
        RUNING,
        PAUSE,
        GAMEOVER
    }

    GameState gameState;
    BacgroundGenerator bacgroundGenerator;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        bacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight);
    }

    @Override
    public void update() {
        if(gameState == GameState.READY) {
            updateStateReady();
        }
        if(gameState == GameState.RUNING) {
            updateStateRuning();
        }
        if(gameState == GameState.PAUSE) {
            updateStatePause();
        }
        if(gameState == GameState.GAMEOVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
//        graficsFW.drawText("Game Scene",
//                100, 150, Color.GREEN, 60,null);

        if(gameState == GameState.READY) {
            drawingGameReady();
        }
        if(gameState == GameState.RUNING) {
            drawingGameRuning();
        }
        if(gameState == GameState.PAUSE) {
            drawingGamePause();
        }
        if(gameState == GameState.GAMEOVER) {
            drawingGameGameOver();
        }
    }

    private void drawingGameReady() {
        graficsFW.clearScene(Color.BLACK);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }
    private void updateStateReady() {
        if(coreFW.getTouchListenerFW().getTuchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            gameState = GameState.RUNING;
        }
    }

    private void drawingGameRuning() {
        graficsFW.clearScene(Color.BLACK);
//        graficsFW.drawText("Сцена игры",
//                250, 300, Color.WHITE, 60, null);
        bacgroundGenerator.drawing(graficsFW);
    }
    private void updateStateRuning() {
        bacgroundGenerator.update();
    }

    private void drawingGamePause() {
    }
    private void updateStatePause() {
    }

    private void drawingGameGameOver() {
    }
    private void updateStateGameOver() {
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}

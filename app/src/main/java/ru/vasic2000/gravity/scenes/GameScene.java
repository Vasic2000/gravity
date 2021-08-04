package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.classes.GameManager;
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
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
    }

    @Override
    public void update() {
        if(gameState == GameState.READY) {
            updateStateReady();
        }
        if(gameState == GameState.RUNING) {
            updateStateRuning();
            if(GameManager.gameOver) {
                gameState = GameState.GAMEOVER;
            }
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
            drawingGameOver();
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
        gameManager.drawing(coreFW, graficsFW);
    }

    private void updateStateRuning() {
        gameManager.update();
    }

    private void drawingGamePause() {
    }
    private void updateStatePause() {
    }

    private void drawingGameOver() {
        graficsFW.clearScene(Color.BLACK);
        graficsFW.drawText("Game Over", 250, 300, Color.WHITE, 60,  null);
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

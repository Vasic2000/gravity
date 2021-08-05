package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.SettingsGame;
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
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_distance)
                + " " + gameManager.getPassedDistaence(),250, 200, Color.WHITE, 40,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_gameOver),
                250, 300, Color.WHITE, 60,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_replay),
                250, 370, Color.WHITE, 40,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_mainMenu),
                250, 440, Color.WHITE, 40,  null);
    }
    private void updateStateGameOver() {
        SettingsGame.addDistance(gameManager.getPassedDistaence());

        if(coreFW.getTouchListenerFW().getTuchUp(250, 368, 200, 45)) {
            coreFW.setScene(new GameScene(coreFW));
        }
        if(coreFW.getTouchListenerFW().getTuchUp(250, 438, 200, 45)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
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

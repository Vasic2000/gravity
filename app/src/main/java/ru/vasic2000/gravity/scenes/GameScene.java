package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class GameScene extends SceneFW {

    enum GameState {
        READY,
        RUNING,
        PAUSE,
        GAME_OVER
    }

    private GameState mGameState;
    private GameManager mGameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        mGameState = GameState.READY;
        mGameManager = new GameManager(coreFW, sceneWidth, sceneHeight);

        UtilResourse.sGameMusic.play(true, 0.5f);
    }

    @Override
    public void update() {
        if(mGameState == GameState.READY) {
            updateStateReady();
        }
        if(mGameState == GameState.RUNING) {
            updateStateRuning();
            if(GameManager.gameOver) {
                mGameState = GameState.GAME_OVER;
            }
        }
        if(mGameState == GameState.PAUSE) {
            updateStatePause();
        }
        if(mGameState == GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        if(mGameState == GameState.READY) {
            drawingGameReady();
        }
        if(mGameState == GameState.RUNING) {
            drawingGameRuning();
        }
        if(mGameState == GameState.PAUSE) {
            drawingGamePause();
        }
        if(mGameState == GameState.GAME_OVER) {
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
            mGameState = GameState.RUNING;
        }
    }

    private void drawingGameRuning() {
        graficsFW.clearScene(Color.BLACK);
        mGameManager.drawing(graficsFW);
    }

    private void updateStateRuning() {
        mGameManager.update();
    }

    private void drawingGamePause() {
    }
    private void updateStatePause() {
    }

    private void drawingGameOver() {
        graficsFW.clearScene(Color.BLACK);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_distance)
                + " " + mGameManager.getPassedDistaence(),250, 200, Color.WHITE, 40,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_gameOver),
                250, 300, Color.WHITE, 60,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_replay),
                250, 370, Color.WHITE, 40,  null);
        graficsFW.drawText(coreFW.getString(R.string.txt_gameScene_gameOver_mainMenu),
                250, 440, Color.WHITE, 40,  null);
    }
    private void updateStateGameOver() {

        SettingsGame.addDistance(mGameManager.getPassedDistaence());

        if(coreFW.getTouchListenerFW().getTuchUp(250, 368, 250, 45)) {
            coreFW.setScene(new GameScene(coreFW));
        }
        if(coreFW.getTouchListenerFW().getTuchUp(250, 438, 200, 45)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void dispose() {
        UtilResourse.sExplode.dispose();
        UtilResourse.sHit.dispose();
        UtilResourse.sTouch.dispose();
        UtilResourse.sGameMusic.dispose();
    }

    @Override
    public void pause() {
        UtilResourse.sGameMusic.stop();
    }

    @Override
    public void resume() {
        UtilResourse.sGameMusic.play(true, 0.5f);
    }
}

package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

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
        mGameManager = new GameManager(coreFW, pSceneWidth, pSceneHeight);
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
        pGraficsFW.clearScene(Color.BLACK);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
        if(pCoreFW.getTouchListenerFW().getTuchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            mGameState = GameState.RUNING;
        }
    }

    private void drawingGameRuning() {
        pGraficsFW.clearScene(Color.BLACK);
        mGameManager.drawing(pGraficsFW);
    }

    private void updateStateRuning() {
        mGameManager.update();
        if (GameManager.gameOver) {
            mGameState = GameState.GAME_OVER;
        }
        if(pCoreFW.isKeyBackPressed()) {
            mGameState = GameState.PAUSE;
        }
        pCoreFW.setKeyBackPressed(false);
    }

    private void drawingGamePause() {
        pCoreFW.getGraphicsFW().clearScene(Color.BLACK);
        pCoreFW.getGraphicsFW().drawText("PAUSE", 300,300, Color.GREEN, 50, null);
    }

    private void updateStatePause() {
        if(pCoreFW.getTouchListenerFW().getTuchUp(0, pSceneHeight, pSceneWidth, pSceneHeight)) {
            mGameState = GameState.RUNING;
        }
    }

    private void drawingGameOver() {
        pGraficsFW.clearScene(Color.BLACK);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_gameScene_gameOver_distance)
                + " " + mGameManager.getPassedDistaence(),250, 200, Color.WHITE, 40,  null);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_gameScene_gameOver_gameOver),
                250, 300, Color.WHITE, 60,  null);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_gameScene_gameOver_replay),
                250, 370, Color.WHITE, 40,  null);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_gameScene_gameOver_mainMenu),
                250, 440, Color.WHITE, 40,  null);
    }
    private void updateStateGameOver() {

        SettingsGame.addDistance(mGameManager.getPassedDistaence());

        if(pCoreFW.getTouchListenerFW().getTuchUp(250, 368, 250, 45)) {
            pCoreFW.setScene(new GameScene(pCoreFW));
        }
        if(pCoreFW.getTouchListenerFW().getTuchUp(250, 438, 200, 45)) {
            pCoreFW.setScene(new MainMenuScene(pCoreFW));
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
        if(SettingsGame.sMusicOn) {
            UtilResourse.sGameMusic.play(true, 0.5f);
        }
    }
}

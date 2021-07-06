package ru.vasic2000.my_framework;

public class LoopFW implements Runnable {
    private final float FPS = 60;
    private final float SECOND = 1000000000;
    private final float UPDATE_TIME = SECOND/FPS;

    private boolean runing = false;

    Thread gameThread = null;

//    TEMP
    float updates = 0;
    float drawings = 0;
    long timer = 0;
//    TEMP

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;
        float elapsedTime = 0;

        while (runing) {
            float nowTime = System.nanoTime();
            elapsedTime = nowTime - lastTime;
            delta += elapsedTime / UPDATE_TIME;
            if (delta > 1) {
                updateGame();
                drawingGame();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("UPDATES = " + updates + ", DRAWINGS " + drawings);
                timer += 1000;
                updates = 0;
                drawings = 0;
            }
        }
    }



//    @Override
//    public void run() {
//        float lastTime = System.nanoTime();
//        float delta = 0;
//        float elapsedTime = 0;
//        float nowTime;
//
//        while(runing) {
//            nowTime = System.nanoTime();
//            elapsedTime += nowTime - lastTime;
//            if(elapsedTime > UPDATE_TIME) {
//                updateGame();
//                drawingGame();
//                elapsedTime = 0;
//            }
//            if(System.currentTimeMillis() - timer > 1000) {
//                System.out.println("UPDATES = " + updates + ", DRAWINGS " + drawings);
//                timer += 1000;
//            }
//        }
//    }

    private void updateGame() {
        updates++;
    }

    private void drawingGame() {
        drawings++;
    }

    public void startGame() {
        if(runing) return;
        else {
            runing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stopGame() {
        if(!runing) return;
        else {
            runing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

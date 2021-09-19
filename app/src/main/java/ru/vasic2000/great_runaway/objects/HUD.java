package ru.vasic2000.great_runaway.objects;

import android.graphics.Color;

import ru.vasic2000.great_runaway.R;
import ru.vasic2000.great_runaway.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.GraphicsFW;

public class HUD {
    private int mPassedDistaence;
    private double mCurrentPlayerSpeed;
    private int mCurrentPlayerShields;

    private CoreFW coreFW;

    public final int HUD_HEIGHT = 50;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistaence,
            double currentPlayerSpeed,
            int currentPlayerShields) {
        this.mPassedDistaence = passedDistaence;
        this.mCurrentPlayerSpeed = currentPlayerSpeed;
        this.mCurrentPlayerShields = currentPlayerShields;

    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0, HUD_HEIGHT, graphicsFW.getWidthFrameBuffer(), HUD_HEIGHT, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_passedDistaence) + mPassedDistaence,
                10, 30, Color.YELLOW, 25, UtilResourse.sMainMenuFont);
        if(mCurrentPlayerShields >= 0) {
            graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_currentPlayerSpeed) + String.format("%.2f", mCurrentPlayerSpeed),
                    350, 30, Color.YELLOW, 25, UtilResourse.sMainMenuFont);
        } else mCurrentPlayerShields = 0;
        graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_currentPlayerShields) + mCurrentPlayerShields,
                600, 30, Color.YELLOW, 25, UtilResourse.sMainMenuFont);
    }

    public int getHUD_HEIGHT() {
        return HUD_HEIGHT;
    }
}

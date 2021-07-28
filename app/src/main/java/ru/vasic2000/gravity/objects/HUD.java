package ru.vasic2000.gravity.objects;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;

public class HUD {
    private int passedDistaence;
    private double currentPlayerSpeed;
    private int currentPlayerShields;

    CoreFW coreFW;

    public final int HUD_HEIGHT = 50;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistaence,
            double currentPlayerSpeed,
            int currentPlayerShields) {
        this.passedDistaence = passedDistaence;
        this.currentPlayerSpeed = currentPlayerSpeed;
        this.currentPlayerShields = currentPlayerShields;

    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0, HUD_HEIGHT, graphicsFW.getWidthFrameBuffer(), HUD_HEIGHT, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_passedDistaence) + passedDistaence,
                10, 30, Color.YELLOW, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_currentPlayerSpeed) + String.format("%.2f", currentPlayerSpeed),
                350, 30, Color.YELLOW, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_HUD_currentPlayerShields) + currentPlayerShields,
                600, 30, Color.YELLOW, 25, null);

    }

}

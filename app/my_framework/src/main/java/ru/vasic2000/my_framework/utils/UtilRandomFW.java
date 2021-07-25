package ru.vasic2000.my_framework.utils;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }

    public static int getGap(int minNumber, int maxNumber) {
        int gap = 0;
        gap = (int) (Math.random()*++maxNumber) + minNumber;
        return gap;
    }
}

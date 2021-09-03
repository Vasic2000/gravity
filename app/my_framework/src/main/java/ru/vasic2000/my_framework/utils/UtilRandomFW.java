package ru.vasic2000.my_framework.utils;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }

    public static int getGap(int minNumber, int maxNumber) {
        int gap;
        gap = (int) (Math.random() * (maxNumber + 1 - minNumber)) + minNumber;
        return gap;
    }
}

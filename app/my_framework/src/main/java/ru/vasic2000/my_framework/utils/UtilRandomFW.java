package ru.vasic2000.my_framework.utils;

import java.util.Random;

public class UtilRandomFW {
    public static int getCasualNumber(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }
}

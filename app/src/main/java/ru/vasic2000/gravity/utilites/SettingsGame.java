package ru.vasic2000.gravity.utilites;

import android.content.SharedPreferences;

import ru.vasic2000.my_framework.CoreFW;

public class SettingsGame {
    public static int[] distance = {0,0,0,0,0};

    public static void addDistance(int value) {
        for(int i = 0; i < 5; i++) {
            if(distance[i] < value) {
                for(int j = 4; j > i; j--) {
                    distance[j] = distance[j-1];
                }
                distance[i] = value;
            }
        }
    }

    public static void saveSettings(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
    }
}

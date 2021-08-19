package ru.vasic2000.gravity.utilites;

import android.content.SharedPreferences;

import ru.vasic2000.my_framework.CoreFW;

public class SettingsGame {
    
    public static int[] distance = {0,0,0,0,0};

    public static void addDistance(int value) {
        for(int i = 0; i < 5; i++) {
            if(distance[i] == value) break;
            if(distance[i] < value) {
                for(int j = 4; j > i; j--) {
                    distance[j] = distance[j-1];
                }
                distance[i] = value;
                break;
            }
        }
    }

    public static void saveScore(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance" + i, distance[i]);
        }
        editor.apply();
    }

    public static void loadScore(CoreFW coreFW) {
        for (int i = 0; i < 5; i++) {
            distance[i] = coreFW.getSharedPreferences().getInt("passedDistance" + i, distance[i]);
        }
    }

    public static int[] getDistance() {
        return distance;
    }
}

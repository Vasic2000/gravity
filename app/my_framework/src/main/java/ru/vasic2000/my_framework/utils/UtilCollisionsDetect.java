package ru.vasic2000.my_framework.utils;

import ru.vasic2000.my_framework.core.ObjectFW;

public class UtilCollisionsDetect {
    private static double sObject1x;
    private static double sObject2x;
    private static double sObject1y;
    private static double sObject2y;

    private static double sRadius1;
    private static double sRadius2;

    private static double sDx;
    private static double sDy;

    private static double sDistance;

    public static boolean collisionDetect(ObjectFW object1, ObjectFW object2) {
        sObject1x = object1.getHitBox().centerX();
        sObject1y = object1.getHitBox().centerY();
        sObject2x = object2.getHitBox().centerX();
        sObject2y = object2.getHitBox().centerY();

        sRadius1 = object1.getRadius();
        sRadius2 = object2.getRadius();

        sDx = sObject1x - sObject2x;
        sDy = sObject1y - sObject2y;

        sDistance = Math.sqrt(sDx * sDx + sDy * sDy);

        return sDistance < (sRadius1 + sRadius2);
    }

}

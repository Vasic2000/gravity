package ru.vasic2000.my_framework;

public class CollisionsDetect {
    static double object1x;
    static double object2x;
    static double object1y;
    static double object2y;

    static double radius1;
    static double radius2;

    static double dx;
    static double dy;

    static double distance;

    public static boolean collisionDetect(ObjectFW object1, ObjectFW object2) {
        object1x = object1.getHitBox().centerX();
        object1y = object1.getHitBox().centerY();
        object2x = object2.getHitBox().centerX();
        object2y = object2.getHitBox().centerY();

        radius1 = object1.getRadius();
        radius2 = object2.getRadius();

        dx = object1x - object2x;
        dy = object1y - object2y;

        distance = Math.sqrt(dx*dx + dy*dy);

        return distance < (radius1 + radius2);
    }

}

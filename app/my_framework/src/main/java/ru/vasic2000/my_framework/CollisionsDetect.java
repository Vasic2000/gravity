package ru.vasic2000.my_framework;

public class CollisionsDetect {

    public static boolean collisionDetect(ObjectFW object1, ObjectFW object2) {
        double object1x = object1.getHitBox().centerX();
        double object1y = object1.getHitBox().centerY();
        double object2x = object2.getHitBox().centerX();
        double object2y = object2.getHitBox().centerY();

        double radius1 = object1.getRadius();
        double radius2 = object2.getRadius();

        double dx = object1x - object2x;
        double dy = object1y - object2y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < (radius1 + radius2);
    }

}

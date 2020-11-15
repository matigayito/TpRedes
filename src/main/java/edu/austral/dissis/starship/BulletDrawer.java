package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class BulletDrawer {
    private static final float IMAGE_SIZE = 200;
    public static final int SQUARE_SIZE = 10;


    public BulletDrawer() {
    }

    private float getImageCenter() {
        return IMAGE_SIZE / -2f;
    }

    public void draw(PGraphics graphics, Bullet bullet) {
        final Vector2 position = bullet.getPosition();
        final float angle = calculateRotation(bullet);

        graphics.pushMatrix();

        graphics.translate(position.getX(), position.getY());
        graphics.rotate(angle);

//        graphics.image(image, getImageCenter(), getImageCenter());
        graphics.fill(0, 0, 0);
        graphics.rect(SQUARE_SIZE / -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);


        graphics.popMatrix();

        graphics.fill(0, 255, 0);
    }

    private float calculateRotation(Bullet bullet) {
        return bullet.getDirection().rotate(PConstants.PI / 2).getAngle();
    }

    public SquareCollisionable getCollisionable(Bullet bullet) {
        return new SquareCollisionable(
                SQUARE_SIZE,
                calculateRotation(bullet),
                bullet.getPosition()
        );
    }
}

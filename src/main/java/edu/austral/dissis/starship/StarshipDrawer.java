package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.objects.Starship;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class StarshipDrawer {
    private static final float IMAGE_SIZE = 200;
    public static final int SQUARE_SIZE = 50;

    private final PImage image;

    public StarshipDrawer(PImage image) {
        this.image = image;
    }

    private float getImageCenter() {
        return IMAGE_SIZE / -2f;
    }

    public void draw(PGraphics graphics, Starship starship) {
        final Vector2 position = starship.getPosition();
        final float angle = calculateRotation(starship);

        graphics.pushMatrix();

        graphics.translate(position.getX(), position.getY());
        graphics.rotate(angle);

//        graphics.image(image, getImageCenter(), getImageCenter());
        graphics.noFill();
        graphics.stroke(255, 255, 255);
        graphics.rect(SQUARE_SIZE / -2f , SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);


        graphics.popMatrix();

    }

    private float calculateRotation(Starship starship) {
        return starship.getDirection().rotate(PConstants.PI / 2).getAngle();
    }

    public SquareCollisionable getCollisionable(Starship starship) {
        return new SquareCollisionable(
                SQUARE_SIZE,
                calculateRotation(starship),
                starship.getPosition()
        );
    }
}

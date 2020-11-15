package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Asteroid extends GameObject {
    private final Vector2 position;
    private final Vector2 direction;

    public Asteroid(Vector2 position, Vector2 direction) {
        this.position = position;
        this.direction = direction.asUnitary();
    }

    public Asteroid moveForward(float speed) {
        return new Asteroid(position.add(direction.multiply(speed)), direction);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }
}

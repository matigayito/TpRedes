package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.BulletDrawer;
import edu.austral.dissis.starship.base.vector.Vector2;

public class Bullet extends GameObject {
    private final Vector2 position;
    private final Vector2 direction;

    public Bullet(Vector2 position, Vector2 direction) {
        this.position = position;
        this.direction = direction.asUnitary();
    }

    public Bullet moveForward(float speed) {
        return new Bullet(position.add(direction.multiply(speed)), direction);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }
}

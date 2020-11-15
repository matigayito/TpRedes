package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.BulletDrawer;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PGraphics;

public class Starship extends GameObject{
    private final Vector2 position;
    private final Vector2 direction;
    public final GUN gun;

    public Starship(Vector2 position, Vector2 direction) {
        this.position = position;
        this.direction = direction.asUnitary();
        gun = new GUN(this.position, this.direction);
    }

    public Starship rotate(float angle) {
        return new Starship(position, direction.rotate(angle));
    }

    public Starship moveForward(float speed) {
        return new Starship(position.add(direction.multiply(speed)), direction);
    }

    public Starship moveBackwards(float speed) {
        return new Starship(position.subtract(direction.multiply(speed)), direction);
    }

    public Vector2 getPosition() { return position; }

    public Vector2 getDirection() { return direction; }

    public void shoot(PGraphics graphics){
        System.out.println("pew");
        gun.shoot(graphics);
    }

}

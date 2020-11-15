package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.BulletDrawer;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.List;

public class GUN {
    private final Vector2 position;
    private final Vector2 direction;
    private List<Bullet> bullets;
    private final BulletDrawer drawer;

    public GUN(Vector2 position, Vector2 direction){
        this.position = position;
        this.direction = direction.asUnitary();
        bullets = new ArrayList<>();
        drawer = new BulletDrawer();
    }

    public void shoot(PGraphics graphics){
        Bullet bullet = new Bullet(this.position, this.direction);
        drawer.draw(graphics, bullet);
        bullets.add(bullet);
    }

    public void travel(PGraphics graphics){
        for (int i = 0; i < bullets.size(); i++) {
            bullets.set(i, bullets.get(i).moveForward(25));
            drawer.draw(graphics, bullets.get(i));
        }
    }
}

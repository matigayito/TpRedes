package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.collision.CollisionEngine;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.objects.Starship;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;
import static java.util.Arrays.asList;

public class CustomGameFramework implements GameFramework {

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;

    private Starship starship1 = new Starship(vector(200, 200), vector(0, -1));
    private Starship starship2 = new Starship(vector(400, 400), vector(0, -1));
    private List<Asteroid> asteroids = new ArrayList<>();

    private final CollisionEngine engine = new CollisionEngine();

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(500, 500);

        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"));
        asteroidDrawer = new AsteroidDrawer();
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        updateStarship(keySet);
        updateCannons(keySet, graphics);
        drawStarships(graphics);
        drawAsteroids(graphics);
        checkCollisions();
        bulletTravel(graphics);
        updateAsteroids();
    }

    private void checkCollisions() {
        final List<SquareCollisionable> collisionables = asList(
                starshipDrawer.getCollisionable(starship1),
                starshipDrawer.getCollisionable(starship2)
        );

        engine.checkCollisions(collisionables);
    }

    private void drawStarships(PGraphics graphics) {
        starshipDrawer.draw(graphics, starship1);
        starshipDrawer.draw(graphics, starship2);
    }

    private void drawAsteroids(PGraphics graphics){
        for (int i = 0; i < asteroids.size(); i++) {
            asteroidDrawer.draw(graphics, asteroids.get(i));
        }
    }

    private void bulletTravel(PGraphics graphics){
        starship1.gun.travel(graphics);
    }

    private void updateStarship(Set<Integer> keySet) {
        if (keySet.contains(PConstants.UP)) {
            starship1 = starship1.moveForward(2);
        }

        if (keySet.contains(PConstants.DOWN)) {
            starship1 = starship1.moveBackwards(2);
        }

        if (keySet.contains(PConstants.LEFT)) {
            starship1 = starship1.rotate(-1 * PConstants.PI / 60);
        }

        if (keySet.contains(PConstants.RIGHT)) {
            starship1 = starship1.rotate(PConstants.PI / 60);
        }
    }

    private void updateCannons(Set<Integer> keySet, PGraphics graphics){
        if (keySet.contains(PConstants.CONTROL)) {
            starship1.shoot(graphics);
        }
    }

    private void updateAsteroids(){
        Random random = new Random();
        float y = random.nextFloat();
        asteroids.add(new Asteroid(vector(0, y), vector(1, 0)));
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.set(i, asteroids.get(i).moveForward(2));
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}

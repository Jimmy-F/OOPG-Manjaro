package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * @author Jonathan Daniel
 * Dit is de speler (een bal).
 */
public class Player extends GameObject implements ICollidableWithGameObjects {

	private int size;

	private Manjaro game;

	public void update() {
		if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=game.getWidth()-size) {
            setxSpeed(0);
            setX(game.getWidth() - size);
        }
        if (getY()>=game.getHeight()-size) {
            setySpeed(0);
            setY(game.getHeight() - size);
        }
	}

	/**
     * Constructor
     * @param world Referentie naar de wereld
     */
	public Player(Manjaro game, int size) {
		this.game = game;
		this.size = size;
		
        setFriction(0.05f);
        
		/* De volgende regels zijn in een zelfgekend object nodig
        om collisiondetectie mogelijk te maken. */
		setHeight(size);
        setWidth(size);
	}

	public void keyPressed(int keyCode, char key) {
		final int speed = 5;
        if (keyCode == game.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == game.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == game.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == game.DOWN) {
            setDirectionSpeed(180, speed);
        }
	}

	public void collisionOccurred(List collidedObjects) {

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER); // Omdat cirkel anders vanuit midden wordt getekend en dat problemen geeft bij collisiondetectie
        g.stroke(255, 25, 22, 72);
        g.fill(255, 18, 18, 90);
        g.ellipse(getX(), getY(), size, size);
	}

}

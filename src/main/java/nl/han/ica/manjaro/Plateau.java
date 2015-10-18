package nl.han.ica.manjaro;

import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Plateau object.
 * @author Jimmy Feltsadas
 */
public class Plateau extends GameObject implements ICollidableWithGameObjects {

	
	private float travelspeed; 

	private Manjaro game;
	
	private boolean leftPlateau;

	private float coordinate;
	
	/**
	 * Deletes plateau if it reaches the top
	 */
	public void update() {
		if (getY() < 0 - getHeight()) {
			game.deleteGameObject(this);
		}
	}

	/**
	 * 
	 * @param game Reference to the game object
	 * @param size Width of the plateau
	 * @param travelspeed travelspeed of the plateau
	 * @param leftPlateau left or right plateau
	 */
	public Plateau(Manjaro game, int size, float travelspeed, boolean leftPlateau) {
		this.game = game;
		this.travelspeed = travelspeed;
		this.leftPlateau = leftPlateau;
		setySpeed(-travelspeed);
		setHeight(25);
		setWidth(size);
	}
	
	/**
	 * 
	 * @param game Reference to the game object
	 * @param size Width of the plateau
	 * @param travelspeed travelspeed of the plateau
	 * @param leftPlateau left or right plateau
	 * @param coordinate beginning of the plateau
	 */
	public Plateau(Manjaro game, int size, float travelspeed, boolean leftPlateau, float coordinate) {
		this.game = game;
		this.travelspeed = travelspeed;
		this.leftPlateau = leftPlateau;
		this.coordinate = coordinate;
		setySpeed(-travelspeed);
		setHeight(25);
		setWidth(size);
	}

	/**
	 * 
	 * @return returns travelspeed
	 */
	public float getTravelSpeed() {
		return this.travelspeed;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
	}

	/**
	 * Draws the plateau
	 */
	@Override
	public void draw(PGraphics g) {
		g.fill(255,255,255);
		if (leftPlateau)
			g.rect(0, getY(), getWidth(), getHeight());
		else
			g.rect(coordinate, getY(), getWidth(), getHeight());
	}
}

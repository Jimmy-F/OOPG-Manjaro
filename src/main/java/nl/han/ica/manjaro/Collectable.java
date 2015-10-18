package nl.han.ica.manjaro;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * Collectable abstract class
 * @author Jimmy Feltsadas
 *
 */
public abstract class Collectable extends GameObject implements ICollidableWithGameObjects {

	private int scoreValue;

	private Manjaro game;
	
	private int size;
	
	private int posX;

	/**
	 * 
	 * @param game Reference to the game
	 * @param posX X-position
	 * @param travelSpeed makes it go upward
	 * @param scoreValue score for increase or decrease
	 */
	public Collectable(Manjaro game, int posX, float travelSpeed, int scoreValue) {
		this.scoreValue = scoreValue;
		this.game = game;
		this.size = 25;
		this.posX = posX;
		setX(posX);
		setySpeed(-travelSpeed);
		setHeight(size);
		setWidth(size);
	}

	/**
	 * 
	 * @return returns X position
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Deletes the collectable if it reaches the ceiling
	 */
	public void update() {
		if (getY() < 0 - getHeight())
			game.deleteGameObject(this);
	}

	/**
	 * Returns the score
	 * @return
	 */
	public int getScore() {
		return this.scoreValue;
	}
	
	/**
	 * Returns the size
	 * @return
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * If collectable collides with a player add the scorevalue
	 * to the score and refresh the dashboard.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player) {
				game.setScore(getScore());
				game.deleteGameObject(this);
				game.refreshDashboard();
			}
		}
	}

	@Override
	public void draw(PGraphics g) {
	}
}

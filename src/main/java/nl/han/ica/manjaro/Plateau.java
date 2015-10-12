package nl.han.ica.manjaro;

import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
/**
 * 
 * @author Jimmy
 * @version 1.0
 */
public class Plateau extends GameObject implements ICollidableWithGameObjects {


	private float travelspeed;

	private Manjaro game;
	
	private boolean leftPlateau;

	private int coordinate;
	/**
	 * 
	 */
	public void update() {
		if (getY() < 0 - getHeight())
			game.deleteGameObject(this);
	}

	/**
	 * 
	 * @param game zet de plateau in game
	 * @param size Breedte van de plateau
	 * @param travelspeed Snelheid waarmee de plateau omhoog gaat
	 */
	public Plateau(Manjaro game, int size, float travelspeed, boolean leftPlateau) {
		this.game = game;
		this.travelspeed = travelspeed;
		this.leftPlateau = leftPlateau;
		setySpeed(-travelspeed);
		setHeight(25);
		setWidth(size);
	}
	
	public Plateau(Manjaro game, int size, float travelspeed, boolean leftPlateau, int coordinate) {
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
	 * @return Geeft de travelspeed van dit object terug
	 */
	public float getTravelSpeed() {
		return this.travelspeed;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.fill(255,255,255);
		if (leftPlateau)
			g.rect(0, getY(), getWidth(), getHeight());
		else
			g.rect(coordinate, getY(), getWidth(), getHeight());
	}

}

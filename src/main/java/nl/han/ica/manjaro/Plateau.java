package nl.han.ica.manjaro;

import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Plateau extends GameObject implements ICollidableWithGameObjects {

	private int size;

	private float travelspeed;

	private boolean passable;

	private Manjaro game;

	private GameObject gameObject;

	private PlateauSpawner plateauSpawner;

	public void update() {

	}

	public Plateau(Manjaro game) {

	}

	public void collisionOccurred(List collidedObjects) {

	}

	public float getTravelSpeed() {
		return 0;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
		
	}

}

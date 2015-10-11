package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Antimatter extends Collectable implements ICollidableWithGameObjects {

	private int scoreValue;

	private Manjaro main;

	private GameObject gameObject;

	public Antimatter(Manjaro game) {
		super(game);
	}

	public void update() {

	}

	public void getScore() {

	}

	public void collisionOccurred(List collidedObjects) {

	}

}

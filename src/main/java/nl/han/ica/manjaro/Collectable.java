package nl.han.ica.manjaro;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Collectable extends GameObject implements ICollidableWithGameObjects {

	private int scoreValue;

	private int posX;

	private int posY;

	private Manjaro main;

	public Collectable(Manjaro game) {

	}

	public void update() {

	}

	public void getScore() {

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

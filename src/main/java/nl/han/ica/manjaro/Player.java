package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Player extends GameObject implements ICollidableWithGameObjects {

	private int size;

	private Manjaro game;

	private Player player;

	private GameObject gameObject;


	public void update() {

	}

	public Player(Manjaro game) {

	}

	public void keyPressed(int keyCode, char key) {

	}

	public void collisionOccurred(List collidedObjects) {

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

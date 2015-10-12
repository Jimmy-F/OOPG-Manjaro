package nl.han.ica.manjaro;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

public class Collectable extends GameObject implements ICollidableWithGameObjects {

	private int scoreValue;

	private int posX;

	private int posY;

	private Manjaro game;

	public Collectable(Manjaro game, int size, int posX, int posY, float travelSpeed, int scoreValue) {
		this.scoreValue = scoreValue;
		this.game = game;
		this.posX = posX;
		this.posY = posY;
		setSpeed(travelSpeed);
		setHeight(size);
		setWidth(size);
	}

	public void update() {
		if (getY() < 0 - getHeight())
			game.deleteGameObject(this);
	}

	public int getScore() {
		return this.scoreValue;
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(255,0,0);
		g.ellipse(0, getY(), getWidth(), getHeight());
		
	}

}

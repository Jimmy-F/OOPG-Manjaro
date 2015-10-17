package nl.han.ica.manjaro;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.List;

public abstract class Collectable extends GameObject implements ICollidableWithGameObjects {

	private int scoreValue;

	private Manjaro game;
	
	private int size;
	
	private int posX;

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

	public int getPosX() {
		return posX;
	}

	public void update() {
		if (getY() < 0 - getHeight())
			game.deleteGameObject(this);
	}

	public int getScore() {
		return this.scoreValue;
	}
	
	public int getSize() {
		return this.size;
	}

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
		g.ellipseMode(PConstants.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(255,0,0);
		g.ellipse(posX, getY(), getWidth(), getHeight());
	}
}

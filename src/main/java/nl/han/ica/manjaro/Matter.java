package nl.han.ica.manjaro;


import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Matter extends Collectable implements ICollidableWithGameObjects {

	private int scoreValue;

	public Matter(Manjaro game, float travelSpeed, int scoreValue ) {
		super(game, travelSpeed, scoreValue);
	}

	public void update() {

	}

	public int getScore() {
		return scoreValue;
	}
	
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(PConstants.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(0,255,0);
		g.ellipse(0, getY(), getWidth(), getHeight());
	}

}

package nl.han.ica.manjaro;


import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Matter extends Collectable implements ICollidableWithGameObjects {

	public Matter(Manjaro game, int posX, float travelSpeed, int scoreValue ) {
		super(game, posX, travelSpeed, scoreValue);
	}
	
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(PConstants.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(0,255,0);
		g.ellipse(getPosX(), getY(), getWidth(), getHeight());
	}

}

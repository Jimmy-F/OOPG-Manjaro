package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Antimatter object which increases score.
 * @author Jimmy Feltsadas
 *
 */
public class Antimatter extends Collectable implements ICollidableWithGameObjects {

	/**
	 * 
	 * @param game Reference to the game
	 * @param posX X-position
	 * @param travelSpeed makes it go upward
	 * @param scoreValue score for increase or decrease
	 */
	public Antimatter(Manjaro game, int posX, float travelSpeed, int scoreValue) {
		super(game, posX, travelSpeed, scoreValue);
	}

	/**
	 * Draws the antimatter
	 */
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(PConstants.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(0,0,255);
		g.ellipse(getPosX(), getY(), getWidth(), getHeight());
	}
}

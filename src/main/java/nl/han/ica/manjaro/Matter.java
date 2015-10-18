package nl.han.ica.manjaro;


import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Draws matter which decreases your score
 * @author Jimmy Feltsadas
 *
 */
public class Matter extends Collectable implements ICollidableWithGameObjects {

	/**
	 * 
	 * @param game Reference to the game
	 * @param posX X-position
	 * @param travelSpeed makes it go upward
	 * @param scoreValue score for increase or decrease
	 */
	public Matter(Manjaro game, int posX, float travelSpeed, int scoreValue ) {
		super(game, posX, travelSpeed, scoreValue);
	}
	
	/**
	 * Draws matter
	 */
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(PConstants.CORNER);
		g.stroke(0, 50, 200, 100);
		g.fill(0,255,0);
		g.ellipse(getPosX(), getY(), getWidth(), getHeight());
	}

}

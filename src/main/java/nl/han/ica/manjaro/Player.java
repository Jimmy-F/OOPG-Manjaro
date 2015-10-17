package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * This is the player.
 * @author Jonathan Daniel
 */
public class Player extends GameObject implements ICollidableWithGameObjects {

	private int size;

	private Manjaro game;
	
	private boolean collisionOn; // For the noclip powerup.
	
	private int speed;

	public void update() {
		if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
        	game.deleteGameObject(this);
        	game.gameOver();
        }
        if (getX()>=game.getWidth()-size) {
            setxSpeed(0);
            setX(game.getWidth() - size);
        }
        if (getY()>=game.getHeight()-size) {
            setySpeed(0);
            setY(game.getHeight() - size);
        }
        
        setDirectionSpeed(180, 3);
	}

	/**
     * Constructor
     * @param game Reference to the game object.
     * @param size The size of the ball.
     */
	public Player(Manjaro game, int size) {
		this.game = game;
		this.size = size;
		
        setFriction(0.01f);
        speed = 5;
        collisionOn = true;
        
		/* De volgende regels zijn in een zelfgekend object nodig
        om collisiondetectie mogelijk te maken. */
		setHeight(size);
        setWidth(size);
	}

	@SuppressWarnings("static-access")
	public void keyPressed(int keyCode, char key) {
        if (keyCode == game.LEFT) 
            setDirectionSpeed(270, speed);
        
        if (keyCode == game.RIGHT) 
            setDirectionSpeed(90, speed);
	}

	@Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
        for (GameObject g:collidedGameObjects) {
            if (g instanceof Plateau) {
            	if(collisionOn) 
            		setY(g.getY()-25);
            	else // NoClip powerup is turned on
            		setY(g.getY()+25);
            }
        }
    }

	@SuppressWarnings("static-access")
	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER); // Omdat de cirkel anders vanuit het midden wordt getekend en dat problemen oplevert bij collisiondetectie.
        g.stroke(255, 25, 22, 72);
        g.fill(255, 18, 18, 90);
        g.ellipse(getX(), getY(), size, size);
	}

	/**
	 * Checks if collision is turned on.
	 * @return collision status (on/off).
	 */
	public boolean isCollisionOn() {
		return collisionOn;
	}

	/**
	 * Set collision on/off.
	 * @param scollisionOn collision status.
	 */
	public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
	
	/**
	 * Get the current speed.
	 * @return current speed.
	 */
	public int getPlayerSpeed(){
		return speed;
	}
	
	/**
	 * Set the current speed.
	 * @param speed current speed.
	 */
	public void setPlayerSpeed(int speed) {
		this.speed = speed;
	}
}
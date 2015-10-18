package nl.han.ica.manjaro;

import java.util.Random;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Spawns the plateaus and increases the speed and decreases the fallspace
 * @author Jimmy Feltsadas
 *
 */
public class PlateauSpawner extends GameObject implements IAlarmListener {

	private float plateausPerSecond;

	private Random random;
	
	private static float travelSpeed;

	private static float fallSpace;

	private Manjaro game;
	
	private int scoreMultiplier;

	/**
	 * 
	 * @param game reference to the game
	 * @param plateausPerSecond 
	 * @param fallSpace space between two plateaus
	 */
	public PlateauSpawner(Manjaro game, float plateausPerSecond, float fallSpace) {
		this.plateausPerSecond = plateausPerSecond;
		scoreMultiplier = 1;
		PlateauSpawner.travelSpeed = 1;
		this.game = game;
		PlateauSpawner.fallSpace = fallSpace;
		this.random = new Random();
		
		startAlarm();
	}
	
	/**
	 * Sets the fall space
	 * @param fallSpace
	 */
	public void setFallSpace(float fallSpace) {
		PlateauSpawner.fallSpace = fallSpace;
	}
	
	/**
	 * Get the fallspace
	 * @return
	 */
	public float getFallSpace() {
		return PlateauSpawner.fallSpace;
	}
	
	/**
	 * Sets the travelspeed
	 * @param travelSpeed
	 */
	public void setTravelSpeed(float travelSpeed) {
		PlateauSpawner.travelSpeed = travelSpeed;
	}
	
	/**
	 * Gets the travelspeed
	 * @return
	 */
	public float getTravelSpeed() {
		return PlateauSpawner.travelSpeed;
	}

	/**
	 * Starts the alarm
	 */
	public void startAlarm() {
		Alarm alarm = new Alarm("New plateau", 1 / plateausPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}
	
	/**
	 * Spawns random plateaus
	 * Increases the travel speed
	 * Increases plateaus per second
	 * Decreases the fall space
	 */
	@Override
	public void triggerAlarm(String alarmName) {
		int spawnCollectable = random.nextInt(2);
		boolean spawnMatter = random.nextBoolean();
		boolean collectableLeftSide = random.nextBoolean();
		
		// set the random plateau size
		int plateauSize = random.nextInt(game.getWidth() - (int)fallSpace);
		// Make the plateaus
		Plateau p1 = new Plateau(game, plateauSize, travelSpeed, true);
		Plateau p2 = new Plateau(game, (game.getWidth() - plateauSize), travelSpeed, false, plateauSize + fallSpace);
		
		int collectableX = 0;
		if (plateauSize > 40 && !collectableLeftSide)
			collectableX = random.nextInt(plateauSize - 40);
		else if(collectableLeftSide)
			collectableX = random.nextInt((int) ((game.getWidth() - plateauSize) + fallSpace));
		else
			spawnCollectable = 2;
		
		// Adds the plateaus
		game.addGameObject(p1, 0, game.getHeight());
		game.addGameObject(p2, (plateauSize + fallSpace), game.getHeight());
		
		//Increase travelspeed and plateaus per second
		setTravelSpeed((float)(travelSpeed + 0.015));
		setPlateausPerSecond((float)(plateausPerSecond + 0.012));
		
		// Decreases fall space while it is not under 40
		if (getFallSpace() > 40)
			setFallSpace((float)(fallSpace - 0.8));
		
		// Spawns  the matter or antimatter
		if (spawnCollectable == 1 && !spawnMatter && !collectableLeftSide) {
			Antimatter a = new Antimatter(game, collectableX, (float)(travelSpeed - 0.01), random.nextInt(10));
			game.addGameObject(a, a.getX(), (game.getHeight() -a.getSize()));
		}
		else if (spawnCollectable == 1 && !spawnMatter && collectableLeftSide) {
			Antimatter a = new Antimatter(game, collectableX, (float)(travelSpeed - 0.01), random.nextInt(10));
			game.addGameObject(a, a.getX(), (game.getHeight() -a.getSize()));
		}
		else if (spawnCollectable == 1 && spawnMatter && !collectableLeftSide) {
			Matter m = new Matter(game, collectableX, (float)(travelSpeed - 0.01), -1 * random.nextInt(10));
			game.addGameObject(m, m.getX(), (game.getHeight() -m.getSize()));
		}
		else if (spawnCollectable == 1 && spawnMatter && collectableLeftSide) {
			Matter m = new Matter(game, collectableX, (float)(travelSpeed - 0.01), -1 * random.nextInt(10));
			game.addGameObject(m, m.getX(), (game.getHeight() -m.getSize()));
		}
		
		//Set the score multiplier
		game.setScore(scoreMultiplier);
		
		// Start the alarm
		startAlarm();
	}
	
	/**
	 * Sets the plateaus per second
	 * @param pps
	 */
	public void setPlateausPerSecond(float pps) {
		this.plateausPerSecond = pps;
	}
	
	/**
	 * Returns plateausPerSecond
	 * @return
	 */
	public float getPlateausPerSecond() {
		return this.plateausPerSecond;
	}

	/**
	 * Gets the score multiplier
	 * @return
	 */
	public int getScoreMultiplier() {
		return scoreMultiplier;
	}
	
	/**
	 * Sets the score multiplier
	 * @param scoreMultiplier
	 */
	public void setScoreMultiplier(int scoreMultiplier) {
		this.scoreMultiplier = scoreMultiplier;
	}
	
	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
	}
}
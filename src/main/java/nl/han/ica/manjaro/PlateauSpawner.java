package nl.han.ica.manjaro;

import java.util.Random;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class PlateauSpawner extends GameObject implements IAlarmListener {

	private float plateausPerSecond;

	private Random random;
	
	private static float travelSpeed;

	private static float fallSpace;

	private Manjaro game;
	
	private int scoreMultiplier;

	public PlateauSpawner(Manjaro game, float plateausPerSecond, float fallSpace) {
		this.plateausPerSecond = plateausPerSecond;
		scoreMultiplier = 1;
		PlateauSpawner.travelSpeed = 1;
		this.game = game;
		PlateauSpawner.fallSpace = fallSpace;
		this.random = new Random();
		
		startAlarm();
	}
	
	public void setFallSpace(float fallSpace) {
		PlateauSpawner.fallSpace = fallSpace;
	}

	public void setTravelSpeed(float travelSpeed) {
		PlateauSpawner.travelSpeed = travelSpeed;
	}
	
	public float getTravelSpeed() {
		return PlateauSpawner.travelSpeed;
	}

	public void startAlarm() {
		Alarm alarm = new Alarm("New plateau", 1 / plateausPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
		int spawnCollectable = random.nextInt(2);
		boolean spawnMatter = random.nextBoolean();
		boolean collectableLeftSide = random.nextBoolean();
		int plateauSize = random.nextInt(game.getWidth() - (int)fallSpace);
		Plateau p1 = new Plateau(game, plateauSize, travelSpeed, true);
		Plateau p2 = new Plateau(game, (game.getWidth() - plateauSize), travelSpeed, false, plateauSize + fallSpace);
		
		int collectableX = 0;
		if (plateauSize > 40 && !collectableLeftSide)
			collectableX = random.nextInt(plateauSize - 40);
		else if(collectableLeftSide)
			collectableX = random.nextInt((int) ((game.getWidth() - plateauSize) + fallSpace));
		else
			spawnCollectable = 2;
		
		game.addGameObject(p1, 0, game.getHeight());
		game.addGameObject(p2, (plateauSize + fallSpace), game.getHeight());
		
		travelSpeed += 0.015;
		plateausPerSecond += 0.012;
		
		if (fallSpace > 40)
			fallSpace -= 0.8;
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
		game.setScore(scoreMultiplier);
		startAlarm();
	}

	public int getScoreMultiplier() {
		return scoreMultiplier;
	}
	
	public void setScoreMultiplier(int scoreMultiplier) {
		this.scoreMultiplier = scoreMultiplier;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub
	}
}

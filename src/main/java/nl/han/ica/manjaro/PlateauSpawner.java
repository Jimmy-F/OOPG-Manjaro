package nl.han.ica.manjaro;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class PlateauSpawner implements IAlarmListener {

	private float plateausPerSecond;

	private Random random;
	
	private static float travelSpeed;

	private static float fallSpace;

	private Manjaro game;

	public PlateauSpawner(Manjaro game, float plateausPerSecond, float fallSpace) {
		this.plateausPerSecond = plateausPerSecond;
		PlateauSpawner.travelSpeed = 1;
		this.game = game;
		PlateauSpawner.fallSpace = fallSpace;
		random = new Random();
		startAlarm();
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
		int spawnCollectable = random.nextInt(3);
		boolean spawnMatter = random.nextBoolean();
		int plateauSize = random.nextInt(game.getWidth() - (int)fallSpace);
		Plateau p1 = new Plateau(game, plateauSize, travelSpeed, true);
		Plateau p2 = new Plateau(game, (game.getWidth() - plateauSize), travelSpeed, false, plateauSize + fallSpace);
		
			
		game.addGameObject(p1, 0, game.getHeight());
		game.addGameObject(p2, (plateauSize + fallSpace), game.getHeight());
		travelSpeed += 0.015;
		plateausPerSecond += 0.012;
		if (fallSpace > 40)
			fallSpace -= 0.8;
		//if (spawnCollectable == 1 && !spawnMatter) 
			Antimatter a = new Antimatter(game, (float)(travelSpeed - 0.01), random.nextInt(10));
			game.addGameObject(a, plateauSize, (game.getHeight() -a.getSize()));
			Matter m = new Matter(game, (float)(travelSpeed - 0.01), 10);
			game.addGameObject(m, 300, (game.getHeight() -a.getSize()));
		startAlarm();
	}

}

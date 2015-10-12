package nl.han.ica.manjaro;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class PlateauSpawner implements IAlarmListener {

	private float plateausPerSecond;

	private Random random;
	
	private float travelSpeed;

	private int fallSpace;

	private Manjaro game;

	public PlateauSpawner(Manjaro game, float plateausPerSecond, int fallSpace) {
		this.plateausPerSecond = plateausPerSecond;
		this.travelSpeed = 1;
		this.game = game;
		this.fallSpace = fallSpace;
		random = new Random();
		startAlarm();
	}

	public void setSpeed(float travelSpeed) {
		this.travelSpeed = travelSpeed;
	}
	
	public float getTravelSpeed() {
		return this.travelSpeed;
	}

	public void startAlarm() {
		Alarm alarm = new Alarm("New plateau", 1 / plateausPerSecond);
		alarm.addTarget(this);
		alarm.start();
	}
	
	
	@Override
	public void triggerAlarm(String alarmName) {
		int plateauSize = random.nextInt(game.getWidth() - fallSpace);
		Plateau p1 = new Plateau(game, plateauSize, 1, true);
		Plateau p2 = new Plateau(game, (game.getWidth() - plateauSize), 1, false, plateauSize + fallSpace);
		game.addGameObject(p1, 0, game.getHeight());
		game.addGameObject(p2, (plateauSize + fallSpace), game.getHeight());
		travelSpeed += 1;
		startAlarm();
	}

}

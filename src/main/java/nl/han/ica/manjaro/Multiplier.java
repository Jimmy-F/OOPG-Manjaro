package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import processing.core.PGraphics;

public class Multiplier extends PowerUp {

	private int duration;
	
	private char key;
	
	private int originalMultiplier;
	
	private int multiplier;
	
	private PlateauSpawner platSpawner;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			originalMultiplier = platSpawner.getScoreMultiplier();
			platSpawner.setScoreMultiplier(multiplier);
			startAlarm();
			super.activate();
		} else{
			System.out.println("Not ready yet");
		}
	}

	public void keyPressed(int keyCode, char key) {
		if(key == this.key) {
			this.activate();
		}
	}
	
	public Multiplier(PlateauSpawner platSpawner, int multiplier, int duration, int cooldown, char key) {
		super(cooldown);
		this.key = key;
		this.duration = duration;
		this.platSpawner = platSpawner;
		this.multiplier = multiplier;
	}

	private void startAlarm() {
	    Alarm alarm=new Alarm("duration", duration);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	public void triggerAlarm(String alarmName) {
		if(alarmName == "duration") {
			// Turn off multiplier
			platSpawner.setScoreMultiplier(originalMultiplier);
			System.out.println("De-activated");
		} else if (alarmName == "cooldown") {
			super.setReady(true);
			System.out.println("Cooldown over");
		}
	}
	
	@Override
	public void draw(PGraphics g) {
	}
}

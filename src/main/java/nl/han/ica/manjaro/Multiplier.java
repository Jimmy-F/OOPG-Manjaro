package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;

public class Multiplier extends PowerUp {

	private int duration;
	
	private char key;
	
	private int originalMultiplier;
	
	private int multiplier;
	
	private TextObject dashText;
	
	private PlateauSpawner platSpawner;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			dashText.setForeColor(255, 0, 0, 255); // Red.
			originalMultiplier = platSpawner.getScoreMultiplier();
			platSpawner.setScoreMultiplier(multiplier);
			startAlarm();
			super.activate();
		}
	}

	public void keyPressed(int keyCode, char key) {
		if(key == this.key) {
			this.activate();
		}
	}
	
	public Multiplier(PlateauSpawner platSpawner, int cooldown, char key, int multiplier, int duration, TextObject dashText) {
		super(cooldown);
		this.key = key;
		this.duration = duration;
		this.platSpawner = platSpawner;
		this.multiplier = multiplier;
		this.dashText = dashText;
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
		} else if (alarmName == "cooldown") {
			dashText.setForeColor(0, 255, 0, 255); // Green.
			super.setReady(true);
		}
	}
	
	@Override
	public void draw(PGraphics g) {
	}
}
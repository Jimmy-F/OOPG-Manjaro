package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;

/**
 * Multiplier powerup.
 * @author Jonathan Daniel.
 */
public class Multiplier extends PowerUp {

	private int duration;
	
	private char key;
	
	private int originalMultiplier;
	
	private int multiplier;
	
	private TextObject dashText;
	
	private PlateauSpawner platSpawner;
	
	/*
	 * Activate the powerup.
	 */
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
	
	/**
	 * Constructor
	 * @param platSpawner The plateauSpawner of the game to apply the multiplier to.
	 * @param cooldown The cooldown of the powerup.
	 * @param key The key to bind the powerup to.
	 * @param multiplier The multiplier of the score (normal is 1).
	 * @param duration The duration of the powerup.
	 * @param dashText The TextObject on the dashboard to associate with this powerup.
	 */
	public Multiplier(PlateauSpawner platSpawner, int cooldown, char key, int multiplier, int duration, TextObject dashText) {
		super(cooldown);
		this.key = key;
		this.duration = duration;
		this.platSpawner = platSpawner;
		this.multiplier = multiplier;
		this.dashText = dashText;
	}

	/**
	 * Start alarm for the duration of the powerup.
	 */
	private void startAlarm() {
	    Alarm alarm=new Alarm("duration", duration);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	/*
	 * Handle end of duration and cooldown alarms.
	 */
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
package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;

public class NoClip extends PowerUp {
	
	private int duration;
	
	private char key;

	private Player player;
	
	private  TextObject dashText;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			dashText.setForeColor(255, 0, 0, 255); // Red.
			player.setCollisionOn(false);
			startAlarm();
			super.activate();
		} 
	}

	public void keyPressed(int keyCode, char key) {
		if(key == this.key) {
			this.activate();
		}
	}
	
	public NoClip(Player player, int cooldown, char key, int duration, TextObject dashText) {
		super(cooldown);
		this.key = key;
		this.duration = duration;
		this.player = player;
		this.dashText = dashText;
	}

	private void startAlarm() {
	    Alarm alarm=new Alarm("duration", duration);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	public void triggerAlarm(String alarmName) {
		if(alarmName == "duration") {
			// Turn off noclip
			player.setCollisionOn(true);
		} else if (alarmName == "cooldown") {
			super.setReady(true);
			dashText.setForeColor(0, 255, 0, 255); // Green.
		}
	}
	
	@Override
	public void draw(PGraphics g) {
	}
}
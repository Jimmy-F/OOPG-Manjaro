package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import processing.core.PGraphics;

public class NoClip extends PowerUp {
	
	private int duration;
	
	private char key;

	private Player player;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			player.setCollisionOn(false);
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
	
	public NoClip(Player player, int cooldown, char key, int duration) {
		super(cooldown);
		this.key = key;
		this.duration = duration;
		this.player = player;
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

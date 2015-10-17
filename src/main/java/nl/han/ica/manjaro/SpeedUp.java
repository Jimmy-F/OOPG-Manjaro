package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import processing.core.PGraphics;

public class SpeedUp extends PowerUp implements IAlarmListener {

	private int duration;

	private int speedValue;
	
	private int originalSpeed;
	
	private Player player;
	
	private char key;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			originalSpeed = player.getPlayerSpeed();
			System.out.println(originalSpeed);
			player.setPlayerSpeed(speedValue);
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
	
	public SpeedUp(Player player, int cooldown, char key, int speedValue) {
		super(cooldown);
		this.key = key;
		this.speedValue = speedValue;
		this.player = player;
		duration = 6;
	}

	@Override
	public void draw(PGraphics g) {
	}
	
	private void startAlarm() {
	    Alarm alarm=new Alarm("duration", duration);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	public void triggerAlarm(String alarmName) {
		if(alarmName == "duration") {
			player.setPlayerSpeed(originalSpeed);
			System.out.println("De-activated");
		} else if (alarmName == "cooldown") {
			super.setReady(true);
			System.out.println("Cooldown over");
		}
	}
}

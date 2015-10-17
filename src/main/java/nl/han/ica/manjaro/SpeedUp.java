package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;

public class SpeedUp extends PowerUp implements IAlarmListener {

	private int duration;

	private int speedValue;
	
	private int originalSpeed;
	
	private Player player;
	
	private TextObject dashText;
	
	private char key;
	
	public void activate() {
		if(super.isReady()) {
			// Activate the powerup.
			dashText.setForeColor(255, 0, 0, 255); // Red.
			originalSpeed = player.getPlayerSpeed();
			System.out.println(originalSpeed);
			player.setPlayerSpeed(speedValue);
			startAlarm();
			super.activate();
		} 
	}

	public void keyPressed(int keyCode, char key) {
		if(key == this.key) {
			this.activate();
		}
	}
	
	public SpeedUp(Player player, int cooldown, char key, int speedValue, int duration, TextObject dashText) {
		super(cooldown);
		this.key = key;
		this.speedValue = speedValue;
		this.player = player;
		this.dashText = dashText;
		this.duration = duration;
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
		} else if (alarmName == "cooldown") {
			super.setReady(true);
			dashText.setForeColor(0, 255, 0, 255); // Green.
		}
	}
}
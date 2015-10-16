package nl.han.ica.manjaro;
import java.util.Timer;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;


public abstract class PowerUp implements IAlarmListener {

	private int cooldown;
	
	private boolean ready;

	private Manjaro manjaro;


	public PowerUp(Manjaro game, int cooldown) {
		this.manjaro = game;
		this.cooldown = cooldown;
		ready = false;
	}

	public void activate() {
		if(!ready) {
			return;
		}
		
		startAlarm(); // reset the cooldown
		if(!ready)
			return;
		startAlarm();
	}

	public void update() {

	}
	
	public void keyPressed(int keyCode, char key) {
	}
	
	 private void startAlarm() {
	    Alarm alarm=new Alarm(null, cooldown);
	    alarm.addTarget(this);
	    alarm.start();
	    ready= false;
	}
	 
	 public boolean isReady() {
			return ready;
		}

	@Override
	public void triggerAlarm(String alarmName) {
		ready = true;
		Alarm alarm=new Alarm(null, cooldown);
		alarm.addTarget(this);
		alarm.start();
		ready= false;
	}
	 
}

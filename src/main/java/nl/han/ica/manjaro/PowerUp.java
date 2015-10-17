package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public abstract class PowerUp extends GameObject implements IAlarmListener {

	private int cooldown;
	
	private boolean ready;

	public PowerUp(int cooldown) {
		this.cooldown = cooldown;
		ready = true;
	}

	public void activate() {
		if(!ready) {
			System.out.println("Not ready yet");
			return;
		}
		System.out.println("Activated");
		startAlarm(); // reset the cooldown
	}

	public void update() {
	}
	
	private void startAlarm() {
	    Alarm alarm=new Alarm("cooldown", cooldown);
	    alarm.addTarget(this);
	    alarm.start();
	    ready= false;
	}
	 
	public boolean isReady() {
			return ready;
	}
	
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public void triggerAlarm(String alarmName) {
	}
}

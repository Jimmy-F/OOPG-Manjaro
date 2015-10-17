package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

/**
 * Powerup abstract class.
 * @author Jonathan Daniel.
 */
public abstract class PowerUp extends GameObject implements IAlarmListener {

	private int cooldown;
	
	private boolean ready;

	/**
	 * Constructor.
	 * @param cooldown The cooldown of the powerup.
	 */
	public PowerUp(int cooldown) {
		this.cooldown = cooldown;
		ready = true;
	}

	/**
	 * Activates the powerup if ready.
	 */
	public void activate() {
		if(!ready) 
			return;
		
		startAlarm(); // Reset the cooldown.
	}

	public void update() {
	}
	
	/**
	 * Starts the cooldown alarm.
	 */
	private void startAlarm() {
	    Alarm alarm=new Alarm("cooldown", cooldown);
	    alarm.addTarget(this);
	    alarm.start();
	    ready= false;
	}
	 
	/**
	 * Check if the powerup is ready.
	 * @return Ready status.
	 */
	public boolean isReady() {
		return ready;
	}
	
	/**
	 * Set the ready state.
	 * @param ready The state to set the ready boolean to.
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public void triggerAlarm(String alarmName) {
	}
}
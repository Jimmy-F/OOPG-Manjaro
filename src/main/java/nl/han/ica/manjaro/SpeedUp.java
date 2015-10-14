package nl.han.ica.manjaro;

public class SpeedUp extends PowerUp {

	private int speedValue;
	
	private Manjaro game;

	public void activate() {
		
		super.activate();
	}

	public SpeedUp(Manjaro game, int cooldown, int speed) {
		super(game, cooldown);
		this.game = game;
		speedValue = speed;
	}

}

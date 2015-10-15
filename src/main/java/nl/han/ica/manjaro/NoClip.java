package nl.han.ica.manjaro;

public class NoClip extends PowerUp {

	private int cooldown;
	
	private Manjaro game;

	public void activate() {
		super.activate();
	}

	public NoClip(Manjaro game, int cooldown) {
		super(game, cooldown);
		this.game = game;
	}

}

package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

/**
 * Main class.
 * @author Jimmy Feltsadas.
 * @author Jonathan Daniel.
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Manjaro extends GameEngine {

	boolean spawner = false;

	private Player player;

	private int score;
	
	private int highscore;
	
	private IPersistence persistence;
	
	private TextObject dashboardText;
	
	private TextObject powerupTextq;
	
	private TextObject powerupTextw;
	
	private TextObject powerupTexte;
	
	private Menu menu;
	
	private boolean startGame = false;
	
	private GameOver gameOver;
	
	private PlateauSpawner plateauSpawner;
	
	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.manjaro.Manjaro"});
    }
	
	public void update() {
	}
	
	/**
	 * Increase the score. 
	 * @param scoreValue the value to increase the score by.
	 */
	public void setScore(int scoreValue) {
		score += scoreValue;
		refreshDashboard();
	}
	
	/**
	 * Get current score. 
	 * @return Current score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setup method of the game.
	 */
	public void setupGame() {
		final int worldWidth = 500;
		final int worldHeight = 700;
		
		createViewWithoutViewport(worldWidth, worldHeight);
		
		initializePersistence();
		
		if (getStartGame()) {
			createPlateauSpawner();
			createDashboard(worldWidth, worldHeight);
			refreshDashboard();
			
			spawnPlayer();
		}
		
		if (!getStartGame()) {
			menu = new Menu(this);
			addGameObject(menu);
		}
		
		// Powerups.
		SpeedUp speedup = new SpeedUp(player, // Player object.
									  10, // Cooldown.
									  'q', // Key binding.
									  10, // Speed when activated.
									  6, // Duration.
									  powerupTextq); // Dashboard text object.
		
		NoClip noclip = new NoClip(player, // Player object.
								   10, // Cooldown.
								   'w', // Key binding.
								   5, // Duration.
								   powerupTextw); // Dashboard text object.
		
		Multiplier multiplier = new Multiplier(plateauSpawner, // PlaateauSpawner object.
											   10, // Cooldown.
											   'e', // Key binding.
											   10, // Multiplier.
											   5, // Duration.
											   powerupTexte); // Dashboard text object.
		addGameObject(noclip);
		addGameObject(speedup);
		addGameObject(multiplier);
	}

	/**
	 * Initialises the plateau spawner.
	 */
	public void createPlateauSpawner() {
		if (!spawner) {
			setPlateauSpawner(new PlateauSpawner(this, 1, 150));
			spawner = true;
		}
	}
	
	/**
	 * Creates a view without viewport.
	 * @param screnWidth the width.
	 * @param screenHeight the height.
	 */
	public void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/manjaro/media/background.jpg"));
		setView(view);
		size(screenWidth, screenHeight);
	}

	/**
	 * Initialises the FilePersistence object.
	 */
	public void initializePersistence() {
		persistence = new FilePersistence("main/java/nl/han/ica/manjaro/highscores/highscores.txt");
		if (persistence.fileExists()) {
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}

	/**
	 * Initialises dashboard and text objects.
	 * @param width the width of the dashboard.
	 * @param height the height of the dashboard.
	 */
	public void createDashboard(int width, int height) {
		Dashboard dashboard = new Dashboard(0,0, width, height);
		
		final int fontSize = 20;
		dashboardText = new TextObject("", fontSize);
		powerupTextq = new TextObject("", fontSize);
		powerupTextw = new TextObject("", fontSize);
		powerupTexte = new TextObject("", fontSize);
		
		dashboardText.setText("iets");
		powerupTextq.setText("Q");
		powerupTextw.setText("W");
		powerupTexte.setText("E");
		
		// Set the powerup texts to color green.
		powerupTextq.setForeColor(0, 255, 0, 255);
		powerupTextw.setForeColor(0, 255, 0, 255);
		powerupTexte.setForeColor(0, 255, 0, 255);
		
		dashboard.addGameObject(dashboardText, 300, 40);
		dashboard.addGameObject(powerupTextq, 40, 40);
		dashboard.addGameObject(powerupTextw, 40, 80);
		dashboard.addGameObject(powerupTexte, 40, 120);
		
		addDashboard(dashboard);
	}
	
	/**
	 * Get the highscore.
	 * @return the highscore.
	 */
	public int getHighscore() {
		return this.highscore;
	}
	
	/**
	 * Turn on the game over screen.
	 */
	public void gameOver() {
		if (score >= highscore) {
			persistence.saveData(Integer.toString(score));
			highscore = score;
		}
		gameOver = new GameOver(this);
		addGameObject(gameOver);
		score = 0;
	}
	
	/**
	 * Reset the game and its objects.
	 */
	public void resetGame() {
		score = 0;
		this.deleteAllDashboards();
		this.deleteAllGameOBjects();
		this.deleteGameObject(plateauSpawner);
	}
	
	/**
	 * Update dashboard text for the score.
	 */
	public void refreshDashboard() {
		dashboardText.setText("Score: " + score);
	}
	
	/**
	 * Spawns a new player.
	 */
	public void spawnPlayer() {
		final int position = 100;
		player = new Player(this, 25);
		addGameObject(player, position, position);
	}

	/**
	 * Check if startGame is true.
	 * @return startGame status.
	 */
	public boolean getStartGame() {
		return startGame;
	}

	/**
	 * Set startGame status.
	 * @param startGame status to set (true or false).
	 */
	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}

	/**
	 * Get the plateauSpawner object.
	 * @return the plateauSpawner object.
	 */
	public PlateauSpawner getPlateauSpawner() {
		return plateauSpawner;
	}

	/**
	 * Set the plateauSpawner object.
	 * @param plateauSpawner the new plateauSpawner or null.
	 */
	public void setPlateauSpawner(PlateauSpawner plateauSpawner) {
		this.plateauSpawner = plateauSpawner;
	}	
}
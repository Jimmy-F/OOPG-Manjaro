package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Manjaro extends GameEngine {
	
	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.manjaro.Manjaro"});
    }
	
	boolean spawner = false;

	private Player player;

	private int score;
	
	private int highscore;
	
	private IPersistence persistence;
	
	private TextObject dashboardText;
	
	private PlateauSpawner plateauSpawner;
	
	public void update() {

	}
	
	public void setScore(int scoreValue) {
		score += scoreValue;
		refreshDashboard();
	}
	
	public int getScore() {
		return score;
	}

	public void setupGame() {
		int worldWidth = 500;
		int worldHeight = 700;
		
		createPlateauSpawner();
		createDashboard(500,500);
		refreshDashboard();
		createViewWithoutViewport(worldWidth, worldHeight);
		initializePersistence();
		
		// Spawn the player
		spawnPlayer();

	}

	/**
	 * Maakt de plateau spawner aan
	 */
	public void createPlateauSpawner() {
		if (!spawner) {
			plateauSpawner = new PlateauSpawner(this, 1, 150);
			spawner = true;
		}
	}
	
	public void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(100,100,100);
		setView(view);
		size(screenWidth, screenHeight);
	}


	public void initializePersistence() {
		persistence = new FilePersistence("main/java/nl/han/ica/manjaro/highscores/highscores.txt");
		if (persistence.fileExists()) {
			highscore = Integer.parseInt(persistence.loadDataString());
		}
	}

	public void createDashboard(int width, int height) {
		Dashboard dashboard = new Dashboard(0,0, width, height);
		dashboardText = new TextObject("", 20);
		dashboardText.setText("iets");
		dashboard.addGameObject(dashboardText, 300, 40);
		addDashboard(dashboard);
	}
	
	public void gameOver() {
		if (score >= highscore) {
			persistence.saveData(Integer.toString(score));
			highscore = score;
		}
		Dashboard gameover = new Dashboard(0,0, getWidth(), getHeight());
		
		TextObject highscoreText = new TextObject("", 20);
		highscoreText.setText("Highscore: " + highscore);
		gameover.addGameObject(highscoreText, 100, 100);
		
		TextObject yourscoreText = new TextObject("", 20);
		yourscoreText.setText("Your score: " + score);
		gameover.addGameObject(yourscoreText, 100, 200);
		
		gameover.setBackground(255, 255, 255);
		addDashboard(gameover);
		score = 0;
		this.deleteAllGameOBjects();
		//this.deleteAllDashboards();	
		//setupGame();
	}
	

	public void refreshDashboard() {
		dashboardText.setText("Score: " + score);
	}
	
	public void spawnPlayer() {
		player = new Player(this, 25);
		addGameObject(player, 100, 100);
	}
}

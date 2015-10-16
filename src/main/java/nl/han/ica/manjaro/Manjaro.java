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
	
	private Menu menu;
	
	private boolean startGame = false;
	
	private GameOver gameOver;
	
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
		
		createViewWithoutViewport(worldWidth, worldHeight);
		
		initializePersistence();
		
		
		if (getStartGame()) {
			createPlateauSpawner();
			createDashboard(500,500);
			refreshDashboard();
			
			spawnPlayer();
		}
		
		if (!getStartGame()) {
			menu = new Menu(this);
			addGameObject(menu);
		}
		

	}

	/**
	 * Maakt de plateau spawner aan
	 */
	public void createPlateauSpawner() {
		if (!spawner) {
			setPlateauSpawner(new PlateauSpawner(this, 1, 150));
			spawner = true;
		}
	}
	
	public void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/manjaro/media/background.jpg"));
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
	
	public int getHighscore() {
		return this.highscore;
	}
	
	public void gameOver() {
		if (score >= highscore) {
			persistence.saveData(Integer.toString(score));
			highscore = score;
		}
		gameOver = new GameOver(this);
		addGameObject(gameOver);
		score = 0;
	}
	
	public void resetGame() {
		score = 0;
		this.deleteAllDashboards();
		this.deleteAllGameOBjects();
		this.deleteGameObject(plateauSpawner);
		
	}
	
	public void refreshDashboard() {
		dashboardText.setText("Score: " + score);
	}
	
	public void spawnPlayer() {
		player = new Player(this, 25);
		addGameObject(player, 100, 100);
	}

	public boolean getStartGame() {
		return startGame;
	}

	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}

	public PlateauSpawner getPlateauSpawner() {
		return plateauSpawner;
	}

	public void setPlateauSpawner(PlateauSpawner plateauSpawner) {
		this.plateauSpawner = plateauSpawner;
	}
	
}

package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Manjaro extends GameEngine {
	
	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.manjaro.Manjaro"});
    }

	private Player player;

	private int score;
	
	private TextObject dashboardText;
	
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
		
		// Spawn the player
		spawnPlayer();

	}

	/**
	 * Maakt de plateau spawner aan
	 */
	@SuppressWarnings("unused")
	public void createPlateauSpawner() {
		PlateauSpawner plateauSpawner = new PlateauSpawner(this, 1, 150);
	}
	
	public void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(100,100,100);
		setView(view);
		size(screenWidth, screenHeight);
	}


	public void initializePersistence() {

	}

	public void createDashboard(int width, int height) {
		Dashboard dashboard = new Dashboard(0,0, width, height);
		dashboardText = new TextObject("", 20);
		dashboardText.setText("iets");
		dashboard.addGameObject(dashboardText, 300, 40);
		addDashboard(dashboard);
	}

	public void refreshDashboard() {
		dashboardText.setText("Score: " + score);
	}
	
	public void spawnPlayer() {
		player = new Player(this, 25);
		addGameObject(player, 100, 100);
	}
}

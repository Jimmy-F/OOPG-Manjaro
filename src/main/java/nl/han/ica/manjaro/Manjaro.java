package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

public class Manjaro extends GameEngine {
	
	public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.manjaro.Manjaro"});
    }

	private Player player;

	private int score;

	private GameEngine gameEngine;

	//private Persistence persistence;

	private Dashboard dashboard;

	public void update() {

	}

	public void setupGame() {
		int worldWidth = 500;
		int worldHeight = 700;
		
		createViewWithoutViewport(worldWidth, worldHeight);
		
		// Spawn the player
		spawnPlayer();

	}

	public void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(0, 0, 0);
		setView(view);
		size(screenWidth, screenHeight);
	}

	public void increaseSpeed() {

	}

	public void spawnPlateau() {

	}

	public void initializePersistence() {

	}

	public void createDashboard() {

	}

	public void refreshDashboard() {

	}
	
	public void spawnPlayer() {
		player = new Player(this, 25);
		addGameObject(player, 100, 100);
	}
}

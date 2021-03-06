package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Menu class for starting the game
 * Extends GameObject to use the keyPressed function.
 * @author Jimmy Feltsadas
 *
 */
public class Menu extends GameObject {
	
	private Manjaro game;
	
	/**
	 * Draws the dashboard 
	 * @param game
	 */
	public Menu(Manjaro game) {
		this.game = game;
		Dashboard mainMenu = new Dashboard(0, 0, game.getWidth(), game.getHeight());
		TextObject start = new TextObject("", 20);
		start.setText("Press Enter to start");
		
		TextObject highscore = new TextObject("", 20);
		highscore.setText("Highscore: " + game.getHighscore());
		
		TextObject exit = new TextObject("", 20);
		exit.setText("Press Escape to exit");
		
		mainMenu.addGameObject(start, 150, 200);
		mainMenu.addGameObject(highscore, 170, 250);
		mainMenu.addGameObject(exit, 140, 300);
		mainMenu.setBackground(255, 255, 255);
		game.addDashboard(mainMenu);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
	}
	
	/** 
	 * If enter is pressed start the game
	 */
	public void keyPressed(int keyCode, char key) {
        if (keyCode == PConstants.ENTER) {
        	game.setPlateauSpawner(null);
        	game.setStartGame(true);
        	game.deleteAllDashboards();
            game.resetGame();
            game.setupGame();
        }
            
	}

}

package nl.han.ica.manjaro;

import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Draws the gameover screen.
 * Uses gameObject to use the keyPressed function
 * @author Jimmy Feltsadas
 *
 */
public class GameOver extends GameObject {
	
	private Manjaro game;
	
	/**
	 * Adds the dashboard to the game.
	 * With the highscore and player score
	 * @param game
	 */
	public GameOver(Manjaro game) {
		this.game = game;
		Dashboard mainMenu = new Dashboard(0, 0, game.getWidth(), game.getHeight());
		
		TextObject score = new TextObject("", 20);
		score.setText("Your score: " + game.getScore());
		
		TextObject highscore = new TextObject("", 20);
		highscore.setText("Highscore: " + game.getHighscore());
		
		TextObject start = new TextObject("", 20);
		start.setText("Press Enter to restart");
		
		TextObject exit = new TextObject("", 20);
		exit.setText("Press backspace to return to the main menu");
		
		mainMenu.addGameObject(score, 170, 100);
		mainMenu.addGameObject(highscore, 170, 150);
		mainMenu.addGameObject(start, 150, 200);
		mainMenu.addGameObject(exit, 50, 250);
		mainMenu.setBackground(255, 255, 255);
		game.addDashboard(mainMenu);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(PGraphics g) {
		// TODO Auto-generated method stub	
	}
	
	/**
	 * If backspace is pressed return to the main menu.
	 * If enter is pressed restart the game
	 */
	public void keyPressed(int keyCode, char key) {
        if (keyCode == PConstants.BACKSPACE) {
        	game.setStartGame(false);
        	game.deleteAllDashboards();
            game.resetGame();
            game.setupGame();
        }
        if (keyCode == PConstants.ENTER) {
        	game.setStartGame(true);
        	game.deleteAllDashboards();
            game.resetGame();
            game.setupGame();
        }
            
	}

}

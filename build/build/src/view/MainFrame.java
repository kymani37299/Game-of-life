package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import listeners.MouseListener;

public class MainFrame extends Stage{
	
	private static MainFrame instance;
	
	private BorderPane layout;
	private GameView game;
	
	private MainFrame() {
		this.layout = new BorderPane();
		this.game = new GameView();
		this.layout.setCenter(game);
		Button btnStart = new Button("Start");
		btnStart.setOnAction(e -> {
			if(this.game.getGame().isGameActive()){
				btnStart.setText("Start");
				this.game.getGame().stopGame();
				this.game.setBorder();
				
			}else{
				btnStart.setText("Stop");
				this.game.getGame().startGame();
				this.game.removeBorder();
			}
		});
		this.layout.setTop(btnStart);
		this.setTitle("Game of life");
		Scene sc = new Scene(this.layout);
		sc.setOnMouseClicked(new MouseListener(this.game));
		this.setScene(sc);
	}

	public static MainFrame getInstance() {
		if(instance==null){
			instance = new MainFrame();
		}
		return instance;
	}
	
}

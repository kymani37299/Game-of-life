package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import model.Cell;
import model.Game;
import model.Pos;

public class GameView extends GridPane implements Observer{

	private static final double cellSize = 15;
	
	private Game game;
	private Rectangle mapView[][];
	
	
	public GameView(){
		this.game = new Game();
		this.game.addObserver(this);
		this.mapView = new Rectangle[Game.mapHeight][Game.mapWidth];
		for(int i=0;i<Game.mapHeight;i++){
			for(int j=0;j<Game.mapWidth;j++){
				this.mapView[i][j] = new Rectangle(cellSize, cellSize);
				this.mapView[i][j].setStroke(Color.rgb(220, 220, 220, 0.5));
				this.mapView[i][j].setStrokeType(StrokeType.INSIDE);
				this.mapView[i][j].setStrokeWidth(0.5);
				if(game.getCell(i, j).isLive()){
					this.mapView[i][j].setFill(Color.BLUE);
				}else{
					this.mapView[i][j].setFill(Color.GRAY);
				}
				this.add(this.mapView[i][j], j, i);
			}
		}
	}

	public Pos findRect(Rectangle rect){
		for(int i=0;i<Game.mapHeight;i++){
			for(int j=0;j<Game.mapWidth;j++){
				if(rect == this.mapView[i][j]){
					return new Pos(i,j);
				}
			}
		}
		return null;
	}
	
	public void setBorder(){
		for(int i=0;i<Game.mapHeight;i++){
			for(int j=0;j<Game.mapWidth;j++){
				this.mapView[i][j].setStrokeWidth(0.5);
			}
		}
	}
	
	public void removeBorder(){
		for(int i=0;i<Game.mapHeight;i++){
			for(int j=0;j<Game.mapWidth;j++){
				this.mapView[i][j].setStrokeWidth(0);
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		for(int i=0;i<Game.mapHeight;i++){
			for(int j=0;j<Game.mapWidth;j++){
				Cell c = this.game.getCell(i, j);
				if(c.isLive()){
					this.mapView[c.getPosition().x][c.getPosition().y].setFill(Color.BLUE);
				}else{
					this.mapView[c.getPosition().x][c.getPosition().y].setFill(Color.GRAY);
				}
			}
		}
	}

	public Game getGame() {
		return game;
	}

}

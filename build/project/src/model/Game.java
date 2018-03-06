package model;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable implements Runnable{
	public static final int mapHeight = 50;
	public static final int mapWidth = 50;
	
	private Cell map[][];
	private boolean gameActive;
	
	public Game(){
		this.map = new Cell[mapHeight][mapWidth];
		this.gameActive = false;
		for(int i=0;i<mapHeight;i++){
			for(int j=0;j<mapWidth;j++){
				this.map[i][j] = new Cell(new Pos(i,j));
			}
		}
		for(int i=0;i<mapHeight;i++){
			for(int j=0;j<mapWidth;j++){
				ArrayList<Pos> neighbours = new ArrayList<>();
				neighbours.add(new Pos(i+1,j));
				neighbours.add(new Pos(i-1,j));
				neighbours.add(new Pos(i,j+1));
				neighbours.add(new Pos(i,j-1));
				neighbours.add(new Pos(i+1,j+1));
				neighbours.add(new Pos(i-1,j+1));
				neighbours.add(new Pos(i+1,j-1));
				neighbours.add(new Pos(i-1,j-1));
				for(Pos p : neighbours){
					if(p.x>=0 && p.x<mapHeight && p.y>=0 && p.y<mapWidth){
						this.map[i][j].addNeighbour(this.map[p.x][p.y]);
					}
				}
			}
		}
	}
	
	public Cell getCell(int row,int col){
		return this.map[row][col];
	}
	
	public void startGame(){
		this.gameActive = true;
		new Thread(this).start();
	}
	
	public void stopGame(){
		this.gameActive = false;
	}
	
	@Override
	public void run() {
		while(this.gameActive){
			for(int i=0;i<mapHeight;i++){
				for(int j=0;j<mapWidth;j++){
					this.map[i][j].update();
				}
			}
			this.notifyObservers();
			this.setChanged();
			for(int i=0;i<mapHeight;i++){
				for(int j=0;j<mapWidth;j++){
					this.map[i][j].finalUpdate();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public boolean isGameActive() {
		return gameActive;
	}
	
}

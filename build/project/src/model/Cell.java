package model;

import java.util.ArrayList;

public class Cell {
	
	private ArrayList<Cell> neighbours;
	private boolean live;
	private boolean finalLive;
	private Pos position;
	
	public Cell(Pos position){
		this(false,position);
	}
	
	public Cell(boolean live,Pos position){
		this.live = live;
		this.finalLive = live;
		this.neighbours = new ArrayList<>();
		this.position = position;
	}
	
	public void update(){
		int liveNeighbours = 0;
		for(Cell c : neighbours){
			if(c.live){
				liveNeighbours++;
			}
		}
		if(this.live){
			if(liveNeighbours<2){
				this.finalLive = false;
			}else if(liveNeighbours>3){
				this.finalLive = false;
			}
		}else{
			if(liveNeighbours==3){
				this.finalLive = true;
			}
		}
	}
	
	public void finalUpdate(){
		this.live = this.finalLive;
	}
	
	public void addNeighbour(Cell n){
		this.neighbours.add(n);
	}

	public Pos getPosition() {
		return position;
	}

	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live){
		this.finalLive = live;
		this.live = live;
	}
	
}

package model;

public class Pos {
	public int x;
	public int y;
	
	public Pos(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pos){
			Pos p = (Pos)obj;
			return p.x == this.x && p.y == this.y;
		}
		return super.equals(obj);
	}
}

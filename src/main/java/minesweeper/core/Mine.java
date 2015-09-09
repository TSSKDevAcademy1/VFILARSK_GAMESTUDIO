package minesweeper.core;

import minesweeper.core.Tile.State;

/**
 * Mine tile.
 */
public class Mine extends Tile {
	private int y;
	private int x;
	
	public Mine(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		String string = "";
		if(this.getState() == State.OPEN){
			string = "<img src=\"resources/gfx/mine.png\">";
		} else if(this.getState() == State.MARKED){
			string="M";
		} else {
			//string="<a href=\"http://www.w3schools.com\"><img src=\"resources/gfx/closed.png\"></a>";
			string="<a href=\"?rowC="+this.y+"&columnC="+this.x+"\"><img src=\"resources/gfx/closed.png\"></a>";
		}
		
		return string;
	}
	
	  public void setX(int x){
	    	this.x = x;
	    }
	    
	    public void setY(int y){
	    	this.y = y;
	    }
	    
	    public int getX(){
	    	return this.x;
	    }
	    
	    public int getY(){
	    	return this.y;
	    }
}

package minesweeper.core;

/**
 * Clue tile.
 */
public class Clue  extends Tile {
    /** Value of the clue. */
    private final int value;
    private int y=5;
    private int x=5;
    /**
     * Constructor.
     * @param value  value of the clue
     */
    public Clue(int x,int y,int value){
    	this.x = x;
    	this.y = y;
    	this.value = value;
    }
    
    public Clue(int value) {
        this.value = value;
    }

	public int getValue() {
		return value;
	}
	
	public String toString(){
		String string = "";
		if(this.getState() == State.OPEN){
			string = "<img src=\"resources/gfx/open"+value+".png\">";
		} else if(this.getState() == State.MARKED){
			string="M";
		} else {
			
			// string="<a href=\"http://www.w3schools.com\"><img src=\"resources/gfx/closed.png\"></a>";
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

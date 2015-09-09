package stones;

import java.io.Serializable;

public class Tile implements Serializable {

	private int positionX = 0;
	private int positionY = 0;
	private int value = 0;
	private boolean isClickable = true;

	public Tile(int positionX, int positionY, int value) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.value = value;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString(){
		return "<img src=\"?valueX="+this.positionX+"%d&valueY="+this.positionY+"value="+this.value+">";
		
	}
	
	public boolean getClickable() {
		return isClickable;
	}



	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
	}
}

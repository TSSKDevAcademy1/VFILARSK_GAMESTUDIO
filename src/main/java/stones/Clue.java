package stones;

import java.io.Serializable;

public class Clue extends Tile implements Serializable{
	

	public Clue(int positionX, int positionY, int value) {
		super(positionX, positionY,value);
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String toString(){
		if(this.getClickable()){
			return  "<a href=\"?valueX="+this.getPositionX()+"&valueY="+this.getPositionY()+"\"><button type = \"button\" class=\"button\">"+this.getValue()+"</button></a>";
		} else {return "<button type=\"button\">"+this.getValue()+"</button>";}
	}
	// <img src=\"?valueX="+this.getPositionX()+"&valueY="+this.getPositionY()+"\" value=\""+this.getValue()+"\">";
}

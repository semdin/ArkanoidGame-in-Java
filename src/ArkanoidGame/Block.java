package ArkanoidGame;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Block extends Rectangle{
	JLabel brick;
	int brickDur;
	private int brickPoint;
	
	Block(Icon brickIcon, int i, int brickDur, int brickPoint){
	    brick = new JLabel(brickIcon);
	    this.brickDur = brickDur;
	    this.brickPoint = brickPoint;
	    if(i>=20 && i<30) {
	    	brick.setBounds(40+(i-20)*60,100,50,19);
	    }
	    else if(i>=10 && i<20) {
		    brick.setBounds(40+(i-10)*60,70,50,19);
	    }
	    else {
		    brick.setBounds(40+(i)*60,40,50,19);
	    }

		
	}

	public int getBrickPoint(){
		return brickPoint;
	}
	

}

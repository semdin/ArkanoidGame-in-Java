package ArkanoidGame;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Players{
	
	private String name;
	private int score;
	private LocalDateTime dateTime;
    static ArrayList<Players> playersArray  = new ArrayList<Players>(30);
	
	Players(String name, int score, LocalDateTime dateTime){
		this.name = name;
		this.score = score;
		this.dateTime = dateTime;
		playersArray.add(this);
	}
	
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public String toString() {
		return this.name+ " "+ this.score + " "+ this.dateTime;
	}

}

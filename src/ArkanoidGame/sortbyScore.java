package ArkanoidGame;

import java.util.Comparator;

public class sortbyScore implements Comparator<Players> {

	@Override
	public int compare(Players o1, Players o2) {
		return o2.getScore() - o1.getScore();
	}

}

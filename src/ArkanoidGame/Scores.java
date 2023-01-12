package ArkanoidGame;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Scores extends JFrame{
	
	private Players players;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	Scores(){
		super("Score Table");
 		setFocusable(true);
 		setFocusTraversalKeysEnabled(false);
 		setLayout(new CardLayout());
 		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		Collections.sort(Players.playersArray, new sortbyScore());
		
		DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model); 
		


		// Create a couple of columns 
		model.addColumn("Name"); 
		model.addColumn("Score");
		model.addColumn("Date-Time");

		// Append a row 
		model.addRow(new Object[]{"Name", "Score", "Date-Time"});
		
		if(Players.playersArray.size() >=10) {
			for(int i = 0; i<10; i++) {
				if(Players.playersArray.get(i) != null) {
					String formattedDate = Players.playersArray.get(i).getDateTime().format(myFormatObj);
					model.addRow(new Object[]{Players.playersArray.get(i).getName(), Players.playersArray.get(i).getScore(), formattedDate});
				}
			}
		}
		else if(Players.playersArray.size()>0 && Players.playersArray.size()<10) {
			for(int i = 0; i<Players.playersArray.size(); i++) {
				if(Players.playersArray.get(i) != null) {
				    String formattedDate = Players.playersArray.get(i).getDateTime().format(myFormatObj);
					model.addRow(new Object[]{Players.playersArray.get(i).getName(), Players.playersArray.get(i).getScore(), formattedDate});
				}
			}
		}
		
		table.setVisible(true);
		add(table);

	}

}

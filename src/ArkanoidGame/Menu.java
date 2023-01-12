package ArkanoidGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame{
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	
	public Menu() {
		super("Main Menu");
		setBounds(300,100,700,600);
		setLayout(new GridLayout(6,1, 0,25));
		
		button1 = new JButton("New game");
		button2 = new JButton("Options");
		button3 = new JButton("Scores");
		button4 = new JButton("Help");
		button5 = new JButton("About");
		button6 = new JButton("Exit");
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				
				JFrame levelMenu = new JFrame("Select Starting Level");
				levelMenu.setBounds(300,100,350,300);
				levelMenu.setLayout(new GridLayout(1,3, 0,250));
				levelMenu.setVisible(true);
				
				JButton level1;
				JButton level2;
				JButton level3;
				
				level1 = new JButton("LEVEL 1");
				level2 = new JButton("LEVEL 2");
				level3 = new JButton("LEVEL 3");
				levelMenu.add(level1);
				levelMenu.add(level2);
				levelMenu.add(level3);
				
				levelMenu.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				    	levelMenu.dispose();
			            Menu.this.setVisible(true);
				    }
				});
				
				
				level1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						levelMenu.dispose();
						Game game = new Game(1,8);
						game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						game.setBounds(300,100,700,600);
						game.setResizable(false);
						game.setVisible(true);
					}
				});
				level2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						levelMenu.dispose();
						Game game = new Game(2,6);
						game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						game.setBounds(300,100,700,600);
						game.setResizable(false);
						game.setVisible(true);
					}
				});
				level3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						levelMenu.dispose();
						Game game = new Game(3,4);
						game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						game.setBounds(300,100,700,600);
						game.setResizable(false);
						game.setVisible(true);
					}
				});
				
				
				
				/*Game game = new Game();
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setBounds(300,100,700,600);
				game.setResizable(false);
				game.setVisible(true);
				Menu.this.dispose();*/
			
			
			}
    });
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Menu.this, String.format("You Pressed: %s", e.getActionCommand()));
			}
    });
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Scores scores = new Scores();
				scores.setBounds(300,100,700,600);
				scores.setResizable(false);
				scores.setVisible(true);
				Menu.this.dispose();
				
				scores.addWindowListener(new java.awt.event.WindowAdapter() {
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        	scores.dispose();
			            Menu.this.setVisible(true);
				    }
				});
				
			}
    });
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		         JOptionPane.showMessageDialog(null,
		                 "--> You can start the game when press \"Enter\"\n"
		                 + "--> You can change the x-direction of paddle with Left Arrow Key and Right Arrow Key.\n"
		                 + "--> While the ball and the brick intersect each other, the brick will be destroyed and you will get point.\n"
		                 + "--> If the ball falls under the paddle. You will lose 1 life.\n"
		                 + "--> If your lives be 0 GAME is OVER! ",
		                 "How To Play",
		                 JOptionPane.INFORMATION_MESSAGE);
			}
    });
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = "Mehmet Semdin";
				String surname = "Aktay";
				String number = "20190702102";
				String email = "mehmetsemdin.aktay@std.yeditepe.edu.tr";
				JOptionPane.showMessageDialog(Menu.this, String.format("Name: %s\nSurname: %s\nSchool Number: %s\nEmail: %s",name, surname, number, email ));
			}
    });
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
    });
	}

}

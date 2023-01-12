package ArkanoidGame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.time.LocalDateTime;


public class Game extends JFrame implements KeyListener, ActionListener{
	
	//Components
    ArrayList<Block> blockArray  = new ArrayList<Block>(30);
	private JLabel background;
	private JLabel paddle;
	private JLabel ball;
	//topBar
	private JLabel scoreBar;
	private JLabel levelBar;
	private JLabel livesBar;
	
	//images
	private Icon backgroundImage = new ImageIcon(new ImageIcon(getClass().getResource("images/bg.png")).getImage().getScaledInstance(700, 600, Image.SCALE_DEFAULT));
	private Icon brick1 = new ImageIcon(new ImageIcon(getClass().getResource("images/brick-green.png")).getImage().getScaledInstance(50, 19, Image.SCALE_DEFAULT));
	private Icon brick2 = new ImageIcon(new ImageIcon(getClass().getResource("images/brick-red.png")).getImage().getScaledInstance(50, 19, Image.SCALE_DEFAULT));
	private Icon brick3 = new ImageIcon(new ImageIcon(getClass().getResource("images/brick-purple.png")).getImage().getScaledInstance(50, 19, Image.SCALE_DEFAULT));

	private Icon paddleIcon;
	private Icon ballIcon;
	
	//initial
	private int paddleX_initial = 310;
	//private int paddleY_initial = 500;
	private int ballX_initial = 350;
	private int ballY_initial = 400;
	
	//timer
	//private Timer timer;
	//private int delay = 8;
	private int delay = 8;
	
	
	//info
	private boolean play = false;
	private boolean flag = false;
	private int score = 0;
	private int lives = 3;
	private int level = 1;
	//paddle
	private int paddleX = 300;
	private int paddleY = 500;
	//ball
	private int ballX = 350;
	private int ballY = 400;
	private int ballXdir = 0;
	private int ballYdir = 2;
	//brick
	private int brick1Dur = 1;
	private int brick1Point = 20;
	private int brick2Dur = 2;
	private int brick2Point = 40;
	private int brick3Dur = 3;
	private int brick3Point = 60;

	
 	public Game(int level, int delay) {
		super("Arkanoid Game");
 		addKeyListener(this);
 		setFocusable(true);
 		setFocusTraversalKeysEnabled(false);
		setLayout(null);
		background = new JLabel(backgroundImage);
		setContentPane( background );
		

		this.level  = level;
		this.delay = delay;
		
		createBricks();
		createPaddle();
		createBall();
		createTopBar();
		
	}

	private void createTopBar() {
		scoreBar = new JLabel("Score: "+score);
		scoreBar.setBounds(0,0, 100,30);
		scoreBar.setFont(new Font("Calibri", Font.PLAIN, 20));
		scoreBar.setForeground(Color.white);
		add(scoreBar);
		
		levelBar = new JLabel("Level: "+level);
		levelBar.setBounds(320,0, 100,30);
		levelBar.setFont(new Font("Calibri", Font.PLAIN, 20));
		levelBar.setForeground(Color.white);
		add(levelBar);

		livesBar = new JLabel("Lives: "+lives);
		livesBar.setBounds(620,0, 100,30);
		livesBar.setFont(new Font("Calibri", Font.PLAIN, 20));
		livesBar.setForeground(Color.white);
		add(livesBar);
		
	}

	private void createBricks() {
		
		if(level == 1) {
			for (int i = 0; i <10 ; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}
			
			for (int i = 10; i < 20; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}

			for (int i= 20; i < 30; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}
		}
		else if(level == 2) {
			for (int i = 0; i <10 ; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}
			
			for (int i = 10; i < 20; i++) {
				blockArray.add( new Block(brick2,i,brick2Dur,brick2Point));
				add(blockArray.get(i).brick);
			}

			for (int i= 20; i < 30; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}

		}
		else if(level == 3) {
			for (int i = 0; i <10 ; i++) {
				blockArray.add( new Block(brick3,i,brick3Dur,brick3Point));
				add(blockArray.get(i).brick);
			}
			
			for (int i = 10; i < 20; i++) {
				blockArray.add( new Block(brick2,i,brick2Dur,brick2Point));
				add(blockArray.get(i).brick);
			}

			for (int i= 20; i < 30; i++) {
				blockArray.add( new Block(brick1,i,brick1Dur,brick1Point));
				add(blockArray.get(i).brick);
			}

		}
		
	}

	private void createPaddle() {
		paddleIcon = new ImageIcon(new ImageIcon(getClass().getResource("images/paddle.png")).getImage().getScaledInstance(120, 25, Image.SCALE_DEFAULT));
		paddle = new JLabel(paddleIcon);
		paddle.setBounds(paddleX,paddleY, 120,25);
		add(paddle);
		
	}
	
	private void createBall() {
		ballIcon = new ImageIcon(new ImageIcon(getClass().getResource("images/ball.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		ball = new JLabel(ballIcon);
		ball.setBounds(ballX,ballY, 20, 20);
		add(ball);
	}
	
	private void createSavingPanel() {
		
		ball.setVisible(false);
		paddle.setVisible(false);
		
		//Saving
		JInternalFrame saveFrame;
		JButton saveButton;
		JTextField name;
		JLabel nameLabel;
		
        saveFrame = new JInternalFrame();
        saveFrame.setTitle("Save Your Score! Your Score: "+score);
        saveFrame.setResizable(false);
        saveFrame.setBounds(200,150,300,300);
        
        
        nameLabel=new JLabel("Name:");
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        nameLabel.setForeground(Color.BLACK);
        name = new JTextField(20);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	LocalDateTime dateTime = LocalDateTime.now();
                Players newPlayer = new Players(name.getText(), score, dateTime);
                /*System.out.println("Name: " + newPlayer.getName());
                System.out.println("Score: " + newPlayer.getScore());
                System.out.println("Date-Time: " + newPlayer.getDateTime());
                System.out.println("Total Players: " + Players.playersArray.size());*/
				Menu menu = new Menu();
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				menu.setResizable(false);
				menu.setVisible(true);
                Game.this.dispose();
				
            }
        });
		

		saveFrame.setVisible(true);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
		
		p.add(nameLabel);
		p.add(name);
		p.add(saveButton);
		saveFrame.add(p);
		add(saveFrame);
		//saveFrame.pack();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//timer.start();
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if((lives>0 && !flag)) {
				play = true;
				flag = true;
				new Thread(()->{
					while(true) {
						update();
						try {
								Thread.sleep(delay);
						}
						catch(InterruptedException err) {
							err.printStackTrace();
						}
					}
				}).start();
			}
			/*play = true;
			new Thread(()->{
				while(true) {
					update();
					try {
						Thread.sleep(8);
					}
					catch(InterruptedException err) {
						err.printStackTrace();
					}
				}
			}).start();*/
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(paddleX >=570) {
				paddleX = 570;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(paddleX <=0) {
				paddleX = 0;
			}
			else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(play == false && lives>0)
				play = true;
		}
		
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {

            System.exit(0);

        }
		
	}


	public void moveRight() {
		if(play && lives>0) {
			paddleX +=20;
			//paddle.setLocation(paddleX,paddleY);
		}
		//System.out.printf("sað");
		
	}

	public void moveLeft() {
		if(play && lives>0) {
			paddleX -= 20;
			//paddle.setLocation(paddleX,paddleY);
		}
		//System.out.printf("sol");
		
	}
	
	public void update() {
		
		if(play && lives>0) {
			ballX += ballXdir;
			if (ballX > 670 || ballX < 0) {
				ballXdir *= -1;
			}
			if(ballY <0 || intersects(ball, paddle)) {
				if(intersects(ball, paddle)) {
					playSound("sounds/bump.wav");
					if(ballX <= paddleX+50 && ballXdir >= 0) {
						if(ballXdir == 0 && ballX == paddleX+50)
							ballXdir = 0;
						else if(ballXdir == 0)
							ballXdir +=1;
						ballXdir = -ballXdir;
						//System.out.printf("Selam");
					}
					else if(ballX >= paddleX+50 && ballXdir <= 0) {
						if(ballXdir == 0)
							ballXdir -=1;
						ballXdir = -ballXdir;
					}
					else if(ballX >= paddleX+45 && ballX <= paddleX+55)
						ballXdir = 0;
				}
				
				ballYdir = -ballYdir;
			}
			for(int i = 0; i<blockArray.size(); i++) {
				if(intersects(ball, blockArray.get(i).brick)) {
					
					//System.out.printf("%d -- %d\n", ballY, blockArray.get(i).brick.getY());
					
					if(ballX == blockArray.get(i).brick.getX()+15) {
						ballXdir = 0;
						ballYdir = -ballYdir;
					}
					else if((ballY - blockArray.get(i).brick.getY()) < 18 && (ballY - blockArray.get(i).brick.getY()>-18)) { //sadece kenarlardan vurma
						ballXdir = -ballXdir;
					}
					else
						ballYdir = -ballYdir;
						
					
					
					/*if(ballX <= blockArray.get(i).brick.getX()+15 && ballXdir >=0) {
						if(ballXdir == 0)
							ballXdir +=1;
						ballXdir = -ballXdir;
					}
					else if(ballX >= blockArray.get(i).brick.getX()+15 && ballXdir <=0) {
						if(ballXdir == 0)
							ballXdir -=1;
						ballXdir = -ballXdir;
					}
					else if(ballX >= blockArray.get(i).brick.getX()+14 || ballX <= blockArray.get(i).brick.getX()+16)
						ballXdir = 0;*/
					--blockArray.get(i).brickDur;
					if(blockArray.get(i).brickDur == 0) {
						score += blockArray.get(i).getBrickPoint();
						scoreBar.setText("Score: "+score);
						remove(blockArray.get(i).brick);
						blockArray.remove(i);
						//sound
						playSound("sounds/brick_break.wav");
					}
					else
						playSound("sounds/brick_hit.wav");
					break;
				}
			}
			
			if(blockArray.size()==0) {
				if(level == 3) {
					createSavingPanel();
				}
				else {
					levelBar.setText("Level: "+(++level));
					delay -= 2;
					createBricks();
				}
				ballX = ballX_initial;
				ballY = ballY_initial;
				ballXdir = 0;
				paddleX = paddleX_initial;
				repaint();
				play = false;
			}
			if(ballY>560) {
				play = false;
				--lives;
				livesBar.setText("Lives: "+lives);
				ballX = ballX_initial;
				ballY = ballY_initial;
				ballXdir = 0;
				paddleX = paddleX_initial;
				//ball.setLocation(ballX, ballY);
				//paddle.setLocation(paddleX, paddleY);
				if(lives == 0) {
					createSavingPanel();
					playSound("sounds/game_over.wav");
				}
				else
					playSound("sounds/fail.wav");
			}
				
			ballY += ballYdir;
			ball.setLocation(ballX, ballY);
			paddle.setLocation(paddleX, paddleY);
			repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean intersects(JLabel testa, JLabel testb){
	    Area areaA = new Area(testa.getBounds());
	    Area areaB = new Area(testb.getBounds());

	    return areaA.intersects(areaB.getBounds2D());
	}
	
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		        		this.getClass().getResource(url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	
}



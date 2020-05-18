//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/10/20
//
//  Notes:
//       Holds all the GUI for the project and houses the main. Uses almost every other class to run a game of Pokemon.
//
//  Bugs:
//      Lots Probably. Will be listed all be listed at the end of the project
//
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Battle {
    public static int P1numberOfFaintedMons=0;//self explanatory
    public static int P2numberOfFaintedMons=0;//self explanatory

	private JFrame frame=new JFrame();//self explanatory
	private JPanel mainPanel;//self explanatory
	private JPanel leftPanel, rightPanel, leftDisplayPanel, rightDisplayPanel;//the display shown to the user
	private JPanel leftUI, rightUI;//the users interface
	private JPanel leftMovePanel=new JPanel(),rightMovePanel = new JPanel();//move options
	private JPanel leftSwitchPanel= new JPanel(),rightSwitchPanel=new JPanel();//switch options

	private static JLabel name1, name2, image1, image2,name3, name4, image3, image4,
			attack = new JLabel("Attack"), switchOut = new JLabel("Switch");
	private HealthBar bar1, bar2, bar3, bar4;
	private Button[] leftMoveButtons = new Button[4],rightMoveButtons=new Button[4];
	private int p1Selection=-1;
	private int p2Selection =-1;
	private Button[] leftSwitchButtons = new Button[6],rightSwitchButtons = new Button[6];
	private ButtonGroup leftButtons=new ButtonGroup(),rightButtons=new ButtonGroup();
	public JTextArea leftText, rightText;
	private Timer timer;
	boolean confirm1=false,confirm2=false;//i hate action performed not being able to access things like a normal method
	static Player P1, P2;
	private static Object myObject1 =new Object(), myObject2=new Object(), myObject3=new Object();






	public Battle() {

        String filepath1 = "Music/PokemonTitleScreen.wav";
        String filepath2 = "Music/BattleVsWildPokemon.wav";
        String filepath3 = "Music/BattleVsTrainer.wav";
        String filepath4 = "Music/BillsLighthouse.wav";
        String filepath5 = "Music/PaletteTown.wav";
        String filepath6 = "Music/PewterCity.wav";
        String filepath7 = "Music/PokemonGym.wav";
        String filepath8 = "Music/RivalAppears.wav";
        String filepath9 = "Music/TeamRocketHideout.wav";
        String filepath10 = "Music/ViridianForest.wav";
        PlayMusic musicObject = new PlayMusic();
//self explanatory
        int random = (int) (Math.random()*10);
//        if(random < 1)
//        {
//        	musicObject.playMusic(filepath1);
//        }
//        else if(random < 2)
//        {
//        	musicObject.playMusic(filepath2);
//        }
//        else if(random < 3)
//        {
//        	musicObject.playMusic(filepath3);
//        }
//        else if(random < 4)
//        {
//        	musicObject.playMusic(filepath4);
//        }
//        else if(random < 5)
//        {
//        	musicObject.playMusic(filepath5);
//        }
//        else if(random < 6)
//        {
//        	musicObject.playMusic(filepath6);
//        }
//        else if(random < 7)
//        {
//        	musicObject.playMusic(filepath7);
//        }
//        else if(random < 8)
//        {
//        	musicObject.playMusic(filepath8);
//        }
//        else if(random < 9)
//        {
//        	musicObject.playMusic(filepath9);
//        }
//        else if(random < 10)
//        {
//        	musicObject.playMusic(filepath10);
//        }
        frame = new JFrame();
        BufferedImage icon;
        try {
			icon = ImageIO.read(new File("Images/Pokeball.png"));
	        frame.setIconImage(icon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}


//    	panel = new JPanel(new GridBagLayout());
//    	display = new JPanel();
//    	movePanel = new JPanel();
//    	switchPanel = new JPanel();
    	GridBagConstraints c = new GridBagConstraints();
//		mainPanel=new JPanel();
//		frame.add(mainPanel);
//				frame.getContentPane().add(rightPanel);

		frame.setBounds(100, 100, 3000, 750);
		frame.setVisible(true);
		 frame.setResizable(false);
		frame.setTitle("NOT Pokemon");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		confirm1 =false;
//		confirm2=false;
		teamBuilder();

//		while(wait3){
//			if((wait1==false)&&(wait2==false)){
//				wait3=false;
//				mainPanel.removeAll();
//			}
//			System.out.print("");
//		}

//		mainPanel.wait();
		try{
		synchronized(myObject1){
			myObject1.wait();
		}}
		catch (Exception e){
			e.printStackTrace();
		}
//		mainPanel.remove(leftPanelTB);
//		mainPanel.remove(rightPanelTB);
//		mainPanel.removeAll();
		frame.getContentPane().removeAll();
		mainPanel=new JPanel();
			P1.setOpposingPlayer(P2);
			P2.setOpposingPlayer(P1);

			mainPanel.setLayout(new GridLayout(1, 2));
		frame.add(mainPanel);
			leftPanel = new JPanel(new GridBagLayout());
			rightPanel = new JPanel(new GridBagLayout());
			int backgroundNumber;
			do {
				backgroundNumber = (int)(Math.random()*18)+1;
			} while(backgroundNumber == 9 || backgroundNumber == 12);
			BufferedImage background;
			try {
				background = ImageIO.read(new File("Images/Backgrounds/BattleBackground"
						+ backgroundNumber + ".png"));
				leftDisplayPanel = new BackgroundPanel(background);
				rightDisplayPanel = new BackgroundPanel(background);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			leftPanel.add(leftDisplayPanel);
			rightPanel.add(rightDisplayPanel);
			leftUI = new JPanel(new GridLayout(2, 1));
			rightUI = new JPanel(new GridLayout(2, 1));
//		leftPanel.add(leftUI, BorderLayout.SOUTH);
//		rightPanel.add(rightUI,BorderLayout.SOUTH);
//		leftUI.add(attack);
			leftUI.add(leftMovePanel);
//		leftUI.add(switchOut);
			leftUI.add(leftSwitchPanel);
			rightUI.add(rightMovePanel);
			rightUI.add(rightSwitchPanel);
			mainPanel.add(leftPanel);
			mainPanel.add(rightPanel);
//		p1PokemonButton.setVisible(true);
//		p1PokemonButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane leftPokemonInfoPopup=new JOptionPane("P1 current Pokemon info");
//				leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel,
//						p1.getCurrentMon().toString(),"Pokemon Info",
//						2,
//						new ImageIcon("Images/Sprites/SpritesFront/"+p1.getCurrentMon().getID()+".gif"));
////			leftText.setText(leftText.getText()+p1.getCurrentMon().toString());
//			}
//		});
//		p2PokemonButton.setVisible(true);
//		p2PokemonButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane leftPokemonInfoPopup=new JOptionPane("P2 current Pokemon info");
//				leftPokemonInfoPopup.showMessageDialog(
//						rightDisplayPanel,
//						p2.getCurrentMon().toString(),
//						"Pokemon Info",
//						2,
//						new ImageIcon("Images/Sprites/SpritesFront/"+p2.getCurrentMon().getID()+".gif"));
////				rightText.setText(rightText.getText()+p2.getCurrentMon().toString());
//			}
//		});
P1.setCurrentMon();
P2.setCurrentMon();
			name1 = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);

//			System.out.println(P2.getCurrentMon().getName());
			name2 = new JLabel(P2.getCurrentMon().getName(), SwingConstants.CENTER);
			bar1 = new HealthBar(P1.getCurrentMon());
			bar1.setBackground(Color.LIGHT_GRAY);
			bar1.setXOffset(true);
			bar2 = new HealthBar(P2.getCurrentMon());
			bar2.setBackground(Color.LIGHT_GRAY);
			bar2.setXOffset(false);
			ImageIcon pic =
//					new ImageIcon
//					(new ImageIcon(
//							"Images/Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif")
//							.getImage().
//									getScaledInstance(100, 500, Image.SCALE_DEFAULT));

					new ImageIcon("Images/Sprites/SpritesBack/" + P1.getCurrentMon().getID() + "-back.gif");
			//pic = new ImageIcon(pic.getImage().getScaledInstance((int) (pic.getIconWidth() * 4), (int) (pic.getIconHeight() * 4), Image.SCALE_DEFAULT));
			image1 = new JLabel(pic);
			image1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");

						leftPokemonInfoPopup.setBackground(Color.DARK_GRAY);
						leftPokemonInfoPopup.showMessageDialog(leftDisplayPanel,
								P1.getCurrentMon().toString(), "Player 1 Pokemon Info",
								2,
//								(new ImageIcon (new ImageIcon("Images/Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
								new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif"));
					}
				}
			});
//			leftP1Image.setIcon(pic);
			pic = new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif");
			image2 = new JLabel(pic);
			image2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
						leftPokemonInfoPopup.showMessageDialog(
								leftDisplayPanel,
								P2.getCurrentMon().toString(),
								"Player 2 Pokemon Info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif"));
//				rightText.setText(rightText.getText()+p2.getCurrentMon().toString());
					}
				}
			});
			leftDisplayPanel.setLayout(new GridBagLayout());
			GridBagConstraints c2 = new GridBagConstraints();
			c2.gridx = 0;
			c2.gridy = 3;
			c2.fill = GridBagConstraints.HORIZONTAL;
			c2.insets = new Insets(5, 5, 5, 5);
			c2.anchor = GridBagConstraints.LAST_LINE_START;
			c2.weightx = 0.5;
			c2.weighty = 0.5;
//			c2.ipadx=-200000;
			leftDisplayPanel.add(name1, c2);
//			c2.ipadx=0;
			c2.gridy = 4;
			leftDisplayPanel.add(bar1, c2);
			c2.gridy = 5;
			leftDisplayPanel.add(image1, c2);
			c2.gridx = 1;
			c2.gridy = 0;
			c2.insets = new Insets(5, 105, 5, 5);
			c2.anchor = GridBagConstraints.FIRST_LINE_END;
			leftDisplayPanel.add(name2, c2);
			c2.gridy = 1;
			leftDisplayPanel.add(bar2, c2);
			c2.gridy = 2;
			leftDisplayPanel.add(image2, c2);
			leftDisplayPanel.setBackground(Color.LIGHT_GRAY);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 4;
			c.gridheight = 3;
			c.weightx = 0.0;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5, 5, 5, 0);
			leftPanel.add(leftDisplayPanel, c);
//
			attack = new JLabel("Attack");
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 10, 0, 0);
			leftPanel.add(attack, c);
			GridLayout layout2 = new GridLayout(2, 2);
			leftMovePanel.setLayout(layout2);
			for (int i = 0; i < leftMoveButtons.length; i++) {
				leftMoveButtons[i] = new Button(P1.getCurrentMon().getMoves()[i].getName(), i);
//    		leftMoveButtons[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
				leftMovePanel.add(leftMoveButtons[i]);
			}
			c.gridx = 0;
			c.gridy = 4;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 0, 0, 0);
			leftPanel.add(leftMovePanel, c);
			switchOut = new JLabel("Switch");
			c.gridx = 0;
			c.gridy = 5;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 10, 0, 0);
			leftPanel.add(switchOut, c);
			layout2 = new GridLayout(3, 2);
			leftSwitchPanel.setLayout(layout2);
			for (int i = 0; i < leftSwitchButtons.length; i++) {
				leftSwitchButtons[i] = new Button(P1.getPokemon()[i].getName(), i + 4);
//    		switches[mon].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
				leftSwitchPanel.add(leftSwitchButtons[i]);
			}
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 0, 0, 0);
			leftPanel.add(leftSwitchPanel, c);
			leftText = new JTextArea(20, 20);
//    	text.setEditable(false);
			leftText.setLineWrap(true);
			leftText.setWrapStyleWord(true);
			JScrollPane pane = new JScrollPane(leftText,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			c.gridx = 4;
			c.gridy = 0;
			c.gridwidth = 3;
			c.gridheight = 7;
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(5, 5, 5, 5);
			leftPanel.add(pane, c);


			GridBagConstraints y = new GridBagConstraints();
			name3 = new JLabel(P2.getCurrentMon().getName(), SwingConstants.CENTER);
			
			name4 = new JLabel(P1.getCurrentMon().getName(), SwingConstants.CENTER);
			bar3 = new HealthBar(P2.getCurrentMon());
			bar3.setBackground(Color.LIGHT_GRAY);
			bar3.setXOffset(true);
			bar4 = new HealthBar(P1.getCurrentMon());
			bar4.setBackground(Color.LIGHT_GRAY);
			bar4.setXOffset(false);
			ImageIcon pic2 = new ImageIcon("Images/Sprites/SpritesBack/" + P2.getCurrentMon().getID() + "-back.gif");
			image3 = new JLabel(pic2);
			image3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P2 current Pokemon info");
					leftPokemonInfoPopup.showMessageDialog(
							rightDisplayPanel,
							P2.getCurrentMon().toString(),
							"Player 2 Pokemon Info",
							2,
							new ImageIcon("Images/Sprites/SpritesFront/" + P2.getCurrentMon().getID() + ".gif"));


				}
			});
			pic2 = new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif");
			image4 = new JLabel(pic2);
			image4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane leftPokemonInfoPopup = new JOptionPane("P1 current Pokemon info");
					leftPokemonInfoPopup.showMessageDialog(rightDisplayPanel,
							P1.getCurrentMon().toString(), "Player 1 Pokemon Info",
							2,
							new ImageIcon("Images/Sprites/SpritesFront/" + P1.getCurrentMon().getID() + ".gif"));
				}
			});
			rightDisplayPanel.setLayout(new GridBagLayout());
			GridBagConstraints y2 = new GridBagConstraints();
			y2.gridx = 0;
			y2.gridy = 3;
			y2.fill = GridBagConstraints.HORIZONTAL;
			y2.insets = new Insets(5, 5, 5, 5);
			y2.anchor = GridBagConstraints.LAST_LINE_START;
			y2.weightx = 0.5;
			y2.weighty = 0.5;
			rightDisplayPanel.add(name3, y2);
			y2.gridy = 4;
			rightDisplayPanel.add(bar3, y2);
			y2.gridy = 5;
			rightDisplayPanel.add(image3, y2);
			y2.gridx = 1;
			y2.gridy = 0;
			y2.insets = new Insets(5, 105, 5, 5);
			y2.anchor = GridBagConstraints.FIRST_LINE_END;
			rightDisplayPanel.add(name4, y2);
			y2.gridy = 1;
			rightDisplayPanel.add(bar4, y2);
			y2.gridy = 2;
			rightDisplayPanel.add(image4, y2);
			rightDisplayPanel.setBackground(Color.LIGHT_GRAY);
			y.gridx = 0;
			y.gridy = 0;
			y.gridwidth = 4;
			y.gridheight = 3;
			y.weightx = 0.0;
			y.weighty = 0.0;
			y.anchor = GridBagConstraints.FIRST_LINE_START;
			y.fill = GridBagConstraints.HORIZONTAL;
			y.insets = new Insets(5, 5, 5, 0);
			rightPanel.add(rightDisplayPanel, y);


			attack = new JLabel("Attack");
			y.gridx = 0;
			y.gridy = 3;
			y.gridwidth = 4;
			y.gridheight = 1;
			y.weightx = 0.5;
			y.weighty = 0.0;
			y.anchor = GridBagConstraints.FIRST_LINE_START;
			y.fill = GridBagConstraints.HORIZONTAL;
			y.insets = new Insets(0, 10, 0, 0);
			rightPanel.add(attack, y);
			GridLayout layout3 = new GridLayout(2, 2);
			rightMovePanel.setLayout(layout3);
			for (int i = 0; i < rightMoveButtons.length; i++) {
				rightMoveButtons[i] = new Button(P2.getCurrentMon().getMoves()[i].getName(), i);
//    		rightMoveButtons[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
				rightMovePanel.add(rightMoveButtons[i]);
			}
			y.gridx = 0;
			y.gridy = 4;
			y.gridwidth = 4;
			y.gridheight = 1;
			y.weightx = 0.5;
			y.weighty = 0.0;
			y.anchor = GridBagConstraints.FIRST_LINE_START;
			y.fill = GridBagConstraints.HORIZONTAL;
			y.insets = new Insets(0, 0, 0, 0);
			rightPanel.add(rightMovePanel, y);
			switchOut = new JLabel("Switch");
			y.gridx = 0;
			y.gridy = 5;
			y.gridwidth = 4;
			y.gridheight = 1;
			y.weightx = 0.5;
			y.weighty = 0.0;
			y.anchor = GridBagConstraints.FIRST_LINE_START;
			y.fill = GridBagConstraints.HORIZONTAL;
			y.insets = new Insets(0, 10, 0, 0);
			rightPanel.add(switchOut, y);
			layout3 = new GridLayout(3, 2);
			rightSwitchPanel.setLayout(layout3);
			for (int i = 0; i < rightSwitchButtons.length; i++) {
				rightSwitchButtons[i] = new Button(P2.getPokemon()[i].getName(), i + 4);
//    		switches[mon].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
				rightSwitchPanel.add(rightSwitchButtons[i]);
			}
			y.gridx = 0;
			y.gridy = 6;
			y.gridwidth = 1;
			y.gridheight = 1;
			y.weightx = 0.5;
			y.weighty = 0.0;
			y.anchor = GridBagConstraints.FIRST_LINE_START;
			y.fill = GridBagConstraints.HORIZONTAL;
			y.insets = new Insets(0, 0, 0, 0);
			rightPanel.add(rightSwitchPanel, y);
			rightText = new JTextArea(20, 20);
//    	text.setEditable(false);
			rightText.setLineWrap(true);
			rightText.setWrapStyleWord(true);
			JScrollPane pane2 = new JScrollPane(rightText,
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			y.gridx = 4;
			y.gridy = 0;
			y.gridwidth = 3;
			y.gridheight = 7;
			y.weightx = 0.5;
			y.weighty = 0.5;
			y.anchor = GridBagConstraints.FIRST_LINE_END;
			y.fill = GridBagConstraints.BOTH;
			y.insets = new Insets(5, 5, 5, 5);
			rightPanel.add(pane2, y);

		name1.setOpaque(true);
			name1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name1.setBackground(Color.lightGray);
		name2.setOpaque(true);
		name2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name2.setBackground(Color.lightGray);
		name3.setOpaque(true);
		name3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name3.setBackground(Color.lightGray);
		name4.setOpaque(true);
		name4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name4.setBackground(Color.lightGray);
			leftPanel.updateUI();
			rightPanel.updateUI();
			repaint(P1, P2);

    }
//the main, where everything happens
    public static void main (String[] args) {

		EaseOfUse ez = new EaseOfUse();

		Calculator calc = new Calculator();
		Pokemon[] p1mons = new Pokemon[6];
		Pokemon[] p2mons = new Pokemon[6];
		int[][] givenMoves = new int[6][4];
		int[][] givenMoves2 = new int[6][4];

		p1mons[0] = new Pokemon(130);
		p1mons[1] = new Pokemon(143);
		p1mons[2] = new Pokemon(150);
		p1mons[3] = new Pokemon(384);
		p1mons[4] = new Pokemon(493);
		p1mons[5] = new Pokemon(487);

		p2mons[0] = new Pokemon(700);
		p2mons[1] = new Pokemon(802);
		p2mons[2] = new Pokemon(801);
		p2mons[3] = new Pokemon(800);
		p2mons[4] = new Pokemon(798);
		p2mons[5] = new Pokemon(791);
//
//
		givenMoves[0] = new int[]{14, 370, 609, 26};
		givenMoves[1] = new int[]{53, 56, 59, 76};
		givenMoves[2] = new int[]{85, 89, 94, 98};
		givenMoves[3] = new int[]{14, 370, 609, 26};
		givenMoves[4] = new int[]{53, 56, 59, 76};
		givenMoves[5] = new int[]{85, 89, 94, 98};
//
//
//
		givenMoves2[0] = new int[]{22, 23, 24, 25};
		givenMoves2[1] = new int[]{14, 370, 609, 1};
		givenMoves2[2] = new int[]{6, 7, 8, 9};
		givenMoves2[3] = new int[]{10, 11, 12, 13};
		givenMoves2[4] = new int[]{14, 15, 16, 17};
		givenMoves2[5] = new int[]{18, 19, 20, 21};
		P1 = new Player(p1mons, givenMoves);//default teams, not used for anything now that we have the teambuilder
		P2 = new Player(p2mons, givenMoves2);

		Battle b = new Battle();// calls the constructor, which sets up the gui
		Player p1 = b.P1;// doesn't use the global variables because this was created before we had GUI

		Player p2 = b.P2;
		p1.setOpposingPlayer(p2);

		b.leftText.setText("The match has begun!");//self explanatory
		b.rightText.setText("The match has begun!");//self explanatory
		boolean gameNotOver = true;//self explanatory
		boolean p1WillSwitch = false;//self explanatory
		boolean p2WillSwitch = false;//self explanatory
		int p1SelectedMoveIndex = -1;//the move a player is selecting
		int p2SelectedMoveIndex = -1;
		int p1SwitchIn = -1;
		int p2SwitchIn = -1;
		JOptionPane popup = new JOptionPane("");//the joption pane used to get input or show feedback
		popup.setIcon(new ImageIcon(new ImageIcon("icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));//self explanatory

		for (int i = 0; i < b.leftMoveButtons.length; i++) {

			b.leftMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {//mouse listener for right click, doesnt select anything


					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getCurrentMon().getMoves()[x].toString();//gets the info about the move this button represents
						popup.showMessageDialog(b.leftDisplayPanel,//in the left display panel
								textInfo,//see above
								"Move Info",//self explanatory
								2,//self explanatory
								(new ImageIcon(new ImageIcon("Images/Types/" + //the icon depends on the type of the move
										p1.getCurrentMon().getMoves()[x].getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.leftMoveButtons[i].addActionListener(new ActionListener() {//for left clicks
				//the reason we used a mouse and action listener is because mouslistener has weird specifications for a left click
				//and makes it feel weird to click it on a trackpad, although it would proably be fine on a mouse
				@Override
				public void actionPerformed(ActionEvent e) {


					b.p1Selection = ((Button) e.getSource()).getNum();//sets the selection
					synchronized (myObject2) {//notifies the object
						myObject2.notify();
					}
				}
			});
			b.leftButtons.add(b.leftMoveButtons[i]);//adds it to the button group

		}
		for (int i = 0; i < b.leftSwitchButtons.length; i++) {//same things as move buttons but its for switches

			b.leftSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p1.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.leftDisplayPanel,
								textInfo,
								"Pokemon Switch info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + p1.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.leftSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p1Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject2) {
						myObject2.notify();
					}

				}
			});
			b.leftButtons.add(b.leftSwitchButtons[i]);
		}


		for (int i = 0; i < b.rightMoveButtons.length; i++) {//same thing as for the left move buttons, see above comments

			b.rightMoveButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getCurrentMon().getMoves()[x].toString();
						popup.showMessageDialog(b.rightDisplayPanel,
								textInfo,
								"Move Info",
								2,
								(new ImageIcon(new ImageIcon("Images/Types/" + p2.getCurrentMon().getMoves()[x].getType() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

					}

				}
			});
			b.rightMoveButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p2Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject3) {
						myObject3.notify();
					}
				}
			});


			b.rightButtons.add(b.rightMoveButtons[i]);
		}
		for (int i = 0; i < b.rightSwitchButtons.length; i++) {//same things as for the left switch buttons, see above

			b.rightSwitchButtons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (SwingUtilities.isRightMouseButton(e)) {
						int x = ((Button) e.getSource()).getNum();
						String textInfo = p2.getPokemon()[x - 4].toString();
						popup.showMessageDialog(b.rightDisplayPanel,
								textInfo,
								"Pokemon Switch info",
								2,
								new ImageIcon("Images/Sprites/SpritesFront/" + p2.getPokemon()[x - 4].getID() + ".gif"));
					}

				}
			});
			b.rightSwitchButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					b.p2Selection = ((Button) e.getSource()).getNum();
					synchronized (myObject3) {
						myObject3.notify();
					}

				}
			});


			b.rightButtons.add(b.rightSwitchButtons[i]);
		}

		while (gameNotOver) {

//**for p1***
			try {
				synchronized (myObject2) {
					myObject2.wait();//waits until it is notified by a button
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

//buttons betweens 0 and 4 are for moves, buttons between 4 and 8 are for switches
			if (b.p1Selection >= 5 && P1numberOfFaintedMons < 5) {
				p1WillSwitch = true;
				p1SwitchIn = b.p1Selection - 4;

				b.p1Selection = -1;
			} else if (b.p1Selection <= 4 && b.p1Selection >= 0) {


				p1SelectedMoveIndex = b.p1Selection;

				b.p1Selection = -1;
			}



//**for p2**
//same thing as p1


			try {
			synchronized (myObject3) {
				myObject3.wait();//waits until it is notified by a button
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (b.p2Selection >= 5 && P2numberOfFaintedMons < 5) {
			p2WillSwitch = true;
			p2SwitchIn = b.p2Selection - 4;

			b.p2Selection = -1;
		} else if (b.p2Selection <= 4 && b.p2Selection >= 0) {


			p2SelectedMoveIndex = b.p2Selection;

			b.p2Selection = -1;
		}

		if (p1WillSwitch && p2WillSwitch) {//calculates who switches first based on whos faster
			//kind of useless since you cant see it and use the information it gives you since there are no
			//animations but, good to have implemented incase we add them
			if (p1.getCurrentMon().getSpeed() > p2.getCurrentMon().getSpeed()) {//self explanatory
				p1.getCurrentMon().resetBoosts();//you reset your boosts when you switch out
				p2.getCurrentMon().resetBoosts();
				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
				p1WillSwitch = false;
				p2WillSwitch = false;
			} else if (p2.getCurrentMon().getSpeed() > p1.getCurrentMon().getSpeed()) {//self explanatory

				p1.getCurrentMon().resetBoosts();
				p2.getCurrentMon().resetBoosts();
				p2.switchOut(p2.getPokemon()[p2SwitchIn]);
				p1.switchOut(p1.getPokemon()[p1SwitchIn]);
				p1WillSwitch = false;
				p2WillSwitch = false;
//self explanatory
			} else {
				if (Math.random() > 0.5) {
					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;
//self explanatory
				} else {
					p1.getCurrentMon().resetBoosts();
					p2.getCurrentMon().resetBoosts();
					p2.switchOut(p2.getPokemon()[p2SwitchIn]);
					p1.switchOut(p1.getPokemon()[p1SwitchIn]);
					p1WillSwitch = false;
					p2WillSwitch = false;

				}
			}
//self explanatory
		} else if (p1WillSwitch) {
			p1.getCurrentMon().resetBoosts();
//switching always outspeeds anything else
			p1.switchOut(p1.getPokemon()[p1SwitchIn]);
			b.repaint(p1, p2);//repaints the display
			p2.fight(p2SelectedMoveIndex);
			p1WillSwitch = false;
			p2WillSwitch = false;

		} else if (p2WillSwitch) {

			p2.getCurrentMon().resetBoosts();
			p2.switchOut(p2.getPokemon()[p2SwitchIn]);
			b.repaint(p1, p2);//repaints the display
			p1.fight(p1SelectedMoveIndex);
			p1WillSwitch = false;
			p2WillSwitch = false;

		} else {
//self explanatory
			int x = calc.calculateWhoGoesFirst(p1, p2, p1.getCurrentMon().getMoves()[p1SelectedMoveIndex], p2.getCurrentMon().getMoves()[p2SelectedMoveIndex]);
			if (x == 1) {
				p1.fight(p1SelectedMoveIndex);
				p2.fight(p2SelectedMoveIndex);
			} else if (x == 2) {
				p2.fight(p2SelectedMoveIndex);
				p1.fight(p1SelectedMoveIndex);
			} else {
				while (true) {
					ez.print("Something went wrong");//questioinable style, but gets your attention when it happens, and we have never had it happen
				}
			}
		}

		b.repaint(p1, p2);//repaints the display
		if (p1.isDefeated()) {//self explanatory
			gameNotOver = false;
			popup.showMessageDialog(b.mainPanel,
					"Player 2 Won the game!",
					"Game Finished",
					2,
					(new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
			break;
		}
		if (p2.isDefeated()) {//self explanatory
			gameNotOver = false;
//				popup.showMessageDialog(b.mainPanel,"Player 1 Won the game!");
			popup.showMessageDialog(b.mainPanel,
					"Player 1 Won the game!",
					"Game Finished",
					2,
					(new ImageIcon(new ImageIcon("Images/Pokeball.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));

			break;
		}
		if (p1.getCurrentMon().getHealth() <= 0) {//self explanatory
			P1numberOfFaintedMons++;

			String x[] = new String[6 - b.P1numberOfFaintedMons];
			int z = 0;
			for (int i = 0; i < p1.getPokemon().length; i++) {
				if (p1.getPokemon()[i].getHealth() <= 0) {//doesnt add to list if dead

				} else if (p1.getPokemon()[i] == p1.getCurrentMon()) {//doesnt add to list if already in play(would be dead anyway)

				} else {


					x[z] = (i + 1) + ") " + p1.getPokemon()[i].getName() + "\n";//adds to list
					z++;

				}
			}

			String y;
			do {
				y = (String) popup.showInputDialog(b.leftDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in."//self explanatory
						, "Pokemon defeated",
						2,
						new ImageIcon(new ImageIcon("Images/Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x,
						"Please select a Pokemon");
			}
			while (y == null);//prevents you from exiting without giving an answer

			p1SwitchIn = Integer.parseInt(String.valueOf(y.charAt(0)));//gets the first character, which is  its number in your team
			p1.switchOut(p1.getPokemon()[p1SwitchIn - 1]);//switches in your selection

			p1SwitchIn = -1;//resets selection
			b.repaint(p1, p2);
		}

            if(p2.getCurrentMon().getHealth()<=0){//same thing as for p1
				P2numberOfFaintedMons++;
            	int firstOccurence=-1;
            	int z=0;
				String [] x = new String[6-b.P2numberOfFaintedMons];
				for (int i =0;i<p2.getPokemon().length;i++){
					if(p2.getPokemon()[i].getHealth()<=0){

					}
					else if(p2.getPokemon()[i]==p2.getCurrentMon()){

					}
					else{
						if (firstOccurence==-1){
							firstOccurence=i;
						}
						x[z]=(i+1)+") "+p2.getPokemon()[i].getName()+"\n";
						z++;
					}
				}
				String y;
						do{y= (String) popup.showInputDialog(b.rightDisplayPanel,
						"Your current Pokemon fainted. Please choose which pokemon you want to switch in."
						,"Pokemon defeated",
						2,
						new ImageIcon (new ImageIcon("Images/Skull.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)),
						x,
						"Please select a Pokemon");}
						while(y==null);

				p2SwitchIn=Integer.parseInt(String.valueOf(y.charAt(0)));
            	p2.switchOut(p2.getPokemon()[p2SwitchIn-1]);

				p2SwitchIn=-1;
				b.repaint(p1, p2);
            }

        }

    }
    public void repaint(Player p1, Player p2) {

			name1.setText(p1.getCurrentMon().getName());//self explanatory

			name2 .setText(p2.getCurrentMon().getName());//self explanatory
			
			if(bar1.getPokemon() != p1.getCurrentMon()) {//self explanatory
				bar1.setPokemon(p1.getCurrentMon());
			}
			bar1.repaint();
			
			if(bar2.getPokemon() != p2.getCurrentMon()) {//self explanatory
				bar2.setPokemon(p2.getCurrentMon());
			}
			bar2.repaint();
			
			Icon pic = null;


				image1.setIcon(new ImageIcon("Images/Sprites/SpritesBack/" + p1.getCurrentMon().getID() + "-back.gif"));//self explanatory



				image2.setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + p2.getCurrentMon().getID() + ".gif"));//self explanatory


			for (int i =0;i<leftMoveButtons.length;i++){
				leftMoveButtons[i].setText(p1.getCurrentMon().getMoves()[i].getName());//self explanatory
				if(p1.getCurrentMon().getMoves()[i].getPP() == 0) {
					leftMoveButtons[i].setEnabled(false);
				}
				else {
					leftMoveButtons[i].setEnabled(true);
				}
			}
			for (int i =0;i<leftSwitchButtons.length;i++){
				leftSwitchButtons[i].setText(p1.getPokemon()[i].getName());
				if(p1.getPokemon()[i].getHealth() <= 0 || p1.getPokemon()[i] == p1.getCurrentMon()) {//self explanatory
					leftSwitchButtons[i].setEnabled(false);
				}
				else{
					leftSwitchButtons[i].setEnabled(true);
				}
			}




		name3.setText(p2.getCurrentMon().getName());//self explanatory

		name4 .setText(p1.getCurrentMon().getName());//self explanatory
		
		if(bar3.getPokemon() != p2.getCurrentMon()) {
			bar3.setPokemon(p2.getCurrentMon());//self explanatory
		}
		bar3.repaint();
		
		if(bar4.getPokemon() != p1.getCurrentMon()) {
			bar4.setPokemon(p1.getCurrentMon());
		}//self explanatory
		bar4.repaint();
		
		pic = null;

//		pic = ;
		image3.setIcon(new ImageIcon("Images/Sprites/SpritesBack/" + p2.getCurrentMon().getID() + "-back.gif"));



//		pic = ;
		image4.setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + p1.getCurrentMon().getID() + ".gif"));


		for (int i =0;i<rightMoveButtons.length;i++){//self explanatory
			rightMoveButtons[i].setText(p2.getCurrentMon().getMoves()[i].getName());
			if(p2.getCurrentMon().getMoves()[i].getPP() == 0) {
				rightMoveButtons[i].setEnabled(false);
			}
			else {
				rightMoveButtons[i].setEnabled(true);
			}
		}
		for (int i =0;i<rightSwitchButtons.length;i++){
			rightSwitchButtons[i].setText(p2.getPokemon()[i].getName());//self explanatory
			if(p2.getPokemon()[i].getHealth() <= 0 || p2.getPokemon()[i] == p2.getCurrentMon()) {
				rightSwitchButtons[i].setEnabled(false);
			}
			else{
				rightSwitchButtons[i].setEnabled(true);
			}
		}
		animateHPChange();
	}
    
    private void animateHPChange() {
    	timer = new Timer(25, new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if(!bar1.isChanging() && !bar2.isChanging()
    					&& !bar3.isChanging() && !bar4.isChanging()) {
    				timer.stop();
    			}
    			if(bar1.isChanging()) {
    				bar1.repaint();
    			}
    			if(bar2.isChanging()) {
    				bar2.repaint();
    			}
    			if(bar3.isChanging()) {
    				bar3.repaint();
    			}
    			if(bar4.isChanging()) {
    				bar4.repaint();
    			}
    		}
    	});
    	timer.setInitialDelay(500);
    	timer.start();
    }
    // This method lets you choose your pokemon and their moves
	private void teamBuilder(){
		Calculator calc= new Calculator();
		JPanel mainPanelTB=new JPanel();// the main panel with all of the the components inside
		mainPanelTB.setLayout(new GridLayout(1,2));
		frame.add(mainPanelTB);//using the frame from the field so as not to use multiple Jframes
		 JPanel leftPanelTB=new JPanel(new GridLayout(2,1));// the panel for P1
		 JPanel rightPanelTB=new JPanel(new GridLayout(2,1));// the panel for P2
		JPanel leftDisplay=new JPanel(new GridLayout(1,6)),rightDisplay=new JPanel(new GridLayout(1,6));// the display of the pokemon when you choose them
		mainPanelTB.add(leftPanelTB);
		mainPanelTB.add(rightPanelTB);
		leftPanelTB.add(leftDisplay);
		rightPanelTB.add(rightDisplay);
		JPanel leftUI=new JPanel(new GridBagLayout()), rightUI=new JPanel(new GridBagLayout());// the panel for all the user input
		leftPanelTB.add(leftUI);
		rightPanelTB.add(rightUI);
		GridBagConstraints constraints=new GridBagConstraints();//we used a  grid bag layout for the UI
		JLabel[] leftPanelImages=new JLabel[6], rightPanelImages=new JLabel[6];//self explanatory
		JTextArea[] rightPokemonInputs=new JTextArea[6], leftPokemonInputs=new JTextArea[6];//self explanatory
		JTextField[][] rightMoveInputs=new JTextField[6][4],leftMoveInputs=new JTextField[6][4];//self explanatory
		Button[] rightConfirmationButtons=new Button[6],leftConfirmationButtons=new Button[6];//confirms a pokemon
		JButton rightValidationButton=new JButton("Confirm Team"),leftValidationButton=new JButton("Confirm Team");//confirms the entire team

		Pokemon[] p1Pokemon=new Pokemon[6],p2Pokemon=new Pokemon[6];//self explanatory
		JButton leftRandomButton=new JButton("Random!"),rightRandomButton=new JButton("Random!");//buttons to create a random team will be implemented soon

	for (int i = 0; i < leftPanelImages.length; i++) {

		ImageIcon pic = new ImageIcon("Images/QuestionMark.png");//self explanatory
			pic = new ImageIcon((pic).getImage().getScaledInstance((int)(pic.getIconWidth() * 0.1), (int)(pic.getIconHeight() * 0.1), Image.SCALE_DEFAULT));//self explanatory
		leftPanelImages[i] = new JLabel( pic);//self explanatory
		leftDisplay.add(leftPanelImages[i]);//adding the image to the display
	}
	for (int i = 0; i < rightPanelImages.length; i++) {

		ImageIcon pic = new ImageIcon("Images/QuestionMark.png");//self explanatory
		pic = new ImageIcon((pic).getImage().getScaledInstance((int)(pic.getIconWidth() * 0.1), (int)(pic.getIconHeight() * 0.1), Image.SCALE_DEFAULT));//self explanatory
		rightPanelImages[i] = new JLabel(pic);//self explanatory
		rightDisplay.add(rightPanelImages[i]);//adding the image to the display
	}
		for (int i = 0; i < leftPokemonInputs.length; i++) {//i is the column you are on

			leftPokemonInputs[i] = new JTextArea("Charizard");//default pokemon for player 1 is charizard
			leftPokemonInputs[i].setRows(3);
			constraints.gridx = i;//your current column
			constraints.gridy = 0;//1st row
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(5, 5, 5, 0);
			leftUI.add(leftPokemonInputs[i], constraints);//adding to the ui
		}
		for (int i = 0; i < 6; i++) {//keeps track of the column you are on

			for (int k = 0; k < 4; k++) {//keeps track of the row
				leftMoveInputs[i][k] = new JTextField("Pound");//pound is the default move
				constraints.gridx = i;//the column you are on
				constraints.gridy = k + 1;//row+1 because the pokemon input are in the first row
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.weightx = 0.5;
				constraints.weighty = 0.0;
				constraints.anchor = GridBagConstraints.PAGE_START;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.insets = new Insets(5, 5, 5, 0);
				leftUI.add(leftMoveInputs[i][k], constraints);
			}
			leftConfirmationButtons[i] = new Button("Confirm", i);
			leftConfirmationButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					int x = ((Button) e.getSource()).getNum();//x is the column its from
					boolean validMove=true;//if the user chooses a move that is valid
					for (int i = 0; i <4; i++) {//length is 4
						if(calc.isMove(leftMoveInputs[x][i].getText())){

						}
						else{//if the input is not a move, do this
							leftConfirmationButtons[x].setProblem(true);
							validMove=false;
							JOptionPane.showMessageDialog(leftDisplay,
									"Your move in the "+(x+1)+"th column and "+(i+1)+"th row is not a valid move",//yes I know this doesnt work for 1 2 and 3,
									// but its really not a big deal, you get the point
									"Invalid Move",//self explanatory
									2,//self explanatory
									(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));//self explanatory


						}
					}
					if(validMove) {//do this if the move is valid
						leftConfirmationButtons[x].setProblem(false);
						Pokemon pokemon = new Pokemon(leftPokemonInputs[x].getText());//get the text from the pokemon input at column x
						Move[] moves = new Move[4];
						for (int i = 0; i < moves.length; i++) {//length is 4
							moves[i] = new Move(leftMoveInputs[x][i].getText());
						}
						pokemon.setMoves(moves);
						p1Pokemon[x] = pokemon;
						leftPanelImages[x].setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + pokemon.getID() + ".gif"));
					}
				}
			});
			constraints.gridx = i;//the column you are on
			constraints.gridy = 5;//should be the 6th row of things in this column
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0.0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(50, 0, 5, 0);

			leftUI.add(leftConfirmationButtons[i], constraints);//adding to the ui with these constraitns
		}
		constraints.gridx = 0;//the column you are on
		constraints.gridy = 6;//should be the 6th row of things in this column
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(50, 5, 5, 0);
		leftValidationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean problem=false;
				for (int i = 0; i < leftConfirmationButtons.length; i++) {
					leftConfirmationButtons[i].doClick();//clicks everybutton
					if(leftConfirmationButtons[i].hasProblem()){
							problem=true;//if there is a problem
					}
					else {
//if theres isnt a problem, turn off all the buttons for htis player
						leftConfirmationButtons[i].setEnabled(false);
						leftPokemonInputs[i].setEnabled(false);
						for (int k = 0; k < leftMoveInputs[i].length; k++) {

							leftMoveInputs[i][k].setEnabled(false);
						}

					}

				}//if there isn't a problem
				if(problem==false){
					synchronized (myObject1){
						if(confirm2==true){//notifys the object in main if the other player has selected
						myObject1.notify();
					}else{
						confirm1 =true;//if the other player hasn't confirmed, confirms your own
					}
					}
			}
			}
		});
		leftUI.add(leftValidationButton, constraints);//adds to UI







		//**********PLAYER 2******************//
		//this isn't really any different from player 1, except its on the right side, so i wont be commenting this
		//they are practically identical so just look at it for player 1








		for (int i = 0; i < rightPokemonInputs.length; i++) {
		rightPokemonInputs[i] = new JTextArea("Blastoise");//P2 pokemon default is blastoise
		rightPokemonInputs[i].setRows(3);
		constraints.gridx = i;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(5, 5, 5, 0);

		rightUI.add(rightPokemonInputs[i], constraints);
	}
	for (int i = 0; i < 6; i++) {//keeps track of the column you are on

		for (int k = 0; k < 4; k++) {//keeps track of the row
			rightMoveInputs[i][k] = new JTextField("Pound");
			constraints.gridx = i;//the column you are on
			constraints.gridy = k + 1;//row+1 because the pokemon input are in the first row
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5;
			constraints.weighty = 0.0;
			constraints.anchor = GridBagConstraints.PAGE_START;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(5, 5, 5, 0);

			rightUI.add(rightMoveInputs[i][k], constraints);
		}
		rightConfirmationButtons[i] = new Button("Confirm", i);
		rightConfirmationButtons[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				{

					int x = ((Button) e.getSource()).getNum();//x is the column its from
					boolean validMove = true;
					for (int i = 0; i < 4; i++) {//length is 4
						if (calc.isMove(rightMoveInputs[x][i].getText())) {

						} else {
							rightConfirmationButtons[x].setProblem(true);
							validMove = false;
							JOptionPane.showMessageDialog(rightDisplay,
									"Your move in the " + (x + 1) + "th column and " + (i + 1) + "th row is not a valid move",
									"Invalid Move",
									2,
									(new ImageIcon(new ImageIcon("Images/Exclamation.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))));


						}
					}
					if (validMove) {
						rightConfirmationButtons[x].setProblem(false);
						Pokemon pokemon = new Pokemon(rightPokemonInputs[x].getText());//get the text from the pokemon input at column x
						Move[] moves = new Move[4];
						for (int i = 0; i < moves.length; i++) {//length is 4
							moves[i] = new Move(rightMoveInputs[x][i].getText());
						}
						pokemon.setMoves(moves);
						p2Pokemon[x] = pokemon;
						rightPanelImages[x].setIcon(new ImageIcon("Images/Sprites/SpritesFront/" + pokemon.getID() + ".gif"));
					}
				}
			}
		});
		constraints.gridx = i;//the column you are on
		constraints.gridy = 5;//should be the 6th row of things in this column
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(50, 5, 5, 0);

		rightUI.add(rightConfirmationButtons[i], constraints);
	}
	constraints.gridx = 0;//the column you are on
	constraints.gridy = 6;//should be the 6th row of things in this column
	constraints.gridwidth = 5;
	constraints.gridheight = 1;
	constraints.weightx = 0.5;
	constraints.weighty = 0.0;
	constraints.anchor = GridBagConstraints.PAGE_START;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	constraints.insets = new Insets(50, 0, 5, 0);
	rightValidationButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean problem=false;
			for (int i = 0; i < rightConfirmationButtons.length; i++) {
				rightConfirmationButtons[i].doClick();
				if(rightConfirmationButtons[i].hasProblem()){
					problem=true;
				}
				else {

					rightConfirmationButtons[i].setEnabled(false);
					rightPokemonInputs[i].setEnabled(false);
					for (int k = 0; k < rightMoveInputs[i].length; k++) {

						rightMoveInputs[i][k].setEnabled(false);
					}


				}}
			if(problem==false){
			synchronized (myObject1){if(confirm1==true){
				myObject1.notify();
			}else{
				confirm2 =true;
			}
			}}
		}
			});
	rightUI.add(rightValidationButton, constraints);//self explanatory
	leftPanelTB.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
	rightPanelTB.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
	rightDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory
	leftDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));//self explanatory

		P1=new Player(p1Pokemon);
// set the pokemon chosen to the fields
		P2=new Player(p2Pokemon);

}


}
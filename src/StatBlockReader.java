import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.*;

public class StatBlockReader extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1;
	private HashMap<String, Component> comps;
	private ArrayList<String> compNames;
	private HashMap<String, Long> indexes; 
	
	public StatBlockReader() {
		readIndexes();
		initializeStartFrame();
	}
	
	private void readIndexes() {
		indexes = new HashMap<String, Long>();
		try {
			RandomAccessFile in = new RandomAccessFile( "indexes.dat", "r" );
			in.seek(0);
			
			boolean read = true;
			while( read ) {
				String name = in.readUTF();
				long i = in.readLong();
				indexes.put( name, i );
				if( name.equals("END") ) read = false;
			}
				
			
			in.close();
			
		} catch ( Exception e ) {
			indexes.put( "END", 0l );
			System.out.println("readIndexes():");
			e.printStackTrace();
		}
	}
	
	private void initializeStartFrame() {
		setTitle("Stat Blocks");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 500, 250 );
		setPreferredSize(getSize());
		setLocationByPlatform(true);
		
		comps = new HashMap<String, Component>();
		compNames = new ArrayList<String>();
		
		JButton createNew = new JButton("Create New Stat Block");
		createNew.setSize( 175, 25 );
		createNew.setLocation( 162, 30 );
		createNew.addActionListener(this);
		compNames.add("CreateNew");
		comps.put( compNames.get(compNames.size()-1), createNew );
		add(createNew);

		JButton lookUp = new JButton("Look Up Stat Block:");
		lookUp.setSize( 150, 25 );
		lookUp.setLocation( 97, 70 );
		lookUp.addActionListener(this);
		compNames.add("LookUp");
		comps.put( compNames.get(compNames.size()-1), lookUp );
		add(lookUp);
		
		JTextField lookUpName = new JTextField();
		lookUpName.setToolTipText("Creature to Look Up");
		lookUpName.setSize( 150, 25 );
		lookUpName.setLocation( 253, 70 );
		compNames.add("LookUpName");
		comps.put( compNames.get(compNames.size()-1), lookUpName );
		add(lookUpName);
		
		JTextField name = new JTextField("Creature Name");
		name.setToolTipText("Creature Name");
		name.setSize( 100, 25 );
		name.setLocation( 15, 15 );
		name.setVisible(false);
		compNames.add("CreatureName");
		comps.put( compNames.get(compNames.size()-1), name );
		add(name);
		
		JLabel acLabel = new JLabel();
		acLabel.setText("AC:");
		acLabel.setSize( 25, 25 );
		acLabel.setLocation( 15, 40 );
		acLabel.setVisible(false);
		compNames.add("ACLabel");
		comps.put( compNames.get(compNames.size()-1), acLabel );
		add(acLabel);
		
		JTextField ac = new JTextField();
		ac.setToolTipText("Armor Class");
		ac.setSize( 20, 25 );
		ac.setLocation( 40, 40 );
		ac.setVisible(false);
		compNames.add("AC");
		comps.put( compNames.get(compNames.size()-1), ac );
		add(ac);
		
		JLabel hpLabel = new JLabel();
		hpLabel.setText("HP:");
		hpLabel.setSize( 25, 25 );
		hpLabel.setLocation( 70, 40 );
		hpLabel.setVisible(false);
		compNames.add("HPLabel");
		comps.put( compNames.get(compNames.size()-1), hpLabel );
		add(hpLabel);
		
		JTextField hp = new JTextField();
		hp.setToolTipText("Hit Points");
		hp.setSize( 20, 25 );
		hp.setLocation( 95, 40 );
		hp.setVisible(false);
		compNames.add("HP");
		comps.put( compNames.get(compNames.size()-1), hp );
		add(hp);
		
		JLabel speedLabel = new JLabel();
		speedLabel.setText("Speed:");
		speedLabel.setSize( 40, 25 );
		speedLabel.setLocation( 15, 65 );
		speedLabel.setVisible(false);
		compNames.add("SpeedLabel");
		comps.put( compNames.get(compNames.size()-1), speedLabel );
		add(speedLabel);
		
		JTextField speed = new JTextField();
		speed.setToolTipText("in feet");
		speed.setSize( 20, 25 );
		speed.setLocation( 60, 65 );
		speed.setVisible(false);
		compNames.add("Speed");
		comps.put( compNames.get(compNames.size()-1), speed );
		add(speed);
		
		JLabel abLabel = new JLabel();
		abLabel.setText("Ability Modifiers:");
		abLabel.setSize( 100, 25 );
		abLabel.setLocation( 15, 90 );
		abLabel.setVisible(false);
		compNames.add("AbilitiesLabel");
		comps.put( compNames.get(compNames.size()-1), abLabel );
		add(abLabel);
		
		JTextField ab = new JTextField();
		ab.setToolTipText("STR  DEX  CON  INT  WIS  CHA");
		ab.setSize( 120, 25 );
		ab.setLocation( 15, 115 );
		ab.setVisible(false);
		compNames.add("Abilities");
		comps.put( compNames.get(compNames.size()-1), ab );
		add(ab);
		
		JLabel challLabel = new JLabel();
		challLabel.setText("Chall:");
		challLabel.setSize( 35, 25 );
		challLabel.setLocation( 120, 40 );
		challLabel.setVisible(false);
		compNames.add("ChallengeLabel");
		comps.put( compNames.get(compNames.size()-1), challLabel );
		add(challLabel);
		
		JTextField challenge = new JTextField();
		challenge.setSize( 30, 25 );
		challenge.setLocation( 155, 40 );
		challenge.setVisible(false);
		compNames.add("Challenge");
		comps.put( compNames.get(compNames.size()-1), challenge );
		add(challenge);
		
		JTextField xp = new JTextField();
		xp.setText(" XP");
		xp.setSize( 25, 25 );
		xp.setLocation( 185, 40 );
		xp.setVisible(false);
		compNames.add("XP");
		comps.put( compNames.get(compNames.size()-1), xp );
		add(xp);
		
		JLabel actionLabel = new JLabel();
		actionLabel.setText("Attacks:");
		actionLabel.setSize( 50, 25 );
		actionLabel.setLocation( 230, 15 );
		actionLabel.setVisible(false);
		compNames.add("AttackLabel");
		comps.put( compNames.get(compNames.size()-1), actionLabel );
		add(actionLabel);
		
		JTextField atckName1 = new JTextField();
		atckName1.setToolTipText("Attack Name");
		atckName1.setSize( 80, 25 );
		atckName1.setLocation( 230, 40 );
		atckName1.setVisible(false);
		compNames.add("AttackName1");
		comps.put( compNames.get(compNames.size()-1), atckName1 );
		add(atckName1);
		
		JTextField atckBonus1 = new JTextField();
		atckBonus1.setToolTipText("Attack Bonus");
		atckBonus1.setSize( 20, 25 );
		atckBonus1.setLocation( 310, 40 );
		atckBonus1.setVisible(false);
		compNames.add("AttackBonus1");
		comps.put( compNames.get(compNames.size()-1), atckBonus1 );
		add(atckBonus1);
		
		JTextField atckRange1 = new JTextField();
		atckRange1.setToolTipText("Range (separated by \"/ \" )");
		atckRange1.setSize( 40, 25 );
		atckRange1.setLocation( 330, 40 );
		atckRange1.setVisible(false);
		compNames.add("AttackRange1");
		comps.put( compNames.get(compNames.size()-1), atckRange1 );
		add(atckRange1);
		
		JTextField atckDmg1 = new JTextField();
		atckDmg1.setToolTipText("Damage");
		atckDmg1.setSize( 40, 25 );
		atckDmg1.setLocation( 370, 40 );
		atckDmg1.setVisible(false);
		compNames.add("AttackDamage1");
		comps.put( compNames.get(compNames.size()-1), atckDmg1 );
		add(atckDmg1);
		
		JTextField atckDmgType1 = new JTextField();
		atckDmgType1.setToolTipText("Damage Type (one letter)");
		atckDmgType1.setSize( 20, 25 );
		atckDmgType1.setLocation( 410, 40 );
		atckDmgType1.setVisible(false);
		compNames.add("AttackDamageType1");
		comps.put( compNames.get(compNames.size()-1), atckDmgType1 );
		add(atckDmgType1);
		
		JTextField atckNotes1 = new JTextField();
		atckNotes1.setToolTipText("Additional Notes");
		atckNotes1.setSize( 50, 25 );
		atckNotes1.setLocation( 430, 40 );
		atckNotes1.setVisible(false);
		compNames.add("AttackNotes1");
		comps.put( compNames.get(compNames.size()-1), atckNotes1 );
		add(atckNotes1);
		
		JTextField atckName2 = new JTextField();
		atckName2.setToolTipText("Attack Name");
		atckName2.setSize( 80, 25 );
		atckName2.setLocation( 230, 65 );
		atckName2.setVisible(false);
		compNames.add("AttackName2");
		comps.put( compNames.get(compNames.size()-1), atckName2 );
		add(atckName2);
		
		JTextField atckBonus2 = new JTextField();
		atckBonus2.setToolTipText("Attack Bonus");
		atckBonus2.setSize( 20, 25 );
		atckBonus2.setLocation( 310, 65 );
		atckBonus2.setVisible(false);
		compNames.add("AttackBonus2");
		comps.put( compNames.get(compNames.size()-1), atckBonus2 );
		add(atckBonus2);
		
		JTextField atckRange2 = new JTextField();
		atckRange2.setToolTipText("Range (separated by \"/ \" )");
		atckRange2.setSize( 40, 25 );
		atckRange2.setLocation( 330, 65 );
		atckRange2.setVisible(false);
		compNames.add("AttackRange2");
		comps.put( compNames.get(compNames.size()-1), atckRange2 );
		add(atckRange2);
		
		JTextField atckDmg2 = new JTextField();
		atckDmg2.setToolTipText("Damage");
		atckDmg2.setSize( 40, 25 );
		atckDmg2.setLocation( 370, 65 );
		atckDmg2.setVisible(false);
		compNames.add("AttackDamage2");
		comps.put( compNames.get(compNames.size()-1), atckDmg2 );
		add(atckDmg2);
		
		JTextField atckDmgType2 = new JTextField();
		atckDmgType2.setToolTipText("Damage Type (one letter)");
		atckDmgType2.setSize( 20, 25 );
		atckDmgType2.setLocation( 410, 65 );
		atckDmgType2.setVisible(false);
		compNames.add("AttackDamageType2");
		comps.put( compNames.get(compNames.size()-1), atckDmgType2 );
		add(atckDmgType2);
		
		JTextField atckNotes2 = new JTextField();
		atckNotes2.setToolTipText("Additional Notes");
		atckNotes2.setSize( 50, 25 );
		atckNotes2.setLocation( 430, 65 );
		atckNotes2.setVisible(false);
		compNames.add("AttackNotes2");
		comps.put( compNames.get(compNames.size()-1), atckNotes2 );
		add(atckNotes2);
		
		JTextField atckName3 = new JTextField();
		atckName3.setToolTipText("Attack Name");
		atckName3.setSize( 80, 25 );
		atckName3.setLocation( 230, 90 );
		atckName3.setVisible(false);
		compNames.add("AttackName3");
		comps.put( compNames.get(compNames.size()-1), atckName3 );
		add(atckName3);
		
		JTextField atckBonus3 = new JTextField();
		atckBonus3.setToolTipText("Attack Bonus");
		atckBonus3.setSize( 20, 25 );
		atckBonus3.setLocation( 310, 90 );
		atckBonus3.setVisible(false);
		compNames.add("AttackBonus3");
		comps.put( compNames.get(compNames.size()-1), atckBonus3 );
		add(atckBonus3);
		
		JTextField atckRange3 = new JTextField();
		atckRange3.setToolTipText("Range (separated by \"/ \" )");
		atckRange3.setSize( 40, 25 );
		atckRange3.setLocation( 330, 90 );
		atckRange3.setVisible(false);
		compNames.add("AttackRange3");
		comps.put( compNames.get(compNames.size()-1), atckRange3 );
		add(atckRange3);
		
		JTextField atckDmg3 = new JTextField();
		atckDmg3.setToolTipText("Damage");
		atckDmg3.setSize( 40, 25 );
		atckDmg3.setLocation( 370, 90 );
		atckDmg3.setVisible(false);
		compNames.add("AttackDamage3");
		comps.put( compNames.get(compNames.size()-1), atckDmg3 );
		add(atckDmg3);
		
		JTextField atckDmgType3 = new JTextField();
		atckDmgType3.setToolTipText("Damage Type (one letter)");
		atckDmgType3.setSize( 20, 25 );
		atckDmgType3.setLocation( 410, 90 );
		atckDmgType3.setVisible(false);
		compNames.add("AttackDamageType3");
		comps.put( compNames.get(compNames.size()-1), atckDmgType3 );
		add(atckDmgType3);
		
		JTextField atckNotes3 = new JTextField();
		atckNotes3.setToolTipText("Additional Notes");
		atckNotes3.setSize( 50, 25 );
		atckNotes3.setLocation( 430, 90 );
		atckNotes3.setVisible(false);
		compNames.add("AttackNotes3");
		comps.put( compNames.get(compNames.size()-1), atckNotes3 );
		add(atckNotes3);
		
		JLabel addInfoLabel = new JLabel();
		addInfoLabel.setText("Additional Information:");
		addInfoLabel.setSize( 140, 25 );
		addInfoLabel.setLocation( 15, 140 );
		addInfoLabel.setVisible(false);
		compNames.add("AdditionalInformationLabel");
		comps.put( compNames.get(compNames.size()-1), addInfoLabel );
		add(addInfoLabel);
		
		JTextArea addInfo = new JTextArea();
		addInfo.setWrapStyleWord(true);
		addInfo.setLineWrap(true);
		addInfo.setFont(new Font("Courier", Font.PLAIN, 16));
		addInfo.setSize( 470, 350 );
		addInfo.setLocation( 15, 165 );
		addInfo.setVisible(false);
		compNames.add("AdditionalInformation");
		comps.put( compNames.get(compNames.size()-1), addInfo );
		add(addInfo);
		
		JButton complete = new JButton("Complete Creature");
		complete.setToolTipText("Saves Creature");
		complete.setSize( 150, 25 );
		complete.setLocation( 340, 5 );
		complete.addActionListener(this);
		complete.setVisible(false);
		compNames.add("CompleteCreature");
		comps.put( compNames.get(compNames.size()-1), complete );
		add(complete);

		JButton delete = new JButton("Delete Creature:");
		delete.setSize( 150, 25 );
		delete.setLocation( 97, 110 );
		delete.addActionListener(this);
		compNames.add("Delete");
		comps.put( compNames.get(compNames.size()-1), delete );
		add(delete);
		
		JTextField deleteName = new JTextField();
		deleteName.setToolTipText("Creature to Delete");
		deleteName.setSize( 150, 25 );
		deleteName.setLocation( 253, 110 );
		compNames.add("DeleteName");
		comps.put( compNames.get(compNames.size()-1), deleteName );
		add(deleteName);

		JButton list = new JButton("List Creatures");
		list.setSize( 175, 25 );
		list.setLocation( 162, 150 );
		list.addActionListener(this);
		compNames.add("List");
		comps.put( compNames.get(compNames.size()-1), list );
		add(list);
	}
	
	public static void main( String[] args ) {
		JFrame f = new StatBlockReader();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals(comps.get("CreateNew")) ) {
			
			newScreen( Screens.CREATE_NEW );
			
		} else if( e.getSource().equals(comps.get("LookUp")) ) {
			
			String creatureName = ((JTextField)comps.get("LookUpName")).getText();
			String creatureInfo = lookUp( creatureName );
			if( creatureInfo == null ) return;
			creatureInfo = creatureInfo.substring( creatureName.length() + 1 );
			//start thread:
			new Thread(new InfoFrame( creatureName, creatureInfo )).start();
			
		} else if( e.getSource().equals(comps.get("Delete")) ) {
			
			if( JOptionPane.showConfirmDialog( null, "Are you sure you want to delete this creature?" ) == 0 )
				if( deleteCreatureIndex( ((JTextField)comps.get("DeleteName")).getText() ) ) {
					JOptionPane.showMessageDialog( null, "Creature Deleted" );
					((JTextField)comps.get("DeleteName")).setText("");
				} else
					JOptionPane.showMessageDialog( null, "Creature Not Found" );
			
		} else if( e.getSource().equals(comps.get("List")) ) {
			
			String list = orderNames();
			new Thread(new InfoFrame( "List", list )).start();
			
		} else if( e.getSource().equals(comps.get("CompleteCreature")) ) {
			
			if( saveCreature() ) {
				newScreen( Screens.OPENING );
				resetCreatureBoxes();
			}
			
		} else {
			System.out.println( e.toString() );
		}
	}
	
	private void newScreen( Screens s ) {
		switch(s) {
			case OPENING:
				turnOffAll();
				
				setSize(getPreferredSize());
				
				for( int i = 0; i < 3; i++ )
					comps.get(compNames.get(i)).setVisible(true);
				for( int i = 36; i < 40; i++ ) 
					comps.get(compNames.get(i)).setVisible(true);
				comps.get("LookUpName").requestFocus();
				
				
				break;
			case CREATE_NEW:
				turnOffAll();

				setSize( 505, 580 );
				
				for( int i = 3; i < 37; i++ )
					comps.get(compNames.get(i)).setVisible(true);
				comps.get("CreatureName").requestFocus();
				
				break;
			default:
				break;
		}
	}
	
	private void turnOffAll() {
		for( String n : compNames ) {
			comps.get(n).setVisible(false);
		}
	}
	
	private boolean deleteCreatureIndex( String creatureName ) {
		if( indexes.remove(creatureName) == null )
			return false;
		saveIndexes();
		return true;
	}
	
	private String orderNames() {
		
		//create ArrayList of creature names:
		ArrayList<String> list = new ArrayList<String>();
		for( String s : indexes.keySet() )
			list.add(s.toString());
		
		//remove END:
		list.remove("END");
		
		//create sub ArrayList of Creatures starting with "My "
		ArrayList<String> mys = new ArrayList<String>();
		for( int i = 0; i < list.size(); i++ )
			if( list.get(i).substring( 0, 3 ).equals("My ") )
				mys.add( list.remove(i) );
		
		//Alphabetize mys and add to output:
		String output = "";
		if ( mys.size() > 0 ) {
			for (int i = 1; i < mys.size(); i++) {
				int j = i;
				while (j >= 0 && mys.get(i).compareTo(mys.get(j)) <= 0)
					j--;
				j++;
				String temp = mys.remove(i);
				mys.add(j, temp);
			}
			for (String m : mys)
				output += m + ", ";
			output = output.substring(0, output.length() - 2) + "\n";
		}
		
		for( int i = 1; i < list.size(); i++ ) {
			int j = i;
			while( j >= 0 && list.get(i).compareTo(list.get(j)) <= 0 )
				j--;
			j++;
			String temp = list.remove(i);
			list.add( j, temp );
		}
		
		for( String l : list )
			output += l + ", ";
		output = output.substring( 0, output.length() - 2 );
		
		return output;
	}

	@SuppressWarnings("resource") 
	private boolean saveCreature() {
		try {
			//start file readers:
			RandomAccessFile creatures = new RandomAccessFile( new File("creatures.dat"), "rw" );
			
			
			//save name of creature to save index later && check is already exists:
			String name = ((JTextField)comps.get("CreatureName")).getText();
			if( name.length() == 0 ) throw new Exception( "No Creature Name" );
			if( indexes.containsKey(name) ) throw new Exception( "Creature Name already exists" );
			
			//save creature info:
			creatures.seek( indexes.get("END") );
			creatures.writeUTF( name );//UTF: name
			
			creatures.writeShort( Short.parseShort( ((JTextField)comps.get("AC")).getText() ) );//short: AC
			
			//save HP:
			StringTokenizer hp = new StringTokenizer( ((JTextField)comps.get("HP")).getText(), " ()" );
			if( hp.countTokens() != 2 ) throw new Exception( "HP Input Error" );
			creatures.writeShort( Short.parseShort( hp.nextToken() ) );//short: average HP
			creatures.writeUTF( hp.nextToken() );//UTF: roll for HP

			//save Speed:
			try {
				creatures.writeShort(Short.parseShort(((JTextField) comps.get("Speed")).getText()));//short: speed
			} catch ( Exception e ) {
				throw new Exception("Speed Input Error");
			}
			
			//save challenge:
			StringTokenizer chall = new StringTokenizer( ((JTextField)comps.get("Challenge")).getText(), " /" );
			if( chall.countTokens() == 2 ) {
				creatures.writeBoolean(true);
				creatures.writeByte( Integer.parseInt(chall.nextToken()) );
				creatures.writeByte( Integer.parseInt(chall.nextToken()) );
			} else if( chall.countTokens() == 1 ) {
				creatures.writeBoolean(false);
				creatures.writeByte( Integer.parseInt(chall.nextToken()) );
			} else {
				throw new Exception("Challenge Input Error");
			}
			
			//save XP:
			StringTokenizer xp = new StringTokenizer( ((JTextField)comps.get("XP")).getText(), " xpXP" );
			if( xp.countTokens() != 1 ) throw new Exception("XP Input Error");
			creatures.writeInt( Integer.parseInt(xp.nextToken()) );
			
			//save Ability modifiers:
			StringTokenizer abs = new StringTokenizer( ((JTextField)comps.get("Abilities")).getText(), " ," );
			if( abs.countTokens() != 6 ) throw new Exception("Ability Modifier Input Error");
			for( int i = 0; i < 6; i++ ) {
				creatures.writeByte( Integer.parseInt(abs.nextToken()) );
			}
			
			//save actions:
			for( int i = 16; i < 34; i++ ) {
				switch(i) {
					case 16: case 22: case 28://names:
						String attackName = ((JTextField)comps.get( compNames.get(i) )).getText();
						if( attackName.equals("") || attackName.equals(" Attack Name") ) {
							if( i == 16 ) throw new Exception("No Attack Added");
							i = 34;
							creatures.writeBoolean(false);
							break;
						} else if( i != 16 ) {
							creatures.writeBoolean(true);
						}
					case 19: case 21: case 25: case 27: case 31: case 33://damages and notes:
						creatures.writeUTF( ((JTextField)comps.get( compNames.get(i) )).getText() );
						break;
					case 17: case 23: case 29://attack bonuses:
						creatures.writeByte( Integer.parseInt(((JTextField)comps.get( compNames.get(i) )).getText()) );
						break;
					case 18: case 24: case 30://ranges:
						StringTokenizer range = new StringTokenizer( ((JTextField)comps.get( compNames.get(i) )).getText(), " /ftFT" );
						if( range.countTokens() > 2 ) throw new Exception("Range Input Error");
						if( range.countTokens() == 2 ) {
							creatures.writeBoolean(true);
							creatures.writeShort( Integer.parseInt(range.nextToken()) );
							creatures.writeShort( Integer.parseInt(range.nextToken()) );
						} else if( range.countTokens() == 1 ) {
							creatures.writeBoolean(false);
							creatures.writeShort( Integer.parseInt(range.nextToken()) );
						} else if( range.countTokens() == 0 ) {
							creatures.writeBoolean(false);
							creatures.writeShort( 5 );
						}
						break;
					case 20: case 26: case 32://damage types:
						StringTokenizer typeTokenizer = new StringTokenizer( ((JTextField)comps.get( compNames.get(i) )).getText(), " ()" );
						Exception e = new Exception("Attack Type Input Error");
						if( typeTokenizer.countTokens() > 1 ) throw e;
						String s = typeTokenizer.nextToken();
						if( s.length() > 1 ) throw e;
						if( s.length() == 0 )
							creatures.writeChar('z');
						else
							creatures.writeChar( s.charAt(0) );
						break;
				}
			}
			
			//save Additional notes:
			creatures.writeUTF( ((JTextArea)comps.get("AdditionalInformation")).getText() );
			
			
			//save creature name and index
			indexes.put( name, indexes.get("END") );
			indexes.put( "END", creatures.length() );
			
			//rewrite index file:
			saveIndexes();
			//close creatures:
			creatures.close();
			
			return true;
		} catch( IOException e ) {
			if( e.equals(new Exception("Stream Closed")) ) {
				System.out.println("Saved");
			} else {
				System.out.println( "saveCreature():" );
				e.printStackTrace();
				JOptionPane.showMessageDialog( null, "Unknown Filing Error", "Filing Error", JOptionPane.ERROR_MESSAGE );
			}
		} catch ( Exception e ) {
			System.out.println( "saveCreature():" );
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE );
		}
		return false;
	}
	
	private void saveIndexes() {
		try {
			Object[] temp = indexes.keySet().toArray();
			ArrayList<String> keys = new ArrayList<String>();
			for( Object t : temp )
				keys.add( (String)t );
			RandomAccessFile ind = new RandomAccessFile("indexes.dat", "rw" );
			
			ind.setLength(0);
			for( String k : keys ) {
				if( !k.equals("END") ) {
					ind.writeUTF( k );
					ind.writeLong( indexes.get(k) );
				}
			}
			
			ind.writeUTF("END");
			ind.writeLong( indexes.get("END") );
			
			ind.close();
			
		} catch (Exception e) {
			System.out.println( "saveIndexes():" );
			e.printStackTrace();
		}
		
	}
	
	private void resetCreatureBoxes() {

		for( int i = 3; i < 35; i++ )
			if( comps.get(compNames.get(i)) instanceof JTextField )
				((JTextField)comps.get(compNames.get(i))).setText("");
		
		((JTextArea)comps.get("AdditionalInformation")).setText("");
		
	}
	
	private String lookUp( String creatureName ) {
		try {
			
			RandomAccessFile creatures = new RandomAccessFile( "creatures.dat", "r" );
			creatures.seek( indexes.get(creatureName) );
			
			String output = "";
			output += creatures.readUTF() + "\n";
			
			output += "AC: " + creatures.readShort() + "\t";
			output += "HP: " + creatures.readShort() + "(" + creatures.readUTF() + ")\t";
			output += "Speed: " + creatures.readShort() + "ft\n";
			
			output += "Challenge: ";
			if( creatures.readBoolean() ) {
				output += creatures.readByte() + "/" + creatures.readByte() + "\t";
			} else {
				output += creatures.readByte() + "\t";
			}
			output += creatures.readInt() + "XP\n";	
			
			output += "STR DEX CON INT WIS CHA\n";
			
			for( int i = 0; i < 6; i++ ) {
				output += String.format( "%+3d ", creatures.readByte() );
			}
			output += "\n";
			
			output += "\nAttacks:\n";
			
			boolean areMoreAttacks = true;
			while(areMoreAttacks) {
				output += creatures.readUTF() + ": ";
				output += String.format( "%+d ", creatures.readByte() );
				if( creatures.readBoolean() ) {
					output += "(" + creatures.readShort() + "/" + creatures.readShort() + "ft)\t";
				} else {
					output += "(" + creatures.readShort() + "ft)\t";
				}
				output += creatures.readUTF() + "dmg ";
				char t;
				if( (t = creatures.readChar()) != 'z' )
					output += "(" + t + ") ";
				output += creatures.readUTF() + "\n";
				areMoreAttacks = creatures.readBoolean();
			}
			
			output += "\nMore Info:\n" + creatures.readUTF();
			
			//close creatures:
			creatures.close();
			
			return output;
		} catch( IOException e ) {
			if( e.equals(new Exception("Stream Closed")) ) {
				System.out.println("Saved");
			} else {
				System.out.println( "saveCreature():" );
				e.printStackTrace();
				JOptionPane.showMessageDialog( null, "Unknown Filing Error", "Filing Error", JOptionPane.ERROR_MESSAGE );
			}
		} catch (Exception e) {
			System.out.println( "lookUp(String):" );
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( null, e.getMessage(), "Error", ERROR );
		}
		return null;
	}
	
}

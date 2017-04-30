import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	
	public StatBlockReader() {
		initializeScreens();
	}
	
	private void initializeScreens() {
		setTitle("Stat Blocks");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 550, 215 );
		setPreferredSize(getSize());
		setLocationByPlatform(true);
		
		comps = new HashMap<String, Component>();
		
		initializeOpeningScreen();
		initializeNewCreatureScreen();
	}
	
	private void initializeOpeningScreen() {
		setLayout(new FlowLayout( FlowLayout.CENTER, 0, 10 ));
		
		JPanel openingScreen = new JPanel();
		openingScreen.setLayout(new GridLayout( 4, 2, 10, 10 ));
		comps.put( "PanelOpeningScreen", openingScreen );
		add(openingScreen);
		
		JButton newCreature = new JButton("New Creature");
		newCreature.addActionListener(this);
		newCreature.setToolTipText("Creates a new creature file");
		comps.put( "ButtonNewCreature", newCreature );
		openingScreen.add(newCreature);
		
		openingScreen.add(new JLabel(" "));
		
		JButton lookUp = new JButton("Look Up");
		lookUp.addActionListener(this);
		lookUp.setToolTipText("Look Up the stats of a creature");
		comps.put( "ButtonLookUp", lookUp );
		openingScreen.add(lookUp);
		
		JTextField lookUpName = new JTextField();
		lookUpName.addActionListener(this);
		lookUpName.setToolTipText("The name of the creature to look up");
		lookUpName.setColumns(10);
		comps.put( "TextFieldLookUpName", lookUpName );
		openingScreen.add(lookUpName);
		
		JButton delete = new JButton("Delete Creature");
		delete.addActionListener(this);
		delete.setToolTipText("Delete a creature from the saves");
		comps.put( "ButtonDelete", delete );
		openingScreen.add(delete);
		
		JTextField deleteName = new JTextField();
		deleteName.addActionListener(this);
		deleteName.setToolTipText("The name of the creature to delete");
		deleteName.setColumns(10);
		comps.put( "TextFieldDeleteNaem", deleteName );
		openingScreen.add(deleteName);
		
		JButton listCreatures = new JButton("List Creatures");
		listCreatures.addActionListener(this);
		listCreatures.setToolTipText("List the names of saved creatures in alphabetical order");
		comps.put( "ButtonListCreatures", listCreatures );
		openingScreen.add(listCreatures);
	}
	
	private void initializeNewCreatureScreen() {
		JPanel creatureCreator = new JPanel();
		creatureCreator.setLayout(new GridLayout( 0, 1, 0, 10 ));
		creatureCreator.setVisible(false);
		comps.put( "PanelCreatureCreator", creatureCreator );
		add(creatureCreator);
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		creatureCreator.add(namePanel);
		
		JTextField nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setToolTipText("Creature Name");
		namePanel.add(nameField);
		comps.put( "TextFieldCreatureName", nameField );
		
		JPanel statPanel = new JPanel();
		statPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		creatureCreator.add(statPanel);
		
		JLabel acLabel = new JLabel("AC:");
		acLabel.setToolTipText("Armor Class");
		statPanel.add(acLabel);
		
		JTextField acField = new JTextField();
		acField.setColumns(3);
		acField.setToolTipText("Armor Class");
		comps.put( "TextField", acField );
		statPanel.add(acField);
		
		statPanel.add(new JLabel("  "));
		
		JLabel hpLabel = new JLabel("HP:");
		hpLabel.setToolTipText("Health Points");
		statPanel.add(hpLabel);
		
		JTextField hpField = new JTextField();
		hpField.setColumns(8);
		hpField.setToolTipText("Example: 24(4d8+6)");
		comps.put( "TextFieldHP", hpField );
		statPanel.add(hpField);
		
		statPanel.add(new JLabel("  "));
		
		JLabel speedLabel = new JLabel("Speed:");
		speedLabel.setToolTipText("Speed in Feet");
		statPanel.add(speedLabel);
		
		JTextField speedField = new JTextField();
		speedField.setColumns(3);
		speedField.setToolTipText("Speed in Feet");
		comps.put( "TextFieldSpeed", speedField );
		statPanel.add(speedField);
		
		JPanel challengePanel = new JPanel();
		challengePanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		creatureCreator.add(challengePanel);
		
		challengePanel.add(new JLabel("Challenge:"));
		
		JTextField challengeField = new JTextField();
		challengeField.setToolTipText("Challenge Rating");
		challengeField.setColumns(3);
		comps.put( "TextFieldChallenge", challengeField );
		challengePanel.add(challengeField);
		
		JTextField xpField = new JTextField();
		xpField.setToolTipText("XP");
		xpField.setText(" XP");
		xpField.setColumns(5);
		comps.put( "TextFieldXP", xpField );
		challengePanel.add(xpField);
		
		JPanel abilityPanel = new JPanel();
		abilityPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		creatureCreator.add(abilityPanel);
		
		abilityPanel.add(new JLabel("Ability Modifiers:"));
		
		JTextField abilityField = new JTextField();
		abilityField.setToolTipText("STR  DEX  CON  INT  WIS  CHA");
		abilityField.setColumns(15);
		comps.put( "TextFieldAbility", abilityField );
		abilityPanel.add(abilityField);
		
		JPanel attackLabelPanel = new JPanel();
		attackLabelPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		attackLabelPanel.add(new JLabel("Attacks:"));
		creatureCreator.add(attackLabelPanel);
		
		JPanel attackPanel = new JPanel();
		attackPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		comps.put( "PanelAttack1", attackPanel );
		creatureCreator.add(attackPanel);
		
		JTextField attackField = new JTextField();
		attackField.setToolTipText("Attack Name");
		attackField.setColumns(5);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Attack Bonus");
		attackField.setColumns(3);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Range");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage Type");
		attackField.setColumns(2);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Additional Notes");
		attackField.setColumns(10);
		attackPanel.add(attackField);
		
		//Attack 2:
		attackPanel = new JPanel();
		attackPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		comps.put( "PanelAttack1", attackPanel );
		creatureCreator.add(attackPanel);
		
		attackField = new JTextField();
		attackField.setToolTipText("Attack Name");
		attackField.setColumns(5);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Attack Bonus");
		attackField.setColumns(3);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Range");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage Type");
		attackField.setColumns(2);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Additional Notes");
		attackField.setColumns(10);
		attackPanel.add(attackField);
		
		//Attack 3:
		attackPanel = new JPanel();
		attackPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		comps.put( "PanelAttack1", attackPanel );
		creatureCreator.add(attackPanel);
		
		attackField = new JTextField();
		attackField.setToolTipText("Attack Name");
		attackField.setColumns(5);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Attack Bonus");
		attackField.setColumns(3);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Range");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage");
		attackField.setColumns(6);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Damage Type");
		attackField.setColumns(2);
		attackPanel.add(attackField);
		
		attackField = new JTextField();
		attackField.setToolTipText("Additional Notes");
		attackField.setColumns(10);
		attackPanel.add(attackField);
		
		//JPanel additionalNotesPanel = new JPanel();
		//additionalNotesPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		//creatureCreator.add(additionalNotesPanel);
		
		JTextArea additionalNotesArea = new JTextArea();
		additionalNotesArea.setText("");
		additionalNotesArea.setWrapStyleWord(true);
		additionalNotesArea.setLineWrap(true);
		additionalNotesArea.setRows(10);
		additionalNotesArea.setColumns(50);
		additionalNotesArea.setFont(new Font( "Courier", Font.PLAIN, 16 ));
		comps.put( "TextAreaAdditionalNotes", additionalNotesArea );
		
		
		JScrollPane additionalNotesScroll = new JScrollPane(additionalNotesArea);
		additionalNotesScroll.setAutoscrolls(true);
		add(additionalNotesScroll);
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals(comps.get("ButtonNewCreature")) ) {
			
			newScreen( Screens.CREATE_NEW );
			
		} else if( e.getSource().equals(comps.get("ButtonLookUp")) || e.getSource().equals(comps.get("TextFieldLookUpName")) ) {
			
			String creatureName = ((JTextField)comps.get("LookUpName")).getText();
			String creatureInfo = lookUp( creatureName );
			if( creatureInfo == null ) return;
			creatureInfo = creatureInfo.substring( creatureName.length() + 1 );
			//start thread:
			new Thread(new InfoFrame( creatureName, creatureInfo )).start();
			
		} else if( e.getSource().equals(comps.get("ButtonDelete")) || e.getSource().equals(comps.get("TextFieldDeleteName")) ) {
			
			if( JOptionPane.showConfirmDialog( null, "Are you sure you want to delete this creature?" ) == 0 )
				if( deleteCreatureFile( ((JTextField)comps.get("DeleteName")).getText() ) ) {
					JOptionPane.showMessageDialog( null, "Creature Deleted" );
					((JTextField)comps.get("DeleteName")).setText("");
				} else
					JOptionPane.showMessageDialog( null, "Creature Not Found" );
			
		} else if( e.getSource().equals(comps.get("ButtonListCreatures")) ) {
			
			String list = orderNames();
			new Thread(new InfoFrame( "List", list )).start();
			
		} else if( e.getSource().equals(comps.get("ButtonCompleteCreature")) ) {
			
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
				
				comps.get("PanelCreatureCreator").setVisible(false);
				
				setSize(getPreferredSize());
				setLayout(new FlowLayout( FlowLayout.CENTER, 0, 10 ));
				
				comps.get("PanelOpeningScreen").setVisible(true);
				comps.get("TextFieldLookUpName").requestFocus();
				
				break;
			case CREATE_NEW:
				
				comps.get("PanelOpeningScreen").setVisible(false);

				this.setResizable(true);
				setSize( 550, 580 );
				setLayout(new FlowLayout( FlowLayout.LEFT, 0, 10 ));
				
				comps.get("PanelCreatureCreator").setVisible(true);
				comps.get("TextFieldCreatureName").requestFocus();
				
				break;
			default:
				break;
		}
	}
	
	private boolean deleteCreatureFile( String creatureName ) {
		File f = new File( creatureName + ".dat" );
		return f.delete();
	}
	
	private String orderNames() {
		
		//create ArrayList of creature names:
		File file = new File(System.getProperty("user.dir"));
		File[] fileList = file.listFiles();
		if( fileList == null )
			System.out.println("NULL");
		System.out.println(fileList.length);
		ArrayList<String> list = new ArrayList<String>();
		for( File f : fileList )
			list.add( new StringTokenizer( f.getName(), "." ).nextToken() );
		list.remove("Monster Manual");
		
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
			
			//save name of creature to save index later && check is already exists:
			String name = ((JTextField)comps.get("CreatureName")).getText();
			if( name.length() == 0 ) throw new Exception( "No Creature Name" );
			
			//start file readers:
			File f = new File( name + ".dat" );
			if( f.exists() ) throw new Exception( "This Creature File Already Exists" );
			RandomAccessFile creatures = new RandomAccessFile( f, "rw" );
			
			//save creature info:
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
						if( i == 33 )
							creatures.writeBoolean(false);
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

	private void resetCreatureBoxes() {

		for( int i = 3; i < 35; i++ )
			if( comps.get(compNames.get(i)) instanceof JTextField )
				((JTextField)comps.get(compNames.get(i))).setText("");
		
		((JTextArea)comps.get("AdditionalInformation")).setText("");
		
	}
	
	private String lookUp( String creatureName ) {
		String output = "";
		RandomAccessFile creatures = null;
		try {
			
			creatures = new RandomAccessFile( creatureName + ".dat", "r" );
			
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
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, "No Creature of that Name Found", "Filing Error", JOptionPane.ERROR_MESSAGE );
		} catch( IOException e ) {
			if( e.equals(new Exception("Stream Closed")) ) {
				
			} else {
				System.out.println( "lookUp(String):" );
				e.printStackTrace();
				JOptionPane.showMessageDialog( null, "Unknown Filing Error", "Filing Error", JOptionPane.ERROR_MESSAGE );
			}
		} catch (Exception e) {
			System.out.println( "lookUp(String):" );
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog( null, e.getMessage(), "Error", ERROR );
		}
		
		//close creatures:
		if( creatures != null )
			try {
				creatures.close();
			} catch( IOException e ) {
				e.printStackTrace();
			}
		
		return "E ERROR READING\n" + output;
	}
	
	public static void main( String[] args ) {
		JFrame f = new StatBlockReader();
		f.setVisible(true);
	}
	
}

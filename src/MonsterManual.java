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
import utils.MyFocusListener;

public class MonsterManual extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1;
	private HashMap<String, Component> comps;
	private boolean isEditing = false;
	
	public MonsterManual() {
		initialize();
	}
	
	private void initialize() {
		setTitle("Stat Blocks");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 550, 250 );
		setPreferredSize(getSize());
		setLocationByPlatform(true);
		
		comps = new HashMap<String, Component>();
		
		initializeOpeningScreen();
		initializeNewCreatureScreen();
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
		comps.put( "TextFieldCreatureName", nameField );
		namePanel.add(nameField);
		
		namePanel.add(new JLabel("                                                                                 "));
		
		JButton completeButton = new JButton("Complete Creature");
		completeButton.addActionListener(this);
		comps.put( "ButtonCompleteCreature", completeButton );
		namePanel.add(completeButton);
		
		
		JPanel statPanel = new JPanel();
		statPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		creatureCreator.add(statPanel);
		
		JLabel acLabel = new JLabel("AC:");
		acLabel.setToolTipText("Armor Class");
		statPanel.add(acLabel);
		
		JTextField acField = new JTextField();
		acField.setColumns(3);
		acField.setToolTipText("Armor Class");
		comps.put( "TextFieldAC", acField );
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
		comps.put( "PanelAttack2", attackPanel );
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
		comps.put( "PanelAttack3", attackPanel );
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
		
		JPanel additionalInfoPanel = new JPanel();
		additionalInfoPanel.setLayout(new FlowLayout( FlowLayout.LEFT, 10, 0 ));
		additionalInfoPanel.setVisible(false);
		comps.put( "PanelAdditionalInfo", additionalInfoPanel );
		add(additionalInfoPanel);
		
		JTextArea additionalInfoArea = new JTextArea();
		additionalInfoArea.setText("");
		additionalInfoArea.setWrapStyleWord(true);
		additionalInfoArea.setLineWrap(true);
		additionalInfoArea.setRows(11);
		additionalInfoArea.setColumns(52);
		additionalInfoArea.setFont(new Font( "Courier", Font.PLAIN, 16 ));
		comps.put( "TextAreaAdditionalInfo", additionalInfoArea );
		
		
		JScrollPane additionalInfoScroll = new JScrollPane(additionalInfoArea);
		additionalInfoScroll.setAutoscrolls(true);
		additionalInfoPanel.add(additionalInfoScroll);
	}
	
	private void initializeOpeningScreen() {
		setLayout(new FlowLayout( FlowLayout.CENTER, 0, 10 ));
		
		JPanel openingScreen = new JPanel();
		openingScreen.setLayout(new GridLayout( 5, 2, 10, 10 ));
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
		lookUpName.addFocusListener(new MyFocusListener(lookUpName));
		comps.put( "TextFieldLookUpName", lookUpName );
		openingScreen.add(lookUpName);
		
		JButton edit = new JButton("Edit");
		edit.addActionListener(this);
		edit.setToolTipText("Edit a creature's saved information");
		comps.put( "ButtonEdit", edit );
		openingScreen.add(edit);
		
		JTextField editName = new JTextField();
		editName.addActionListener(this);
		editName.setToolTipText("The name of the creature to edit");
		editName.setColumns(10);
		editName.addFocusListener(new MyFocusListener(editName));
		comps.put( "TextFieldEditName", editName );
		openingScreen.add(editName);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(this);
		delete.setToolTipText("Delete a creature from the saves");
		comps.put( "ButtonDelete", delete );
		openingScreen.add(delete);
		
		JTextField deleteName = new JTextField();
		deleteName.addActionListener(this);
		deleteName.setToolTipText("The name of the creature to delete");
		deleteName.setColumns(10);
		deleteName.addFocusListener(new MyFocusListener(deleteName));
		comps.put( "TextFieldDeleteName", deleteName );
		openingScreen.add(deleteName);
		
		JButton listCreatures = new JButton("List Creatures");
		listCreatures.addActionListener(this);
		listCreatures.setToolTipText("List the names of saved creatures in alphabetical order");
		comps.put( "ButtonListCreatures", listCreatures );
		openingScreen.add(listCreatures);
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource().equals(comps.get("ButtonNewCreature")) ) {
			
			newScreen( Screens.CREATE_NEW );
			
		} else if( e.getSource().equals(comps.get("ButtonLookUp")) || e.getSource().equals(comps.get("TextFieldLookUpName")) ) {
			
			String creatureName = ((JTextField)comps.get("TextFieldLookUpName")).getText();
			String creatureInfo = lookUp( creatureName );
			if( creatureInfo == null ) return;
			//creatureInfo = creatureInfo.substring( creatureName.length() + 1 );
			//start thread:
			new Thread(new InfoFrame( creatureName, creatureInfo )).start();
			
		} else if( e.getSource().equals(comps.get("ButtonEdit")) || e.getSource().equals(comps.get("TextFieldEditName")) ) { 
			
			String creatureName =  ((JTextField)comps.get("TextFieldEditName")).getText();
			if(creatureName.length() >= 1) {
				if(loadForEdit(creatureName)) {
					newScreen(Screens.CREATE_NEW);
				}
			} else {
				JOptionPane.showMessageDialog( null, "No Creature Name: " + creatureName, "Input Error", JOptionPane.ERROR_MESSAGE );
			}
			
		} else if( e.getSource().equals(comps.get("ButtonDelete")) || e.getSource().equals(comps.get("TextFieldDeleteName")) ) {
			
			if( JOptionPane.showConfirmDialog( null, "Are you sure you want to delete this creature?" ) == 0 ) {
				if( deleteCreatureFile( ((JTextField)comps.get("TextFieldDeleteName")).getText() ) ) {
					JOptionPane.showMessageDialog( null, "Creature Deleted" );
					((JTextField)comps.get("TextFieldDeleteName")).setText("");
				} else {
					JOptionPane.showMessageDialog( null, "Creature Not Found" );
				}
			}
			
		} else if( e.getSource().equals(comps.get("ButtonListCreatures")) ) {
			
			String list = orderNames();
			new Thread(new InfoFrame( "List", list )).start();
			
		} else if( e.getSource().equals(comps.get("ButtonCompleteCreature")) ) {
			
			String creatureName = ((JTextField)comps.get("TextFieldEditName")).getText();
			if(isEditing) {
				new File(creatureName + ".dat").renameTo(new File(creatureName + "TMP.dat"));
			}
			if(saveCreature()) {
				newScreen( Screens.OPENING );
				resetCreatureBoxes();
				if(isEditing) {
					new File(creatureName + "TMP.dat").delete();
					isEditing = false;
				}
			} else if(isEditing) {
				new File(creatureName + ".dat").delete();
				new File(creatureName + "TMP.dat").renameTo(new File(creatureName + ".dat"));
			}
			
		} else {
			System.out.println( e.toString() );
		}
	}
	
	private boolean deleteCreatureFile( String creatureName ) {
		File f = new File( creatureName + ".dat" );
		return f.delete();
	}
	
	private boolean loadForEdit(String creatureName) {
		RandomAccessFile creature = null;
		try {
			creature = new RandomAccessFile(creatureName + ".dat", "r");
			
			((JTextField)comps.get("TextFieldCreatureName")).setText(creature.readUTF());//name
			((JTextField)comps.get("TextFieldAC")).setText(creature.readShort()+"");//AC
			((JTextField)comps.get("TextFieldHP")).setText(creature.readShort() + " (" + creature.readUTF() + ")");//HP
			((JTextField)comps.get("TextFieldSpeed")).setText(creature.readShort() + "ft");

			//challenge
			if( creature.readBoolean() ) {
				((JTextField)comps.get("TextFieldChallenge")).setText(creature.readByte() + "/" + creature.readByte());
			} else {
				((JTextField)comps.get("TextFieldChallenge")).setText(creature.readByte()+"");
			}
			
			((JTextField)comps.get("TextFieldXP")).setText(creature.readInt() + " XP");//XP

			//Ability Modifiers:
			String abMod = "";
			for( int i = 0; i < 6; i++ ) {
				abMod += String.format( "%+3d ", creature.readByte() );
			}
			((JTextField)comps.get("TextFieldAbility")).setText(abMod);
			
			//attacks:
			boolean areMoreAttacks = true;
			for(int i = 0; i < 3 && areMoreAttacks; i++) {
				JPanel p = (JPanel)comps.get( "PanelAttack" + (i+1) );
				for(int j = 0; j < 6; j++) {
					switch(j) {
						case 0: case 3: case 5:
							((JTextField)p.getComponent(j)).setText(creature.readUTF());
							if(j == 5) {
								areMoreAttacks = creature.readBoolean();
							}
							break;
						case 1:
							((JTextField)p.getComponent(j)).setText(creature.readByte()+"");
							break;
						case 2:
							if(creature.readBoolean()) {
								((JTextField)p.getComponent(j)).setText(creature.readShort() + "/" + creature.readShort() + "ft");
							} else {
								Short range = creature.readShort();
								if(range == 5) {
									//ignore
								} else {
									((JTextField)p.getComponent(j)).setText(range + "ft");
								}
							}
							break;
						case 4:
							char dmgType = creature.readChar();
							if(dmgType == 'z') {
								//ignore
							} else {
								((JTextField)p.getComponent(j)).setText(dmgType+"");
							}
							break;
					}
				}
			}
			
			//Additional Notes:
			((JTextArea)comps.get("TextAreaAdditionalInfo")).setText(creature.readUTF());

			isEditing = true;
			return true;
		} catch(FileNotFoundException fnf) {
			JOptionPane.showMessageDialog( null, "File Not Found when loading for Editing", "I/O Error", JOptionPane.ERROR_MESSAGE );
			return false;
		} catch(Exception e) {
			//TODO write catch
			e.printStackTrace();
			resetCreatureBoxes();
			return false;
		} finally {
			try {
				if(creature != null) creature.close();
			} catch(IOException io) {
				JOptionPane.showMessageDialog( null, "RAF not closed when Editing", "I/O Error", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	
	private String lookUp( String creatureName ) {
		String output = "";
		RandomAccessFile creature = null;
		try {
			
			creature = new RandomAccessFile( creatureName + ".dat", "r" );
			
			output += creature.readUTF() + "\n";//name
			
			output += "AC: " + creature.readShort() + "\t";//AC
			output += "HP: " + creature.readShort() + "(" + creature.readUTF() + ")\t";//HP
			output += "Speed: " + creature.readShort() + "ft\n";//speed
			
			//challenge & XP:
			output += "Challenge: ";
			if( creature.readBoolean() ) {
				output += creature.readByte() + "/" + creature.readByte() + "\t";
			} else {
				output += creature.readByte() + "\t";
			}
			output += creature.readInt() + "XP\n";	
			
			//ability modifiers
			output += "STR DEX CON INT WIS CHA\n";
			for( int i = 0; i < 6; i++ ) {
				output += String.format( "%+3d ", creature.readByte() );
			}
			output += "\n";
			
			//attacks:
			output += "\nAttacks:\n";
			boolean areMoreAttacks = true;
			while(areMoreAttacks) {
				output += creature.readUTF() + ": ";
				output += String.format( "%+d ", creature.readByte() );
				if( creature.readBoolean() ) {
					output += "(" + creature.readShort() + "/" + creature.readShort() + "ft)\t";
				} else {
					output += "(" + creature.readShort() + "ft)\t";
				}
				output += creature.readUTF() + "dmg ";
				char t;
				if( (t = creature.readChar()) != 'z' )
					output += "(" + t + ") ";
				output += creature.readUTF() + "\n";
				areMoreAttacks = creature.readBoolean();
			}
			
			output += "\nMore Info:\n" + creature.readUTF();
			
			//close creatures:
			creature.close();
			
			return output;
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, "No Creature of that Name Found", "Filing Error", JOptionPane.ERROR_MESSAGE );
			return null;
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
		if( creature != null )
			try {
				creature.close();
			} catch( IOException e ) {
				e.printStackTrace();
			}
		
		return "ERROR READING\n" + output;
	}
	
	private void newScreen( Screens s ) {
		switch(s) {
			case OPENING:
				
				comps.get("PanelCreatureCreator").setVisible(false);
				comps.get("PanelAdditionalInfo").setVisible(false);
				
				setSize(getPreferredSize());
				setLayout(new FlowLayout( FlowLayout.CENTER, 0, 10 ));
				
				comps.get("PanelOpeningScreen").setVisible(true);
				comps.get("TextFieldLookUpName").requestFocus();
				
				break;
			case CREATE_NEW:
				
				comps.get("PanelOpeningScreen").setVisible(false);

				setSize( 548, 605 );
				setLayout(new FlowLayout( FlowLayout.LEFT, 0, 10 ));
				
				comps.get("PanelCreatureCreator").setVisible(true);
				comps.get("PanelAdditionalInfo").setVisible(true);
				comps.get("TextFieldCreatureName").requestFocus();
				
				break;
			default:
				break;
		}
	}
	
	private String orderNames() {
		
		//create ArrayList of creature names:
		File file = new File(System.getProperty("user.dir"));
		File[] fileList = file.listFiles();
		if( fileList == null )
			System.out.println("NULL");
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

	private void resetCreatureBoxes() {
		String e = "";
		((JTextField)comps.get("TextFieldAbility")).setText(e);
		((JTextField)comps.get("TextFieldCreatureName")).setText(e);
		((JTextField)comps.get("TextFieldXP")).setText(" XP");
		((JTextField)comps.get("TextFieldAC")).setText(e);
		((JTextArea)comps.get("TextAreaAdditionalInfo")).setText(e);
		((JTextField)comps.get("TextFieldHP")).setText(e);
		((JTextField)comps.get("TextFieldChallenge")).setText(e);
		((JTextField)comps.get("TextFieldSpeed")).setText(e);

		//attacks:
		for( int i = 0; i < 3; i++ ) {
			JPanel p = (JPanel)comps.get( "PanelAttack" + (i+1) );
			for( int j = 0; j < 6; j++ ) {
				((JTextField)p.getComponent(j)).setText(e);
			}
		}
	}
	
	private boolean saveCreature() {
		String name = ((JTextField)comps.get("TextFieldCreatureName")).getText();
		RandomAccessFile creature = null;
		try {
			
			//save name of creature to save index later && check is already exists:
			if( name.length() == 0 ) throw new Exception( "No Creature Name" );
			
			//start file readers:
			File file = new File( name + ".dat" );
			if( file.exists() ) throw new Exception( "This Creature File Already Exists" );
			creature = new RandomAccessFile( file, "rw" );
			
			//save creature info:
			creature.writeUTF( name );//UTF: name
			
			creature.writeShort( Short.parseShort( ((JTextField)comps.get("TextFieldAC")).getText() ) );//short: AC
			
			//HP:
			StringTokenizer hp = new StringTokenizer( ((JTextField)comps.get("TextFieldHP")).getText(), " ()" );
			if( hp.countTokens() != 2 ) throw new Exception( "HP Input Error" );
			creature.writeShort( Short.parseShort( hp.nextToken() ) );//short: average HP
			creature.writeUTF( hp.nextToken() );//UTF: roll for HP

			//Speed:
			StringTokenizer speed = new StringTokenizer(((JTextField)comps.get("TextFieldSpeed")).getText(), " ftFT");
			if(speed.countTokens() != 1) throw new Exception("Speed Input Error");
			creature.writeShort(Short.parseShort(speed.nextToken()));//short: speed
			
			//challenge:
			StringTokenizer chall = new StringTokenizer( ((JTextField)comps.get("TextFieldChallenge")).getText(), " /" );
			if( chall.countTokens() == 2 ) {
				creature.writeBoolean(true);
				creature.writeByte( Integer.parseInt(chall.nextToken()) );
				creature.writeByte( Integer.parseInt(chall.nextToken()) );
			} else if( chall.countTokens() == 1 ) {
				creature.writeBoolean(false);
				creature.writeByte( Integer.parseInt(chall.nextToken()) );
			} else {
				throw new Exception("Challenge Input Error");
			}
			
			//XP:
			StringTokenizer xp = new StringTokenizer( ((JTextField)comps.get("TextFieldXP")).getText(), " xpXP" );
			if( xp.countTokens() != 1 ) throw new Exception("XP Input Error");
			creature.writeInt( Integer.parseInt(xp.nextToken()) );
			
			//Ability modifiers:
			StringTokenizer abs = new StringTokenizer( ((JTextField)comps.get("TextFieldAbility")).getText(), " ," );
			if( abs.countTokens() != 6 ) throw new Exception("Ability Modifier Input Error");
			for( int i = 0; i < 6; i++ ) {
				creature.writeByte( Integer.parseInt(abs.nextToken()) );
			}
			
			//attacks:
			for( int i = 0; i < 3; i++ ) {
				JPanel p = (JPanel)comps.get( "PanelAttack" + (i+1) );
				for( int j = 0; j < 6; j++ ) {
					String t = ((JTextField)p.getComponent(j)).getText();
					switch(j) {
						case 0:
							if( t.equals("") ) {
								if( i == 0 ) throw new Exception("No Attack Added");
								creature.writeBoolean(false);
								i = 3;
								j = 6;
								break;
							} else if( i != 0 ) {
								creature.writeBoolean(true);
							}
						case 3: case 5:
							creature.writeUTF(t);
							if( i == 2 && j == 5 )
								creature.writeBoolean(false);
							break;
						case 1:
							creature.writeByte( Integer.parseInt(t) );
							break;
						case 2:
							StringTokenizer range = new StringTokenizer( t, " /ftFT" );
							if( range.countTokens() > 2 ) throw new Exception("Range Input Error");
							if(range.countTokens() == 2 ) {
								creature.writeBoolean(true);
								creature.writeShort( Short.parseShort(range.nextToken()) );
								creature.writeShort( Short.parseShort(range.nextToken()) );
							} else if( range.countTokens() == 1 ) {
								creature.writeBoolean(false);
								creature.writeShort( Short.parseShort(range.nextToken()) );
							} else if( range.countTokens() == 0 ) {
								creature.writeBoolean(false);
								creature.writeShort( 5 );
							}
							break;
						case 4:
							StringTokenizer typeTokenizer = new StringTokenizer( t, " ()" );
							if( typeTokenizer.countTokens() > 1 ) throw new Exception("Attack Type Input Error");;
							String s = typeTokenizer.nextToken();
							if( s.length() > 1 ) throw new Exception("Attack Type Input Error");;
							if( s.length() == 0 )
								creature.writeChar('z');
							else
								creature.writeChar( s.charAt(0) );
							break;
					}
				}
			}
			
			//Additional notes:
			creature.writeUTF( ((JTextArea)comps.get("TextAreaAdditionalInfo")).getText() );
			
			//close creatures:
			creature.close();
			
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
		} finally {
			try {
				if(creature != null) creature.close();
			} catch(IOException io) {
				JOptionPane.showMessageDialog( null, "RAF not closed when Creating", "I/O Error", JOptionPane.ERROR_MESSAGE );
			}
		}
		
		try {
			File toDelete = new File( name + ".dat" );
			toDelete.delete();
		} catch( Exception e ) {
			
		}
		return false;
	}

	public static void main( String[] args ) {
		JFrame f = new MonsterManual();
		f.setVisible(true);
	}
	
}

enum Screens {
	OPENING,
	CREATE_NEW;
}
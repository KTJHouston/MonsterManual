import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class InfoFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 2;
	private JTextArea box;
	private String title, info;
	
	public InfoFrame( String creatureName, String info ) {
		title = creatureName;
		this.info = info;
	}
	
	private void initialize() {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 800, 300 );
		setLocationByPlatform(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		box = new JTextArea();
		box.setText(info);
		box.setWrapStyleWord(true);
		box.setRows(6);
		box.setFont(new Font("Courier", Font.PLAIN, 18));
		box.setColumns(67);
		box.setLineWrap(true);
		add(box);
	}
	
	@Override
	public void run() {
		initialize();
		setVisible(true);
	}

}

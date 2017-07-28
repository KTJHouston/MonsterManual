import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoFrame extends JFrame implements Runnable {

	private static final long serialVersionUID = 2;
	private JTextArea box;
	private String title, info;
	private boolean isEditable;
	
	
	public InfoFrame( String title, String info ) {
		this.title = title;
		this.info = info;
		isEditable = true;
	}
	
	public InfoFrame( String title, String info, boolean isEditable ) {
		this.title = title;
		this.info = info;
		this.isEditable = isEditable;
	}
	
	private void initialize() {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize( 800, 600 );
		setLocationByPlatform(true);
		setLayout(new FlowLayout( FlowLayout.CENTER, 5, 10 ));
		
		box = new JTextArea();
		box.setText(info);
		box.setWrapStyleWord(true);
		box.setRows(21);
		box.setFont(new Font("Courier", Font.PLAIN, 18));
		box.setColumns(68);
		box.setLineWrap(true);
		box.setEditable(isEditable);
		
		JScrollPane scroll = new JScrollPane(box);
		scroll.setAutoscrolls(false);
		add(scroll);
	}
	
	@Override
	public void run() {
		initialize();
		setVisible(true);
	}

}

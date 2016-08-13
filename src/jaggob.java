import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class jaggob extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private GUI dieGUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jaggob frame = new jaggob();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jaggob() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dieGUI.getName(getName());
//				GUI.getPath(getPath());
				//GUI.gnButton();  // ->braucht noch arbeit
				System.out.println(getPath()+" ,"+getName());
				zu();
			}
		});
		btnNewButton.setBounds(10, 115, 414, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Dateiname");
		lblNewLabel.setBounds(10, 11, 85, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buttonname");
		lblNewLabel_1.setBounds(10, 36, 85, 17);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(119, 8, 305, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 34, 305, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
	
	public String getPath(){
		String path = textField.getText();
		return path;
	}
	
	public String getName(){
		String name = textField_1.getText();
		return name;
	}
	public void zu(){
		this.setVisible(false);
	}
}

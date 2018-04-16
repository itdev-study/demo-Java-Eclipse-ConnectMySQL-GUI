//Batch for demo DB connection in GUI - v 0.1
package demo.Java.Eclipse.ConnectMySQL.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;
	private JTable table;
	JButton btnNewButton = new JButton("New button");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
					
				
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	

	
	
	
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		btnNewButton.setBounds(24, 464, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(153, 464, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		table = new JTable();
		table.setBounds(24, 12, 583, 303);
		frame.getContentPane().add(table);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(24, 341, 583, 100);
		frame.getContentPane().add(editorPane);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				//editorPane.setText('TEST 2222');
								
			}
		});
		
		
	}
	
	
	
	
	
	
	
}

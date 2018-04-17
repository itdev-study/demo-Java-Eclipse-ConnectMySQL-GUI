//Batch for demo DB connection in GUI - v 0.1
package demo.Java.Eclipse.ConnectMySQL.GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class MainForm {
	private JFrame frame = new JFrame();
	
	JButton btnNewButton = new JButton("Show data");
	JEditorPane editorPane = new JEditorPane();	
	JButton btnNewButton_1 = new JButton("Clear");
	Connection con =null;     
    
    DefaultTableModel model = new DefaultTableModel();
    private JTable table = new JTable(model);    
	
	String url = "jdbc:mysql://localhost/testdb";
	String user = "testdbuser";
	String password = "dbpass";

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

	public MainForm() {
		initialize();
	}

	private void initialize() {
		model.addColumn("Z1");
		model.addColumn("Z2");
		frame.setBounds(100, 100, 634, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		btnNewButton.setBounds(24, 464, 117, 25);
		frame.getContentPane().add(btnNewButton);		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorPane.setText("Clear data from grid");				
				int RowCount = model.getRowCount();			
				while (RowCount > 0) {
					System.out.println(RowCount + "row Cleared");
					model.removeRow(RowCount-1);
				RowCount--;
				}
			}
		});
		
		btnNewButton_1.setBounds(153, 464, 117, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		table.setBounds(24, 12, 583, 303);
		frame.getContentPane().add(table);		
		
		editorPane.setBounds(24, 341, 583, 100);
		frame.getContentPane().add(editorPane);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {							
				System.out.println("Test Connection to MySQL");
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver loaded");
				} catch (ClassNotFoundException e) {
					System.out.println("Error loading jdbc driver");
					e.printStackTrace();
				}
								
				try {
					con=DriverManager.getConnection(url, user, password);
					System.out.println("Connection to DB created");
					
					Statement statment = con.createStatement();
					System.out.println("Statement created");
					
					//String insertSQL = "insert into items values (1, 'TEST-555')";			
					//statment.executeUpdate(insertSQL);		
					
					String sql;
				    sql = "SELECT id, name FROM items";
				    ResultSet rs = statment.executeQuery(sql);	 
				    
				    while(rs.next()){		       
				         int id  = rs.getInt("id");
				         String name = rs.getString("name");  
				         System.out.print("ID: " + id + "; Name:" + name + "\n");			        
				         editorPane.setText("Add data to grid");	
				         model.addRow(new Object[] {id,name});				         			         				         
				    }		      
				    rs.close();
				    statment.close();
				    con.close();
				      
				} catch (SQLException e) {					
					e.printStackTrace();
				}								
			}
		});		
		
	}
	
}

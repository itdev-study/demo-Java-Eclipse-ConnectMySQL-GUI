//Batch for demo DB connection in GUI - v 0.1
package demo.Java.Eclipse.ConnectMySQL.GUI;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;


public class MainForm {
	private JFrame frame = new JFrame();
	
	JButton btnNewButton = new JButton("New button");
	JEditorPane editorPane = new JEditorPane();	
	JButton btnNewButton_1 = new JButton("New button");
	Connection con =null; 
    //Массив содержащий информацию для таблицы
	Object[] headers = { "Name", "Surname"};
    Object[][] data = {
        { "Record 1", "test 1", "111111" },
        { "Record 2", "test 2", "222222" },
        { "Record 3", "test 3", "333333" },
        { "Record 4", "test 4", "444444" },
        { "Record 5", "test 5", "555555" },
        { "Record 6", "test 6", "666666" },
        { "Record 7", "test 7", "777777" },
        { "Record 8", "test 8", "888888" },
    };
    private JTable table = new JTable(data, headers);    
	
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
		frame.setBounds(100, 100, 634, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		btnNewButton.setBounds(24, 464, 117, 25);
		frame.getContentPane().add(btnNewButton);		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editorPane.setText("TEST 3333");
				
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
				// Button press handler
				System.out.println("Button pressed");
				editorPane.setText("TEST 2222");
				
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
				    
				    //table.add(data);
				    
				    while(rs.next()){		       
				         int id  = rs.getInt("id");
				         String name = rs.getString("name");  
				         System.out.print("ID: " + id);		        
				         System.out.println("Name" + name);
				         editorPane.setText("ID: " + id + "," + "Name" + name);	
				         //String[] row = {rs.getString("id"),rs.getString("name")};
				         
				         				         
				    }		      
				    rs.close();
				    statment.close();
				    con.close();
				      
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
				
				
								
			}
		});		
		
	}
	
}

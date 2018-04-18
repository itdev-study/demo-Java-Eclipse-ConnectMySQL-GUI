//Batch for demo DB connection in GUI basic - v Alpha 1.7
package demo.Java.Eclipse.ConnectMySQL.GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MainForm {
	private JFrame frame = new JFrame();	
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

	public MainForm() { // All logic in the constructor
		model.addColumn("Z1");
		model.addColumn("Z2");	
		frame.setBounds(100, 100, 634, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		table.setBounds(24, 12, 583, 303);
		frame.getContentPane().add(table);			
		
		System.out.println("Test Connection to MySQL started");
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
			String sql;
		    sql = "SELECT id, name FROM items";
		    ResultSet rs = statment.executeQuery(sql);		    
		    while(rs.next()){		       
		         int id  = rs.getInt("id");
		         String name = rs.getString("name");  
		         System.out.print("ID: " + id + "; Name:" + name + "\n");		         	
		         model.addRow(new Object[] {id,name});				         			         				         
		    }		      
		    rs.close();
		    statment.close();
		    con.close();		      
		} catch (SQLException e) {					
			e.printStackTrace();
		}
	}	
}

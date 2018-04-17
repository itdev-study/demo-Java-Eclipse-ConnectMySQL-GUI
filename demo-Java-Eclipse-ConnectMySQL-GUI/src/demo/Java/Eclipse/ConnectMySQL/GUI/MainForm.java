//Batch for demo DB connection in GUI - v Alpha 1.2
package demo.Java.Eclipse.ConnectMySQL.GUI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class MainForm {
	private JFrame frame = new JFrame();
	private JDialog alert = new JDialog();	
	
	JButton btnShowGridDataButton = new JButton("Show data");
	JButton btnDeleteRowButton = new JButton("Delete row");
	JEditorPane editorPane = new JEditorPane();	
	JButton btnClearGridButton = new JButton("Clear");
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
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		System.out.println("Connection to DB created");
		
		model.addColumn("Z1");
		model.addColumn("Z2");
		alert.setBounds(200, 100, 300, 100);
		frame.setBounds(100, 100, 634, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		btnShowGridDataButton.setBounds(34, 335, 117, 25);
		frame.getContentPane().add(btnShowGridDataButton);		
		btnClearGridButton.addActionListener(new ActionListener() {
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
		
		btnClearGridButton.setBounds(163, 335, 117, 25);
		frame.getContentPane().add(btnClearGridButton);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				alert.setTitle("Mouse clicked");
				alert.setVisible(true);				
			}
		});
		
		table.setBounds(24, 12, 583, 303);
		frame.getContentPane().add(table);		
		
		editorPane.setBounds(24, 389, 583, 100);
		frame.getContentPane().add(editorPane);		
		
		btnDeleteRowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				//Delete selected record				
				Statement statment;
				try {
					statment = con.createStatement();										
					String insertSQL = "delete from items where id = 1";			
					statment.executeUpdate(insertSQL);
					statment.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				alert.setTitle("Record deleted");
				alert.setVisible(true);
				System.out.println("Record deleted");
			}
		});
		btnDeleteRowButton.setBounds(288, 335, 117, 25);
		frame.getContentPane().add(btnDeleteRowButton);
		
		JButton btnInsertRowButton = new JButton("Insert");
		btnInsertRowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Insert record
				try {
					Statement statment = con.createStatement();
					System.out.println("Statement created");					
					String insertSQL = "insert into items values (1, 'TEST-555')";			
					statment.executeUpdate(insertSQL);
					statment.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				alert.setTitle("Record inserted");
				alert.setVisible(true);
					
			}
		});
		btnInsertRowButton.setBounds(417, 335, 117, 25);
		frame.getContentPane().add(btnInsertRowButton);
		
		btnShowGridDataButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {	
				try {					
					Statement statment = con.createStatement();
					System.out.println("Statement created");
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
				    //con.close();
				      
				} catch (SQLException e) {					
					e.printStackTrace();
				}								
			}
		});		
		
	}
}

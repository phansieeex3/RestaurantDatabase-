package service;
/**
 * @author Phansa Chaonpoj
 * @author Kevin Nguyen 
 * @author Ankur Suri --skeleton code for connection. 
 * 
 *  @version 1.0 
 *  SqlConnection.java - is the utility class for setting up the 
 *  database connection.
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import
java.sql.*;
import
java.util.*;
import
javax.swing.*;
import
javax.swing.table.DefaultTableModel;
import
javax.swing.table.TableColumn;

public class SqlConnection {
	
    public static Connection conn;
	
	
	public static JTable sendMeQuery(String q)
	{
		Vector columnNames	= new Vector();
		Vector data = new Vector();
		JPanel panel = new JPanel();   
		Properties connectionProps = new Properties();
		connectionProps.put("user", "kevintn");
		connectionProps.put("password", "witGars");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://cssgate.insttech.washington.edu/", connectionProps);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(metaData.getColumnName(i));
            }
            
            while (resultSet.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                data.addElement(row);
            }
           resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        JTable table = new JTable(data, columnNames);
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
       // JScrollPane scrollPane = new JScrollPane(table);        
        //panel.add(scrollPane);               
        //JFrame frame = new JFrame();
        //frame.add(panel);         //adding panel to the frame
        //frame.setSize(600, 400); //setting frame size
        //frame.setVisible(true);  //setting visibility true
        
        return table;
	}
	
	public static void updateTable(String q){
		
		
		Vector columnNames	= new Vector();
		Vector data = new Vector();
		JPanel panel = new JPanel();   
		Properties connectionProps = new Properties();
		connectionProps.put("user", "kevintn");
		connectionProps.put("password", "witGars");
		try {
			conn = DriverManager.getConnection("jdbc:mysql://cssgate.insttech.washington.edu/kevintn", connectionProps);
            Statement statement = conn.createStatement();
            statement.executeUpdate(q);
            
            conn.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}




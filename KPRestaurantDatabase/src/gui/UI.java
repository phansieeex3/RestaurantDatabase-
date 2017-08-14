package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import service.SqlConnection;

public class UI extends JFrame {

	/** An initial size for the frame. */
    private static final Dimension INITIAL_SIZE = new Dimension(800, 500);

    /** A minimum size for the frame. */
    private static final Dimension MIN_SIZE = new Dimension(150, 300);
    
    /** The default width for many components. */
    private static final int DEFAULT_WIDTH = 80;
    
    /** The default height for many components. */
    private static final int DEFAULT_HEIGHT = 60;
    
    /** The default space between components. */
    private static final int PADDING = 5;
    
    /**are we signed in? */
    private boolean signedin;
    
    /**dark blue */
    private Color darkBlue; 
    /**light blue */
    private Color lightBlue;
    
    /**current query being executed. */
    public String query;
    
    /** My center panel.*/
    public JPanel centerP; 
    
    
    // Instance fields
    
    /** The top level Window for this GUI. */
    private JFrame myFrame;

    /**
     * Initialize the instance fields.
     */
    public UI() {
        myFrame = new JFrame("Kevin & Phansa's Restaurant");
        signedin = false;
        darkBlue = new Color(98, 94, 216);
        lightBlue = new Color(165, 163, 242);
        query = null;
    }
    
    public static void main(String[] args){
    	UI frame = new UI();
    	frame.start();
    }
    
    /**
     * Sets up and displays the GUI.
     */
    public void start() {
        
        centerP = new JPanel();
        centerP.setBackground(lightBlue);
        setupEast();
        
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(INITIAL_SIZE);
        myFrame.setMinimumSize(MIN_SIZE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }

    /**
     * Sets up the JPanel for the EAST region.
     */
    private void setupEast() {
        
        final JPanel eastPanel = new JPanel();
        final BoxLayout b = new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS);
        eastPanel.setLayout(b);
        eastPanel.setBackground(Color.BLACK);

        
        final JPanel innerPanel3 = new JPanel();
        innerPanel3.setBackground(darkBlue);
        innerPanel3.add(new JButton("Past Order"));
        
        final JPanel innerPanel4 = new JPanel();
        innerPanel4.setBackground(darkBlue);
        innerPanel4.add(new JButton("Payment"));
        
        //setting up sign in button.
        final JPanel innerPanel1 = new JPanel();
        innerPanel1.setBackground(darkBlue);
        JButton signInOut = new JButton();
        signInOut.setText("Sign In");
        	

        
        
        signInOut.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  	System.out.println("button clicked");
        		  	System.out.println(signedin);
        		  	if(signedin)
        		  	{
        		  		signedin = false;
        		  		myFrame = new UI();
        		  		signInOut.setText("Sign In");
        		  	 	eastPanel.repaint();
        		  	}
        		  	else
        		  	{
        		  		signedin = true;
        	        	signInOut.setText("Sign Out");
        		  	 	eastPanel.repaint();
        		  		
        		  	}
        		  } 
        		} );       
        
        
        innerPanel1.add(signInOut);
        
        
        final JPanel innerPanel2 = new JPanel();
        innerPanel2.setBackground(darkBlue);
        JButton menu = new JButton("Menu");
        menu.addActionListener(new ActionListenerMenu());
        innerPanel2.add(menu);
        
        
       
        
        eastPanel.add(innerPanel1);
        
        /* Methods of the Box class can be used to create several types
         * of invisible components in a GUI that can be used as 'spacers'.
         * One type of invisible component is a 'strut' which can be created
         * with a horizontal or vertical orientation.
         */
        eastPanel.add(Box.createVerticalStrut(PADDING));
        
        eastPanel.add(innerPanel2);
        
        eastPanel.add(Box.createVerticalStrut(PADDING));
        eastPanel.add(innerPanel3);
        eastPanel.add(Box.createVerticalStrut(PADDING));
        eastPanel.add(innerPanel4);
        eastPanel.add(Box.createVerticalStrut(PADDING));
        myFrame.add(eastPanel, BorderLayout.EAST);

        
        myFrame.add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Sets up the JPanel for displaying information. 
     */
    private void setupCenter(JTable table) {
        
        JScrollPane scrollPane = new JScrollPane(table);
        centerP.add(scrollPane);
        
        /*
        if(signedin) //display my information.....
        {
        	
        }
        else {
        	
        }
        */
        centerP.repaint();
        
        myFrame.add(centerP, BorderLayout.CENTER);
    }

    private class ActionListenerMenu implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//write query for this 
			//query = "Select * from kevintn.FoodMenu";
			query = "select * from kevintn.Customers";
			//invoke method to call this query from database.
			JTable table = SqlConnection.sendMeQuery(query);
			setupCenter(table);
			centerP.repaint();
			
		}
    	
    }
    
    private class ActionListenerSignIn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//write query for this 
			query = "Select * from FoodMenu";
			//invoke method to call this query from database.
			//setUpCenter(); recall set up center. 
			
		}
    	
    }

}
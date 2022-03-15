import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.* ;

public class admin {
	
	public void admin() {
		//setting up how JFrame will be set up
		JFrame f = new JFrame("Admin Manager") ; 
		f.setVisible(true);
		f.getContentPane().setBackground(Color.LIGHT_GRAY) ; 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		f.setSize(450, 350) ;
		f.setLayout(new GridLayout(3,2)) ; 
		f.setLocationRelativeTo(null); // makes the application start in the middle of the screen
//		JPanel panel = new JPanel() ; 
//		f.add(panel) ;
		// creates the panel the buttons will sit o
		// This is the button layout and what order they will be in. b1 will be first and b5 will be last
		JButton b1 = new JButton("Student") ; // will pull up the student gui 
		f.add(b1) ;
		b1.addActionListener(new Button1()) ;
		
		JButton b2 = new JButton("TA") ; //will pull up the TA gui
		f.add(b2) ;;
		b2.addActionListener(new Button2()) ;
		
		JButton b3 = new JButton("Staff") ; //will pull up the Staff Gui
		f.add(b3) ;
		b3.addActionListener(new Button3()) ;
		
		JButton b4 = new JButton("Profeesor") ; //Will pull up the professor gui
		f.add(b4) ;
		b4.addActionListener(new Button4()) ; 
		
		JButton b5 = new JButton("Administrator") ; //will pull up the admin gui
		f.add(b5) ;
		b5.addActionListener(new Button5());
		
		
		JButton b6 = new JButton("Exit") ; //will exit
		f.add(b6) ; 
		b6.addActionListener(new exit()) ; 
		 
	}
	
}
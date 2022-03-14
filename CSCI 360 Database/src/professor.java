import java.io.* ; 
import java.awt.event.* ;  
import javax.sound.sampled.* ;
import javax.swing.* ;
import java.util.* ;

//initial code from final project from another class
//hi
public class professor implements ActionListener{
	private static JLabel Userlabel;
	private static JLabel intro ; 
	private static JLabel message ; 
	public static JTextField userText;
	public static JButton LogInbutton;
	private static JLabel LogTrue;
	public static JButton Exit ; 
	
	static int LogSuccess;
	
	public void prof() {
		JFrame frame = new JFrame("TA") ; 
		JPanel panel = new JPanel();
    	frame.setSize(600,600);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(panel);
    	panel.setLayout(null);
    	
    	
    	message = new JLabel("This is the GUI for the Basic Professor Layout") ; 
    	message.setBounds(100,10,600,10) ; 
    	panel.add(message) ;
    	
    	Exit = new JButton("Exit") ; 
    	Exit.setBounds(300,70,100,25);
    	Exit.addActionListener(new exit());
    	panel.add(Exit) ; 
    	
    	LogTrue = new JLabel("");
    	LogTrue.setBounds(10,110,300,25);
    	panel.add(LogTrue);
    	frame.setVisible(true);
    	
    	
//    	JFrame frame = new JFrame("startup") ; 
//		JPanel panel = new JPanel();
//    	frame.setSize(550, 200);
//    	frame.setLocationRelativeTo(null);
//    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	frame.add(panel);
//    	panel.setLayout(null);
//    	
//    	intro = new JLabel("THIS SOFTWARE IS NOT USED TO BE USED FOR UNIVERSITY MANAGEMENT PURPOSE") ; 
//    	intro.setBounds(10,10,600,10) ; 
//    	panel.add(intro) ;
//    	
//    	message = new JLabel("Please enter your ID") ; 
//    	message.setBounds(100,70,600,10) ; 
//    	panel.add(message) ;
//    	
//    	Userlabel = new JLabel("ID: ");
//    	Userlabel.setBounds(70, 40, 80, 25); 
//    	panel.add(Userlabel);
//    	
//    	
//    	userText = new JTextField(20);
//    	userText.setBounds(100, 40, 165, 25);
//    	panel.add(userText) ; 
//    	
//        LogInbutton = new JButton("Log In");
//    	LogInbutton.setBounds(300, 40, 100, 25);
//    	LogInbutton.addActionListener(new startup());
//    	panel.add(LogInbutton);
//    	
//    	Exit = new JButton("Exit") ; 
//    	Exit.setBounds(300,70,100,25);
//    	Exit.addActionListener(new exit());
//    	panel.add(Exit) ; 
//    	
//    	LogTrue = new JLabel("");
//    	LogTrue.setBounds(10,110,300,25);
//    	panel.add(LogTrue);
//    	frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
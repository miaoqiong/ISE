package entity;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JframeEditQuestion {
	
	public static void main(String[] args){		

		JframeEditQuestion edit = new JframeEditQuestion();
		edit.loadPanel();
       
	}
	public void loadPanel(){
		 // create a frame
		JFrame frame = new JFrame("Edit Question");
		frame.setLocation(400, 300);
		frame.setPreferredSize(new Dimension(500,120));
		Image icon = new javax.swing.ImageIcon("./WebContent/images/edit.png").getImage();
		frame.setIconImage(icon);
		frame.setResizable(false);
		
		//What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	     
		
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Edit question");
		panel.add(title);
			 
	
		
		String s = "change to submitted question ";
        JTextArea testField = new JTextArea();
        testField.setText(s);

        
		JButton submitBtn=new JButton("Submit");

	
	
		frame.getContentPane().add(panel, BorderLayout.BEFORE_FIRST_LINE);	
		frame.getContentPane().add(testField,BorderLayout.CENTER);
		frame.getContentPane().add(submitBtn,BorderLayout.AFTER_LAST_LINE);
		
		//4. Size the frame.
		frame.pack();

		//5. Show it.
		frame.setVisible(true);
		
		
		submitBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            // do something
	        	String test= testField.getText();
	        	System.out.println(test);
	        	frame.setVisible(false);
	         }          
	      });
	}


}

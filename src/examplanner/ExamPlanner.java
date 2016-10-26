package examplanner;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ExamPlanner {
	
	//TODO:
	// MySQL database for all the courses from schools n shiz
	// Swing gui
	// two character name range support
	
	protected static String[] SCHOOLS = {"University of Toronto St. George", "University of Toronto Scarborough", "University of Toronto Mississauga", "Queen's University", "University of Waterloo"};
	
	public static JFrame buildWindow() {
		
		try {//setting swing look and feel
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if ("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}catch (Exception e){
			System.out.println("Nimbus not found, using default UI");
		}
		
		JFrame examFrame = new JFrame("UofT STG Exam Planner");
		examFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		try{
			Image image = new ImageIcon("/img/logo.png/").getImage();
			examFrame.setIconImage(image);
		}catch(Exception e2){
			System.out.println("ERROR: Icon not found");
		}
		
		JLabel examLabel = new JLabel("Enter Course CODE and LEC section (if required):");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		examFrame.add(examLabel, c);
		JLabel nameLabel = new JLabel("Enter your LAST name:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		examFrame.add(nameLabel, c);
		JLabel schoolLabel = new JLabel("Select your School:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		examFrame.add(schoolLabel, c);
		JLabel exLabel = new JLabel("Your exams are:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		examFrame.add(exLabel, c);
		JTextArea nameArea = new JTextArea(1,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		examFrame.add(nameArea, c);
		JComboBox schoolSelect = new JComboBox(SCHOOLS);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		examFrame.add(schoolSelect, c);
		JTextArea textArea = new JTextArea(6,10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		examFrame.add(textArea, c);
		JTextArea outArea = new JTextArea(6,30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		examFrame.add(outArea, c);

		//greasy logo here
		ImageIcon image = new ImageIcon("img/image.png");
		Image img = image.getImage();
		Image newimg = img.getScaledInstance(200, 33, java.awt.Image.SCALE_SMOOTH);
		ImageIcon image2 = new ImageIcon(newimg);
		
		JLabel imageLabel = new JLabel(image2);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		examFrame.add(imageLabel,c);
		
		JButton submitButton = new JButton("Confirm Courses");
		submitButton.setVerticalTextPosition(AbstractButton.CENTER);
		submitButton.setHorizontalTextPosition(AbstractButton.LEADING);
		
		ActionListener buttonListener = new CourseDirectory(examFrame, nameArea, textArea, outArea, schoolSelect);
		submitButton.addActionListener(buttonListener);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		examFrame.add(submitButton, c);
		
		examFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		examFrame.pack();
		return examFrame;
		
	}
	public static void main(String args[]){
		ExamPlanner.buildWindow().setVisible(true);
	}
}

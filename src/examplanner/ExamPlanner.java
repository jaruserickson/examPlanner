package examplanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class ExamPlanner {
	
	//TODO:
	// MySQL database for all the courses from schools n shiz
	// FIX nameArea
	// UI/UX
	// buildCourseDirectory
	
	protected static String[] SCHOOLS = {"University of Toronto St. George", "University of Toronto Scarborough", "University of Toronto Mississauga", "Queen's", "Waterloo"};
	
	public static JFrame buildWindow() {
		JFrame examFrame = new JFrame("UofT STG Exam Planner");
		examFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
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
		JLabel courseLabel = new JLabel("Locate course_exams folder:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		examFrame.add(courseLabel, c);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JButton openButton = new JButton("Choose Directory");
		ActionListener openListener = new DirectoryFinder(examFrame, courseLabel, fileChooser);
		openButton.addActionListener(openListener);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		examFrame.add(openButton, c);
		
		JButton submitButton = new JButton("Confirm Courses");
		submitButton.setVerticalTextPosition(AbstractButton.CENTER);
		submitButton.setHorizontalTextPosition(AbstractButton.LEADING);
		
		ActionListener buttonListener = new CourseDirectory(examFrame, nameArea, textArea, outArea, schoolSelect);
		submitButton.addActionListener(buttonListener);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		examFrame.add(submitButton, c);

		examFrame.pack();
		return examFrame;
		
	}
	public static void main(String args[]){
		ExamPlanner.buildWindow().setVisible(true);
	}
}

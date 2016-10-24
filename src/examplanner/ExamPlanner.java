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
	
	public static JFrame buildWindow() {
		JFrame examFrame = new JFrame("UofT STG Exam Planner");
		
		JLabel examLabel = new JLabel("Enter your LAST Name, then Courses");
		JTextArea nameArea = new JTextArea("LAST_NAME",1,1);
		//dropdown chooser of schools here
		JTextArea textArea = new JTextArea("COURSE_CODES",6,10);
		JTextArea outArea = new JTextArea("EXAMS",6,30);
		
		JButton submitButton = new JButton("Confirm Courses");
		submitButton.setVerticalTextPosition(AbstractButton.CENTER);
		submitButton.setHorizontalTextPosition(AbstractButton.LEADING);
		
		ActionListener buttonListener = new CourseDirectory(examFrame, examLabel, textArea, outArea);
		submitButton.addActionListener(buttonListener);
		
		Container c = examFrame.getContentPane();
		c.add(examLabel,  BorderLayout.PAGE_START);
		c.add(textArea, BorderLayout.WEST);
		c.add(nameArea, BorderLayout.NORTH);
		c.add(submitButton,BorderLayout.CENTER);
		c.add(outArea, BorderLayout.EAST);
		
		examFrame.pack();
		return examFrame;
		
	}
	public static void main(String args[]){
		ExamPlanner.buildWindow().setVisible(true);
	}
}

package examplanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class CourseDirectory implements ActionListener{
	private JFrame exFrame;
	private JLabel exLabel;
	private static JTextArea textArea;
	private JTextArea outArea;
	
	public CourseDirectory(JFrame exFrame, JLabel exLabel, JTextArea textArea, JTextArea outArea){
		this.exFrame = exFrame;
		this.exLabel = exLabel;
		this.textArea = textArea;
		this.outArea = outArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuffer contents = new StringBuffer();
		try {
			buildExamContents(new FileReader("/Users/jacoberickson1/Desktop/examplanner/course_exams/utsg.txt"),contents);
		} catch (FileNotFoundException e1) {
			System.out.println("CONTENT FILE NOT FOUND");
		} catch (IOException e1) {
			System.out.println("IOEXCEPTION");
		}
		exLabel.setText("Your Exams are:");
		this.outArea.setText(contents.toString());
	}
	
	private static void buildExamContents(FileReader f, StringBuffer contents) throws IOException{
		//lets have different files with the different schools/campus' at a file/mySQL database
		String s = textArea.getText();
		BufferedReader courses = new BufferedReader(f);
		String l = courses.readLine();
		while(l != null){
			contents.append(l + "\n");
			l = courses.readLine();
		}
		courses.close();
		
	}
	
}
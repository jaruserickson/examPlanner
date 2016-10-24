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
	private static JTextArea textArea;
	private JTextArea outArea;
	private JComboBox schoolSelect;
	private JTextArea name;
	
	public CourseDirectory(JFrame exFrame, JTextArea name, JTextArea textArea, JTextArea outArea, JComboBox schoolSelect){
		this.exFrame = exFrame;
		this.textArea = textArea;
		this.outArea = outArea;
		this.schoolSelect = schoolSelect;
		this.name = name;
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
		this.outArea.setText(contents.toString());
	}
	
	private static void buildExamContents(FileReader f, StringBuffer contents) throws IOException{
		//lets have different files with the different schools/campus' at a file/mySQL database
		String s = textArea.getText();
		BufferedReader courses = new BufferedReader(f);
		String l = courses.readLine();
		while(l != null){
			if (s.toUpperCase().contains(l.substring(0, 6))){
				contents.append(l + "\n");
			}
			l = courses.readLine();
		}
		courses.close();
		
	}
	
}

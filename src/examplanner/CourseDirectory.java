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
	private static JTextArea name;
	
	private String [] line;
	
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
			
			String filePath = "course_exams/utsg.txt";
			String school = schoolSelect.getSelectedItem().toString();
			
			if (school.equals("University of Toronto St. George")){
				filePath = "course_exams/utsg.txt";
			}else if (school.equals("University of Toronto Scarborough")){
				filePath = "course_exams/utsc.txt";
			}else if (school.equals("University of Toronto Mississauga")){
				filePath = "course_exams/utm.txt";
			}else if (school.equals("Queen's University")){
				filePath = "course_exams/queens.txt";
			}else if (school.equals("University of Waterloo")){
				filePath = "coruse_exams/uw.txt";
			}

			try {
				buildExamContents(new FileReader(filePath),contents);
			} catch (FileNotFoundException e1) {
				System.out.println("CONTENT FILE NOT FOUND");
			} catch (IOException e1) {
				System.out.println("IOEXCEPTION");
			}
			
			this.outArea.setText(contents.toString());
	}
	
	private static void buildExamContents(FileReader f, StringBuffer contents) throws IOException{
		//lets have different files with the different schools/campus' at a file/mySQL database
		String entry = textArea.getText();
		String initials = "AAA";
		try{
		initials = name.getText().toUpperCase();
		}catch(StringIndexOutOfBoundsException e4){
			name.setText("MISSING NAME");
		}
		BufferedReader courses = new BufferedReader(f);
		String l = courses.readLine();
		
		while(l != null){
			String[] sp = l.split(", ");
			
			if (sp[1].contains(" - ")){ //if there is a name range
				String[] range = sp[1].split(" - ");
				if (entry.toUpperCase().contains(sp[0].substring(0, 6)) && namerange(range[0],initials,range[1])){
					contents.append(l + "\n");
				}
			}else if (!sp[1].contains(" - ")){
				if (entry.toUpperCase().contains(sp[0].substring(0, 6))){
					contents.append(l + "\n");
				}
			}
			
			l = courses.readLine();
		}
		
		courses.close();
		
	}
	
	/**
	 * Method to check if string b is in the range of strings a and c
	 * @param a first range string
	 * @param b name to check
	 * @param c second range string
	 * @return true if b is in range of a and c
	 */
	private static boolean namerange(String a, String b, String c){
		if (b.length() == 1){ //to catch when name length is 1
			b += "AAA";
		}
		
		for (int i = 0;i<a.length();i++){ //iterative bulk of code
			if (a.toCharArray()[i] < b.toCharArray()[i]){
				break;
			}else if(a.toCharArray()[i] == b.toCharArray()[i]){
				if (i == a.length()-1){//inclusive
					return true;
				}
			}else{
				return false;
			}
		}
		for (int i = 0;i<c.length();i++){
			if(c.toCharArray()[i] > b.toCharArray()[i]){
				return true;
			}else if(c.toCharArray()[i] == b.toCharArray()[i]){
				if (i == c.length()-1){//inclusive
					return true;
				}
			}else{
				return false;
			}
		}
		return false;
	}
	
}

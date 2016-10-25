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
		
			String filePath = DirectoryFinder.filePath;
			String school = schoolSelect.getSelectedItem().toString();
			
			if (school.equals("University of Toronto St. George")){
				filePath += "/utsg.txt";
			}else if (school.equals("University of Toronto Scarborough")){
				filePath += "/utsc.txt";
			}else if (school.equals("University of Toronto Mississauga")){
				filePath += "/utm.txt";
			}else if (school.equals("Queen's University")){
				filePath += "/queens.txt";
			}else if (school.equals("University of Waterloo")){
				filePath += "/uw.txt";
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
		char initial = 'a';
		try{
		initial = name.getText().toCharArray()[0];
		}catch(StringIndexOutOfBoundsException e4){
			name.setText("MISSING NAME");
		}
		BufferedReader courses = new BufferedReader(f);
		String l = courses.readLine();
		
		while(l != null){
			String[] sp = l.split(", ");
			
			if (sp[1].contains(" - ")){ //if there is a name range
				String[] range = sp[1].split(" - ");
				char r1 = range[0].toCharArray()[0]; //need to add support for dual character name range
				char r2 = range[1].toCharArray()[0];
				if (entry.toUpperCase().contains(sp[0].substring(0, 6)) && initial >= r1 && initial <= r2){
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
	
}

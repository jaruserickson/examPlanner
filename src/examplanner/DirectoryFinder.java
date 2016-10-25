package examplanner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DirectoryFinder implements ActionListener{

	private JFileChooser filechooser;
	private JFrame examFrame;
	static String filePath;
	private JLabel courseLabel;
	
	public DirectoryFinder(JFrame examFrame, JLabel courseLabel, JFileChooser filechooser){
		this.filechooser = filechooser;
		this.examFrame = examFrame;
		this.courseLabel = courseLabel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = filechooser.showOpenDialog(examFrame.getContentPane());
		if (i == JFileChooser.APPROVE_OPTION){
			filePath = filechooser.getSelectedFile().getAbsolutePath();
			courseLabel.setText("Location chosen.");
		}else{
			courseLabel.setText("No location chosen");
		}
	}
	
}

package main_application;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import reports1.report1;
import combined.*;

public class start_up {
public static Insets margin;
public static String timestamp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ts=new SimpleDateFormat("MM.dd.yyyy.hh.mm.ss").format(new Date());
		 timestamp=ts.replace(".","_");
		 
		JPanel label=null;
		JPanel gui=null;
		JFrame f=null;
		
		gui=new JPanel(new GridLayout(0,1,10,10));
		gui.setBorder(new EmptyBorder(20,30,20,30));
		String[] buttonlabels={
				"Convert Input XLSX's to CSV's",
				//"Insert single record in to DB",
				"Report 1"
		};
		
		 margin=new Insets(20,150,20,150);
		JButton b=null;
		for(String s:buttonlabels){
			b=new JButton(s);
			b.setMargin(margin);
			if(s.equals("Convert Input XLSX's to CSV's")){
				b.addActionListener(new Action1());
			}else if(s.equals("Report 1")){
				b.addActionListener(new Action2());
			}
			gui.add(b);
			
			}
		f=new  JFrame("Crusher Application");
		f.add(gui);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocationByPlatform(true);
		f.pack();f.setMinimumSize(f.getSize());
		f.setVisible(true);
	}


	static class Action1 implements ActionListener{
		
	public void actionPerformed(ActionEvent e){
		
			try {
				convert_xlsx_csv x=new convert_xlsx_csv();
				x.change();
				JOptionPane.showMessageDialog(null, "XLSX's to CSV's conversion done successfully");
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "XLSX's to CSV's conversion was not done.");
			}
	
	}

   }
	
	static class Action2 implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
				try {
					report1 r=new report1();
					r.report(timestamp);
					JOptionPane.showMessageDialog(null, "Report 1 successfully generated");
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Report 1 was not generated");
				}
		
		}

	   }
	
	
/*static class Action3 implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
				try {
					generate_report_1.report1();
					JOptionPane.showMessageDialog(null, "Reported Open Successfully");
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Reported Not Open.");
				}
		
		}

	   }*/
	
	
}
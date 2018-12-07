package main_application;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import report2.report2;
import report3.report3;
import reports1.report1;
import reports4.report4;
import combined.*;

public class start_up {
public static Insets margin;
public static String timestamp;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("D:\\janardhan\\workspace\\reports\\logs.txt")), true));
		String ts=new SimpleDateFormat("MM.dd.yyyy.hh.mm.ss").format(new Date());
		 timestamp=ts.replace(".","_");
		 
		JPanel label=null;
		JPanel gui=null;
		JFrame f=null;
		
		gui=new JPanel(new GridLayout(0,1,10,10));
		gui.setBorder(new EmptyBorder(20,30,20,30));
		String[] buttonlabels={
			//	"Convert Input XLSX's to CSV's",
				//"Insert single record in to DB",
				"Creation of Template",
				"Report 1[KPI Daily PV]",
				"Report 2[KPI Daily Lead Count]",
				"Report 3[KPI Daily PV Sales Count]",
				"Report 4[Lead Distributors List]",
				"Update New Distributors List[1,2,3]"
		};
		
		 margin=new Insets(20,150,20,150);
		JButton b=null;
		for(String s:buttonlabels){
			b=new JButton(s);
			b.setMargin(margin);
			if(s.equals("Convert Input XLSX's to CSV's")){
				b.addActionListener(new Action1());
			}else if(s.equals("Creation of Template")){
				b.addActionListener(new Action0());
			}else if(s.equals("Report 1[KPI Daily PV]")){
				b.addActionListener(new Action2());
			}else if(s.equals("Report 2[KPI Daily Lead Count]")){
				b.addActionListener(new Action3());
			}else if(s.equals("Report 3[KPI Daily PV Sales Count]")){
				b.addActionListener(new Action4());
			}else if(s.equals("Report 4[Lead Distributors List]")){
				b.addActionListener(new Action5());
			}else if(s.equals("Update New Distributors List[1,2,3]")){
				b.addActionListener(new Action6());
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

	
	
	static class Action0 implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
				try {
					creation_of_template x=new creation_of_template();
					x.generate();
					//JOptionPane.showMessageDialog(null, "Template creation done successfully");
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Template creation was not done.");
				}
		
		}

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
					r.report();
				String	source=System.getProperty("user.dir")+"\\report1_reports\\report1_KPI_Daily_PV.csv";
				String	destination=System.getProperty("user.dir")+"\\report1_reports\\FilterData_report1_KPI_Daily_PV.csv";
					clean_up.clear(source, destination);
					//JOptionPane.showMessageDialog(null, "Report 1 successfully generated");
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Report 1 was not generated");
				}
		
		}

	   }
	
	
static class Action3 implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
				try {
					report2 t=new report2();
					t.report();
					String	source=System.getProperty("user.dir")+"\\report2_reports\\report2_KPI_Daily_Leads.csv";
					String	destination=System.getProperty("user.dir")+"\\report2_reports\\FilterData_report2_KPI_Daily_Leads.csv";
						clean_up.clear(source, destination);
					
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Report 2 was not generated");
				}
		
		}

	   }
	
static class Action4 implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		
			try {
				report3 t=new report3();
				t.report();
				String	source=System.getProperty("user.dir")+"\\report3_reports\\report3_KPI_Daily_PV_Order_Numbers.csv";
				String	destination=System.getProperty("user.dir")+"\\report3_reports\\FilterData_report3_KPI_Daily_PV_Order_Numbers.csv";
					clean_up.clear(source, destination);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "Report 3 was not generated");
			}
	
	}

   }
//System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"

static class Action5 implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		
		try {
			report4 t=new report4();
			t.report();
		
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(e1.getMessage());
			JOptionPane.showMessageDialog(null, "Report 4 was not generated");
		}
	
	}

   }

static class Action6 implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		
			try {
				Update_Distributor_List.update(System.getProperty("user.dir")+"\\report1_reports\\report1_KPI_Daily_PV.csv");
				Update_Distributor_List.update(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI_Daily_Leads.csv");
				Update_Distributor_List.update(System.getProperty("user.dir")+"\\report3_reports\\report3_KPI_Daily_PV_Order_Numbers.csv");
				JOptionPane.showMessageDialog(null, "Distributors List is updated in Report 1,2,3");	
				
				
				//report3 t=new report3();
				//t.report();
				
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(null, "Report 2 was not generated");
			}
	
	}

   }
	
}
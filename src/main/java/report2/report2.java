package report2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVReader;

public class report2 {
	public static String Timestamp;
	//public static void main(String args[]) throws Throwable{
		public static void report(String Timestam) throws Throwable{
		
			Timestamp=Timestam;
			File folder = new File(System.getProperty("user.dir")+"\\report2");
			File[] listOfFiles = folder.listFiles();
			for (File fil : listOfFiles) {
			    if (fil.isFile()) {
			    	
			    	if(fil.getName().indexOf(Timestamp)>-1){
			
		 String leadname = null,distributor,date = null,total = "Lead Name,Date,Distributor Name\n";
		 try { 
			  
		        // Create an object of filereader 
		        // class with CSV file as a parameter. 
			 Path file=Paths.get(System.getProperty("user.dir")+"\\report2\\report2_Lead_Origination"+"_"+Timestamp+".csv");
		        FileReader filereader = new FileReader(file.toString()); 
		  
		        // create csvReader object passing 
		        // file reader as a parameter 
		        CSVReader csvReader = new CSVReader(filereader); 
		        String[] nextRecord; 
		  
		        // we are going to read data line by line 
		        int i=0;int j=0;boolean f=false;boolean t=false;
		       
		        while ((nextRecord = csvReader.readNext()) != null) { 
		        	i=0;
		            for (String cell : nextRecord) { 
		            	
		            		if(i==0 && cell.isEmpty()==false){
		            			
		            			if(cell.equalsIgnoreCase("Message3-dots-h")){
		            				f=true;
		            				t=true;
		            			}else if(f==true && j==1){
		            				//lead name
		            				 // System.out.println("lead name"+i+"---"+cell + "\t"); 
		            				  leadname=cell;
		            			}else if(f==true && j<6){
		            				//Distributor
		            				//  System.out.println("Distributor name"+i+"---"+cell + "\t");
		            				if(cell.indexOf("Added by")>-1 && cell.indexOf(" on ")>-1)  {
		            				  distributor=cell;
		            				  String[] p=distributor.split(" by ");
		            				  String[] q=p[1].split(" on ");
		            				  
		            				  total+=leadname+","+date+","+q[0]+","+"\n";
		            				}else if(cell.indexOf("Added by")>-1 && cell.indexOf(" Yesterday")>-1 ){
		            					 distributor=cell;
			            				  String[] p=distributor.split(" by ");
			            				  String[] q=p[1].split(" Yesterday");
			            				  
			            				  total+=leadname+","+date+","+q[0]+","+"\n";
		            					
		            				}
		            			}
		            		}else if(i==0 && cell.isEmpty()==true){
		            			f=false;
		            			j=-1;
		            			break;
		            		}else if((i==1 && cell.isEmpty()==false) && t==true){
		            			//Date
		            			//date=cell;
		            			String dateStr = cell;
		            			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		            			Date dat = (Date)formatter.parse(dateStr);
		            			//System.out.println(date);        

		            			Calendar cal = Calendar.getInstance();
		            			cal.setTime(dat);
		            			 //date = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
		            			 date = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR);
			            			
		            			// System.out.println("formatedDate : " + date); 
		            			//System.out.println("Date"+i+"---"+cell + "\t"); 
		            			i=-1;
		            			t=false;
		            			break;
		            		}else if(i>1 && cell.isEmpty()==true){
		            			t=false;
		            			break;
		            		}
		                //System.out.print("Value"+i+"---"+cell + "\t"); 
		               i=i+1;
		            } 
		            j=j+1;
		            //System.out.println("----------------------------"); 
		        } 
		    } 
		    catch (Exception e) { 
		        e.printStackTrace(); 
		    } 
		
		
		//System.out.println(total);
		File outputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_Lead_Origination"+"_"+Timestamp+".csv");
		 FileOutputStream fos = new FileOutputStream(outputFile);
		 fos.write(total.toString().getBytes());
         fos.close();
         JOptionPane.showMessageDialog(null, "Report2 Lead Origination Reported Generated.File location is "+System.getProperty("user.dir")+"\\report2_reports\\report2_Lead_Origination"+"_"+Timestamp+".csv");
	}
			    }
			}
		}
}

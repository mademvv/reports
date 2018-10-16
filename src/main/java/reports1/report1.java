package reports1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class report1 {
	public static boolean ASC = true;
    public static boolean DESC = false;
    public static  Map<String, Integer> unsortMap;
    public static Reader r;
    public static CSVParser csvParser;

public static String[][] dump;
public static int array;
public static int j;
public static List<String> enrollment_id;
public static Hashtable name_hashtable;
public static String timestamp;
public static DesiredCapabilities cp;
	//public static void main(String[] args) throws Throwable {
		public static void report(String timestam) throws Throwable {
		
			
			
			cp=DesiredCapabilities.internetExplorer();
			cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//drivers//IEDriverServer.exe");
				
			
			
			
			timestamp=timestam;
			
			File folder = new File(System.getProperty("user.dir")+"\\report1");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			    	
			    	if(file.getName().indexOf(timestamp)>-1){
			    
		 
		//generate timestamp
		
		
		//System.out.println(Paths.get("D:\\janardhan\\workspace\\reports\\report1\\report1_data.csv"));
		
		 enrollment_id = new ArrayList<>();
		
		//BufferedReader r = new BufferedReader(new FileReader("D:\\janardhan\\workspace\\reports\\report1\\data.csv"));
		try(
				Reader r=Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+"\\report1\\report1_data"+"_"+timestamp+".csv"));
				CSVParser csvParser=new CSVParser(r,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		
				){
				 array=0;
			for(CSVRecord csvRecord:csvParser){
				String val=csvRecord.get("Member Status");
				if(val.equalsIgnoreCase("distributor")){
					array=array+1;
				}
			}
		}
		
		try(
				Reader	 r=Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+"\\report1\\report1_data"+"_"+timestamp+".csv"));
				CSVParser csvParser=new CSVParser(r,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
				
						){
			 dump=new String[array][3];
			 j=0;
		for(CSVRecord csvRecord:csvParser){
			//System.out.println("CS.get(Enroller ID)"+csvRecord.get("Enroller ID"));
			//System.out.println("hi");
			//enrollement_number[i] =csvRecord.get("Enroller ID");
			//enrollment_id.add(csvRecord.get("Enroller ID"));
			String val=csvRecord.get("Member Status");
			if(val.equalsIgnoreCase("distributor")){
				dump[j][0]=csvRecord.get("Enroller ID");
				dump[j][1]=csvRecord.get("Native Name");
				dump[j][2]=csvRecord.get("PV");
				j=j+1;
			}			
		}
		
		int sumpv=0;
		String name="";
		String enrol2="";
		 Hashtable pv_hashtable = new Hashtable();
		  name_hashtable = new Hashtable();
		  unsortMap = new HashMap<String, Integer>();
		 
		  for(int f=0;dump.length>f;f++){
			  enrollment_id.add(dump[f][0]);
		  }
		  Set<String> s = new LinkedHashSet<>(enrollment_id);
		  
		  for (String sr : s) {
			   // System.out.println(sr);
			    sumpv=0;
			    for(int f=0;dump.length>f;f++){
			    	String enrol1=dump[f][0];
			    	if(sr.equalsIgnoreCase(enrol1)){
			    		sumpv+=Integer.parseInt(dump[f][2].substring(0, dump[f][2].length()-2));
						name=dump[f][1];
			    		
			    	}
			    }
			    unsortMap.put(sr, sumpv);
				name_hashtable.put(sr, name);
			}
		  
		
		
	//	System.out.println("Before sorting......");
      //  printMap(unsortMap);
		
	}
		/*System.out.println("After sorting ascending order......");
        Map<String, Integer> sortedMapAsc = sortByComparator(unsortMap, ASC);
        printMap(sortedMapAsc);*/


       // System.out.println("After sorting descindeng order......");
        Map<String, Integer> sortedMapDesc = sortByComparator(unsortMap, DESC);
       // printMap(sortedMapDesc);
        
        create_page(sortedMapDesc);
			    	}
			    }
			}
             
	}

	public static void printMap(Map<String, Integer> map)
    {
        for (Entry<String, Integer> entry : map.entrySet())
        {
           // System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
    }

	public static void create_page(Map<String, Integer> map)
    {
		
		 String header="<html><script src='https://code.jquery.com/jquery-1.9.1.min.js'></script>"
         		+"<script type='text/javascript'>"
         		+"$(document).ready(function(){)"
         		+ "$('#table_id td.y_n').each(function(){"
         		+"var mystr=$(this).text.toString().toLowercase();"
         		+"if(mystr.trim()=='failed'){"
         		+"$(this).css('background-color','#f00');"
         		+"} else if(mystr.trim()=='success'){"
         		+"$(this).css('background-color','green');}});});"
         		+"</script>"
         +"<style>body{background-color:lightyellow;}h1 {color:green;}p{color:blue;}"
         +"IMG {float: right;max-width:500px;margin-right:110px;padding:1em;}"
         +"nav{margin-left:120px;padding:1em;overflow:hidden;}"
         +"table{table-layout:fixed;}"
         +"td{word-wrap:break-word;}"
         +"div#fixedheader {position:fixed;top:0px;left:0px;width:100%;color:#CCC;background:#333;padding:10px;}"
         +"div#fixedfooter {position:fixed;bottom:0px;left:0px;width:100%;color:#CCC;background:#333;padding:20px;}"
         +"</style><br/><br/><BODY>"
         +"<div id=\"fixedheader\"><center><h3>Report</h3></center></div>"
         
         +"</br>"
         +"<br>";
		 
		  StringBuilder htmlcontent4=new StringBuilder();
          
          htmlcontent4.append("<nav><left>"
          		+"<TABLE id='table_id' height=10 width='100%' borderColorLight=#008080 border=2>&nbsp"
          		+ "<TD vAlign=middle align=middle width='5%'bgColor=#e1e1e1 height=24>"
          				+"<FONT face=Verdana color=#000000 size=3><b>Details</b></FONT>"
          						+ "</TD>"
          		+"</TR>"
          		+"<TR><TD align=left width='0%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+"Details"+"</b></FONT></TD></TR>"
          		+"<TR><TD align=left width='0%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+"Desending Order of 'Distributors' List by suming of 'PV' values according to 'Enroller ID'"+"</b></FONT></TD></TR>"
          		);
          
          StringBuilder htmlcontent1=new StringBuilder();
          // htmlcontent1.append("<nav></br><center>"
           htmlcontent1.append("<nav></br><left>"
           		+ "<TABLE height=10 width='100%' borderColorLight=#008080 border=2>&nbsp"
        		   +"<TD vAlign=middle align=middle width='5%' bgColor=#e1e1e1 height=24>"
        		   +"<FONT face=Verdana color=#000000 size=3><b>S.No</B></FONT>"
        		   +"</TD>"
        		   +"<TD vAlign=middle align=middle width='5%' bgColor=#e1e1e1 height=24>"
        		   +"<FONT face=Verdana color=#000000 size=3><b>Distributor Name</B></FONT>"
        		   +"</TD>"
        		   +"<TD vAlign=middle align=middle width='5%' bgColor=#e1e1e1 height=24>"
        		   +"<FONT face=Verdana color=#000000 size=3><b>Enroller ID</B></FONT>"
        		   +"</TD>"
        		   +"<TD vAlign=middle align=middle width='5%' bgColor=#e1e1e1 height=24>"
        		   +"<FONT face=Verdana color=#000000 size=3><b>PV</B></FONT>"
        		   +"</TD>");
          StringBuilder htmlcontent2=new StringBuilder();
          int count=1;
        for (Entry<String, Integer> entry : map.entrySet())
        {
           // System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
            htmlcontent2.append("<TR>"
	                  +"<TD align=center width='1%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+count+"</b></FONT></TD>"
	                  +"<TD align=center width='1%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+name_hashtable.get(entry.getKey())+"</b></FONT></TD>"
	                  +"<TD align=center width='1%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+entry.getKey().substring(0,entry.getKey().length()-2)+"</b></FONT></TD>"
	                   +"<TD align=center width='1%' bgColor=#ffffe1 height=19><FONT face=Verdana size=2><b>"+entry.getValue()+"</b></FONT></TD>"
	                  +"</tr>");
	                 // System.out.format("%s, %s, %s\n",  count, member_id, name);
	                  count=count+1;
	                  //System.out.format("%s, %s, %s\n",  s_no, member_id, name);
		               
	              //  }
	               
        }
        StringBuilder htmlcontent3=new StringBuilder();
        htmlcontent3.append( "</Table>"
               // +"</center></br></br>");
       +"</left></br></br></br></nav>");
        ByteArrayInputStream obj= new ByteArrayInputStream((header+htmlcontent4+htmlcontent1+htmlcontent2+htmlcontent3).toString().getBytes());
        
        
        try{
        	IOUtils.copy(obj, new FileOutputStream(System.getProperty("user.dir")+"//report1_reports//Report1_"+timestamp+".html"));
       
        WebDriver driver=new InternetExplorerDriver(cp);
        driver.get("file:///"+System.getProperty("user.dir")+"\\report1_reports\\Report1_"+timestamp+".html");
        driver.manage().window().maximize();
        
        JOptionPane.showMessageDialog(null, "Report 1 successfully generated.File location is "+System.getProperty("user.dir")+"\\report1_reports\\Report1_"+timestamp+".html");
        }catch(Exception w){
        	//System.out.println(w);
        	JOptionPane.showMessageDialog(null, w.getMessage());
        }
        
    }
	
	
	
	
	
	 private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
	    {

	        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

	        // Sorting the list based on values
	        Collections.sort(list, new Comparator<Entry<String, Integer>>()
	        {
	            public int compare(Entry<String, Integer> o1,
	                    Entry<String, Integer> o2)
	            {
	                if (order)
	                {
	                    return o1.getValue().compareTo(o2.getValue());
	                }
	                else
	                {
	                    return o2.getValue().compareTo(o1.getValue());

	                }
	            }
	        });

	        // Maintaining insertion order with the help of LinkedList
	        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
	        for (Entry<String, Integer> entry : list)
	        {
	            sortedMap.put(entry.getKey(), entry.getValue());
	        }

	        return sortedMap;
	    }

			    
}

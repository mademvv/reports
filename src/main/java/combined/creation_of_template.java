package combined;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class creation_of_template {
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
	public static String[][] dim_array;
	public static String[] strArray;
	public static CSVReader reader;
	public static List<String[]> csvBody;
	public static File inputFile;
	public static CSVWriter writer;
	//public static List<String> c;
	public static String goodresult="";
	public static String badresult="";
	public static List<String> found;
	public static List<String> unfound;
	public static void main(String[] args) throws Throwable {
		
		
		
		
		
		
		
		
		
inputFile = new File(System.getProperty("user.dir")+"\\combined\\Combined_lookup.csv");
		
		// Read existing file
		reader = new CSVReader(new FileReader(inputFile), ',');
		csvBody = reader.readAll();
		Map<String, String> dictionary = new HashMap<String, String>();
		for( int i=1; i<csvBody.size(); i++){
			strArray = csvBody.get(i);
			//System.out.println("i"+i);
			String id="";
			String name="";
			for( j=0; j<strArray.length; j++){
				if(j==0){
					//System.out.print(strArray[j].substring(0,strArray[j].length()-2));
					id=strArray[j].substring(0,strArray[j].length());
				}else if(j==1){
					//System.out.print(strArray[j]);
					//System.out.println("j"+j);
					String[] x=strArray[j].trim().split(",");
					if(x.length>1){
					String c=x[1].trim()+" "+x[0].trim();
					name=c;
					//System.out.println("@@@ccc"+c);
					}else{
						name=strArray[j].trim();
					}
				}
				dictionary.put(id, name);
			}
			//System.out.println();
		}

		System.out.println("dictionary.length"+dictionary.size());
		
		Map<String, String> map = sortByValues(dictionary); 
	      System.out.println("After Sorting:");
	      Set set2 = map.entrySet();
	      Iterator iterator2 = set2.iterator();
	      j=1;String dis=",";String num=",";
	      while(iterator2.hasNext()) {
	           Map.Entry me2 = (Map.Entry)iterator2.next();
	           System.out.print(me2.getKey() + ":");
	           System.out.println(me2.getValue());
	           csvBody.get(0)[j]=me2.getValue().toString();
	           
	           dis=dis+me2.getValue()+",";
	           num=num+me2.getKey()+",";
	           
	      }	
		dis=dis+"Daily Total";
		String outputFile = System.getProperty("user.dir")+"\\combined\\template.csv";
		
		 // specified by filepath 
	    File file = new File(outputFile); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	       // String[] header = { "Name", "Class", "Marks" }; 
	       // writer.writeNext(header); 
	  
	        // add data to csv 
	        String[] data1 = dis.split(","); 
	        writer.writeNext(data1); 
	        String[] data2 = num.split(","); 
	        writer.writeNext(data2);
	        
	        String[] data3 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data3[0]="1";
	        	}else{
	        		data3[y]="0";
	        	}
	        }
	        writer.writeNext(data3); 
	        
	        
	        String[] data4 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data4[0]="2";
	        	}else{
	        		data4[y]="0";
	        	}
	        }
	        writer.writeNext(data4);
	        
	        
	       /* String[] data4 = { "2"}; 
	        writer.writeNext(data4); */
	        String[] data5 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data5[0]="3";
	        	}else{
	        		data5[y]="0";
	        	}
	        }
	        writer.writeNext(data5);
	        
	        /*String[] data5 = { "3"};
	        writer.writeNext(data5); */
	        
	        String[] data6 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data6[0]="4";
	        	}else{
	        		data6[y]="0";
	        	}
	        }
	        writer.writeNext(data6);
	        
	        /*String[] data6 = { "4"}; 
	        writer.writeNext(data6);*/ 
	        
	        String[] data7 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data7[0]="5";
	        	}else{
	        		data7[y]="0";
	        	}
	        }
	        writer.writeNext(data7);
	        
	       /* String[] data7 = { "5"}; 
	        writer.writeNext(data7); */
	        
	        String[] data8 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data8[0]="Sub Total";
	        	}else{
	        		data8[y]="0";
	        	}
	        }
	        writer.writeNext(data8);
	        
	        /*String[] data8 = { "Sub Total"}; 
	        writer.writeNext(data8); */
	        
	        String[] data9 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data9[0]="6";
	        	}else{
	        		data9[y]="0";
	        	}
	        }
	        writer.writeNext(data9);
	        
	        /*String[] data9 = { "6"};
	        writer.writeNext(data9);*/ 
	        
	        String[] data10 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data10[0]="7";
	        	}else{
	        		data10[y]="0";
	        	}
	        }
	        writer.writeNext(data10);
	        
	       /* String[] data10 = { "7"}; 
	        writer.writeNext(data10); */
	        String[] data11 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data11[0]="8";
	        	}else{
	        		data11[y]="0";
	        	}
	        }
	        writer.writeNext(data11);
	        
	        /*String[] data11 = { "8"}; 
	        writer.writeNext(data11); */
	        String[] data12 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data12[0]="9";
	        	}else{
	        		data12[y]="0";
	        	}
	        }
	        writer.writeNext(data12);
	        
	        /*String[] data12 = { "9"}; 
	        writer.writeNext(data12);*/
	        
	        String[] data13 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data13[0]="10";
	        	}else{
	        		data13[y]="0";
	        	}
	        }
	        writer.writeNext(data13);
	        
	        /*String[] data13 = { "10"}; 
	        writer.writeNext(data13); */
	        String[] data14 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data14[0]="Sub Total";
	        	}else{
	        		data14[y]="0";
	        	}
	        }
	        writer.writeNext(data14);
	        
	       /* String[] data14 = { "Sub Total"}; 
	        writer.writeNext(data14)*/
	        String[] data15 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data15[0]="11";
	        	}else{
	        		data15[y]="0";
	        	}
	        }
	        writer.writeNext(data15);
	        
	       /* String[] data15 = { "11"}; 
	        writer.writeNext(data15); */
	        
	        String[] data16 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data16[0]="12";
	        	}else{
	        		data16[y]="0";
	        	}
	        }
	        writer.writeNext(data16);
	        
	        /*String[] data16 = { "12"}; 
	        writer.writeNext(data16);*/ 
	        
	        String[] data17 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data17[0]="13";
	        	}else{
	        		data17[y]="0";
	        	}
	        }
	        writer.writeNext(data17);
	        
	       /* String[] data17 = { "13"};
	        writer.writeNext(data17); */
	        String[] data18 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data18[0]="14";
	        	}else{
	        		data18[y]="0";
	        	}
	        }
	        writer.writeNext(data18);
	        
	        /*String[] data18 = { "14"};
	        writer.writeNext(data18);*/
	        
	        String[] data19 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data19[0]="15";
	        	}else{
	        		data19[y]="0";
	        	}
	        }
	        writer.writeNext(data19);
	        
	        /*String[] data19 = { "15"};
	        writer.writeNext(data19);*/
	        
	        String[] data20 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data20[0]="Sub Total";
	        	}else{
	        		data20[y]="0";
	        	}
	        }
	        writer.writeNext(data20);
	        
	        /*String[] data20 = { "Sub Total"};
	        writer.writeNext(data20);*/
	        
	        String[] data21 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data21[0]="16";
	        	}else{
	        		data21[y]="0";
	        	}
	        }
	        writer.writeNext(data21);
	        
	       /* String[] data21 = { "16"};
	        writer.writeNext(data21);*/
	        
	        String[] data22 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data22[0]="17";
	        	}else{
	        		data22[y]="0";
	        	}
	        }
	        writer.writeNext(data22);
	        
	        
	        /*String[] data22 = { "17"};
	        writer.writeNext(data22);*/
	        
	        String[] data23 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data23[0]="18";
	        	}else{
	        		data23[y]="0";
	        	}
	        }
	        writer.writeNext(data23);
	        
	        /*String[] data23 = { "18"};
	        writer.writeNext(data23);*/
	        String[] data24 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data24[0]="19";
	        	}else{
	        		data24[y]="0";
	        	}
	        }
	        writer.writeNext(data24);
	        
	        /*String[] data24 = { "19"};
	        writer.writeNext(data24);*/
	        String[] data25 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data25[0]="20";
	        	}else{
	        		data25[y]="0";
	        	}
	        }
	        writer.writeNext(data25);
	        
	       /* String[] data25 = { "20"};
	        writer.writeNext(data25);*/
	        
	        String[] data26 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data26[0]="Sub Total";
	        	}else{
	        		data26[y]="0";
	        	}
	        }
	        writer.writeNext(data26);
	        
	        /*String[] data26 = { "Sub Total"};
	        writer.writeNext(data26);*/
	        
	        String[] data27 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data27[0]="21";
	        	}else{
	        		data27[y]="0";
	        	}
	        }
	        writer.writeNext(data27);
	        
	       /* String[] data27 = { "21"};
	        writer.writeNext(data27);*/
	        String[] data28 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data28[0]="22";
	        	}else{
	        		data28[y]="0";
	        	}
	        }
	        writer.writeNext(data28);
	        
	       /* String[] data28 = { "22"};
	        writer.writeNext(data28);*/
	        
	        String[] data29 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data29[0]="23";
	        	}else{
	        		data29[y]="0";
	        	}
	        }
	        writer.writeNext(data29);
	        
	        /*String[] data29 = { "23"};
	        writer.writeNext(data29);*/
	        
	        String[] data30 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data30[0]="24";
	        	}else{
	        		data30[y]="0";
	        	}
	        }
	        writer.writeNext(data30);
	        
	        /*String[] data30 = { "24"};
	        writer.writeNext(data30);*/
	        
	        String[] data31 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data31[0]="25";
	        	}else{
	        		data31[y]="0";
	        	}
	        }
	        writer.writeNext(data31);
	        
	       /* String[] data31 = { "25"};
	        writer.writeNext(data31);*/
	        
	        String[] data32 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data32[0]="Sub Total";
	        	}else{
	        		data32[y]="0";
	        	}
	        }
	        writer.writeNext(data32);
	        
	        /*String[] data32 = { "Sub Total"};
	        writer.writeNext(data32);*/
	        
	        String[] data33 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data33[0]="26";
	        	}else{
	        		data33[y]="0";
	        	}
	        }
	        writer.writeNext(data33);
	        
	       /* String[] data33 = { "26"};
	        writer.writeNext(data33);*/
	        String[] data34 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data34[0]="27";
	        	}else{
	        		data34[y]="0";
	        	}
	        }
	        writer.writeNext(data34);
	        
	        /*String[] data34 = { "27"};
	        writer.writeNext(data34);*/
	        String[] data35 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data35[0]="28";
	        	}else{
	        		data35[y]="0";
	        	}
	        }
	        writer.writeNext(data35);
	        
	        /*String[] data35 = { "28"};
	        writer.writeNext(data35);*/
	        
	        String[] data36 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data36[0]="29";
	        	}else{
	        		data36[y]="0";
	        	}
	        }
	        writer.writeNext(data36);
	        
	       /* String[] data36 = { "29"};
	        writer.writeNext(data36);*/
	        
	        String[] data37 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data37[0]="30";
	        	}else{
	        		data37[y]="0";
	        	}
	        }
	        writer.writeNext(data37);
	        
	       /* String[] data37 = { "30"};
	        writer.writeNext(data37);*/
	        
	        String[] data38 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data38[0]="Sub Total";
	        	}else{
	        		data38[y]="0";
	        	}
	        }
	        writer.writeNext(data38);
	        
	        /*String[] data38 = { "Sub Total"};
	       
	        writer.writeNext(data38);*/
	        String[] data40 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data40[0]="31";
	        	}else{
	        		data40[y]="0";
	        	}
	        }
	        writer.writeNext(data40);
	        
	        /*String[] data40 = { "31"};
	        writer.writeNext(data40);*/
	        String[] data41 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	data41[y]="";
	        	
	        }
	        writer.writeNext(data41);
	        
	        /*String[] data41 = { ""};
	        writer.writeNext(data41);*/
	        String[] data42 = new String[data1.length+1]; 
	        for(int y=0;data1.length>y;y++){
	        	if(y==0){
	        		data42[0]="Total";
	        	}else{
	        		data42[y]="0";
	        	}
	        	
	        }
	        writer.writeNext(data42);
	       /* String[] data42 = { "Total"};
	        writer.writeNext(data42);*/
	        
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	private static HashMap sortByValues(Map<String, String> dictionary) { 
	       List list = new LinkedList(dictionary.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

}

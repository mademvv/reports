package combined;

import java.awt.Label;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.csv.CSVParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.csvreader.CsvWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class Update_Distributor_List {
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
	public static String source;
	public static String destination;
	
	//public static void main(String args[]) throws Throwable{
		
		public static void update(String destinationfile) throws Throwable{
		source=System.getProperty("user.dir")+"\\combined\\Combined_lookup.csv";
		destination=destinationfile;
		inputFile = new File(source);
		
		// Read existing file
		reader = new CSVReader(new FileReader(inputFile), ',');
		csvBody = reader.readAll();
		Map<String, String> dictionary = new HashMap<String, String>();
		for(int i=1; i<csvBody.size(); i++){
			strArray = csvBody.get(i);
			//System.out.println("i"+i);
			String id="";
			String name="";
			for(int j=0; j<strArray.length; j++){
				if(j==0){
					//System.out.print(strArray[j].substring(0,strArray[j].length()-2));
					id=strArray[j].substring(0,strArray[j].length());
				}else if(j==1){
					//System.out.print(strArray[j]);
					//System.out.println("j"+j);
					String[] x=strArray[j].trim().split(",");
					if(x.length>1){
					String c=x[1]+" "+x[0];
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
		
		
		 Set set = dictionary.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	           Map.Entry me = (Map.Entry)iterator.next();
	           System.out.print(me.getKey() + ": ");
	           System.out.println(me.getValue());
	      }
	      
	      
	      
		//reading csv
	      inputFile = new File(destination);
			//System.out.println("csvBody.size()"+csvBody.size());
			//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
			// Read existing file
			reader = new CSVReader(new FileReader(inputFile), ',');
			csvBody = reader.readAll();
			strArray = csvBody.get(0);
			System.out.println("csvBody.size()"+csvBody.size());
			 dim_array=new String[csvBody.size()][strArray.length-1];
			for(int i=0; i<csvBody.size(); i++){
				 strArray = csvBody.get(i);
				 System.out.println("strArray"+strArray.length);
				 int k=0;
				for(int j=1; j<strArray.length; j++){
					
					if(j>=1){
						if(csvBody.get(i)[j]!=null){
						System.out.println("i"+i);
						System.out.println("j"+j);
						System.out.println("k"+k);
						System.out.println("csvBody.get(i)[j]"+csvBody.get(i)[j]);
						
					
					if(csvBody.get(i)[j].isEmpty()==true){
						dim_array[i][k]="0";
					}else{
						dim_array[i][k]=csvBody.get(i)[j];
					System.out.println("dim_array[i][k]"+dim_array[i][k]);
					}
					k=k+1;
					}
					}
				}
			}
			
			reader.close();
			
			for(int i=0; i<dim_array.length; i++){
				for(int j=0; j<dim_array[0].length; j++){
					System.out.print(dim_array[i][j]);
					//dim_array[i][j-1]=csvBody.get(i)[j];
				}
				System.out.println();
			}
			
			inputFile = new File(destination);
			reader = new CSVReader(new FileReader(inputFile), ',');
			csvBody = reader.readAll();	
			for(int i=0; i<csvBody.size(); i++){
				strArray = csvBody.get(i);
							
							Map<String, String> map = sortByValues(dictionary); 
						      System.out.println("After Sorting:");
						      Set set2 = map.entrySet();
						      Iterator iterator2 = set2.iterator();
						      j=1;String dis=",";String num=",";
						      while(iterator2.hasNext()) {
						           Map.Entry me2 = (Map.Entry)iterator2.next();
						           System.out.print(me2.getKey() + ": ");
						           System.out.println(me2.getValue());
						           csvBody.get(0)[j]=me2.getValue().toString();
						           
						           dis=dis+me2.getValue()+",";
						           num=num+me2.getKey()+",";
						           
						      }	
							dis=dis+"Daily Total";
							String outputFile = destination;
							
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
						        
						        String[] data3 = new String[data1.length]; 
						        for(int y=0;data1.length>y;y++){
						        	if(y==0){
						        		data3[0]="1";
						        	}else{
						        		data3[y]="0";
						        	}
						        }
						        writer.writeNext(data3); 
						        
						        
						        String[] data4 = new String[data1.length]; 
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
						        String[] data5 = new String[data1.length]; 
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
						        
						        String[] data6 = new String[data1.length]; 
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
						        
						        String[] data7 = new String[data1.length]; 
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
						        
						        String[] data8 = new String[data1.length]; 
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
						        
						        String[] data9 = new String[data1.length]; 
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
						        
						        String[] data10 = new String[data1.length]; 
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
						        String[] data11 = new String[data1.length]; 
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
						        String[] data12 = new String[data1.length]; 
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
						        
						        String[] data13 = new String[data1.length]; 
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
						        String[] data14 = new String[data1.length]; 
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
						        String[] data15 = new String[data1.length]; 
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
						        
						        String[] data16 = new String[data1.length]; 
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
						        
						        String[] data17 = new String[data1.length]; 
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
						        String[] data18 = new String[data1.length]; 
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
						        
						        String[] data19 = new String[data1.length]; 
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
						        
						        String[] data20 = new String[data1.length]; 
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
						        
						        String[] data21 = new String[data1.length]; 
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
						        
						        String[] data22 = new String[data1.length]; 
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
						        
						        String[] data23 = new String[data1.length]; 
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
						        String[] data24 = new String[data1.length]; 
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
						        String[] data25 = new String[data1.length]; 
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
						        
						        String[] data26 = new String[data1.length]; 
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
						        
						        String[] data27 = new String[data1.length]; 
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
						        String[] data28 = new String[data1.length]; 
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
						        
						        String[] data29 = new String[data1.length]; 
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
						        
						        String[] data30 = new String[data1.length]; 
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
						        
						        String[] data31 = new String[data1.length]; 
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
						        
						        String[] data32 = new String[data1.length]; 
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
						        
						        String[] data33 = new String[data1.length]; 
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
						        String[] data34 = new String[data1.length]; 
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
						        String[] data35 = new String[data1.length]; 
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
						        
						        String[] data36 = new String[data1.length]; 
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
						        
						        String[] data37 = new String[data1.length]; 
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
						        
						        String[] data38 = new String[data1.length]; 
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
						        String[] data40 = new String[data1.length]; 
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
						        String[] data41 = new String[data1.length]; 
						        for(int y=0;data1.length>y;y++){
						        	data41[y]="";
						        	
						        }
						        writer.writeNext(data41);
						        
						        /*String[] data41 = { ""};
						        writer.writeNext(data41);*/
						        String[] data42 = new String[data1.length]; 
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
	
	//loop
			
			
			
			inputFile = new File(destination);
			reader = new CSVReader(new FileReader(inputFile), ',');
			csvBody = reader.readAll();	
			//for(int i=0; i<csvBody.size(); i++){
			String[] st=new String[csvBody.get(1).length];
				strArray = csvBody.get(1);
				st=strArray;
				//reader.close();
				for(int j=1; j<st.length; j++){
					
					String number=st[j];
					boolean found=false;
					if(number.isEmpty()==false ){
						int col_index=0;int csv_col_index=0;
						System.out.println("number "+number);
						
						
						for(int h=0;dim_array[1].length>h;h++){
							//System.out.println("dim_array[1][h]"+dim_array[1][h]);
							//if(dim_array[1][h].equals("1870382"))
							if(dim_array[1][h]!=null){
							if(dim_array[1][h].equals(number)){
								col_index=h;
								csv_col_index=j;
								found=true;
								h=0;
								break;
								
							}
							}
							
						}
						if(found==false){
							
							System.out.println("Date was not there in lookup excel "+number);
						}else{
							
							System.out.println("dim_array.length"+dim_array.length);
							System.out.println("csvBody.size()"+csvBody.size());
							
							inputFile = new File(destination);
							reader = new CSVReader(new FileReader(inputFile), ',');
							csvBody = reader.readAll();	
							for(int i=2; i<csvBody.size(); i++){
								 strArray = csvBody.get(i);boolean fg=false;
								 for(int je=0; je<strArray.length; je++){
									 
									
									// if(je==col_index){
									if(je==csv_col_index){
										 System.out.println("dim_array[i][col_index]"+dim_array[i][col_index]);
										 csvBody.get(i)[csv_col_index]=dim_array[i][col_index].toString();
										// fg=true;
										 break;
									 }
										 
										 
									 }
									 /*if(fg==true){
										 break;
									 }*/
								// }
							
						}
							
							reader.close();

							// Write to CSV file which is open
							writer = new CSVWriter(new FileWriter(destination), ',');
							writer.writeAll(csvBody);
							writer.flush();
							writer.close();	
							
					}
					
					
				}
			}
			System.out.println("trmp");
			total.tot(destination);
			
				/*String[][] subtotal;
				//if(dim_array.length%5==0){
				//	subtotal=new String[6][strArray.length-1];
				//}else{
					subtotal=new String[7][strArray.length-2];
				//}
				//String[][] subtotal=new String[7][strArray.length];
				int sum=0,u=0,t=0;boolean et=false;
				int uk=0;int count=0;
				
				for(int y=0;dim_array[0].length>y;y++){
					et=false;sum=0;int sumtot=0;boolean star=false;
					for(int e=2;dim_array.length>e;e++){
						if(dim_array[e][y].isEmpty()||dim_array[e][y]==""){
							dim_array[e][y]="0";
						}
						
						if(e>=0+2 && e<=4+2){
							System.out.println("dim_array[e][y]"+dim_array[e][y]);
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==4+2){
								
								subtotal[0][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}else if(e>4+2 && e<=9+2){
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==9+2){
								
								subtotal[1][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}else if(e>9+2 && e<=14+2){
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==14+2){
								
								subtotal[2][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}else if(e>14+2 && e<=19+2){
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==19+2){
								
								subtotal[3][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}else if(e>19+2 && e<=24+2){
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==24+2){
								
								subtotal[4][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}else if(e>24+2 && e<=29+2){
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							
							star=true;
							if(e==29+2){
								
								subtotal[5][y]=String.valueOf(sumtot);
								sumtot=0;
							}
						}
						if(e>29+2){
							
							 sumtot=0;
							
							for(int r1=0;subtotal.length-1>r1;r1++){
								sumtot=sumtot+Integer.parseInt(subtotal[r1][y]); 
							}
							
							sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
							subtotal[6][y]=String.valueOf(sumtot);
							// sum=0;
							count=0;
							sumtot=0;
							uk=0;
							break;
						}

						
						
					}

				}
				for(int fw=0;subtotal.length>fw;fw++){
					for(int yr=0;subtotal[fw].length>yr;yr++){
						System.out.print(subtotal[fw][yr]);
					}
					System.out.println();
				}
				int sy=0;
				for(int i=2;dim_array.length>i;i++){
					sy=0;
					for(int j=0;dim_array[0].length>j;j++){
						if(dim_array[i][j]!=null){
						sy=sy+Integer.parseInt(dim_array[i][j]);
						}
						
					}
					
					System.out.println("sy"+sy);
					dim_array[i][dim_array[0].length-1]=String.valueOf(sy);
				}


				for(int i=0;subtotal.length>i;i++){
					int er=0;
					for(int j=0;subtotal[0].length-1>j;j++){
						//System.out.print("i :"+i+"j :"+j+" "+subtotal[i][j]);
						//System.out.print("---");
						if(subtotal[i][j]!=null){
						er=er+Integer.parseInt(subtotal[i][j]);
						}

					}
					System.out.println("er"+er);
					subtotal[i][subtotal[0].length-1]=String.valueOf(er);
					//System.out.println("");
				}


				
				for(int f=0;dim_array.length>f;f++){
					
					for(int x=0;dim_array[f].length>x;x++){
						//System.out.print("row :"+f);
						//System.out.print("col :"+x);
						System.out.print("dim_array["+f+"]["+x+"]"+dim_array[f][x]);
						System.out.print(" | ");
					}
					System.out.println();
				}

				int day_count=31;  
				inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");
				//System.out.println("csvBody.size()"+csvBody.size());
				//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
				// Read existing file
				reader = new CSVReader(new FileReader(inputFile), ',');
				csvBody = reader.readAll();
				int col_index=0;
				int days=31;
				// get CSV row column and replace with by using row and column
				// for(int k=0;k<dump.length;k++){
				boolean str=false;
				boolean stk=false;
				int f=0;int k=0;
				
				
				
				for(int i=0; i<csvBody.size(); i++){
					strArray = csvBody.get(i);
					//col_index=0;
					int h=0;str=false;stk=false;
				System.out.println(strArray.length);
					for(int j=0; j<strArray.length; j++){
						stk=false;
						
				 if(i==7){
					break;
				}else if(i==13){
					break;
				}else if(i==19){
					break;
				}else if(i==25){
					break;
				}else if(i==31){
					break;
				}else if(i==37){
					break;
				}else if(i==39){
					break;
				}else if(i==49){
					break;
				}else if(i>1 && j>0){
				
					if(k==31){
						break;
					}
					if(h==strArray.length-2 && i>1){
						System.out.println("csvBody.get(i)[j]"+csvBody.get(i)[j]);
						System.out.println("dim_array[k][h]"+dim_array[i][dim_array[0].length-1]);
					csvBody.get(i)[j] = dim_array[i][dim_array[0].length-1];
					}
					h=h+1;
					stk=true;
					
				}
					}
					if(stk==true){
						
						k=k+1;
					}
				}
				
				reader.close();

				// Write to CSV file which is open
				writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"), ',');
				writer.writeAll(csvBody);
				writer.flush();
				writer.close(); 
				
			
				inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");
				//System.out.println("csvBody.size()"+csvBody.size());
				//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
				// Read existing file
				reader = new CSVReader(new FileReader(inputFile), ',');
				csvBody = reader.readAll();		
				
			//	boolean str=false;
		//	boolean	stk=false;int k=0;
				
				k=0;
				for(int i=0; i<csvBody.size(); i++){
					strArray = csvBody.get(i);
					//col_index=0;
					int h=0;stk=false;str=false;
				
					for(int j=0; j<strArray.length-1; j++){
						stk=false;
						if(k==32){
							break;
						}
						System.out.println("h"+h);
				if(i==7  && j>0 ){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==13  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==19  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==25  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==31  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==37  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==40  && j>0){
					csvBody.get(i)[j] = subtotal[k][h-1];
					stk=true;
				}else if(i==39  && j>0){
					csvBody.get(i)[j] = "";
					
				}
				h=h+1;
					}
					if(stk==true){
						
						k=k+1;
					}
				}
				
				reader.close();

				// Write to CSV file which is open
				writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"), ',');
				writer.writeAll(csvBody);
				writer.flush();
				writer.close(); 
				
			
			
			
			
	*/
	
	}
					
							
							
				
			 // }
		      
	      
	
	
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

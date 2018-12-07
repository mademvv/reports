package report3;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.csv.CSVParser;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import combined.markup;

public class report3 {
	
	public static boolean ASC = true;
	public static boolean DESC = false;
	public static  Map<String, Integer> unsortMap;
	public static Reader r;
	public static CSVParser csvParser;

	public static String[] raw_dump;
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
	
	public static String source;
	public static String destination;
	//public static void main(String[] args) throws Throwable {
		public static void report() throws Throwable {
		//result="";
			String	badresult="";
			String	goodresult="";
		source=System.getProperty("user.dir")+"\\report3\\report3_Daily_PV_Order_Numbers.csv";
		destination=System.getProperty("user.dir")+"\\report3_reports\\report3_KPI_Daily_PV_Order_Numbers.csv";
			 
		
		inputFile = new File(destination);
		//System.out.println("csvBody.size()"+csvBody.size());
		//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
		// Read existing file
		reader = new CSVReader(new FileReader(inputFile), ',');
		csvBody = reader.readAll();
		for(int i=0; i<csvBody.size(); i++){
			strArray = csvBody.get(i);
			
		
			for(int j=0; j<strArray.length; j++){
				
				if(csvBody.get(i)[j].equals("-")){
					csvBody.get(i)[j]="0";
				}
			}
		}
		
		reader.close();

		// Write to CSV file which is open
		writer = new CSVWriter(new FileWriter(destination), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
		
		
		
		
		
		
		
		
		
		
		
		
		inputFile = new File(source);

				// Read existing file
				 reader = new CSVReader(new FileReader(inputFile), ',');
				 csvBody = reader.readAll();
				 
			
				 csvBody.size();
				 raw_dump=new String[csvBody.size()-1];
			
			
				 for(int i=0; i<csvBody.size(); i++){
						String[] strArray = csvBody.get(i);
						//col_index=0;
						for(int j=0; j<strArray.length; j++){
							if(j==5 && i>0){
								raw_dump[i-1]=csvBody.get(i)[j];
							}
						}
				 }
				 
				 
				 
				 List<String> arrList = new ArrayList<String>();
			     int cnt= 0;
			       //List<String> arrList = Arrays.asList(arr);
			       List<String> lenList = new ArrayList<String>();
			          for(int i=0;i<raw_dump.length;i++){
			        for(int j=i+1;j<raw_dump.length;j++){
			           if(raw_dump[i].equals(raw_dump[j])){
			             cnt+=1;
			           }                
			        }
			        if(cnt<1){
			          arrList.add(raw_dump[i]);
			        }
			          cnt=0;
			        }
				 
			         
				 
			          dump=new String[arrList.size()] [2];
				 
				 
				 System.out.println("fg");
				 ArrayList<String> list = new ArrayList<String>(); 
				 for(int g=0;raw_dump.length>g;g++){
					 list.add(raw_dump[g].toString());
					 
					 
				 }
				
				 TreeMap<String, Integer> tmap = new TreeMap<String, Integer>(); 
			        for (String t : list) { 
			            Integer c = tmap.get(t); 
			            tmap.put(t, (c == null) ? 1 : c + 1); 
			        } 
			  int s=0;
			        for (Map.Entry m : tmap.entrySet()) {
			            System.out.println("Frequency of " + m.getKey() + " is " + m.getValue()); 
			            dump[s][0]=m.getKey().toString();
			            dump[s][1]=m.getValue().toString();
			            s=s+1;
			    } 
			for(int d=0;dump.length>d;d++){
				for(int y=0;dump[d].length>y;y++){
					System.out.print(dump[d][y]);
					
				}
				System.out.println();
			}
					enrollment_id = new ArrayList<>();

					//BufferedReader r = new BufferedReader(new FileReader("D:\\janardhan\\workspace\\reports\\report1\\data.csv"));
					/*try(
							Reader r=Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+"\\report1\\report1_Daily_PV.csv"));
							CSVParser csvParser=new CSVParser(r,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

							){
						array=0;
						csvParser.
						for(CSVRecord csvRecord:csvParser){
							int val=csvRecord.size();
							dump=new String[val-1][2];
							if(val.equalsIgnoreCase("distributor")){
								array=array+1;
							}
						}
					}

					try(
							Reader	 r=Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+"\\report1\\report1_Daily_PV.csv"));
							CSVParser csvParser=new CSVParser(r,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

							){
						dump=new String[array][2];
						j=0;
						for(CSVRecord csvRecord:csvParser){

							String val=csvRecord.get("Member Status");
							if(val.equalsIgnoreCase("distributor")){
								dump[j][0]=csvRecord.get("Enroller ID").substring(0,csvRecord.get("Enroller ID").length());
								//dump[j][1]=csvRecord.get("Native Name");
								dump[j][1]=csvRecord.get("PV").substring(0,csvRecord.get("PV").length());
								j=j+1;
							}			
						}

						

					}*/




					 inputFile = new File(destination);

					// Read existing file
					 reader = new CSVReader(new FileReader(inputFile), ',');
					 csvBody = reader.readAll();
					int col_index=0;
					
					String input=JOptionPane.showInputDialog(null,"Please enter the date.For example: For 31st Oct need to enter date as 31");
					System.out.println("input"+input);
					if(input!=null){
					String dateval=input;
					int row_index=0;
					for(int i=0; i<csvBody.size(); i++){
						String[] strArray = csvBody.get(i);
						//col_index=0;
						boolean hj=false;int df=0;
						for(int j=0; j<strArray.length; j++){
							if(j==0 && i>1){
								if(csvBody.get(i)[j].equals(input)){
									row_index=i;
									break;
							}
						}
					}
					}
						
						
							//col_index=0;
							//boolean hj=false;int df=0;
					 strArray = csvBody.get(1);
							for(int j=0; j<strArray.length; j++){
								if(j>0){
									csvBody.get(row_index)[j]="0";
								}
							
							}
						
						reader.close();

					// Write to CSV file which is open
					 writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close(); 
					
					// get CSV row column and replace with by using row and column
					int rk=1;int ek=1;
					for(int k=0;k<dump.length;k++){
						boolean st=false;boolean rt=false;
						for(int i=1; i<csvBody.size(); i++){
							 strArray = csvBody.get(i);
							//col_index=0;
							for(int j=0; j<strArray.length; j++){
								boolean q=false;
								if(i==1){
									//System.out.println("dump[k][0]"+dump[k][0]);
									if(strArray[j].equalsIgnoreCase(dump[k][0])){ //String to be replaced

										col_index=j;rt=true;
										break;
									}
								}
								if(strArray[j].equalsIgnoreCase(dateval) && j==0){
									q=true;
								}
								if(q==true && i>1){
									//System.out.println("dump[k][1]"+dump[k][1]);
									if(rt==true){
										String g=csvBody.get(i)[col_index];
										System.out.println("g"+g);
										String y=dump[k][1];
									csvBody.get(i)[col_index] = String.valueOf((Integer.parseInt(g)+Integer.parseInt(y))); //Target replacement
									}
									// i=0;
									st=true;
									break;

								}
							}
							if(st==true){
								
								break;
							}
						}
						if(rt==false){
							System.out.println("Distributor enroller id "+dump[k][0]+" was not found and PV VALUE was "+dump[k][1]);
							badresult+=rk+" Distributor enroller id "+dump[k][0]+" was not found and PV VALUE was "+dump[k][1]+"\n";
						rk=rk+1;
						}else{
							System.out.println("Distributor enroller id "+dump[k][0]+" was Updated and PV VALUE was "+dump[k][1]);
							goodresult+=ek+" Distributor enroller id "+dump[k][0]+" was Updated and PV VALUE was "+dump[k][1]+"\n";
						ek=ek+1;
						}
					}
					reader.close();

					// Write to CSV file which is open
					 writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close();        


					String	source=System.getProperty("user.dir")+"\\report3\\report3_Daily_PV_Order_Numbers.csv";
					String	destination=System.getProperty("user.dir")+"\\report3_reports\\report3_KPI_Daily_PV_Order_Numbers.csv";
						 markup.manipulation(source,destination);
					//sub totals
/*
					inputFile = new File(destination);

					// Read existing file
					//reader = new CSVReader(new FileReader(inputFile), ',');
					//csvBody = reader.readAll();
					// strArray = csvBody.get(0);
					inputFile = new File(destination);

					// Read existing file
					reader = new CSVReader(new FileReader(inputFile), ',');
					csvBody = reader.readAll();
					 strArray = csvBody.get(1);
					 dim_array=new String[31][strArray.length];



					for(int i=0; i<csvBody.size(); i++){
						//String[] strArray = csvBody.get(i);
						//col_index=0;

						for(int j=0; j<strArray.length-1; j++){

							if(i==7  ){
								if(j>0){
									csvBody.get(i)[j] = "0";
								}
								}else if(i==13){
									if(j>0){
										csvBody.get(i)[j] = "0";
									}
								}else if(i==19){
									if(j>0){
										csvBody.get(i)[j] = "0";
									}
								}else if(i==25){
									if(j>0){
										csvBody.get(i)[j] = "0";
									}
								}else if(i==31){
									if(j>0){
										csvBody.get(i)[j] = "0";
									}
								}else if(i==37){
									if(j>0){
										csvBody.get(i)[j] = "0";
									}
								}else if(i==39){
									if(j>0){
										csvBody.get(i)[j] = "0";
								}

							}

						}

					}
					reader.close();

					writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close(); 

					inputFile = new File(destination);

					// Read existing file
					reader = new CSVReader(new FileReader(inputFile), ',');
					csvBody = reader.readAll();
					//System.out.println("csvBody.size()"+csvBody.size());
					int r = 0,c = 0;
					boolean st=false;
					for(int i=0; i<csvBody.size(); i++){
						 strArray = csvBody.get(i);
						//col_index=0;
						st=false;
						for(int j=0; j<strArray.length-1; j++){
							String xyz="0";
							if(i>1 && j>0){
								if(i==7){
									
									break;
								}
								
								if(i==13){
									
									break;
								}
								
								if(i==19){
									
									break;
								}
								
								if(i==25){
									
									break;
								}
								
								if(i==31){
									
									break;
								}
								
								if(i==37){
									
									break;
								}
								
								
								if(i==39){
									
									break;
								}
 
								if(i==40){
									
									break;
								}
								
								xyz= csvBody.get(i)[j];
							
							dim_array[r][c]=xyz.trim();
							
							st=true;
								
								

									
									if(strArray.length-1==c){
										c=0;
										break;
									}else{
										c=c+1;
									}
								}
							}if(st==true){
								r=r+1;
								c=0;
							

						}
						}
					reader.close();
						
					
					 strArray = csvBody.get(1);
					String[][] subtotal;
					if(dim_array.length%5==0){
						subtotal=new String[6][strArray.length];
					}else{
						subtotal=new String[7][strArray.length];
					}
					//String[][] subtotal=new String[7][strArray.length];
					int sum=0,u=0,t=0;boolean et=false;
					System.out.println(dim_array[0].length);
					int uk=0;int count=0;
					for(int y=0;dim_array[0].length>y;y++){
						et=false;sum=0;int sumtot=0;boolean star=false;
						for(int e=0;dim_array.length>e;e++){
							if(dim_array[e][y]!=null){
							if(dim_array[e][y].isEmpty()||dim_array[e][y]==""){
								dim_array[e][y]="0";
							}
							if(y==1238){
								System.out.println("hi");
							}
							if(e>=0 && e<=4){
								System.out.println(sumtot);
								System.out.println(dim_array[e][y]);
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==4){
									
									subtotal[0][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}else if(e>4 && e<=9){
								
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==9){
									
									subtotal[1][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}else if(e>9 && e<=14){
								
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==14){
									
									subtotal[2][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}else if(e>14 && e<=19){
								
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==19){
									
									subtotal[3][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}else if(e>19 && e<=24){
								
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==24){
									
									subtotal[4][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}else if(e>24 && e<=29){
								
								sumtot=sumtot+Integer.parseInt(dim_array[e][y]);
								
								star=true;
								if(e==29){
									
									subtotal[5][y]=String.valueOf(sumtot);
									sumtot=0;
								}
							}
							if(e>29){
								
								 sumtot=0;
								
								for(int r1=0;subtotal.length>r1;r1++){
									if(subtotal[r1][y]!=null){
									sumtot=sumtot+Integer.parseInt(subtotal[r1][y]);
									}
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

					}

					int sy=0;
					for(int i=0;dim_array.length>i;i++){
						sy=0;
						for(int j=0;dim_array[1].length-1>j;j++){
							if(dim_array[i][j]!=null){
							sy=sy+Integer.parseInt(dim_array[i][j]);
							}
						}
						//System.out.println("sy"+sy);
						dim_array[i][dim_array[0].length-1]=String.valueOf(sy);
					}


					for(int i=0;subtotal.length>i;i++){
						int er=0;
						for(int j=0;subtotal[1].length-1>j;j++){
							//System.out.print("i :"+i+"j :"+j+" "+subtotal[i][j]);
							//System.out.print("---");
							if(subtotal[i][j]!=null){
							er=er+Integer.parseInt(subtotal[i][j]);
							}

						}
						//System.out.println("er"+er);
						subtotal[i][subtotal[0].length-1]=String.valueOf(er);
						//System.out.println("");
					}


					
for(int er=0;dim_array.length>er;er++){
						
						for(int tr=0;dim_array[er].length>tr;tr++){
							System.out.print(dim_array[er][tr]);
							System.out.print("|");
						}
						System.out.println();
					}


					int day_count=31;  


					inputFile = new File(destination);
					//System.out.println("csvBody.size()"+csvBody.size());
					//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
					// Read existing file
					reader = new CSVReader(new FileReader(inputFile), ',');
					csvBody = reader.readAll();
					 col_index=0;
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
					
						for(int j=0; j<strArray.length-1; j++){
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
						csvBody.get(i)[j] = dim_array[k][h];
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
					writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close(); 
					
				
					System.out.println(subtotal.length);
					System.out.println();
					for(int fe=0;subtotal.length>fe;fe++){
					
					for(int d=0;subtotal[fe].length>d;d++){
						System.out.print(fe+"Value"+subtotal[fe][d]+"|");
					}
					System.out.println();
				}
					
					inputFile = new File(destination);
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
					if(i==7  && j>0 ){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==13  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==19  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==25  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==31  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==37  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==40  && j>0){
						csvBody.get(i)[j] = subtotal[k][j-1];
						stk=true;
					}else if(i==39  && j>0){
						csvBody.get(i)[j] = "";
						
					}
						}
						if(stk==true){
							
							k=k+1;
						}
					}
					
					reader.close();

					// Write to CSV file which is open
					writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close(); 
					
					inputFile = new File(destination);
					//System.out.println("csvBody.size()"+csvBody.size());
					//System.out.println("csvBody.get(1)"+csvBody.get(1).length);
					// Read existing file
					reader = new CSVReader(new FileReader(inputFile), ',');
					csvBody = reader.readAll();
					for(int i=0; i<csvBody.size(); i++){
						strArray = csvBody.get(i);
						
					
						for(int j=0; j<strArray.length; j++){
							
							if(csvBody.get(i)[j].equals("0")){
								csvBody.get(i)[j]="-";
							}
						}
					}
					
					reader.close();

					// Write to CSV file which is open
					writer = new CSVWriter(new FileWriter(destination), ',');
					writer.writeAll(csvBody);
					writer.flush();
					writer.close();
		*/
		System.out.println("completed");
		//JOptionPane.showMessageDialog(null, goodresult+"\n"+"------------------------------------\n"+badresult);
		//JOptionPane.showMessageDialog(null, "UnFound List:\n"+badresult+"------------------------------------\n"+"Found List:\n"+goodresult);
		JTextArea te=new JTextArea("Report Location:\n"+destination+"\n\n"+"Updated for Date :"+input+"\n\nUnFound List & Not Updated :\n"+badresult+"------------------------------------\n"+"Found List & Updated :\n"+goodresult);
		JScrollPane sc= new JScrollPane(te);
		te.setLineWrap(true);
		te.setWrapStyleWord(true);
		sc.setPreferredSize(new Dimension(500,500));
		JOptionPane.showMessageDialog(null, sc,"KPI Daily PV Sales Report3 Status",JOptionPane.YES_NO_OPTION);
					}else{
						JOptionPane.showMessageDialog(null, "Please enter the date.For example: For 31st Oct need to enter date as 31.");
					}
		}
	
//}
		
	



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

			JOptionPane.showMessageDialog(null, "Report 3 successfully generated.File location is "+System.getProperty("user.dir")+"\\report1_reports\\Report1_"+timestamp+".html");
		}catch(Exception w){
			//System.out.println(w);
			JOptionPane.showMessageDialog(null, w.getMessage());
		}

	}









}

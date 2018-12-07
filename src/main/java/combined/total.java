package combined;

import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.csv.CSVParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class total {
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
	//public static void main(String args[]) throws Throwable{
		public static void tot(String filename) throws Throwable{
		
		inputFile = new File(filename);

		// Read existing file
		reader = new CSVReader(new FileReader(inputFile), ',');
		csvBody = reader.readAll();
		 strArray = csvBody.get(0);
		 dim_array=new String[31][strArray.length];
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
				
			

			String[][] subtotal;
			if(dim_array.length%5==0){
				subtotal=new String[6][strArray.length];
			}else{
				subtotal=new String[7][strArray.length];
			}
			//String[][] subtotal=new String[7][strArray.length];
			int sum=0,u=0,t=0;boolean et=false;
			
			int uk=0;int count=0;
			for(int y=0;dim_array[1].length>y;y++){
				et=false;sum=0;int sumtot=0;boolean star=false;
				for(int e=0;dim_array.length>e;e++){
					if(dim_array[e][y]!=null){
					if(dim_array[e][y].isEmpty()||dim_array[e][y]==""){
						dim_array[e][y]="0";
					}
					
					if(e>=0 && e<=4){
						
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
				for(int j=0;dim_array[1].length>j;j++){
					if(dim_array[i][j]!=null){
					sy=sy+Integer.parseInt(dim_array[i][j]);
					}
				}
				//System.out.println("sy"+sy);
				dim_array[i][dim_array[1].length-2]=String.valueOf(sy);
			}


			for(int i=0;subtotal.length>i;i++){
				int er=0;
				for(int j=0;subtotal[1].length>j;j++){
					//System.out.print("i :"+i+"j :"+j+" "+subtotal[i][j]);
					//System.out.print("---");
					if(subtotal[i][j]!=null){
					er=er+Integer.parseInt(subtotal[i][j]);
					}

				}
				//System.out.println("er"+er);
				subtotal[i][subtotal[1].length-2]=String.valueOf(er);
				//System.out.println("");
			}



			int day_count=31;  


			inputFile = new File(filename);
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
			writer = new CSVWriter(new FileWriter(filename), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close(); 
			
		
			inputFile = new File(filename);
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
			
				for(int j=0; j<strArray.length; j++){
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
			writer = new CSVWriter(new FileWriter(filename), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close(); 
			
			inputFile = new File(filename);
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
			writer = new CSVWriter(new FileWriter(filename), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close(); 

System.out.println("completed");
//JOptionPane.showMessageDialog(null, goodresult+"\n"+"------------------------------------\n"+badresult);
//JOptionPane.showMessageDialog(null, "UnFound List:\n"+badresult+"------------------------------------\n"+"Found List:\n"+goodresult);
/*JTextArea te=new JTextArea("Report Location:\n"+System.getProperty("user.dir")+"\\report1_reports\\report1_KPI_Daily_PV.csv\n\n"+"Updated for Date :"+input+"\n\nUnFound List & Not Updated :\n"+badresult+"------------------------------------\n"+"Found List & Updated :\n"+goodresult);
JScrollPane sc= new JScrollPane(te);
te.setLineWrap(true);
te.setWrapStyleWord(true);
sc.setPreferredSize(new Dimension(500,500));
JOptionPane.showMessageDialog(null, sc,"KPI Daily PV Report1 Status",JOptionPane.YES_NO_OPTION);*/
	}
}
	



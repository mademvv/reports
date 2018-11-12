package report2;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class report2 {
	public static CSVReader reader;
	public static List<String[]> csvBody;
	public static File inputFile;
	public static String[] strArray;
	public static int rt;
	public static CSVWriter writer;
	public static boolean ds;
	public static int a;
public static List<String> found;
public static List<String> unfound;
public static String[][] dim_array;
public static boolean dg=false;

	//public static void main(String[] args) throws Throwable {
		public static void report() throws Throwable {
		
		String d="";
		String k="";
		int count=0;
		found = new ArrayList<>();
		unfound=new ArrayList<>();
		// TODO Auto-generated method stub
		 FileReader filereader = new FileReader(System.getProperty("user.dir")+"\\report2\\report2_Lead_Origination_Daily.csv"); 
		 CSVReader csvReader = new CSVReader(filereader); 
	        String[] nextRecord; 
	        int array=0;
	        while ((nextRecord = csvReader.readNext()) != null) { 
	        	for (String cell : nextRecord) { 
	        		if(cell.equalsIgnoreCase("Message3-dots-h")){
	        			array=array+1;
	        		}
	        	}
	        }
	      //  System.out.println("array"+array);
	        
	        String[][] xyz=new String[array][3];
	        
	        
	     // we are going to read data line by line 
	         filereader = new FileReader(System.getProperty("user.dir")+"\\report2\\report2_Lead_Origination_Daily.csv"); 
			  csvReader = new CSVReader(filereader);
	        int i=0;int j=0;boolean f=false;boolean t=false;int n=-1;
	        String leadname = null,distributor,date = null;
	         nextRecord=null;
	        while ((nextRecord = csvReader.readNext()) != null) { 
	        	i=0;
	            for (String cell : nextRecord) { 
	            	
	            		if(i==0 && cell.isEmpty()==false){
	            			
	            			if(cell.equalsIgnoreCase("Message3-dots-h")){
	            				f=true;
	            				t=true;
	            				n=n+1;
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
	            				  
	            				 // total+=leadname+","+date+","+q[0]+","+"\n";
	            				  xyz[n][0]=leadname;
	            				  xyz[n][1]=date;
	            				  xyz[n][2]=q[0];
	            				}else if(cell.indexOf("Added by")>-1 && cell.indexOf(" Yesterday")>-1 ){
	            					 distributor=cell;
		            				  String[] p=distributor.split(" by ");
		            				  String[] q=p[1].split(" Yesterday");
		            				  
		            				//  total+=leadname+","+date+","+q[0]+","+"\n";
		            				  xyz[n][0]=leadname;
		            				  xyz[n][1]=date;
		            				  xyz[n][2]=q[0];
		            				  
	            					
	            				}else if(cell.indexOf("Added by")>-1  ){
	            					 distributor=cell;
		            				  String[] p=distributor.split(" by ");
		            				  //String[] q=p[1].split(" Yesterday");
		            				  
		            				//  total+=leadname+","+date+","+q[0]+","+"\n";
		            				  xyz[n][0]=leadname;
		            				  xyz[n][1]=date;
		            				  //xyz[n][2]="Suspense Account";
		            				  xyz[n][2]=p[1];
		            				  
	            					
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
	            			//DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	            			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
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
	        
	        inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");

			// Read existing file
			 reader = new CSVReader(new FileReader(inputFile), ',');
			 csvBody = reader.readAll();
		for( i=0; i<csvBody.size(); i++){
			String[] strArray = csvBody.get(i);
			//col_index=0;
			for( j=0; j<strArray.length; j++){
				if(i>=2 && j>0){
				csvBody.get(i)[j]="0";
				}
			}
		}     
	        
		//reader.close();

		// Write to CSV file which is open
		writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();  
	        
	        
	        
	        for(int dt=0;xyz.length>dt;dt++){
	        	for(int s=0;xyz[0].length>s;s++){
	        		System.out.print("xyz["+dt+"]["+s+"]"+xyz[dt][s]);
	        		if(xyz[dt][s].equals("Kim Fincher Pritchard")){
	        			System.out.println("hi");
	        		}
	        	}
	        	System.out.println();
	        }
	        
	        for(int g=0;xyz.length>g;g++){
	        	//for(int y=0;xyz[0].length>y;y++){
	        		 dg=false;
	        		//if(xyz[g][]!=null){
	        			if(xyz[g][2].equals("Lisa Giuliano")){
	        				System.out.println("hi");
	        			}
	        			
	        			String val=xyz[g][1];
	    				 distributor=xyz[g][2];
	    				// System.out.println("distributor"+distributor);
	    				String[] var=val.split("/");
	    				for( j=1;31>=j;j++){
	    					if(var[1].equalsIgnoreCase(String.valueOf(j))){
	    						count=0;
	    						System.out.println("var[1]@@@@@@@@@@@@"+var[1]);
	    						boolean ft=false;
	    						inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");

	    						// Read existing file
	    						reader = new CSVReader(new FileReader(inputFile), ',');
	    						csvBody = reader.readAll();
	    						 strArray = csvBody.get(0);
	    						for( rt=0;strArray.length>rt;rt++){
	    							
	    							String[] a=distributor.split("\\s+");
	    							if(a.length>2){
	    								
	    								if(strArray[rt].trim().equalsIgnoreCase(a[0]+" "+a[a.length-1])){
	    	    							//	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	    	    							//	System.out.println("Date value "+var[1]);
	    	    							//	System.out.println("column index "+rt);
	    	    								System.out.println("Matched Distributor "+distributor);
	    	    							//	System.out.println("******************************************");
	    	    								ft=true;
	    	    								found.add(distributor);
	    	    								//count=count+1;
	    	    								break;
	    	    							}else{
	    	    								if(strArray[rt].trim().equalsIgnoreCase(distributor)){
	    	    	    							//	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	    	    	    							//	System.out.println("Date value "+var[1]);
	    	    	    							//	System.out.println("column index "+rt);
	    	    	    								System.out.println("Matched Distributor "+distributor);
	    	    	    							//	System.out.println("******************************************");
	    	    	    								ft=true;
	    	    	    								found.add(distributor);
	    	    	    								//count=count+1;
	    	    	    								break;
	    	    							}
	    	    							}
	    							
	    							}else{
	    							
	    							if(strArray[rt].trim().equalsIgnoreCase(distributor)){
	    							//	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	    							//	System.out.println("Date value "+var[1]);
	    							//	System.out.println("column index "+rt);
	    								System.out.println("Matched Distributor "+distributor);
	    							//	System.out.println("******************************************");
	    								ft=true;
	    								found.add(distributor);
	    								//count=count+1;
	    								break;
	    							}
	    							}
	    						}
	    						if(ft==true){
	    							k+=distributor+"\n";
	    							
	    							
	    							int row_index=0;
	    							for(int fr=2; fr<csvBody.size(); fr++){
	    								 ds=false;dg=false;
	    								String[] strArray = csvBody.get(fr);
	    								//col_index=0;
	    								for(int q=0; q<strArray.length; q++){
	    									
	    									if(q==0){
	    									//	System.out.println("csvBody.get(fr)[q]"+csvBody.get(fr)[q]);
	    									//	System.out.println("var[1]"+var[1]);
	    										if(csvBody.get(fr)[q].equalsIgnoreCase(var[1])){
	    											row_index=fr;
	    											ds=true;
	    											break;
	    										}
	    										
	    									}
	    									
	    								}
	    								if(ds==true){
	    									break;
	    								}
	    							
	    								}
	    							if(csvBody.get(row_index)[rt].isEmpty()==true || csvBody.get(row_index)[rt]=="0"){
	    								csvBody.get(row_index)[rt]=String.valueOf(1);
	    							}else{
	    								
	    								String x=csvBody.get(row_index)[rt];
	    								//System.out.println("x"+x);
	    								//System.out.println("count"+count);
	    								//csvBody.get(row_index)[rt]+=count;
	    								int a=Integer.parseInt(x)+1;
	    								//System.out.println("a"+a);
	    								csvBody.get(row_index)[rt]=String.valueOf(a);
	    								//csvBody.get(row_index)[rt]=String.valueOf(Integer.parseInt(csvBody.get(row_index)[rt]+Integer.parseInt(x)));
	    								dg=true;
	    							}
	    							 writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"), ',');
	    								writer.writeAll(csvBody);
	    								writer.flush();
	    								writer.close();
	    							a=0;
	    							count=0;
	    							//break;
	    						}else{
	    							unfound.add(distributor);
	    							d+=distributor+"\n";
	    						}
	    						
	    						 
	    						
	    						if(dg=true){
	    						break;
	    					}
	    						
	    						
	    					}
	    					
	    			//	}
	        			
	        			
	        			
	        		}
	        		/*if(dg=true){
						break;
					}*/
	        	//System.out.print(xyz[g][y]);
	        		/*if(dg=true){
						break;
					}*/
	        		/*if(dg=true){
						break;
					}*/
	        	/*}if(dg=true){
					break;
				}*/
	        	
	        	//System.out.println();
	        }
	 
	        Set<String> hs = new HashSet<>();
			hs.addAll(found);
			found.clear();
			found.addAll(hs);
			
			//List<String> al = new ArrayList<>();
			// add elements to al, including duplicates
			Set<String> h = new HashSet<>();
			h.addAll(unfound);
			unfound.clear();
			unfound.addAll(h);
			String cg="";
			for(int w=0;found.size()>w;w++){
				cg+=(w+1)+" "+found.get(w)+"\n";
			}

			String fg="";
			for(int w=0;unfound.size()>w;w++){
				fg+=(w+1)+" "+unfound.get(w)+"\n";
			}
			
			
			
			
			inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");

			// Read existing file
			reader = new CSVReader(new FileReader(inputFile), ',');
			csvBody = reader.readAll();
			 strArray = csvBody.get(0);
			 System.out.println("strArray.length"+strArray.length);
			 dim_array=new String[31][strArray.length];
			 
	
			inputFile = new File(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv");

			// Read existing file
			reader = new CSVReader(new FileReader(inputFile), ',');
			csvBody = reader.readAll();
			//System.out.println("csvBody.size()"+csvBody.size());
			int r = 0,c = 0;
			boolean st=false;
			for( i=0; i<csvBody.size(); i++){
				 strArray = csvBody.get(i);
				//col_index=0;
				st=false;
				
				for( j=0; j<strArray.length-1; j++){
					String xy="0";
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
						//System.out.println("i "+i);
						//System.out.println("j "+j);
						xy= csvBody.get(i)[j];
					
					dim_array[r][c]=xy.trim();
					
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
		//	System.out.println(dim_array.length);
		//	System.out.println(dim_array[1].length);
				/*for(int x=0;dim_array.length>x;x++){
					for(int rf=0;dim_array[x].length>rf;rf++){
						System.out.print("dim_array["+x+"]["+rf+"]"+dim_array[x][rf]);
					}
					System.out.println();
				}*/
			
			String[][] subtotal;
			
			if((dim_array.length)%5==0){
				subtotal=new String[6][strArray.length];
			}else{
				subtotal=new String[7][strArray.length];
			}
			//String[][] subtotal=new String[7][strArray.length];
			int sum=0,u=0;
			int t1=0;boolean et=false;
			
			int uk=0; count=0;
			for(int y=0;dim_array[0].length>y;y++){
				et=false;sum=0;int sumtot=0;boolean star=false;
				for(int e=0;dim_array.length>e;e++){
					if(dim_array[e][y]!=null){
					if(dim_array[e][y].isEmpty()||dim_array[e][y]=="" ){
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
			for( i=0;dim_array.length>i;i++){
				sy=0;
				for( j=0;dim_array[0].length>j;j++){
					if(dim_array[i][j]!=null){
					sy=sy+Integer.parseInt(dim_array[i][j]);
					}

				}
				//System.out.println("sy"+sy);
				dim_array[i][dim_array[1].length-2]=String.valueOf(sy);
			}


			for( i=0;subtotal.length>i;i++){
				int er=0;
				for( j=0;subtotal[1].length>j;j++){
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
			int fr=0; int ke=0;
			
			//System.out.println(dim_array.length);
			//System.out.println(dim_array[1].length);
				/*for(int x=0;dim_array.length>x;x++){
					for(int rf=0;dim_array[x].length>rf;rf++){
						System.out.print("dim_array["+x+"]["+rf+"]"+dim_array[x][rf]);
					}
					System.out.println();
				}*/
				
			
			for( i=0; i<csvBody.size(); i++){
				strArray = csvBody.get(i);
				//col_index=0;
				int hr=0;str=false;stk=false;
			
				for( j=0; j<strArray.length; j++){
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
			
				if(ke==31){
					break;
				}
				csvBody.get(i)[j] = dim_array[ke][hr];
				hr=hr+1;
				stk=true;
				
			}
				}
				if(stk==true){
					
					ke=ke+1;
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
			ke=0;
			for( i=0; i<csvBody.size(); i++){
				strArray = csvBody.get(i);
				//col_index=0;
				int hr=0;stk=false;str=false;
			
				for( j=0; j<strArray.length; j++){
					stk=false;
					if(ke==32){
						break;
					}
			if(i==7  && j>0 ){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==13  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==19  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==25  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==31  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==37  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==40  && j>0){
				csvBody.get(i)[j] = subtotal[ke][j-1];
				stk=true;
			}else if(i==39  && j>0){
				csvBody.get(i)[j] = "";
				
			}
				}
				if(stk==true){
					
					ke=ke+1;
				}
			}
			
			reader.close();

			// Write to CSV file which is open
			writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv"), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close(); 
			
			
			JTextArea te=new JTextArea("Report Location:\n"+System.getProperty("user.dir")+"\\report2_reports\\report2_KPI Daily Leads.csv\n\n"+"UnFound List & NOT Updated :\n"+fg+"------------------------------------\n"+"Found List & Updated :\n"+cg);
			JScrollPane sc= new JScrollPane(te);
			te.setLineWrap(true);
		
			te.setWrapStyleWord(true);
			sc.setPreferredSize(new Dimension(500,500));
			JOptionPane.showMessageDialog(null, sc,"KPI Daily Lead Report2 Status",JOptionPane.YES_NO_OPTION);
	
			
	
	
	
	
	} 
 
	        
	        
	}



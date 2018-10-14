package combined;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class convert_xlsx_csv {



        static void xlsx(File inputFile, File outputFile) {
                // For storing data into CSV files
                StringBuffer data = new StringBuffer();
                try {
                        FileOutputStream fos = new FileOutputStream(outputFile);

                        // Get the workbook object for XLSX file
               XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));

                        // Get first sheet from the workbook
                        XSSFSheet sheet = wBook.getSheetAt(0);
                        Row row;
                        Cell cell;

                        // Iterate through each rows from first sheet
                        Iterator<Row> rowIterator = sheet.iterator();
                        while (rowIterator.hasNext()) {
                                row = rowIterator.next();

                                // For each row, iterate through each columns
                                Iterator<Cell> cellIterator = row.cellIterator();
                                while (cellIterator.hasNext()) {
                                	
                                        cell = cellIterator.next();

                                        switch (cell.getCellType()) {
                                        case Cell.CELL_TYPE_BOOLEAN:
                                                data.append(cell.getBooleanCellValue() + ",");
                                                

                                                break;
                                        case Cell.CELL_TYPE_NUMERIC:
                                               // data.append(cell.getNumericCellValue() + ",");
                                        	 if (DateUtil.isCellDateFormatted(cell)) {
                                                 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                                 //System.out.print(dateFormat.format(cell.getDateCellValue()) + "\t");
                                                 data.append(cell.getDateCellValue() + ",");
                                        	 } else {
                                                // System.out.print(cell.getNumericCellValue() + "\t");
                                                 data.append(cell.getNumericCellValue() + ",");
                                             }

                                                break;
                                        case Cell.CELL_TYPE_STRING:
                                        	
                                        	
                                           //  break;
                                        	String x=cell.getStringCellValue().replace(",", ";");
                                                data.append((x.replace("\n", "").replace("\r", "")) + ",");
                                                break;

                                        case Cell.CELL_TYPE_BLANK:
                                                data.append("" + ",");
                                                break;
                                        default:
                                                data.append(cell + ",");

                                        }
                                      
                                       
                                }
                                data.append("\n");
                        }

                        fos.write(data.toString().getBytes());
                        fos.close();

                } catch (Exception ioe) {
                        ioe.printStackTrace();
                }
        }
       // public static void main(String[] args) {
        	 public static void change() {
                
        	File folder = new File(System.getProperty("user.dir")+"\\raw_data\\");
        	File[] listOfFiles = folder.listFiles();

        	for (int i = 0; i < listOfFiles.length; i++) {
        	  if (listOfFiles[i].isFile()) {
        	    //System.out.println("File " + listOfFiles[i].getName());
        	    if(listOfFiles[i].getName().indexOf("report1")>-1){
        	    	 File inputFile = new File(System.getProperty("user.dir")+"\\raw_data\\"+listOfFiles[i].getName());
                     File outputFile = new File(System.getProperty("user.dir")+"\\report1\\"+listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-5)+".csv");
                     xlsx(inputFile, outputFile);
        	    }else if(listOfFiles[i].getName().indexOf("report2")>-1){
        	    	 File inputFile = new File(System.getProperty("user.dir")+"\\raw_data\\"+listOfFiles[i].getName());
                     File outputFile = new File(System.getProperty("user.dir")+"\\report2\\"+listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-5)+".csv");
                     xlsx(inputFile, outputFile);      	    	     	    	
        	    }else if(listOfFiles[i].getName().indexOf("combined")>-1){
        	    	 File inputFile = new File(System.getProperty("user.dir")+"\\raw_data\\"+listOfFiles[i].getName());
                     File outputFile = new File(System.getProperty("user.dir")+"\\combined\\"+listOfFiles[i].getName().substring(0, listOfFiles[i].getName().length()-5)+".csv");
                     xlsx(inputFile, outputFile); 
        	    }
        	  }
        	}
               
        }
}
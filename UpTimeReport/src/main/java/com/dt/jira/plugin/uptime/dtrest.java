package com.dt.jira.plugin.uptime;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A resource of message.
 */
@Path("/message")
public class dtrest {

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage()
    {
    	
    	setCreateOptionValue();
       return Response.ok(new dtrestModel("Hello World")).build();
    }
    
    private void setCreateOptionValue() {
        try {                                           
            // load report location
        	 String CSV_PATH  = "//opt//app//UpTimeCalculation.xls";
            // fill report
            System.out.println("############################");
            
    FileInputStream fsIP= new FileInputStream(new File(CSV_PATH)); //Read the spreadsheet that needs to be updated
     HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
     HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
     Cell cell = null; // declare a Cell object
     cell = worksheet.getRow(2).getCell(2);   // Access the second cell in second row to update the value
     cell.setCellValue("CanadaTest");  // Get current cell value value and overwrite the value

     cell = worksheet.getRow(3).getCell(2);   // Access the second cell in second row to update the value
     cell.setCellValue("20");  // Get current cell value value and overwrite the value


     
     //INSERT THE CODE TO FILL THE TABLE   
    //loop should always start with 15
         //replace 30 with the number of jira issue count returned from the query
         for (int i = 15; i <= 30; i++) {
        	 
        	HSSFRow row = worksheet.createRow(i); // Create the row in the spreadsheet

        	//1. Create the date cell style
        	HSSFCreationHelper createHelper = wb.getCreationHelper();
        	HSSFCellStyle cellStyle         = wb.createCellStyle();
        	cellStyle.setDataFormat(
        	     createHelper.createDataFormat().getFormat("dd-MMM-yyyy")); 
        	
        	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        	String dateInString = "7-Jun-2013"; //Replace this value from the actual Jira Incident Start date using the loop

        	cell = row.createCell(1);
        	Date dateValue = new Date();
			try {
				dateValue = formatter.parse(dateInString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	cell.setCellValue(dateValue);
        	cell.setCellStyle(cellStyle);
        	
        	
        	//Incident Duration
        	cell = row.createCell(2);
        	cell.setCellValue(i);
        	 
        	//Reason nothing but summary
        	cell = row.createCell(3);
        	cell.setCellValue("Kiran test");
        	 
             
         }
	 
	 
     //Refresh the excel workbook for the formulas to generate the values after cell values are updated.
     HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);  
     
     fsIP.close(); //Close the InputStream
     FileOutputStream output_file =new FileOutputStream(new File(CSV_PATH));  //Open FileOutputStream to write updates
     wb.write(output_file); //write changes
     output_file.close();  //close the stream   

 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
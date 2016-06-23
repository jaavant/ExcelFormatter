package com.dfpray.formatter;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Main {
	@SuppressWarnings("resource")
	public static void main(String args[]){
		ArrayList<BuissnessCard> cards;
		File file;
		FileInputStream fis;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		Row row;
		Cell cell;
		String cellString;

		
		try{
			//Get file * Should be replaced by directory explorer
			file = new File("C:/Users/javant/Documents/Contacts");
			
			//Open up stream
			fis = new FileInputStream(file);
			
			//Create a Workbook instance
			workbook = new XSSFWorkbook(fis);
			
			//Get first sheet form the workbook
			sheet = workbook.getSheetAt(0);
			
			//Iterate through each rows one by one
			rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()){
				row = rowIterator.next();	
			
				//Get first and get String
				cell = row.getCell(0);
				
				throw (new IncompleteException("Must create card"));
	
				
			}
			
			
			
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		
		
		
	}
}



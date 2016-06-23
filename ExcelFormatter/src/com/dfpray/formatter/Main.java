package com.dfpray.formatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Main {
	
	public static void main(String args[]){
		
		ArrayList<BuissnessCard> cards = new ArrayList<BuissnessCard>();
	
		//Get Cards from excel sheet
		try {
			cards = getCardsXL("C:\\Users\\javant\\My Documents\\Contacts");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Load into Gui
		//allow the cards to be processed
		//check if everything is added and everything
		
		
		//Print to another file
		
	}
	
	
	private static ArrayList<BuissnessCard> getCardsRAW(String address) throws IOException{
		ArrayList<BuissnessCard> bCards = new ArrayList<BuissnessCard>();

		return bCards;
	}
	
	
	
	/** Get's address of Excel document to read, then composes an array of B.C from reading file
	 * 
	 * @param address Path to file to read
	 * @return	Array of B.C 
	 * @throws IOException 
	 */
	@Deprecated private static ArrayList<BuissnessCard> getCardsXL(String address) throws IOException{
		ArrayList<BuissnessCard> bCards = new ArrayList<BuissnessCard>();
		File file;
		FileInputStream fis;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		Row row;
		Cell cell;	

			file = new File(address);
			
			//Open up stream
			fis = new FileInputStream(file);
			
			//Create a Workbook instance
			workbook = new XSSFWorkbook(fis);
			
			//Get first sheet from the workbook
			sheet = workbook.getSheetAt(0);

			rowIterator = sheet.iterator();
			
			//Iterate through each rows one by one
			while(rowIterator.hasNext()){
				row = rowIterator.next();	
				cell = row.getCell(0);
				bCards.add(new BuissnessCard(cell.getStringCellValue()));
				
				System.out.print(cell.getStringCellValue());
			}		
			workbook.close();
		
		return bCards;	
	}
}



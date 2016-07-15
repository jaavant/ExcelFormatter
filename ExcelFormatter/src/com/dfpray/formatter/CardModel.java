package com.dfpray.formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dfpray.data.*;
import com.dfpray.exception.*;


public class CardModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<BusinessCard> cards;

	/**
	 * Create a list of BusinessCards from a path to a file
	 * @param path Path to file
	 */
	public CardModel(){
		cards = new ArrayList<BusinessCard>();
	}
	
	
	/** 
	 * Adds cards from a path from a txt file
	 * @param path Absolute Path to File
	 * @throws IncompleteException
	 * @throws IOException
	 */
	public void addCards(String path) throws IncompleteException, IOException{
		this.cards.addAll(parseRAWCards(path));
		checkCollision();
	}
	
	/**
	 * Sets the arraylist of cards to a new arraylist
	 * @param cards
	 */
	public void setCards(ArrayList<BusinessCard> cards){
		this.cards.clear();
		this.cards.addAll(cards);
	}
	
	/** Creates an ArrayList of B.C from a text file
	 * 
	 * @param address Path To File
	 * @return List of B.C 
	 * @throws IOException
	 */
	private ArrayList<BusinessCard> parseRAWCards(String address) throws IOException{	
		ArrayList<BusinessCard> bCards = new ArrayList<BusinessCard>();
		String line;
		String ext;
		boolean first = false;
		int numQuo = 0;
		
		ext = "." + getFileExtension(address);
		
		//System.out.println("Add: " + address + " Ext: " + ext);
		
		if(!(ext.equals(".txt") || ext.equals(".xlsx"))){
			throw new IOException();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(address))){
			//Check if no more lines with null
			while((line = br.readLine()) != null){
				if(line.trim().isEmpty()){
					continue;
				}
				//Skip first line
				if(!first){
					if( (numQuo = checkFormat(line)) != 110) break; //try with close workaround
					first = true;
					continue;
				}
				
				bCards.add(new BusinessCard(line));
							
			}	
		}
		
		if(numQuo != 110) throw new IOException();
		
		
		return bCards;
	}
	
	/**
	 * Returns cards
	 * @return
	 */
	public ArrayList<BusinessCard> getCards(){
		return cards;
	}
	
	/** 
	 * Adds a card to the list
	 * @param card Card to be added
	 * @throws IncompleteException 
	 */
	public void addCard(BusinessCard card) throws IncompleteException{
		cards.add(card);
		checkCollision();
	}
	
	
	/**
	 * Removes a card with the sent UUID
	 * @param id Unique Id of a card
	 * @throws EmptyListException 
	 * @throws CardNotFoundException 
	 */
	public void removeCard(UUID id) throws EmptyListException, CardNotFoundException{
		if(cards.size() == 0) throw new EmptyListException();
		
		cards.forEach(c -> {
			if(c.getUI().equals(id)){
				cards.remove(c);   
				return;
			}
		});
		throw new CardNotFoundException();
	}
	
	/**
	 * Update a card's information
	 * @param id Unique Identifier
	 * @param c Contacts
	 * @param r Representative 
	 * @param com Company
	 * @param cf CFInfo
	 * @param m Misc
	 * @param info
	 * @throws EmptyListException
	 * @throws CardNotFoundException
	 */
	public void updateCard(UUID id, Contacts c, Representative r, Company com, CFInfo cf, Misc m)throws EmptyListException, CardNotFoundException{
		if(cards.size() == 0) throw new EmptyListException();		
		
		for(BusinessCard card : cards){
			if(card.getUI().equals(id)){
				card.updateCard(c, r, com, cf, m);
				return;
			}
		}
		throw new CardNotFoundException();		
	}
	
	/**
	 *Gets size of cards list
	 * @return size of list
	 */
	public int amtCards(){
		return cards.size();
	}
	
	/**
	 * Checks if a file already exists
	 * @param path Path to file
	 * @return Only returns true if the file of that format exists, else it will return false
	 */
	public static boolean fileExists(String path){
		if(path == null){
			return false;
		}
		
		File f = new File(path);

		//Check if we are looking at a file 	
		if(!f.isFile()){
			return false;
		}	
		return f.exists();
	}
	
	/**
	 * Imports a .xlsx file and create a CardModel form it
	 * @param path Path to file
	 * @throws IOException
	 */
	public void importFromExcel(String path) throws IOException{
		  String[] cardInfo = new String[32];
		  FileInputStream file = new FileInputStream(new File(path));
          @SuppressWarnings("resource")
		  XSSFWorkbook workbook = new XSSFWorkbook(file);
          XSSFSheet sheet = workbook.getSheetAt(0);
          Row row;
          Cell cell;
          Iterator<Cell> cellIterator;
          Iterator<Row> rowIterator;
          int i;
          
          //Iterate through each rows one by one
          rowIterator = sheet.iterator();
         
          while (rowIterator.hasNext()){      	  
              row = rowIterator.next();
              cellIterator = row.cellIterator();
              
              i = 0;
              while (cellIterator.hasNext()){          	  
                  cell = cellIterator.next();
                  switch (cell.getCellType()){
                  		
                  		case Cell.CELL_TYPE_BLANK:
                  			cardInfo[i] = " ";
                  			//System.out.println("Nothing");
                  			break;
                
                  		case Cell.CELL_TYPE_NUMERIC:
                  			cardInfo[i] = (Double.toString(cell.getNumericCellValue()));
                  			//System.out.println(Double.toString(cell.getNumericCellValue()));
                  			break;
                          
                  		case Cell.CELL_TYPE_STRING:
                  			cardInfo[i] = cell.getStringCellValue();
                  			//System.out.println(cell.getStringCellValue());
                  			break;                  			
                  			
                  }
                  i++;
              }         
              //Create card and add it tho this 
              cards.add(new BusinessCard(cardInfo));
          }
          file.close();
	}
	
	
	/**
	 * Exports List of B.C to Excel file, Path should include name and format .xlsx ending
	 * @param path
	 * @throws IOException 
	 */
	public void exportToExcel(String path) throws IOException{
		System.out.println("Called ");
		
		BusinessCard card; 
		Cell cell;
		String[] info;
		Double number;
		String cardInfo;
		Row row;
			
		//Create Blank workbook/sheet
	    @SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();      
	    XSSFSheet sheet = workbook.createSheet("Business Data");
	    String[] tmpArray = {"CompanyName", "ContactFirstName",	"ContactLastName", "Title", "Street Address", "Suite/PO Box",
	    					"City",	"State", "ZipCode",	"Country",	"PhoneNumber",	"Extension",	"MobilePhone", 	"FaxNumber",
	    					"EmailAddress",	"Website",	"CsiCodes",	"CompanyFunction",	"MBEAffiliations",	"Labor",	"ServiceArea",	
	    					"CompanyNotes",	"ContactLists",	"CF_Alternate Email", "CF_Do Not Use", "CF_Supplier/Manuf", "CF_Trade",	"CF_Union Value", 
	    					"CF_Unlicensed States", "CF_Will Not Bid"};    
	    
	    Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    
	    XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(headerFont);
       
        
        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFont(headerFont);
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        
	    //Write Template
	    
	    row = sheet.createRow(0);
	    for(int k = 0; k < 30; k++){
	    	cell = row.createCell(k);
	    	cell.setCellStyle(cellStyle);
	    	
	    	if(k == 0 || k == 13 || k == 14 || k ==16 || k == 17){
	    		cell.setCellStyle(cellStyle2);
	    	}
	    	
	    	cell.setCellValue(tmpArray[k]);
	    }
	    
	    
	    //Row = Business
	    for(int i = 1; i <= amtCards(); i++){
	    	row = sheet.createRow(i);
	    	card = cards.get(i-1);
	    	info = card.infoToArray();
	    	  
	        //Create Column = Data for each Business
	    	for(int k = 0; k < 30; k++){
	    		cardInfo = info[k];
	    		cell = row.createCell(k);
	    		
	    		if(k == 24) continue;
	    		
	    		try{
	    			number = Double.parseDouble(cardInfo);
	    			cell.setCellValue(number);
	    		}catch (NumberFormatException e){
	    			cell.setCellValue(cardInfo);
	    		}
	    		
	    	}
	    	card.setExported(true);
	    }
	      	      
	    //Create file system using specific name
	    FileOutputStream out;
		try {
			out = new FileOutputStream(new File(path));
		} catch (FileNotFoundException e) {
			
			//Reset cards to not exported
			for(BusinessCard cardR : cards){
				cardR.setExported(false);
			}
			throw new IOException();
		}      
	    workbook.write(out);
	    out.close();	

	}

	/**
	 * Serializes and write this to a path
	 * @param path absolute path where this object will be written
	 */
	public static void saveModel(CardModel cm, String path) throws IOException{
		 FileOutputStream fileOut = new FileOutputStream(path);
	     ObjectOutputStream out = new ObjectOutputStream(fileOut);
	     out.writeObject(cm);
	     out.close();
	     fileOut.close();
	}
	
	/**
	 * Reads a serialized file of this from a path
	 * @param path Absolute path of file
	 */
	public static CardModel loadModel(String path) throws IOException, ClassNotFoundException{
		CardModel cm = null;
	
		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		cm = (CardModel) in.readObject();
		in.close();
		fileIn.close();
		return cm;
	}
	
	/** 
	 * Get file extension
	 * @param ext Address to File
	 * @return extension of file (.) included
	 */
	private String getFileExtension(String name) {
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	
	
	/**
	 * Checks for a UUID collision
	 * @throws EmptyListException 
	 */
	private void checkCollision() throws IncompleteException{
		if(cards.size() <= 1) return;
						
		for(int i = 0; i < cards.size()-1; i++){
			for(int k = i+1; k < cards.size(); k++){
				//Check if card UUID are the same
				if(cards.get(i).getUI().equals(cards.get(k).getUI())){
					throw new IncompleteException("UUID collison");
				}
			}
		}
	}
	
	/**
	 * Small format checker
	 * @param line a line of the text file
	 * @return number of quotation marks in a line
	 */
	private int checkFormat(String line){
		int counter = 0;
		
		for( int i=0; i<line.length(); i++ ) {
		    if( line.charAt(i) == '"' ) {
		        counter++;
		    } 
		}
		return counter;
	}
	
	/**
	 * Gets card 
	 * @param id Id of card
	 * @return Card that was searched for
	 * @throws IncompleteException
	 */
	public BusinessCard getCard(UUID id) throws IncompleteException{
		for(BusinessCard card : cards){
			if(card.getUI().equals(id)){
				return card;
			}
		}
		throw new IncompleteException("Card does not exist");
	}
	
}

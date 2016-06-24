package com.dfpray.formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dfpray.data.*;
import com.dfpray.exception.*;


public class CardModel {
	
	private ArrayList<BusinessCard> cards;
		
		
	
	/**
	 * Create a list of BusinessCards from a path to a file
	 * @param path Path to file
	 */
	public CardModel(String path){
		cards = new ArrayList<BusinessCard>();
	}
	
	public void addCards(String path) throws IncompleteException, IOException{
		this.cards = parseRAWCards(path);
		checkCollision();
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
		boolean first = false;
		
		try(BufferedReader br = new BufferedReader(new FileReader(address))){
			//Check if no more lines with null
			while((line = br.readLine()) != null){
				if(line.trim().isEmpty()){
					continue;
				}
				//Skip first line
				if(!first){
					first = true;
					continue;
				}
				
				bCards.add(new BusinessCard(line));
				
				
			}	
		}
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
		
		for(BusinessCard card: cards){
			if(card.getUI().equals(id)){
				cards.remove(card);
				return;
			}
		}
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
	public void updateCard(UUID id, Contacts c, Representative r, Company com, CFInfo cf, Misc m, String info )throws EmptyListException, CardNotFoundException{
		if(cards.size() == 0) throw new EmptyListException();		
				
		for(BusinessCard card : cards){
			if(card.getUI().equals(id)){
				card.updateCard(c, r, com, cf, m, info);
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
	 * @return 
	 */
	public boolean fileExists(String path) throws IncompleteException{
		File f = new File(path);
		
		//Check if we are looking at a file 
		if(!f.isFile()) throw new IncompleteException("This is not a file");
		
		return f.exists();
	}
	
	
	/**
	 * Exports List of B.C to Excel file, Path should include name and format .xlsx ending
	 * @param path
	 * @throws IOException 
	 */
	public void exportToExcel(String path) throws IOException{
		//TODO finish method 
		BusinessCard card; 
		Row row;
		Cell cell;
		String[] info;
		
		//Create Blank workbook/sheet
	      XSSFWorkbook workbook = new XSSFWorkbook();      
	      XSSFSheet sheet = workbook.createSheet("Business Data");
	      
	      //Row = Business
	      for(int i = 0; i < amtCards(); i++){
	    	  row = sheet.createRow(i);
	    	  card = cards.get(i);
	    	  info = card.infoToArray();
	    	  
	    	  //Create Column = Data for each Business
	    	  for(int k = 0; k < 30; k++){
	    		  cell = row.createCell(k);
	    	  }	    	  
	    	  
	      }
	      
	      
	      //Create file system using specific name
	      FileOutputStream out = new FileOutputStream(new File(path));     
	      //write operation workbook using file out object 
	      workbook.write(out);
	      out.close();	

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
	
}

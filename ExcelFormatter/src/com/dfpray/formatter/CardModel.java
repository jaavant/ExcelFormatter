package com.dfpray.formatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.dfpray.data.BuissnessCard;
import com.dfpray.data.CFInfo;
import com.dfpray.data.Company;
import com.dfpray.data.Contacts;
import com.dfpray.data.Misc;
import com.dfpray.data.Representative;

public class CardModel {
	// TODO Implement a way when finalizing cards that all cards have mandatory info
	
	private ArrayList<BuissnessCard> cards = new ArrayList<BuissnessCard>();
	
	
	/**
	 * Create a list of BuissnessCards from a path to a file
	 * @param path Path to file
	 * @throws IncompleteException 
	 * @throws IOException If  file could not be found
	 */
	public CardModel(String path) throws IncompleteException, IOException{
		this.cards = getCardsRAW(path);
		checkCollision();
	}
	
	
	/** Creates an ArrayList of B.C from a text file
	 * 
	 * @param address Path To File
	 * @return List of B.C 
	 * @throws IOException
	 */
	private ArrayList<BuissnessCard> getCardsRAW(String address) throws IOException{	
		ArrayList<BuissnessCard> bCards = new ArrayList<BuissnessCard>();
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
				
				bCards.add(new BuissnessCard(line));
				
				
			}	
		}
		return bCards;
	}
	
	/**
	 * Returns cards
	 * @return
	 */
	public ArrayList<BuissnessCard> getCards(){
		return cards;
	}
	
	/** 
	 * Adds a card to the list
	 * @param card Card to be added
	 * @throws IncompleteException 
	 */
	public void addCard(BuissnessCard card) throws IncompleteException{
		cards.add(card);
		checkCollision();
	}
	
	/**
	 * Removes a card with the sent UUID
	 * @param id Unique Id of a card
	 * @throws IncompleteException
	 */
	public void removeCard(UUID id) throws IncompleteException{
		for(BuissnessCard card: cards){
			if(card.getUI().equals(id)){
				cards.remove(card);
				return;
			}
		}
		throw new IncompleteException("Card not found (Remove Card)");
	}
	
	public void updateCard(UUID id, Contacts c, Representative r, Company com, CFInfo cf, Misc m, String info )throws IncompleteException{
		for(BuissnessCard card : cards){
			if(card.getUI().equals(id)){
				card.updateCard(c, r, com, cf, m, info);
				return;
			}
		}
		throw new IncompleteException("Card not found (Update Card)");		
	}
	
	/**
	 * Checks for a UUID collision
	 */
	private void checkCollision() throws IncompleteException{
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

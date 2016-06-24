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
import com.dfpray.exception.CardNotFoundException;
import com.dfpray.exception.EmptyListException;

public class CardModel {
	/* TODO 
	 * Implement a way when finalizing cards that all cards have mandatory info
	 * Check if deck is empty throw exception
	*/
	
	private ArrayList<BuissnessCard> cards;
	
	
	/**
	 * Create a list of BuissnessCards from a path to a file
	 * @param path Path to file
	 */
	public CardModel(String path){
		cards = new ArrayList<BuissnessCard>();
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
	private ArrayList<BuissnessCard> parseRAWCards(String address) throws IOException{	
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
	 * @throws EmptyListException 
	 * @throws CardNotFoundException 
	 */
	public void removeCard(UUID id) throws EmptyListException, CardNotFoundException{
		if(cards.size() == 0) throw new EmptyListException();
		
		for(BuissnessCard card: cards){
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
				
		for(BuissnessCard card : cards){
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

package com.dfpray.formatter;

import java.util.ArrayList;
import java.util.UUID;

import com.dfpray.data.BuissnessCard;

public class CardModel {
	// TODO Implement a way when finalizing cards that all cards have mandatory info
	private ArrayList<BuissnessCard> cards = new ArrayList<BuissnessCard>();
	
	public CardModel(ArrayList<BuissnessCard> cards) throws IncompleteException{
		this.cards = cards;
		checkCollision();

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
	
	
	/**
	 * Removes a card with the sent UUID
	 * @param id Unique Id of a card
	 * @throws IncompleteException
	 */
	public void removeCard(UUID id) throws IncompleteException{
		for(BuissnessCard card: cards){
			if(card.getUI().equals(id)){
				cards.remove(card);
			}
			else{
				throw new IncompleteException("Card not found");
			}
		}
	}
	
	
}

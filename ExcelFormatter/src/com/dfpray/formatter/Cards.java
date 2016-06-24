package com.dfpray.formatter;

import java.util.ArrayList;

public class Cards {
	
	ArrayList<BuissnessCard> cards = new ArrayList<BuissnessCard>();
	
	public Cards(ArrayList<BuissnessCard> cards){
		this.cards = cards;
	}
	
	public ArrayList<BuissnessCard> getCards(){
		return cards;
	}
	
	public void addCard(BuissnessCard card){
		cards.add(card);
	}
	
}

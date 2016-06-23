package com.dfpray.formatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Main {
	
	public static void main(String args[]){
		
		ArrayList<BuissnessCard> cards = new ArrayList<BuissnessCard>();
	
		//Get Cards from excel sheet
		try {
			cards = getCardsRAW("C:\\Users\\javant\\My Documents\\Contacts");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Load into Gui
		//allow the cards to be processed
		//check if everything is added and everything
		
		
		//Print to another file
		
	}
	
	/** Creates an ArrayList of B.C from a text file
	 * 
	 * @param address Path To File
	 * @return List of B.C 
	 * @throws IOException
	 */
	private static ArrayList<BuissnessCard> getCardsRAW(String address) throws IOException{
		
		ArrayList<BuissnessCard> bCards = new ArrayList<BuissnessCard>();
		String line;
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(address))){
			//Check if no more lines with null
			while((line = br.readLine()) != null){
				bCards.add(new BuissnessCard(line));
			}	
		}
		return bCards;
	}

}



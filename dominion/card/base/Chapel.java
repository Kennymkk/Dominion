package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {
	public Chapel() { 
		super("Chapel", 2);	
	}

	@Override
	public void play(Player p) {
		
		int CardLeft = 0;
		CardList playerHand = p.getHand();
		CardList cardToRemove = new CardList();
		String trashedCard="Dummy";
		
		while(CardLeft < 4 && trashedCard != "") {
			trashedCard = p.chooseCard("Choisissez la carte à écarter, il reste (", playerHand, true);
			if(!trashedCard.equalsIgnoreCase("")) {	
				cardToRemove.add(playerHand.getCard(trashedCard));
				CardLeft++;
			}
		}
		if(cardToRemove.size() != 0){
			for(Card c : cardToRemove) {
				playerHand.transferTo(c, p.getGame().getTrashedCards());
			}
		}
	}
}
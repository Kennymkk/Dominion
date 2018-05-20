package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {
	public Remodel() { super("Remodel", 4);	}

	@Override
	public void play(Player p) {
		CardList HandPlayer=p.getHand();
		String cardName=p.chooseCard("Choisissez une carte de votre main à écarter", HandPlayer, false);
		Card c=HandPlayer.getCard(cardName);
		int cost=c.getCost()+2;
		p.getHand().transferTo(c, p.getGame().getTrashedCards());
		Card newCard=null;		
		while(true){		
			String newCardName=p.chooseCard("Choisissez une carte coûtant jusqu'à"+cost+"pièces d'or", p.getGame().availableSupplyCards(), false);
			newCard=p.getGame().
			if(newCard.getCost()<cost) break;
		}
	
		
	}
}
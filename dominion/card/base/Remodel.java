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

	public String toString() {
		return super.toString() + "Écartez une carte de votre main."
				+ "Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.\n";
  	}
	
	@Override
	public void play(Player p) {
		CardList HandPlayer=p.getHand();
		String cardName=p.chooseCard("Choisissez une carte de votre main à écarter", HandPlayer, false);
		Card c=HandPlayer.getCard(cardName);
		int cost=c.getCost()+2;
		p.getHand().transferTo(c, p.getGame().getTrashedCards());
		Card newCard=null;
		int lowestcost=99;
		for(Card loop_c : p.getGame().availableSupplyCards()) {
			int loop_cCost=loop_c.getCost();
			if(loop_cCost<lowestcost) {
				lowestcost=loop_cCost;
			}
		}
		if (lowestcost<=cost) {			
			while(true){		
				String newCardName=p.chooseCard("Choisissez une carte coûtant jusqu'à"+cost+"pièces d'or", p.getGame().availableSupplyCards(), false);
				int cardCost=p.getGame().getFromSupply(newCardName).getCost();
				if(cardCost<=cost) {
					p.gain(newCardName);
					break;
				}
			}
		}
	
		
	}
}
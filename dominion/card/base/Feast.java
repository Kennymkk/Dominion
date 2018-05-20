package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {
	public Feast() { super("Feast", 4);	}

	@Override
	public void play(Player p) {
		p.getHand().transferTo(this, p.getGame().getTrashedCards());
		CardList SupplyCards=p.getGame().availableSupplyCards();
		Card choosed=null;
		while (true) {
			String cardName=p.chooseCard("Choissisez une Carte Coutant jusqu'à 5 pièces",SupplyCards , false);
			choosed=SupplyCards.getCard(cardName);
			if(choosed.getCost()<=5) break;			
		}
		if(choosed!=null) {
			p.getDiscard().add(choosed);
		}
		
		
	}
}
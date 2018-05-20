package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {
	public Workshop() { super("Workshop", 3);	}

	@Override
	public void play(Player p) {
		CardList SupplyCards=p.getGame().availableSupplyCards();
		Card choosed=null;
		while (true) {
			String cardName=p.chooseCard("Choissisez une Carte Coutant jusqu'à 4 pièces",SupplyCards , false);
			choosed=SupplyCards.getCard(cardName);
			if(choosed.getCost()<=4) break;			
		}
		if(choosed!=null) {
			p.getDiscard().add(choosed);
		}
	}
}
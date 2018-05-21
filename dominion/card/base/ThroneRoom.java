package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() { super("Throne Room", 4);	}

	
	public String toString() {
		return super.toString() + "Choisissez 1 carte Action de votre main."
				+ "Jouez-la deux fois.\n";
  	}
	
	
	@Override
	public void play(Player p) {
		
		String choix;
		CardList cl = p.getActionCards();
		if (!cl.isEmpty()) {
			if(cl.size()>=2) {
				choix = p.chooseCard("Choisissez une carte action que vous souhaitez jouer 2 fois", cl, false);
			}				
			else {
				cl.remove("Throne Room");
				choix = p.chooseCard("Choisissez une carte action que vous souhaitez jouer 2 fois", cl, false);
			}
			Card c=cl.getCard(choix);
			p.playCard(c);
			p.getInPlay().remove(c);
			p.playCard(c);
		}
		

		
	}
	
	
		
}

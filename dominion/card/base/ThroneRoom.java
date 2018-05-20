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
	public ThroneRoom() { super("ThroneRoom", 4);	}
	
	
	public void play(Player p) {
		
		
		CardList cl = p.getActionCards();
		
		if(cl.size()>=2) {
		String choix = p.chooseCard("Choisissez une carte action que vous souhaitez jouer 2 fois", cl, false);
		Card chosen_card = cl.getCard(choix);
		
		p.playCard(chosen_card);
		p.returnACardInHand(choix);		
		p.playCard(chosen_card);
		
		}
		
		
	}

}
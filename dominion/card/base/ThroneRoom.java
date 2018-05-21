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
	
	public String toString() {
		return super.toString() + "Choisissez 1 carte Action de votre main."
				+ "Jouez-la deux fois.\n";
  	}
	
	public void play(Player p) {
		
		String choix;
		CardList cl = p.getActionCards();
		
		if(!cl.isEmpty()) {
		
		if(cl.size()>=2) {
			choix = p.chooseCard("Choisissez une carte action que vous souhaitez jouer 2 fois", cl, false);
		
		}
		else {
			p.getActionCards().remove("Throne Room");
			choix = p.chooseCard("Choisissez une carte action que vous souhaitez jouer 2 fois", cl, false);
			
		}
		
		Card chosen_card = cl.getCard(choix);
		
		p.playCard(chosen_card);
		p.removeCardInPlay(choix);
		p.playCard(chosen_card);
		
		
		}
		
	}

}
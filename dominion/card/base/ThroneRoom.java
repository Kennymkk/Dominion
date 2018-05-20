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
			int trou=0;
			trou++;
		}
		
		
	}
	
	public void play2(Player p) {
		// Initialisation de la carte à jouer et de la décision	
		Card cardPlayed;
		String decision;
		
		// Si le joueur a une carte action dans sa main
		if(!p.getActionCards().isEmpty()) {
			// Si il a au moins 2 cartes actions dans sa main, il aura la possibilité de rejouer la carte Throne Room
			if(p.getActionCards().size()>=2) {
				decision = p.chooseCard("Choisissez une carte Action de votre main", p.getActionCards(), false);
			}
			// Sinon on retire la carte Throne Room de sa main pour éviter qu'il puisse la rejouer 2 fois (il doit avoir au moins 2 cartes actions dans sa main pour la rejouer)
			else {
				p.getActionCards().remove("Throne Room");
				decision = p.chooseCard("Choisissez une carte Action de votre main", p.getActionCards(), false);
			}
			
			cardPlayed = p.getActionCards().getCard(decision);
			
			p.playCard(cardPlayed); // On joue la carte choisit
			p.getInPlay().remove(cardPlayed); // On la retire du jeu
			p.playCard(cardPlayed); // Et on la rejoue encore une fois
		}
		
		
}

}
package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.
 */
public class Adventurer extends ActionCard {
	
	public Adventurer() { super("Adventurer", 6);	}
	
	public String toString() {
		return super.toString() + "Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient dévoilées."
				+ " Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.\n";
	}
	
	@Override	
	public void play(Player p) {
		int nbCardRevealed=0;
		while (nbCardRevealed<2) {
			Card c=p.drawCard();
			if(c!=null) {
				c.toString();
				if(c.getTypes().contains(CardType.Treasure)) {
					p.getHand().add(c);
					nbCardRevealed++;
				}
				else {
					p.gain(c);
				}
			}
			
		}
		
	}
}
package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends VictoryCard {
	public Gardens() { super("Gardens", 4);	}

	
	
	 public String toString() {
		return super.toString() + " Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).\n";
	}
	public void play(Player p) {
				
	}
	
	@Override
	public int victoryValue(Player p){
		int nbCard=0;
		nbCard=p.getDraw().size();
		nbCard+=p.getDiscard().size();
		nbCard+=p.getHand().size();
		return nbCard/10;
		
	}
}
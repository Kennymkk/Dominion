package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {
	public Chapel() { 
		super("Chapel", 2);	
	}

	@Override
	public void play(Player p) {
		CardList playerHand=p.getHand();
		for(int i=0;i<4;i++) {
			if(!p.getHand().isEmpty()) {			
				String trashedCard=p.chooseCard("Choissisez encore"+ (4-i)+ "cartes à écarter", playerHand, false);
				
				if(trashedCard!="") {
					Card c=playerHand.getCard(trashedCard);
					if(c!=null) {
						playerHand.transferTo(c, p.getGame().getTrashedCards());
					}
				}
			}						
		}		
	}
}
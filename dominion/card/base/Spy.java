package dominion.card.base;
import java.util.*;

import javax.management.RuntimeErrorException;

import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {
	public Spy() { super("Spy", 4);	}
	
	public String toString() {
		return super.toString() + "+1 Carte."
				+ "+1 Action.\n"
				+ "Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.\n";
  	}

	@Override
	public void play(Player p) {
		p.getHand().add(p.drawCard());
		p.incrementActions(1);
		Card c=p.drawCard();
		System.out.println(c.toString());
		String choice=p.choose("Voulez vous défausser (y) ou replacer (n) sur le deck la carte:"+ c.toString()+" ?", Arrays.asList("y", "n"), false);
		if(choice=="y"){
			p.getDiscard().add(c);
		}
		else if (choice=="n"){
			p.getDraw().add(0, c);
		}
		
		for(Player loop_p : p.otherPlayers()){
			c=loop_p.drawCard();
			choice=p.choose("Voulez vous défausser(y) ou replacer(n) sur le deck la carte:"+ c.toString()+" du joueur "+loop_p.toString()+ " ?", Arrays.asList("y", "n"), false);
			if(choice=="y"){
				loop_p.getDiscard().add(c);
			}
			else if (choice=="n"){
				loop_p.getDraw().add(0, c);
			}
		}
		
	}
}
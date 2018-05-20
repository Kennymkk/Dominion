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

	@Override
	public void play(Player p) {
		p.getHand().add(p.drawCard());
		p.incrementActions(1);
		Card c=p.drawCard();
		System.out.println(c.toString());
		String choice=p.choose("Voulez vous défausser ou replacer sur le deck la carte:"+ c.toString()+"? (d/r)", Arrays.asList("d", "r"), false);
		if(choice=="d"){
			p.getDiscard().add(c);
		}
		else if (choice=="r"){
			p.getDraw().add(0, c);
		}
		
		for(Player loop_p : p.otherPlayers()){
			c=loop_p.drawCard();
			choice=p.choose("Voulez vous défausser ou replacer sur le deck la carte:"+ c.toString()+"du joueur"+loop_p.toString()+ "? (d/r)", Arrays.asList("d", "r"), false);
			if(choice=="d"){
				loop_p.getDiscard().add(c);
			}
			else if (choice=="r"){
				loop_p.getDraw().add(0, c);
			}
		}
		
	}
}
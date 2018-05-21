package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {
	public Library() { super("Library", 5);	}
	
	public String toString() {
		return super.toString() + "Piochez jusqu'à ce que vous ayez 7 cartes en main."
				+ "Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.\n";
  	}
	
	@Override
	public void play(Player p) {
		CardList apartCards=new CardList();
		while(p.getHand().size()<=7) {
			Card c=p.drawCard();
			if(c!=null) {				
				
				if (c.getTypes().contains(CardType.Action)) {				
					String choice=p.choose("Voulez vous mettre" + c.toString()+"de coté? (y/n)" , Arrays.asList("y", "n"), false);
					if (choice=="y") {
						apartCards.add(c);
					}
					else {
						p.getHand().add(c);
					}
				}
				else {
					p.getHand().add(c);
				}
			}
			else {
				break;
			}
		}
		for(Card c_loop : apartCards) {
			p.getDiscard().add(c_loop);
		}
		
	}
	
	
}
package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	public Bureaucrat() { super("Bureaucrat", 4);	}
	@Override
	public void play(Player p) {
		Card c=p.getGame().getFromSupply("Silver");
		if(c!=null){
			p.getDraw().add(0, c);
		}
		List<Player> op;
		op=p.getGame().otherPlayers(p);
		for (Player loop_p : op){
			int cardShowed=0;
			CardList hand=loop_p.getHand();
			CardList victory=new CardList();
			for(Card c_loop : hand){
				if(c_loop.getTypes().contains(CardType.Victory)){
					victory.add(c_loop);
				}
			}
			if(victory.size()>0){
				String choosedCardName=loop_p.chooseCard("Quelle carte voulez vous dévoiler et placer sur votre deck?", victory, false);
				Card choosedCard=victory.getCard(choosedCardName);
				System.out.println(choosedCard.toString());
				hand.transferTo(choosedCard, loop_p.getDraw(), 0);
			}
			else {
				for(Card c_loop : hand) {
					System.out.println(c_loop.toString());
				}
			}
			
		}
		
	}
}
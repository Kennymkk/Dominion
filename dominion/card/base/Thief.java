package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {
	public Thief() { super("Thief", 4);	}

	@Override
	public void play(Player p) {
		CardList otherCards=new CardList();
		CardList treasureCards=new CardList();
		CardList choosedCards=new CardList();
		
		for (Player loop_p : p.otherPlayers()){
			treasureCards.clear();
			otherCards.clear();
			for(int i=0;i<2;i++){
				Card c=loop_p.drawCard();
				System.out.println(c.toString());				
				if(c.getTypes().contains(CardType.Treasure)){
					treasureCards.add(c);
				}
				else{
					otherCards.add(c);
				}
			}
			String cardNameChoosed=p.chooseCard("Choisissez la carte à écarter pour le joueur"+loop_p.toString(), treasureCards, false);
			Card choosedCard=treasureCards.getCard(cardNameChoosed);
			if(choosedCard!=null){
				choosedCards.add(choosedCard);
			}			
			for(Card loop_c : otherCards){
				otherCards.transferTo(loop_c, loop_p.getDiscard());
			}
			for(Card loop_c : treasureCards){
				treasureCards.transferTo(loop_c, loop_p.getDiscard());
			}
			
		}
		while(true){
			//cardNameChoosed=
		}
		
	}
}
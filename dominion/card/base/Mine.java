package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {
    public Mine() { super("Mine", 5);    }
    
    public void play(Player p) {
        
        CardList cl = p.getTreasureCards();       
        Card choosed_card_main;
        int newcard_cost = 0;
                
        
        String choix = p.chooseCard("Choisissez la carte trésor que vous souhaitez écarter.", cl, false);
        choosed_card_main=cl.getCard(choix);      
        cl.transferTo(cl.getCard(choix), p.getGame().getTrashedCards());                    
        newcard_cost = choosed_card_main.getCost()+3;               
      
        CardList supply_cards = p.getGame().availableSupplyCards();
        CardList treasure_card_list= new CardList();
        for(int i=0; i< supply_cards.size(); i++) {
        	
        	if(supply_cards.get(i).getTypes().get(0)==CardType.Treasure && supply_cards.get(i).getCost() <= newcard_cost ) {         	
        		treasure_card_list.add(supply_cards.get(i));        		
        	}
        	
        }
             String choix_carte = p.chooseCard("Choisissez la carte trésor que vous souhaitez récupérer dans votre main.", treasure_card_list, false);
             supply_cards.transferTo(supply_cards.getCard(choix_carte), p.getHand());                
    }
}

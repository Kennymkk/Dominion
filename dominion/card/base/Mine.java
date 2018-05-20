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
        
        CardList cl = p.getHand();
        Boolean choix_valide = false;
        Card choosed_card_main;
        int newcard_cost = 0;
                
        
        if (cl.contains(CardType.Treasure)) {
            while (choix_valide != true) {
                String choix = p.chooseCard("Choisissez la carte trésor que vous souhaitez écarter.", cl, false);
                choosed_card_main=cl.getCard(choix);                
        
                if(choosed_card_main.getTypes().contains(CardType.Treasure)) {
                	cl.transferTo(cl.getCard(choix), p.getGame().getTrashedCards());                    
                    newcard_cost = choosed_card_main.getCost()+3;
                    choix_valide = true;
                }
                
            }
            
        Card choose_card_pioche;
        CardList treasure_card_list = p.getGame().availableSupplyCards();
            while (true){
                String choix_carte = p.chooseCard("Choisissez la carte trésor que vous souhaitez récupérer dans votre pioche.", treasure_card_list, false);
                choose_card_pioche=cl.getCard(choix_carte);
                
                if(choose_card_pioche.getTypes().contains(CardType.Treasure) && choose_card_pioche.getCost()<= newcard_cost){ 
                    
                	treasure_card_list.transferTo(treasure_card_list.getCard(choix_carte), p.getHand());
                    
                    break;
                }
            }
                
            
            
        }
    }
}

package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 *
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {

  public Cellar() { super("Cellar", 2);	}
  
  
  public String toString() {
		return super.toString() + "+1 Action"
				+ " Défaussez autant de cartes que vous voulez.\n"
				+ "+1 Carte par carte défaussée.";
  }
  @Override
  public void play(Player p) {
    p.incrementActions(1);
    String choix = "y";
    
    while(choix !=""){
    CardList cl = p.getHand();
    choix = p.chooseCard("Quelle carte voulez vous défausser?", cl, true);    
    if(choix!=""){    	
    	cl.transferTo(cl.getCard(choix), p.getDiscard());    	
    	cl.add(p.drawCard());   	
    }
    else{
    	break;
    }
    
    }
  }
}

package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 *
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

  public Chancellor() { super("Chancellor", 3);	}
  
  
  public String toString() {
		return super.toString() + "+2 Pièces."
				+ " Vous pouvez immédiatement défausser votre deck.\n";
  }
  @Override
  public void play(Player p) {
    p.incrementMoney(2);
    String decision = p.choose("Voulez-vous déffausser votre deck?", Arrays.asList("y","n"), false);
    if (decision.equals("y")){    	
    	p.getDraw().transferTo(p.getDiscard());    	
    } 
}
  
}

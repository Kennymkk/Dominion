package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 *
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {
  
	public CouncilRoom() { super("Council Room", 5);	}
	
	
	  public String toString() {
			return super.toString() + "+4 Cartes."
					+ "+1 Achat.\n"
					+ "Tous vos adversaires piochent 1 carte.\n";
	  }
  @Override  
  public void play(Player p) {
    for (int i=0;i<4 ;i++ ) {
      p.getHand().add(p.drawCard());
    }
    p.incrementBuys(1);
	for (Player loop_p : p.getGame().otherPlayers(p)){
      loop_p.getHand().add(loop_p.drawCard());
    }
  }
  
  
}

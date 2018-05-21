package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 *
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends ActionCard {
  
	public Market() { super("Market", 5);	}
	
	public String toString() {
		return super.toString() + "+1 Carte."
				+ "+1 Action\n"
				+ "+1 Achat\n"
				+ "+1 Pièce\n";
  	}
	
  @Override
  public void play(Player p) {
		p.getHand().add(p.drawCard());
		p.incrementActions(1);
		p.incrementBuys(1);
		p.incrementMoney(1);
}

}

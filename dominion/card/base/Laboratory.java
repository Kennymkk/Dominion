package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 *
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {
	public Laboratory() { super("Laboratory", 5);	}
	
	
	public String toString() {
		return super.toString() + "+2 Cartes."
				+ "+1 Action\n";
  	}
  @Override	
  public void play(Player p) {
    for (int i=0;i<2 ;i++ ) {
      p.getHand().add(p.drawCard());
    }
    p.incrementActions(1);
  }
}

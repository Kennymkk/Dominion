package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 *
 * +2 Actions.
 * +1 Achat.
 * +2 Pièces.
 */
public class Festival extends ActionCard {

	public Festival() { super("Festival", 5);	}
  
  
	 public String toString() {
		return super.toString() + "+2 Actions."
				+ "+1 Achat.\n"
				+"+2 Pièces.\n";
  	}

  @Override
  public void play(Player p) {
    p.incrementActions(2);
    p.incrementBuys(1);
    p.incrementMoney(2);
  }
}

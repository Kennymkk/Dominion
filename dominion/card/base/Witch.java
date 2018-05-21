package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 *
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {
	public Witch() { super("Witch", 5);	}
	
	public String toString() {
		return super.toString() + "+2 Carte."
				+ "Tous vos adversaires recoivent une carte Curse.\n";
  	}
	
  @Override
  public void play(Player p) {
    for (int i=0;i<2 ;i++ ) {
      p.getHand().add(p.drawCard());
    }
    List<Player> op;
    op=p.getGame().otherPlayers(p);   
    for (Player loop_p : op){
    	
    	if (loop_p.getHand().getCard("Moat")!= null){
    		if(!((Moat) loop_p.cardsInHand().getCard("Moat")).reaction(loop_p)){
    	
      loop_p.getDiscard().add(loop_p.getGame().getFromSupply("Curse"));
      loop_p.getGame().removeFromSupply("Curse");
    		}
    	}
    	else {
    		loop_p.getDiscard().add(loop_p.getGame().getFromSupply("Curse"));
    	     loop_p.getGame().removeFromSupply("Curse");
    	}
    }
  }
}

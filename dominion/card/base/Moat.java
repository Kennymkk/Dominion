package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {
	public Moat() { super("Moat", 2);	}

	
	public boolean reaction(Player p){
		
		String decision;
		decision = p.choose("Voulez-vous contrer cette attaque?", Arrays.asList("y", "n"), false);
		
		if(decision.equalsIgnoreCase("y")){
			play(p);
			return true;	
		}
		else {
			return false;
			
		}
	
}
	@Override
	public void play(Player p) {
		p.getHand().add(p.drawCard());
		p.getHand().add(p.drawCard());
		
	}
}
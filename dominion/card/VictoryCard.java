package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Victoire
 */
public abstract class VictoryCard extends Card {

	public VictoryCard(String name, int cost) {
		super(name, cost);

  }

  public String toString(){
      return super.toString();
  }

  public List<CardType> getTypes(){
    List<CardType> typeList = super.getTypes();
    typeList.add(CardType.Victory);
    return typeList;
  }
}

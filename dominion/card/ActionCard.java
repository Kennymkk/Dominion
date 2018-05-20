package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {

  public ActionCard(String name, int cost) {
		super(name, cost);
	}

  public String toString(){
      return super.toString();
  }

  public List<CardType> getTypes(){
    List<CardType> typeList = super.getTypes();
    typeList.add(CardType.Action);
    return typeList;
  }
}

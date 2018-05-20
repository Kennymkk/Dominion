package dominion.card;
import java.util.List;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {


  public CurseCard(String name, int cost) {
		super(name, cost);
	}

public String toString(){
      return super.toString();
  }

  public List<CardType> getTypes(){
    List<CardType> typeList = super.getTypes();
    typeList.add(CardType.Curse);
    return typeList;
  }
}

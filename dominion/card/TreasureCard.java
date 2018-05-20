package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {

	public CardType type;
	public List<CardList> Treasure_list;

	public TreasureCard(String cardName,int cost){
		super(cardName,cost);
		this.type = CardType.Treasure;

  }

  public String toString(){
      return super.toString();
  }

  public List<CardType> getTypes(){
    List<CardType> typeListe = super.getTypes();
    typeListe.add(CardType.Treasure);
    return typeListe;
  }
}

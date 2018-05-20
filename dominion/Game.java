package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Class représentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;
	
	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;
	
	/**
	 * Liste des piles dans la réserve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la même
	 * carte.
	 * Ces piles peuvent être vides en cours de partie si toutes les cartes de 
	 * la pile ont été achetées ou gagnées par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont été écartées (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entrées au clavier
	 */
	private Scanner scanner;
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent à la 
	 * partie. Le constructeur doit créer les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de réserve à utiliser correspondant 
	 * aux cartes "royaume" à utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		this.scanner=new Scanner(System.in);
		this.initStocks(playerNames, kingdomStacks);
		this.initPlayers(playerNames, kingdomStacks);
		
	}
	
	/**
	 * * @param voir Constructeur de Game
	 * Sous méthode du constructeur, initialisant les listes de cartes
	 */
	private void initStocks(String[] playerNames, List<CardList> kingdomStacks) {
		//Création des piles individuelles
		
				CardList stockCopper = new CardList();
				for(int i=0;i<60;i++) {
					stockCopper.add(new Copper());
				}
				CardList stockSilver = new CardList();
				for(int i=0;i<40;i++) {
					stockSilver.add(new Silver());
				}
				CardList stockGold = new CardList();
				for(int i=0;i<30;i++) {
					stockGold.add(new Gold());
				}
				
				CardList stockEstate = new CardList();
				CardList stockDuchy = new CardList();
				CardList stockProvince = new CardList();
				int NbOfVictCards=0;
				if (playerNames.length==2) {
					NbOfVictCards=8;
				}	
				else {
					NbOfVictCards=12;
				}
				for(int i=0;i<NbOfVictCards;i++) {
					stockEstate.add(new Estate());
					stockDuchy.add(new Duchy());
					stockProvince.add(new Province());
				}
				
				CardList stockCurse=new CardList();
				for(int i=0;i<(playerNames.length-1)*10;i++) {
					stockCurse.add(new Curse());
				}
				
				//Création de la liste principale
				
				this.supplyStacks=new ArrayList<CardList>();
				this.supplyStacks.add(stockCopper);
				this.supplyStacks.add(stockSilver);
				this.supplyStacks.add(stockGold);
				this.supplyStacks.add(stockEstate);
				this.supplyStacks.add(stockDuchy);
				this.supplyStacks.add(stockProvince);
				this.supplyStacks.add(stockCurse);
				for(CardList cl : kingdomStacks) {
					this.supplyStacks.add(cl);
				}
				
				this.trashedCards=new CardList();
		
	}

	/**
	 * Sous méthode du constructeur, initialisant les instances Player
	 * @param voir Constructeur de Game
	 */
	private void initPlayers(String[] playerNames, List<CardList> kingdomStacks) {
		this.players=new Player[playerNames.length];
		this.currentPlayerIndex=0;
		for (int i=0;i<playerNames.length;i++) {
			Player p = new Player(playerNames[i],this);
			this.players[i]=p;
			
		}
	}
	
	/**
	 * Renvoie le joueur correspondant à l'indice passé en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur à renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant à la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur passé en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player p) {
		for(int i=0;i<players.length;i++) {
			if(this.players[i]==p) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Renvoie la liste des adversaires du joueur passé en argument, dans 
	 * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commençant par celui qui se trouve juste après {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considéré 
	 * comme cyclique c'est-à-dire qu'après le premier élément on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		List players =new ArrayList<Player>();
		int nbplayer=this.players.length,i=0,idPlayer_p=0;
		while(i<nbplayer&&this.players[i]!=p) {
			i++;
		}
		idPlayer_p=i;
		while(i<(nbplayer+idPlayer_p)) {
			i++;
			if(this.players[i%nbplayer]!=p) {
				players.add(this.players[i%nbplayer]);
			}
						
		}
		
		return players;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles à l'achat dans la 
	 * réserve.
	 * 
	 * @return une liste de cartes contenant la première carte de chaque pile 
	 * non-vide de la réserve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList availableCards=new CardList();
		for(CardList cl : this.supplyStacks) {
			if(!cl.isEmpty()) {	
				Card c=cl.get(0);
				if ((c instanceof TreasureCard) ||(c instanceof VictoryCard) || (c instanceof CurseCard )) {
					availableCards.add(cl.get(0));
				}
			}
		}
		return availableCards;
	
	}
	
	/**
	 * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
	 * de caractères.
	 * 
	 * Cette représentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la réserve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player currentPlayer = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}
	
	/**
	 * Renvoie une carte de la réserve dont le nom est passé en argument.
	 * 
	 * @param cardName nom de la carte à trouver dans la réserve
	 * @return la carte trouvée dans la réserve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		for (CardList cl : this.supplyStacks) {
			if(cl.get(0).getName()==cardName) {
				return cl.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Retire et renvoie une carte de la réserve
	 * 
	 * @param cardName nom de la carte à retirer de la réserve
	 * @return la carte retirée de la réserve ou {@code null} si aucune carte
	 * ne correspond au nom passé en argument
	 */
	public Card removeFromSupply(String cardName) {
		for (CardList cl : this.supplyStacks) {
			if(!cl.isEmpty()&&cl.get(0).getName()==cardName) {
				Card c=cl.get(0);
				cl.remove(0);
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Teste si la partie est terminée
	 * 
	 * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la réserve sont vides
	 *  - la pile de Provinces de la réserve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la réserve n'est une pile de Provinces, 
	 * c'est que la partie est terminée)
	 */
	public boolean isFinished() {
		int supplyEmpty=0;
		int provinceEmpty=1;
		for(CardList cl : this.supplyStacks) {
			if(cl.isEmpty()) {
				supplyEmpty++;
			}
			else if((cl.get(0).getName()=="Province")) {
				provinceEmpty=0;
			}
		}
		if (supplyEmpty>=3 || provinceEmpty==1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Boucle d'exécution d'une partie.
	 * 
	 * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
	 * terminée. Lorsque la partie se termine, la méthode affiche le score 
	 * final et les cartes possédées par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}

	/**
	 * @return the trashedCards
	 */
	public CardList getTrashedCards() {
		return trashedCards;
	}

	/**
	 * Lit une ligne de l'entrée standard
	 * 
	 * C'est cette méthode qui doit être appelée à chaque fois qu'on veut lire
	 * l'entrée clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une chaîne de caractères correspondant à la ligne suivante de
	 * l'entrée standard (sans le retour à la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
	
}
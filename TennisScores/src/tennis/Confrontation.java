package tennis;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Confrontation {

	public Player winner, loser;
	public Player first, second;

	public Confrontation(Person first, Person second) {
		this.first = new Player(first);
		this.second = new Player(second);
	}

	/**
	 * Oppose deux joueurs
	 * 
	 * @param player
	 *            Un joueur de "référence"
	 * @return true si le joueur de référence a gagné la confrontation
	 */
	public void confront() {
		while (winner == null) {
			Player subWinner = getPlayer(face());
			Player subLoser = (subWinner == first) ? second : first;
			computeSubWin(subWinner, subLoser);
		}
	}

	/**
	 * Affrontement de hiérarchie "inférieure" entre deux joueurs
	 * 
	 * @return Identité du joueur gagnant
	 */
	Person face() {
		return (first.faces(second)) ? first.person : second.person;
	}

	void computeSubWin(Player subWinner, Player subLoser) {
		increaseScore(subWinner, subLoser);
		if (isVictorious(subWinner, subLoser)) {
			this.winner = subWinner;
			this.loser = subLoser;
		}
	}

	/**
	 * Ajuste les scores suite à une victoire
	 * 
	 * @param winner
	 * @param loser
	 */
	abstract public void increaseScore(Player winner, Player loser);

	/**
	 * @return true si le premier joueur en paramètre est sur le point de gagner
	 *         la confrontation
	 */
	abstract boolean isVictorious(Player player, Player other);

	Player getPlayer(Person person) {
		if (first.person.name.equals(person.name)) {
			return first;
		} else if (second.person.name.equals(person.name)) {
			return second;
		} else {
			System.err.println("Correspondance entre joueur et personne non trouvée.");
			return null;
		}
	}

	public class Player {

		Person person;
		public int score = 0;

		public Player(Person person) {
			this.person = person;
		}

		/**
		 * Détermine le joueur gagnant d'un échange. Sur une version évoluée, on
		 * pourrait aussi considérer que l'instance lançant cette méthode est le
		 * joueur qui sert.
		 * 
		 * @param other
		 *            Autre joueur qui est affronté
		 * @return True si le joueur qui lance cette méthode gagne la manche,
		 *         indépendemmment des points de match qui en résultent.
		 */
		boolean faces(Player other) {
			int advantage = 0; // Avantage éventuel du joueur qui fait le
								// service
			int total = this.person.level + other.person.level;
			if (total == 0) {
				return true;
			}
			int winRate = this.person.level + advantage;
			int randomNumber = ThreadLocalRandom.current().nextInt(1, total + 1);
			return winRate >= randomNumber;
		}

		@Override
		public String toString() {
			return person.name + ", " + score;
		}
	}

}

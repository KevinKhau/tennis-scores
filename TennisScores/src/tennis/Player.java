package tennis;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

	String name;
	int level = 0; // Niveau du joueur sur 100.
	int gameScore = 0;
	int setScore = 0;

	/**
	 * Détermine le joueur gagnant suite à une manche. On peut aussi considérer
	 * que l'instance lançant cette méthode est le joueur qui sert.
	 * 
	 * @param other Autre joueur qui est affronté
	 * @return True si le joueur qui lance cette méthode gagne la manche,
	 *         indépendemmment des points de match qui en résultent.
	 */
	boolean faces(Player other) {
		int advantage = 0; // Avantage éventuel du joueur qui fait le service
		int total = this.level + other.level;
		if (total == 0) {
			return true;
		}
		int winRate = (this.level + advantage) / total;
		int randomNumber = ThreadLocalRandom.current().nextInt(1, total + 1);
		return winRate >= randomNumber;
	}

	String displayGameScore() {
		switch (gameScore) {
		case 0:
			return "0";
		case 1:
			return "15";
		case 2:
			return "30";
		case 3:
			return "40";
		case 4:
			return "DEUCE";
		case 5:
			return "ADVANTAGE";
		}
		return null;
	}

}

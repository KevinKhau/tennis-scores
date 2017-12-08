package tennis;

import java.util.ArrayList;
import java.util.List;

public class Match {

	Player first, second;
	List<Game> sets = new ArrayList<>(6);
	

	public class Game {

		public static final boolean DEUCE_ACTIVATED = true;
		Player winner;

		void confront() {
			Player roundWinner = first.faces(second) ? first : second;
			Player roundLoser = (roundWinner == first) ? second : first;
			if (oneStepFromVictory(roundWinner, roundLoser)) {
				winner = roundWinner;
				roundWinner.setScore++;
				resetGameScores();
			} else {
				increaseGameScore(roundWinner, roundLoser);
			}
		}

		/**
		 * Ajuste les scores suite à une manche, avec les spécificités de la
		 * règle DEUCE.
		 */
		private void increaseGameScore(Player roundWinner, Player roundLoser) {
			if (DEUCE_ACTIVATED) {
				if (roundWinner.gameScore == 3 && roundLoser.gameScore == 3) {
					roundWinner.gameScore = 5;
				} else if (roundWinner.gameScore == 3 && roundLoser.gameScore == 5) {
					roundWinner.gameScore = 4;
					roundLoser.gameScore = 4;
				} else if (roundWinner.gameScore == 4 && roundLoser.gameScore == 4) {
					roundWinner.gameScore = 5;
					roundLoser.gameScore = 3;
				} else {
					roundWinner.gameScore++;
				}
			} else {
				roundWinner.gameScore++;
			}

		}

		private void resetGameScores() {
			first.gameScore = 0;
			second.gameScore = 0;
		}

		/**
		 * @return true si le premier joueur en paramètre est sur le point de
		 *         gagner
		 */
		boolean oneStepFromVictory(Player player, Player other) {
			if (!DEUCE_ACTIVATED) {
				return player.gameScore == 3;
			} else {
				return (player.gameScore == 3 && other.gameScore <= 2) || player.gameScore == 5;
			}
		}

	}

}

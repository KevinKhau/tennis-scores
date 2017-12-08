package tennis;

public class Game extends Confrontation {

	public static boolean DEUCE_RULE_ON = true;

	public Game(Person first, Person second) {
		super(first, second);
	}

	@Override
	public void increaseScore(Player roundWinner, Player roundLoser) {
		if (DEUCE_RULE_ON) {
			if (roundWinner.score == 3 && roundLoser.score == 3) {
				roundWinner.score = 5;
			} else if (roundWinner.score == 3 && roundLoser.score == 5) {
				roundWinner.score = 4;
				roundLoser.score = 4;
			} else if (roundWinner.score == 4 && roundLoser.score == 4) {
				roundWinner.score = 5;
				roundLoser.score = 3;
			} else if ((roundWinner.score == 3 && roundLoser.score <= 2) || roundWinner.score == 5) {
				roundWinner.score = 6;
			} else {
				roundWinner.score++;
			}
		} else {
			if (roundWinner.score == 3) {
				roundWinner.score = 6;
			} else {
				roundWinner.score++;
			}
		}
	}

	@Override
	boolean isVictorious(Player player, Player other) {
		return player.score == 6;
	}

	String displayGameScore(int playerGameScore) {
		switch (playerGameScore) {
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
		case 6:
			return "WIN";
		}
		return null;
	}

	@Override
	public String toString() {
		return "\tGame [winner= (" + winner.person.name + ", " + displayGameScore(winner.score) + ") VS " + "("
				+ loser.person.name + ", " + displayGameScore(loser.score) + ")]";
	}

}

package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tennis.Confrontation.Player;
import tennis.Game;
import tennis.Person;

public class GameTests {

	Person firstPerson = new Person("Charles", 20);
	Person secondPerson = new Person("Julien", 20);
	Game game = new Game(firstPerson, secondPerson);

	public void testNewScore(int firstScore, int secondScore, Player roundWinner, Player roundLoser,
			int expectedWinnerScore) {
		game.first.score = firstScore;
		game.second.score = secondScore;
		game.increaseScore(roundWinner, roundLoser);
		assertTrue(roundWinner.score == expectedWinnerScore);
	}

	@Test
	public void testNewScores() {
		Player first = game.first;
		Player second = game.second;
		testNewScore(0, 0, first, second, 1);
		testNewScore(0, 0, second, first, 1);

		Game.DEUCE_RULE_ON = true;
		testNewScore(3, 3, first, second, 5);
		testNewScore(3, 5, first, second, 4);
		testNewScore(5, 3, first, second, 6);
		testNewScore(3, 2, first, second, 6);

		Game.DEUCE_RULE_ON = false;
		testNewScore(3, 3, first, second, 6);
		testNewScore(3, 1, first, second, 6);
	}

}

package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tennis.Confrontation.Player;
import tennis.Person;
import tennis.TieBreak;

public class TennisSetTests {

	Person firstPerson = new Person("Charles", 20);
	Person secondPerson = new Person("Julien", 20);
	TieBreak tieBreak = new TieBreak(firstPerson, secondPerson);

	public void testNewScore(int firstScore, int secondScore, Player roundWinner, Player roundLoser,
			int expectedWinnerScore) {
		tieBreak.first.score = firstScore;
		tieBreak.second.score = secondScore;
		tieBreak.increaseScore(roundWinner, roundLoser);
		assertTrue(roundWinner.score == expectedWinnerScore);
	}
	
	@Test
	public void testNewScores() {
		Player first = tieBreak.first;
		Player second = tieBreak.second;
		testNewScore(0, 0, first, second, 1);
		testNewScore(0, 0, second, first, 1);
	}

	public void testVictory(int firstScore, int secondScore, Player roundWinner, Player roundLoser, boolean wins) {
		tieBreak.first.score = firstScore;
		tieBreak.second.score = secondScore;
		assertTrue(tieBreak.isVictorious(roundWinner, roundLoser) == wins);
	}
	
	@Test
	public void testVictories() {
		Player first = tieBreak.first;
		Player second = tieBreak.second;
		testVictory(2, 0, first, second, false);
		testVictory(2, 0, second, first, false);
		testVictory(6, 0, first, second, false);
		testVictory(7, 0, first, second, true);
		testVictory(7, 5, first, second, true);
		testVictory(7, 6, first, second, false);
		testVictory(10, 8, first, second, true);
		testVictory(8, 6, first, second, true);
		testVictory(8, 7, first, second, false);
		testVictory(8, 8, first, second, false);
		
	}
	
}

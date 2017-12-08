package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import tennis.Confrontation.Player;
import tennis.Game;
import tennis.Person;
import tennis.TennisSet;

public class TieBreakTests {

	Person firstPerson = new Person("Charles", 20);
	Person secondPerson = new Person("Julien", 20);
	TennisSet set = new TennisSet(firstPerson, secondPerson);

	public void testNewScore(int firstScore, int secondScore, Player roundWinner, Player roundLoser,
			int expectedWinnerScore) {
		set.first.score = firstScore;
		set.second.score = secondScore;
		set.increaseScore(roundWinner, roundLoser);
		assertTrue(roundWinner.score == expectedWinnerScore);
	}
	
	@Test
	public void testTieBreakActivation() {
		set.first.score = 6;
		set.second.score = 6;
		set.confront();
		if (TennisSet.TIE_BREAK_RULE_ON) {
			assertNotNull(set.tieBreak);
		} else {
			assertNull(set.tieBreak);
		}
		
	}

	@Test
	public void testNewScores() {
		Player first = set.first;
		Player second = set.second;
		testNewScore(0, 0, first, second, 1);
		testNewScore(0, 0, second, first, 1);
	}

}

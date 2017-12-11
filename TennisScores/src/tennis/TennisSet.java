package tennis;

import java.util.ArrayList;
import java.util.List;

public class TennisSet extends Confrontation {

	public static final boolean TIE_BREAK_RULE_ON = true;

	public TennisSet(Person first, Person second) {
		super(first, second);
	}

	List<Game> games = new ArrayList<>();
	public TieBreak tieBreak;

	@Override
	public void confront() {
		while (winner == null) {
			Player subWinner;
			if (TIE_BREAK_RULE_ON && first.score == 6 && second.score == 6) {
				subWinner = getPlayer(tieBreak());
			} else {
				subWinner = getPlayer(face());
			}
			Player subLoser = (subWinner == first) ? second : first;
			computeSubWin(subWinner, subLoser);
		}
	}

	@Override
	Person face() {
		Game game = new Game(first.person, second.person);
		games.add(game);
		game.confront();
		return game.winner.person;
	}

	Person tieBreak() {
		this.tieBreak = new TieBreak(first.person, second.person);
		tieBreak.confront();
		return tieBreak.winner.person;
	}

	@Override
	public void increaseScore(Player gameWinner, Player gameLoser) {
		gameWinner.score++;
	}

	@Override
	public boolean isVictorious(Player player, Player other) {
		return (player.score == 6 && other.score <= 4) || player.score == 7;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("TennisSet [winner= ");
		sb.append('(').append(this.winner).append(") VS (").append(this.loser).append(")]");
		for (Game game : games) {
			sb.append('\n').append(game);
		}
		sb.append('\n').append("\tTie Break [").append(tieBreak).append(']');
		return sb.toString();
	}

}

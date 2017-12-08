package tennis;

public class TieBreak extends Confrontation {

	public TieBreak(Person first, Person second) {
		super(first, second);
	}

	@Override
	public void increaseScore(Player winner, Player loser) {
		winner.score++;
	}

	@Override
	public boolean isVictorious(Player player, Player other) {
		return player.score >= 7 && (player.score - other.score) >= 2;
	}

	@Override
	public String toString() {
		return "winner=" + winner + " VS " + loser;
	}

}

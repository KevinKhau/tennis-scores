package tennis;

public class Person {

	String name;
	int level = 0; // Niveau du joueur sur 100.

	public Person(String name, int level) {
		this.name = name;
		if (level < 0 || level > 100) {
			throw new IllegalArgumentException("Le niveau du joueur doit être entre 0 et 100");
		}
		this.level = level;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", level=" + level + "]";
	}

}
